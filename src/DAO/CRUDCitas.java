package DAO;

import Entidades.Citas;

import java.io.*;
import java.util.ArrayList;

public class CRUDCitas {
    //crear Cita
    public void agendarCita(Citas c){
        ArrayList<Citas> listaLectura = leerArchivo();

        if (existeCitaConId(c.getIdCita())) {
            System.out.println("ya existe una Cita con ese id");
            return;
        }
        listaLectura.add(c);
        escribirArchivo(listaLectura);
    }
    //buscar Cita
    public Citas buscarCitaPorId(String id) {
        for (Citas c : leerArchivo()) {
            if (c.getIdCita().equals(id)) {
                return c;
            }
        }
        return null;
    }


    //leer archivo
    public static ArrayList<Citas> leerArchivo() throws RuntimeException {
        ArrayList<Citas> listaLectura = new ArrayList<>();
        try {
            FileInputStream leer =
                    new FileInputStream("D:\\temp\\listaCitas.txt");
            ObjectInputStream streamLectura =
                    new ObjectInputStream(leer);
            if (leer.available() > 0) {
                Object o = streamLectura.readObject();
                listaLectura = (ArrayList<Citas>)o;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e) {
            System.out.println("archivo vacio");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listaLectura;
    }
    //escribir en archivo
    public static void escribirArchivo(ArrayList<Citas> d) {
        try {
            FileOutputStream escribir =
                    new FileOutputStream("D:\\temp\\listaCitas.txt");
            ObjectOutputStream streamEscritura =
                    new ObjectOutputStream(escribir);
            streamEscritura.writeObject(d);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //verificar si existen Citaes
    public boolean existeCitaConId(String id) {
        ArrayList<Citas> listaCita = leerArchivo();

        for (Citas c : listaCita) {
            if (c.getIdCita().equals(id)) {
                return true; // La Cita con el ID dado ya existe
            }
        }
        return false;
    }
}
