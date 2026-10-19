package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class PlantaHidraulica extends PlantaEnergiaRenovable{
    private int cantTurbinas;
    private double capacidadEmbalse;

    public PlantaHidraulica(String nombre, String direccion, double capacidadMaxGeneracion, LocalDateTime fechaPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio, int cantTurbinas, double capacidadEmbalse) {
        super(nombre, direccion, capacidadMaxGeneracion, fechaPuestaEnFuncionamiento, fuentePrimaria, eficienciaPromedio);
        this.validarDatosPlantaHidraulica(cantTurbinas, capacidadEmbalse);
        this.cantTurbinas = cantTurbinas;
        this.capacidadEmbalse = capacidadEmbalse;
    }

    private void validarDatosPlantaHidraulica(int cantTurbinas, double capacidadEmbalse){
        if(cantTurbinas<=0){
            throw new RuntimeException("La cantidad de turbinas debe ser mayor a 0");
        }
        if(capacidadEmbalse<=0){
            throw new RuntimeException("La capacidad del embalse debe ser mayor a 0");
        }
    }

    public int getCantTurbinas() {
        return cantTurbinas;
    }

    public double getCapacidadEmbalse() {
        return capacidadEmbalse;
    }

    @Override
    public boolean cumplioConCuota(LocalDateTime fechaHora) {
        //TODO implementar
        return false;
    }

    @Override
    public String toString() {
        StringBuilder s= new StringBuilder();
        s.append(super.toString());
        s.append(", cantTurbinas=").append(cantTurbinas);
        s.append(", capacidadEmbalse=").append(capacidadEmbalse);
        s.append("}");
        return s.toString();
    }
}
