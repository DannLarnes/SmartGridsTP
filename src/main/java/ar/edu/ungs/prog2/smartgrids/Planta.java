package ar.edu.ungs.prog2.smartgrids;

import java.util.Date;
import java.util.List;

public class Planta {
    private String nombre;
    private String direccion;
    private double capacidadMaxGeneracion;
    private boolean activa;
    private Date fechaPuestaEnFuncionamiento;
    private List<Registro> registros;
    private boolean cumplioAlgunaVezConCuota;
}
