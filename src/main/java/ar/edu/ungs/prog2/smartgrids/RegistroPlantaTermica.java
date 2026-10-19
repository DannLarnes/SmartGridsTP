package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class RegistroPlantaTermica extends Registro {
    private double cantCO2Generado;
    private double cantCombustibleUtilizado;

    public RegistroPlantaTermica(String nombrePlanta, LocalDateTime fechaYHora, double totalEnergiaGenerado, int cantIncidenciasDetectadas, double cantCO2Generado, double cantCombustibleUtilizado) {
        super(nombrePlanta, fechaYHora, totalEnergiaGenerado, cantIncidenciasDetectadas);
        this.validarDatos(cantCO2Generado, cantCombustibleUtilizado);
        this.cantCO2Generado = cantCO2Generado;
        this.cantCombustibleUtilizado = cantCombustibleUtilizado;
    }

    private void validarDatos(double cantCO2Generado, double cantCombustibleUtilizado) {
        if(cantCO2Generado<0){
            throw new RuntimeException("La cantidad de CO2 generada en el registro no puede ser negativa");
        }
        if(cantCombustibleUtilizado<0){
            throw new RuntimeException("La cantidad de combustible utilizada en el registro no puede ser negativa");
        }
    }

    public double getCantCO2Generado() {
        return cantCO2Generado;
    }

    public double getCantCombustibleUtilizado() {
        return cantCombustibleUtilizado;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(super.toString());
        s.deleteCharAt(s.length()-1);
        s.append(", cantCO2Generado=").append(cantCO2Generado);
        s.append(", cantCombustibleUtilizado=").append(cantCombustibleUtilizado);
        s.append("}");
        return s.toString();
    }
}
