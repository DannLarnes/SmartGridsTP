package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class RegistroPlantaEolica extends Registro {

    private double velocidadVientoPromedio;

    public RegistroPlantaEolica(String nombrePlanta, LocalDateTime fechaYHora, double totalEnergiaGenerado, int cantIncidenciasDetectadas, double velocidadVientoPromedio) {
        super(nombrePlanta, fechaYHora, totalEnergiaGenerado, cantIncidenciasDetectadas);
        this.validarDatos(velocidadVientoPromedio);
        this.velocidadVientoPromedio = velocidadVientoPromedio;
    }

    private void validarDatos(double velocidadVientoPromedio){
        if(velocidadVientoPromedio<0){
            throw new RuntimeException("la velocidad de vientro promedio en el registro no debe ser negativa");
        }
    }

    public double getVelocidadVientoPromedio() {
        return velocidadVientoPromedio;
    }
}
