package ar.edu.ungs.prog2.smartgrids;

import java.util.Date;

public class RegistroPlantaEolica extends Registro {

    private double velocidadVientoPromedio;

    public RegistroPlantaEolica(String nombrePlanta, Date fechaYHora, double totalEnergiaGenerado, int cantIncidenciasDetectadas, double velocidadVientoPromedio) {
        super(nombrePlanta, fechaYHora, totalEnergiaGenerado, cantIncidenciasDetectadas);
        this.velocidadVientoPromedio = velocidadVientoPromedio;
    }

    public double getVelocidadVientoPromedio() {
        return velocidadVientoPromedio;
    }
}
