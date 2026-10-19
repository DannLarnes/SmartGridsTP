package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PlantaEolica extends PlantaEnergiaRenovable {

    private int cantAerogeneradores;
    private double alturaTorres;
    private double velocidadMinVientro;

    public PlantaEolica(int cantAerogeneradores, double alturaTorres, double velocidadMinVientro, String nombre, String direccion, double capacidadMaxGeneracion, LocalDateTime fechaPuestaEnFuncionamiento) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidadMaxGeneracion = capacidadMaxGeneracion;
        this.activa = true;
        this.fechaPuestaEnFuncionamiento = fechaPuestaEnFuncionamiento;
        this.registros = new ArrayList<Registro>();
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

    @Override
    public boolean cumplioConCuota(LocalDateTime fechaHora) {
        //TODO implementar
        return false;
    }
}
