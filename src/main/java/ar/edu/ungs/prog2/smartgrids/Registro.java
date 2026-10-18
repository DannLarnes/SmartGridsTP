package ar.edu.ungs.prog2.smartgrids;

import java.util.Date;

public class Registro {
    private String nombrePlanta;
    private Date fechaYHora;
    private double totalEnergiaGenerado;
    private int cantIncidenciasDetectadas;

    public Registro(String nombrePlanta, Date fechaYHora, double totalEnergiaGenerado, int cantIncidenciasDetectadas) {
        this.nombrePlanta = nombrePlanta;
        this.fechaYHora = fechaYHora;
        this.totalEnergiaGenerado = totalEnergiaGenerado;
        this.cantIncidenciasDetectadas = cantIncidenciasDetectadas;
    }

    public String getNombrePlanta() {
        return nombrePlanta;
    }

    public Date getFechaYHora() {
        return fechaYHora;
    }

    public double getTotalEnergiaGenerado() {
        return totalEnergiaGenerado;
    }

    public int getCantIncidenciasDetectadas() {
        return cantIncidenciasDetectadas;
    }
}
