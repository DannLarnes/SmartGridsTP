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

    public void guardarRegistro(LocalDateTime fechaYHora, double totalEnergiaGenerado, int cantIncidenciasDetectadas, double cantCO2Generado, double cantCombustibleUtilizado){
        if(!this.activa){
            throw new RuntimeException("Planta inactiva, no se pueden guardar registros");
        }
        Registro ultimo= this.registros.getLast();
        if (ultimo == null || ultimo.getFechaYHora().isBefore(fechaYHora)){
            if(this.fechaPuestaEnFuncionamiento.isAfter(fechaYHora)){
                throw new RuntimeException("FechaHora de registro anterior a puesta en funcionamiento");
            }
            if( totalEnergiaGenerado>=0 && cantIncidenciasDetectadas>=0 && cantCombustibleUtilizado>=0 && cantCO2Generado>=0){
                this.registros.add(new RegistroPlantaTermica(this.nombre,fechaYHora, totalEnergiaGenerado, cantIncidenciasDetectadas,cantCO2Generado,cantCombustibleUtilizado));
            }else{
                throw new RuntimeException("Datos en registro invalidos");
            }
        }else{
            throw new RuntimeException("Fechahora del registro es igual o anterior al ultimo");
        }
    }

}
