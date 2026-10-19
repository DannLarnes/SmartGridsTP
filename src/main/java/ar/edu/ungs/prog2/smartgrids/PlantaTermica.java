package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PlantaTermica extends Planta{
    private String tipoCombustible;
    private double objetivoEmisionCO2;
    private double capacidadAlmacenamientoCombustible;

    public PlantaTermica(String tipoCombustible, double objetivoEmisionCO2, double capacidadAlmacenamientoCombustible, String nombre, String direccion, double capacidadMaxGeneracion, LocalDateTime fechaPuestaEnFuncionamiento) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidadMaxGeneracion = capacidadMaxGeneracion;
        this.activa = true;
        this.fechaPuestaEnFuncionamiento = fechaPuestaEnFuncionamiento;
        this.registros = new ArrayList<Registro>();
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

    @Override
    public boolean cumplioConCuota(LocalDateTime fechaHora) {
        //TODO implementar
        return false;
    }
}
