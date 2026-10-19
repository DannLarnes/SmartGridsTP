package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class Registro {
    private String nombrePlanta;
    private LocalDateTime fechaYHora;
    private double totalEnergiaGenerado;
    private int cantIncidenciasDetectadas;

    public Registro(String nombrePlanta, LocalDateTime fechaYHora, double totalEnergiaGenerado, int cantIncidenciasDetectadas) {
        this.validarDatos(totalEnergiaGenerado, cantIncidenciasDetectadas);
        this.nombrePlanta = nombrePlanta;
        this.fechaYHora = fechaYHora;
        this.totalEnergiaGenerado = totalEnergiaGenerado;
        this.cantIncidenciasDetectadas = cantIncidenciasDetectadas;
    }

    private void validarDatos(double totalEnergiaGenerado, int cantIncidenciasDetectadas) {
        if(cantIncidenciasDetectadas < 0){
            throw new RuntimeException("Cantidad de incidencias en registro no pueden ser negativas");
        }
        if(totalEnergiaGenerado < 0){
            throw new RuntimeException("Total de energia generado en registro no pueden ser negativo");
        }
    }

    public String getNombrePlanta() {
        return nombrePlanta;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public double getTotalEnergiaGenerado() {
        return totalEnergiaGenerado;
    }

    public int getCantIncidenciasDetectadas() {
        return cantIncidenciasDetectadas;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.getClass().getSimpleName());
        s.append("{");
        s.append("nombrePlanta=").append(nombrePlanta);
        s.append("fechaYHora=").append(fechaYHora);
        s.append("totalEnergiaGenerado=").append(totalEnergiaGenerado);
        s.append(", cantIncidenciasDetectadas=").append(cantIncidenciasDetectadas);
        s.append("}");
        return s.toString();
    }
}
