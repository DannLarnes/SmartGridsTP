package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDate;
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
        Planta planta = this.validarYObtenerPlanta(nombre);
        return planta.capacidadGeneracionParaCumplirCuota();
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

    //retorna un arreglo de 24 pos con las incidencias por hora
    @Override
    public int[] incidenciasPorHora(String nombre) {
        Planta planta = this.validarYObtenerPlanta(nombre);
        return planta.calcularIncidenciasPorHora();

    }


    @Override
    public int[] histogramaCumplimiento(String nombre, String fechaDesde, String fechaHasta) {
        Planta planta = this.validarYObtenerPlanta(nombre);
        LocalDateTime fechaD= LocalDate.parse(fechaDesde).atStartOfDay();
        LocalDateTime fechaH= LocalDate.parse(fechaHasta).atTime(23, 59, 59);
        if(!fechaD.isBefore(fechaH)){
            throw new RuntimeException("Fechas invalidas. fechaDesde debe ser anterior fechaHasta");
        }
        return planta.histogramaCumplimiento(fechaD,fechaH);
    }

    @Override
    public Map<String, Integer> plantasRenovablesPorFuente() {
        Map<String, Integer> plantasRenovablesPorFuente = new HashMap<>();
        for(Planta planta : plantas.values()){
            //Veo solo las plantas que Hereden de PlantaEnergiaRenovable
            if(planta instanceof PlantaEnergiaRenovable){
                //casteo a PlantaEnergiaRenovable
                PlantaEnergiaRenovable plantaEnergia = (PlantaEnergiaRenovable) planta;
                //Reviso si ya guarde alguna planta con esta fuente
                Integer cantidadActual= plantasRenovablesPorFuente.get(plantaEnergia.getFuentePrimaria());
                //Si no guarde nada con esta fuente inicializo la cantidad
                if(cantidadActual==null){
                    cantidadActual= 0;
                }
                //Guardo en el map la cantidad actualizada
                plantasRenovablesPorFuente.put(plantaEnergia.getFuentePrimaria(), cantidadActual+1);
            }
        }
        return plantasRenovablesPorFuente;
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
    	
    	// Creo una lista de Plantas con todos los valores del mapa plantas
    	List<Planta> plantas = new ArrayList<Planta>(this.plantas.values());
    	
    	/* Uso el método sort para ordenar la lista, el cual recibe por parámetro el criterio de comparación
    	 
    	   Necesita dos entradas, A y B.
    	   Devuelve un número entero, y en base al resultado, .sort ordena A y B:
    	   - NEGATIVO: A va antes que B (A<B)
    	   - CERO: Son iguales (A==B)
    	   - POSITIVO: A va después que B (B<A)
    	   
    	   Utilizo lambda (método sin nombre escrito en una línea) para enviar por parámetro en .sort el criterio de comparación
    	   
    	   Double.compare(A,B) compara dos double y devuelve un int (ya que .sort necesita un int):
    	   - NEGATIVO: A < B
    	   - CERO: A == B
    	   - POSITIVO: A > B
    	   
    	   Como necesito ordenarlos de mayor a menor, lo invierto en el Double.compare
    	   Ejemplo:plantas.sort(A,B) A = 5.0, B = 2.0
    	   Double.compare(2.0 - 5.0) = -3.0 ---> Es un número negativo
    	   Entonces .sort toma como que A (5.0) antes que B (2.0)
    	   
    	*/
    	
    	// Ordeno la lista de mayor a menor utilizando list.sort y Double.compare
    	plantas.sort((a, b) -> Double.compare(b.consultarTotalGenerado(), a.consultarTotalGenerado()));
    	
    	// Creo la lista de tipo String
    	List<String> plantasOrdenadasPorEnergiaGenerada = new ArrayList<>();
    	
    	// Recorro con un for each la lista de plantas (ya ordenada de mayor a menor)
    	// Creo un objeto de tipo  StringBuilder para hacer el String
    	// Añado a la lista plantasOrdenadasPorEnergiaGenerada el String con el formato solicitado
    	for (Planta planta : plantas) {
    		
    		// Creo un objeto de tipo StringBuilder
    		StringBuilder builder = new StringBuilder();
    		
    		builder.append(planta.getNombre());
    		builder.append(": ");
    		builder.append(planta.consultarTotalGenerado());
    		
    		plantasOrdenadasPorEnergiaGenerada.add(builder.toString());
    		
    	}
    	
        return plantasOrdenadasPorEnergiaGenerada;
        
    }

    @Override
    public List<String> plantasInactivasEn(String fechaHora) {
    	
    	// Creo una lista para almacenar las plantas inactivas
    	List<String> plantasInactivas = new ArrayList<>();
    	
    	// Creo un objeto de tipo LocalDateTime en base al String recibido con la fechaHora
    	LocalDateTime fechaHoraAComparar = LocalDateTime.parse(fechaHora);
    	
    	// Recorro cada planta
    	for(Planta planta : this.plantas.values()) {
    		
    		// Verifico si estaba inactiva en esa fechaHora
    		boolean estabaInactiva = planta.verificarEstabaInactiva(fechaHoraAComparar);
    		
    		// Si estaba inactiva, agrego el nombre de la planta a la lista de plantasInactivas
    		if(estabaInactiva) {
    			plantasInactivas.add(planta.getNombre());
    		}
    		
    	}
    	
        return plantasInactivas;
        
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
