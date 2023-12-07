package Entidades;

import java.io.Serializable;
import java.util.Date;

public class Pacientes implements Serializable {
    private String matricula;
    private String nombre;
    private String apellido;
    private Date fechaNac;

    public Pacientes(String matricula, String nombre, String apellido, Date fechaNac) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }
}
