package DAO;

import Entidades.Pacientes;

import java.io.*;
import java.util.ArrayList;

public class CRUDPacientes {

    public void insertarPaciente(Pacientes p){
        ArrayList<Pacientes> listaLectura = leerArchivo();

        listaLectura.add(p);
        escribirArchivo(listaLectura);
    }

    //Buscar Paciente
    public Pacientes buscarPacientePorMatricula(String matricula) {
        for (Pacientes p : leerArchivo()) {
            if (p.getMatricula().equals(matricula)) {
                return p;
            }
        }
        return null;
    }

    //leer archivo
    public static ArrayList<Pacientes> leerArchivo() throws RuntimeException {
        ArrayList<Pacientes> listaLectura = new ArrayList<>();
        try {
            FileInputStream leer =
                    new FileInputStream("D:\\temp\\listaPacientes.txt");
            ObjectInputStream streamLectura =
                    new ObjectInputStream(leer);
            if (leer.available() > 0) {
                Object o = streamLectura.readObject();
                listaLectura = (ArrayList<Pacientes>)o;
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
    public static void escribirArchivo(ArrayList<Pacientes> p) {
        try {
            FileOutputStream escribir =
                    new FileOutputStream("D:\\temp\\listaPacientes.txt");
            ObjectOutputStream streamEscritura =
                    new ObjectOutputStream(escribir);
            streamEscritura.writeObject(p);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
