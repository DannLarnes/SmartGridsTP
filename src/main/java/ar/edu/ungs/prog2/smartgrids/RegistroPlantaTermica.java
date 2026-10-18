package ar.edu.ungs.prog2.smartgrids;

import java.util.Date;

public class RegistroPlantaTermica extends Registro {
    private double cantCO2Generado;
    private double cantCombustibleUtilizado;

    public RegistroPlantaTermica(String nombrePlanta, Date fechaYHora, double totalEnergiaGenerado, int cantIncidenciasDetectadas, double cantCO2Generado, double cantCombustibleUtilizado) {
        super(nombrePlanta, fechaYHora, totalEnergiaGenerado, cantIncidenciasDetectadas);
        this.cantCO2Generado = cantCO2Generado;
        this.cantCombustibleUtilizado = cantCombustibleUtilizado;
    }

    public double getCantCO2Generado() {
        return cantCO2Generado;
    }

    public double getCantCombustibleUtilizado() {
        return cantCombustibleUtilizado;
    }
}
