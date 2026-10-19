package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    // Devuelve los horarios en los que no se alcanzó la velocidad mínima de viento requerida.
    public List<LocalDateTime> obtenerHorasSinVelocidadMinima(){

        return List.of();
    }

    public void guardarRegistro( LocalDateTime fechaYHora, double totalEnergiaGenerado, int cantIncidenciasDetectadas, double velocidadVientoPromedio){
        if(!this.activa){
            throw new RuntimeException("Planta inactiva, no se pueden guardar registros");
        }
        Registro ultimo= this.registros.getLast();
        if (ultimo == null || ultimo.getFechaYHora().isBefore(fechaYHora)){
            if(this.fechaPuestaEnFuncionamiento.isAfter(fechaYHora)){
                throw new RuntimeException("FechaHora de registro anterior a puesta en funcionamiento");
            }
            if( totalEnergiaGenerado>=0 && cantIncidenciasDetectadas>=0 && velocidadVientoPromedio>=0){
                this.registros.add(new RegistroPlantaEolica(this.nombre,fechaYHora, totalEnergiaGenerado, cantIncidenciasDetectadas, velocidadVientoPromedio));
            }else{
                throw new RuntimeException("Datos en registro invalidos");
            }
        }else{
            throw new RuntimeException("Fechahora del registro es igual o anterior al ultimo");
        }
    }
}
