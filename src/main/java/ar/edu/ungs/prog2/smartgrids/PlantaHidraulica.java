package ar.edu.ungs.prog2.smartgrids;

import java.util.ArrayList;
import java.util.Date;

public class PlantaHidraulica extends PlantaEnergiaRenovable{
    private int cantTurbinas;
    private double capacidadEmbalse;

    public PlantaHidraulica(int cantTurbinas, double capacidadEmbalse, String nombre, String direccion, double capacidadMaxGeneracion, boolean activa, Date fechaPuestaEnFuncionamiento, boolean cumplioAlgunaVezConCuota) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidadMaxGeneracion = capacidadMaxGeneracion;
        this.activa = activa;
        this.fechaPuestaEnFuncionamiento = fechaPuestaEnFuncionamiento;
        this.registros = new ArrayList<Registro>();
        this.cumplioAlgunaVezConCuota = cumplioAlgunaVezConCuota;
        this.cantTurbinas = cantTurbinas;
        this.capacidadEmbalse = capacidadEmbalse;
    }

    public int getCantTurbinas() {
        return cantTurbinas;
    }

    public double getCapacidadEmbalse() {
        return capacidadEmbalse;
    }
}
