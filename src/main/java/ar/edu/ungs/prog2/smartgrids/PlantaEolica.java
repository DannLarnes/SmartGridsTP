package ar.edu.ungs.prog2.smartgrids;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlantaEolica extends PlantaEnergiaRenovable {

    private int cantAerogeneradores;
    private double alturaTorres;
    private double velocidadMinVientro;

    public PlantaEolica(int cantAerogeneradores, double alturaTorres, double velocidadMinVientro, String nombre, String direccion, double capacidadMaxGeneracion, boolean activa, Date fechaPuestaEnFuncionamiento, boolean cumplioAlgunaVezConCuota) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidadMaxGeneracion = capacidadMaxGeneracion;
        this.activa = activa;
        this.fechaPuestaEnFuncionamiento = fechaPuestaEnFuncionamiento;
        this.registros = new ArrayList<Registro>();
        this.cumplioAlgunaVezConCuota = cumplioAlgunaVezConCuota;
        this.cantAerogeneradores = cantAerogeneradores;
        this.alturaTorres = alturaTorres;
        this.velocidadMinVientro = velocidadMinVientro;
    }

    public int getCantAerogeneradores() {
        return cantAerogeneradores;
    }

    public double getAlturaTorres() {
        return alturaTorres;
    }

    public double getVelocidadMinVientro() {
        return velocidadMinVientro;
    }
}
