package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartGrids implements ISmartGrids {

    private Map<String,Planta> plantas;

    public SmartGrids() {
        this.plantas = new HashMap<>();
    }

    private Planta validarYObtenerPlanta(String nombre) {
        Planta planta = plantas.get(nombre);
        if(planta == null){
            throw new RuntimeException("Planta no existe");
        }
        return planta;
    }
    private void validarNoExistePlanta(String nombre) {
        Planta planta = plantas.get(nombre);
        if(planta!= null){
            throw new RuntimeException("Ya existe planta con nombre: " + nombre);
        }
    }

    private LocalDateTime ObtenerFechaHoraSinExtra(String fechaHoraStr){
        LocalDateTime fechaHora =LocalDateTime.parse(fechaHoraStr);
        return fechaHora.truncatedTo(ChronoUnit.HOURS);
    }

    @Override
    public void registrarPlantaSolar(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio, double superficieCubierta, int cantidadPaneles, double horasRadiacionDiaria) {
        this.validarNoExistePlanta(nombre);
        Planta planta = new PlantaSolar(superficieCubierta,cantidadPaneles,horasRadiacionDiaria,nombre,direccion,capacidadMaxima,LocalDateTime.parse(fechaHoraPuestaEnFuncionamiento));
        this.plantas.put(nombre,planta);
    }

    @Override
    public void registrarPlantaEolica(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio, int cantidadAerogeneradores, double alturaTorres, double velocidadMinimaViento) {
        this.validarNoExistePlanta(nombre);
        Planta planta = new PlantaEolica(cantidadAerogeneradores,alturaTorres,velocidadMinimaViento,nombre,direccion,capacidadMaxima,LocalDateTime.parse(fechaHoraPuestaEnFuncionamiento));
        this.plantas.put(nombre,planta);
    }

    @Override
    public void registrarPlantaHidraulica(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio, int cantidadTurbinas, double capacidadEmbalse) {
        this.validarNoExistePlanta(nombre);
        Planta planta = new PlantaHidraulica(cantidadTurbinas,capacidadEmbalse,nombre,direccion,capacidadMaxima,LocalDateTime.parse(fechaHoraPuestaEnFuncionamiento));
        this.plantas.put(nombre,planta);
    }

    @Override
    public void registrarPlantaTermica(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String tipoCombustible, double objetivoEmisionesCo2PorHora, double capacidadAlmacenamientoCombustible) {
        this.validarNoExistePlanta(nombre);
        Planta planta = new PlantaTermica(tipoCombustible,objetivoEmisionesCo2PorHora,capacidadAlmacenamientoCombustible,nombre,direccion,capacidadMaxima,LocalDateTime.parse(fechaHoraPuestaEnFuncionamiento));
        this.plantas.put(nombre,planta);
    }

    @Override
    public List<String> consultarPlantas() {
        return List.of();
    }

    @Override
    public double capacidadMinimaCuota(String nombre) {
        return 0;
    }

    @Override
    public void activarPlanta(String nombre, String fechaHora) {
        Planta planta = this.validarYObtenerPlanta(nombre);
        LocalDateTime fechaActivacion = LocalDateTime.parse(fechaHora);
        planta.activar(fechaActivacion);

    }

    @Override
    public void desactivarPlanta(String nombre, String fechaHora) {
        Planta planta = this.validarYObtenerPlanta(nombre);
        LocalDateTime fechaDesactivacion = LocalDateTime.parse(fechaHora);
        planta.desactivar(fechaDesactivacion);

    }

    @Override
    public boolean estaActiva(String nombre) {
        Planta planta = this.validarYObtenerPlanta(nombre);
        return planta.estaActiva();
    }

    @Override
    public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias) {
        Planta planta = this.validarYObtenerPlanta(nombre);
        planta.guardarRegistro(this.ObtenerFechaHoraSinExtra(fechaHora),energiaGenerada,incidencias);

    }

    @Override
    public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias, double velocidadViento) {
        Planta planta = this.validarYObtenerPlanta(nombre);
        if(planta.getClass().equals(PlantaEolica.class)){
            PlantaEolica plantaEolica = (PlantaEolica) planta;
            plantaEolica.guardarRegistro(this.ObtenerFechaHoraSinExtra(fechaHora),energiaGenerada,incidencias,velocidadViento);
        }else{
            StringBuilder s = new StringBuilder();
            s.append("Planta ");
            s.append(nombre);
            s.append(" no es una planta Eolica, no admite estos registros");
            throw new RuntimeException(s.toString());
        }

    }

    @Override
    public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias, double co2Emitido, double combustibleUtilizado) {
        Planta planta = this.validarYObtenerPlanta(nombre);
        if(planta.getClass().equals(PlantaTermica.class)){
            PlantaTermica plantaTermica = (PlantaTermica) planta;
            plantaTermica.guardarRegistro(this.ObtenerFechaHoraSinExtra(fechaHora),energiaGenerada,incidencias,co2Emitido,combustibleUtilizado);
        }else{
            StringBuilder s = new StringBuilder();
            s.append("Planta ");
            s.append(nombre);
            s.append(" no es una planta Termica, no admite estos registros");
            throw new RuntimeException(s.toString());
        }

    }

    @Override
    public double totalGenerado(String nombre) {
        return 0;
    }

    @Override
    public int[] incidenciasPorHora(String nombre) {
        return new int[0];
    }

    @Override
    public int[] histogramaCumplimiento(String nombre, String fechaDesde, String fechaHasta) {
        return new int[0];
    }

    @Override
    public Map<String, Integer> plantasRenovablesPorFuente() {
        return Map.of();
    }

    @Override
    public List<String> plantasQueNuncaCumplieronCuota() {
        return List.of();
    }

    @Override
    public String horasSinVelocidadMinimaPorPlantaEolica() {
        return "";
    }

    @Override
    public int limpiarMonitoreosSinDatos() {
        return 0;
    }

    @Override
    public List<String> plantasOrdenadasPorEnergiaGenerada() {
        return List.of();
    }

    @Override
    public List<String> plantasInactivasEn(String fechaHora) {
        return List.of();
    }
}
