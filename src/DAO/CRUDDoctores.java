package DAO;

import Entidades.Doctores;

import java.io.*;
import java.util.ArrayList;

public class CRUDDoctores {
    //crear doctor
    public void agregardoctor(Doctores d){
        ArrayList<Doctores> listaLectura = leerArchivo();

        if (existeDoctorConId(d.getId())) {
            System.out.println("ya existe un doctor con ese id");
            return;
        }
        listaLectura.add(d);
        escribirArchivo(listaLectura);
    }

    //buscar doctor
    public Doctores buscarDoctorPorId(String id) {
        for (Doctores d : leerArchivo()) {
            if (d.getId().equals(id)) {
                return d;
            }
        }
        return null;
    }



    //verificar si existen doctores
    public boolean existeDoctorConId(String id) {
        ArrayList<Doctores> listaDoctores = leerArchivo();

        for (Doctores doctor : listaDoctores) {
            if (doctor.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }

    //leer archivo
    public static ArrayList<Doctores> leerArchivo() throws RuntimeException {
        ArrayList<Doctores> listaLectura = new ArrayList<>();
        try {
            FileInputStream leer =
                    new FileInputStream("D:\\temp\\listaDoctores.txt");
            ObjectInputStream streamLectura =
                    new ObjectInputStream(leer);
            if (leer.available() > 0) {
                Object o = streamLectura.readObject();
                listaLectura = (ArrayList<Doctores>)o;
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
    public static void escribirArchivo(ArrayList<Doctores> d) {
        try {
            FileOutputStream escribir =
                    new FileOutputStream("D:\\temp\\listaDoctores.txt");
            ObjectOutputStream streamEscritura =
                    new ObjectOutputStream(escribir);
            streamEscritura.writeObject(d);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
