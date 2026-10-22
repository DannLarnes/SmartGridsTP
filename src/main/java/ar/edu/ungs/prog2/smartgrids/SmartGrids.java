package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class SmartGrids implements ISmartGrids {

    private Map<String,Planta> plantas;


    public SmartGrids() {
        this.plantas = new LinkedHashMap<>();
    }

    //Utilidades

    //Valida si una planta existe y la devuelve, en caso de que no exista lanza RuntimeException
    private Planta validarYObtenerPlanta(String nombre) {
        Planta planta = plantas.get(nombre);
        if(planta == null){
            throw new RuntimeException("Planta no existe");
        }
        return planta;
    }

    //Valida si una planta no existe, en caso de que exista lanza RuntimeException
    private void validarNoExistePlanta(String nombre) {
        Planta planta = plantas.get(nombre);
        if(planta!= null){
            throw new RuntimeException("Ya existe planta con nombre: " + nombre);
        }
    }

    //Obtiene LocalDateTime de un String ignorando minutos y segundos
    private LocalDateTime ObtenerFechaHoraSinExtra(String fechaHoraStr){
        LocalDateTime fechaHora =LocalDateTime.parse(fechaHoraStr);
        return fechaHora.truncatedTo(ChronoUnit.HOURS);
    }


    @Override
    public void registrarPlantaSolar(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio, double superficieCubierta, int cantidadPaneles, double horasRadiacionDiaria) {
        this.validarNoExistePlanta(nombre);
        Planta planta = new PlantaSolar(nombre,direccion,capacidadMaxima,LocalDateTime.parse(fechaHoraPuestaEnFuncionamiento),fuentePrimaria,eficienciaPromedio,superficieCubierta,cantidadPaneles,horasRadiacionDiaria);
        this.plantas.put(nombre,planta);
    }

    @Override
    public void registrarPlantaEolica(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio, int cantidadAerogeneradores, double alturaTorres, double velocidadMinimaViento) {
        this.validarNoExistePlanta(nombre);
        Planta planta = new PlantaEolica(nombre,direccion,capacidadMaxima,LocalDateTime.parse(fechaHoraPuestaEnFuncionamiento),fuentePrimaria,eficienciaPromedio,cantidadAerogeneradores,alturaTorres,velocidadMinimaViento);
        this.plantas.put(nombre,planta);
    }

    @Override
    public void registrarPlantaHidraulica(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio, int cantidadTurbinas, double capacidadEmbalse) {
        this.validarNoExistePlanta(nombre);
        Planta planta = new PlantaHidraulica(nombre,direccion,capacidadMaxima,LocalDateTime.parse(fechaHoraPuestaEnFuncionamiento),fuentePrimaria,eficienciaPromedio,cantidadTurbinas,capacidadEmbalse);
        this.plantas.put(nombre,planta);
    }

    @Override
    public void registrarPlantaTermica(String nombre, String direccion, double capacidadMaxima, String fechaHoraPuestaEnFuncionamiento, String tipoCombustible, double objetivoEmisionesCo2PorHora, double capacidadAlmacenamientoCombustible) {
        this.validarNoExistePlanta(nombre);
        Planta planta = new PlantaTermica(nombre,direccion,capacidadMaxima,LocalDateTime.parse(fechaHoraPuestaEnFuncionamiento),tipoCombustible,objetivoEmisionesCo2PorHora,capacidadAlmacenamientoCombustible);
        this.plantas.put(nombre,planta);
    }

    @Override
    public List<String> consultarPlantas() {
        Collection<Planta> plantas = this.plantas.values();
        List<String> plantasStrList = new ArrayList<>();
        for(Planta planta: plantas){
            StringBuilder builder= new StringBuilder();
            builder.append(planta.getNombre());
            builder.append(" ");
            builder.append( planta.estaActiva()? "(ACTIVA)": "(INACTIVA)");
            builder.append(": ");
            builder.append(planta.getFechaPuestaEnFuncionamiento().format(DateTimeFormatter.ISO_DATE_TIME));
            plantasStrList.add(builder.toString());
        }
        return plantasStrList;
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
        Planta planta = this.validarYObtenerPlanta(nombre);
        return planta.consultarTotalGenerado();
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
        int eliminados=0;
        for( Planta planta :this.plantas.values()){
            eliminados+=planta.limpiarMonitoreosSinDatos();
        }
        return eliminados;
    }

    @Override
    public List<String> plantasOrdenadasPorEnergiaGenerada() {
        return List.of();
    }

    @Override
    public List<String> plantasInactivasEn(String fechaHora) {
        return List.of();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("SmartGrids{");
        s.append("plantas=").append(plantas);
        s.append("}");
        return s.toString();
    }
}
