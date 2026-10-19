package ar.edu.ungs.prog2.smartgrids;

public abstract class PlantaEnergiaRenovable extends Planta {
    private String fuentePrimaria;
    private double eficienciaPromedio;

    public String getFuentePrimaria() {
        return fuentePrimaria;
    }

    public double getEficienciaPromedio() {
        return eficienciaPromedio;
    }
}
