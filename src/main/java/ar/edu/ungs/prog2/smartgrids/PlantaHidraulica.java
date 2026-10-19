package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class PlantaHidraulica extends PlantaEnergiaRenovable{
    private int cantTurbinas;
    private double capacidadEmbalse;

    public PlantaHidraulica(int cantTurbinas, double capacidadEmbalse, String nombre, String direccion, double capacidadMaxGeneracion, LocalDateTime fechaPuestaEnFuncionamiento) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidadMaxGeneracion = capacidadMaxGeneracion;
        this.activa = true;
        this.fechaPuestaEnFuncionamiento = fechaPuestaEnFuncionamiento;
        this.registros = new ArrayList<Registro>();
        this.cantTurbinas = cantTurbinas;
        this.capacidadEmbalse = capacidadEmbalse;
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
}
