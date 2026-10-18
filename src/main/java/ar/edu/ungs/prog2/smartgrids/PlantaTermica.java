package ar.edu.ungs.prog2.smartgrids;

import java.util.ArrayList;
import java.util.Date;

public class PlantaTermica extends Planta{
    private String tipoCombustible;
    private double objetivoEmisionCO2;
    private double capacidadAlmacenamientoCombustible;

    public PlantaTermica(String tipoCombustible, double objetivoEmisionCO2, double capacidadAlmacenamientoCombustible, String nombre, String direccion, double capacidadMaxGeneracion, boolean activa, Date fechaPuestaEnFuncionamiento, boolean cumplioAlgunaVezConCuota) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidadMaxGeneracion = capacidadMaxGeneracion;
        this.activa = activa;
        this.fechaPuestaEnFuncionamiento = fechaPuestaEnFuncionamiento;
        this.registros = new ArrayList<Registro>();
        this.cumplioAlgunaVezConCuota = cumplioAlgunaVezConCuota;
        this.tipoCombustible = tipoCombustible;
        this.objetivoEmisionCO2 = objetivoEmisionCO2;
        this.capacidadAlmacenamientoCombustible = capacidadAlmacenamientoCombustible;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public double getObjetivoEmisionCO2() {
        return objetivoEmisionCO2;
    }

    public double getCapacidadAlmacenamientoCombustible() {
        return capacidadAlmacenamientoCombustible;
    }
}
