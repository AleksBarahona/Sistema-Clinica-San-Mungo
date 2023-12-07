package Entidades;

import java.io.Serializable;
import java.util.Date;

public class Citas implements Serializable {
    private String idCita;
    private Doctores doctor;
    private Pacientes paciente;
    private String motivo;
    private Date fecha;
    private int numCons;

    public Citas(String idCita, Doctores doctor, Pacientes paciente, String motivo, Date fecha) {
        this.idCita = idCita;
        this.doctor = doctor;
        this.paciente = paciente;
        this.motivo = motivo;
        this.fecha = fecha;
        numCons = doctor.getNumeroConsultorio();
    }

    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public Doctores getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctores doctor) {
        this.doctor = doctor;
    }

    public Pacientes getPaciente() {
        return paciente;
    }

    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumCons() {
        return numCons;
    }

    public void setNumCons(int numCons) {
        this.numCons = numCons;
    }
}
