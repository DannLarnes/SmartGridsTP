package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public abstract class PlantaEnergiaRenovable extends Planta {
    private String fuentePrimaria;
    private double eficienciaPromedio;

    public PlantaEnergiaRenovable(String nombre, String direccion, double capacidadMaxGeneracion, LocalDateTime fechaPuestaEnFuncionamiento,String fuentePrimaria,double eficienciaPromedio) {
        super(nombre, direccion, capacidadMaxGeneracion, fechaPuestaEnFuncionamiento);
        this.validarDatosPlantaEnergiaRenovable(fuentePrimaria,eficienciaPromedio);
        this.fuentePrimaria = fuentePrimaria;
        this.eficienciaPromedio = eficienciaPromedio;
    }

    private void validarDatosPlantaEnergiaRenovable(String fuentePrimaria, double eficienciaPromedio){
        if(fuentePrimaria==null || fuentePrimaria.isEmpty()){
            throw new RuntimeException("La fuente primaria es obligatoria en plantas de energia renobable");
        }else if(eficienciaPromedio<0){
            throw new RuntimeException("La eficiencia promedio debe ser mayor a 0");
        }
    }

    public String getFuentePrimaria() {
        return fuentePrimaria;
    }

    public double getEficienciaPromedio() {
        return eficienciaPromedio;
    }
}
