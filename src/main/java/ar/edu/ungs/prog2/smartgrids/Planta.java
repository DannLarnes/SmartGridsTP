package ar.edu.ungs.prog2.smartgrids;

import java.util.Date;
import java.util.List;

public class Planta {
    protected String nombre;
    protected String direccion;
    protected double capacidadMaxGeneracion;
    protected boolean activa;
    protected Date fechaPuestaEnFuncionamiento;
    protected List<Registro> registros;
    protected boolean cumplioAlgunaVezConCuota;

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getCapacidadMaxGeneracion() {
        return capacidadMaxGeneracion;
    }

    public boolean isActiva() {
        return activa;
    }

    public Date getFechaPuestaEnFuncionamiento() {
        return fechaPuestaEnFuncionamiento;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public boolean isCumplioAlgunaVezConCuota() {
        return cumplioAlgunaVezConCuota;
    }
}
