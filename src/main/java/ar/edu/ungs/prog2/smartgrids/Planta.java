package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public abstract class Planta {
    protected String nombre;
    protected String direccion;
    protected double capacidadMaxGeneracion;
    protected boolean activa;
    protected LocalDateTime fechaPuestaEnFuncionamiento;
    protected List<Registro> registros;
    protected Set<LocalDateTime> fechasRegistradas;
    protected boolean cumplioAlgunaVezConCuota;
    protected List<HistorialInactividad>  historialInactividades;

    public Planta(String nombre, String direccion, double capacidadMaxGeneracion, LocalDateTime fechaPuestaEnFuncionamiento) {
        this.validarDatosPlanta(nombre,direccion,capacidadMaxGeneracion,fechaPuestaEnFuncionamiento);
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidadMaxGeneracion = capacidadMaxGeneracion;
        this.activa = true;
        this.fechaPuestaEnFuncionamiento = fechaPuestaEnFuncionamiento;
        this.registros = new ArrayList<>();
        this.cumplioAlgunaVezConCuota = false;
        this.historialInactividades = new ArrayList<>();
        this.fechasRegistradas = new HashSet<>();
    }

    private void validarDatosPlanta(String nombre, String direccion, double capacidadMaxGeneracion, LocalDateTime fechaPuestaEnFuncionamiento){
        if(nombre == null || nombre.isEmpty()){
            throw new RuntimeException("El nombre de la planta es obligatorio");
        }
        if(direccion == null || direccion.isEmpty()){
            throw new RuntimeException("La direccion de la planta es obligatoria");
        }
        if(capacidadMaxGeneracion <= 0){
            throw new RuntimeException("La capacidad de la planta debe ser mayor a 0");
        }
        if(fechaPuestaEnFuncionamiento == null){
            throw new RuntimeException("La fecha de puesta en funcionamiento es obligatoria");
        }else if(fechaPuestaEnFuncionamiento.isAfter(LocalDateTime.now())){
            throw new RuntimeException("La fecha de puesta en funcionamiento no puede ser en el futuro");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getCapacidadMaxGeneracion() {
        return capacidadMaxGeneracion;
    }

    public boolean estaActiva() {
        return activa;
    }

    public LocalDateTime getFechaPuestaEnFuncionamiento() {
        return fechaPuestaEnFuncionamiento;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public boolean isCumplioAlgunaVezConCuota() {
        return cumplioAlgunaVezConCuota;
    }

    // Guarda un nuevo registro de monitoreo.
    void guardarDatosMonitoreo(LocalDateTime fechaHora,
                               double totalEnergiaGenerado,
                               int cantIncidenciasDetectadas){

    }


    // Devuelve el total de energía generado por la planta.
    double consultarTotalGenerado(){
        double totalGenerado = 0;
        for(Registro registro : registros){
            totalGenerado+=registro.getTotalEnergiaGenerado();
        }
        return totalGenerado;
    }


    //Devuelve el total de incidencias por hora en forma de arreglo de 24 pos
    int[] calcularIncidenciasPorHora() {

        int[] incidencias = new int[24];

        for (Registro registro : registros) {

            int hora = registro.getFechaYHora().getHour();

            incidencias[hora] += registro.getCantIncidenciasDetectadas();
        }

        return incidencias;
    }


    // Devuelve el histograma de cumplimiento de cuota dentro del período indicado.
    String obtenerHistogramaCumplimientoCuotaPorHora(
            LocalDateTime fechaInicio, LocalDateTime  fechaFin){
        //TODO implementar

        return "";
    }

    // Devuelve la capacidad de generación necesaria para cumplir con la cuota.
    public abstract double capacidadGeneracionParaCumplirCuota();

    public boolean cumplioConCuota(LocalDateTime fechaHora){
        for(Registro registro : this.registros){
            if(registro.getFechaYHora().isEqual(fechaHora)){
                return this.registroCumpleCuota(registro);
            }
        }
        return false;
    }

    protected boolean registroCumpleCuota(Registro registro){
        return registro.getTotalEnergiaGenerado()>= this.capacidadGeneracionParaCumplirCuota();
    }

    public void desactivar(LocalDateTime fechaHora) {
        if (!this.activa){
            StringBuilder s = new StringBuilder();
            s.append("No se puede desactivar ");
            s.append(this.nombre);
            s.append(", ya esta inactiva");
            throw new RuntimeException(s.toString());
        }
        this.activa = false;
        HistorialInactividad historial= new HistorialInactividad(fechaHora);
        this.historialInactividades.add(historial);
    }

    public void activar(LocalDateTime fechaHora) {
        if(this.activa){
            StringBuilder s = new StringBuilder();
            s.append("No se puede activar ");
            s.append(this.nombre);
            s.append(", ya esta activa");
            throw new RuntimeException(s.toString());
        }
        HistorialInactividad historial = this.historialInactividades.getLast();
        historial.setFinInactividad(fechaHora);
        this.activa = true;
    }

    public void guardarRegistro( LocalDateTime fechaYHora, double totalEnergiaGenerado, int cantIncidenciasDetectadas){
            validarGuardadoRegistro(fechaYHora);
            this.fechasRegistradas.add(fechaYHora);
            Registro nuevoRegistro = new Registro(this.nombre,fechaYHora, totalEnergiaGenerado, cantIncidenciasDetectadas);
            if(!this.cumplioAlgunaVezConCuota){
                this.cumplioAlgunaVezConCuota = this.registroCumpleCuota(nuevoRegistro);
            }
            this.registros.add(nuevoRegistro);

    }

    protected void validarGuardadoRegistro(LocalDateTime fechaYHora){
        if(!this.activa){
            throw new RuntimeException("Planta inactiva, no se pueden guardar registros");
        }
        if(fechasRegistradas.contains(fechaYHora)){
            throw new RuntimeException("FechaHora ya registrada");
        }
        if(this.fechaPuestaEnFuncionamiento.isAfter(fechaYHora)){
            throw new RuntimeException("FechaHora de registro anterior a puesta en funcionamiento");
        }

    }

    public int limpiarMonitoreosSinDatos(){
        Iterator<Registro> iterator = this.registros.iterator();
        int eliminados=0;
        while (iterator.hasNext()){
            Registro registro = iterator.next();
            if(registro.getTotalEnergiaGenerado()==0 && registro.getCantIncidenciasDetectadas()==0){
                this.fechasRegistradas.remove(registro.getFechaYHora());
                iterator.remove();
                eliminados++;
            }
        }
        return eliminados;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.getClass().getSimpleName());
        s.append("{");
        s.append("nombre=").append(nombre);
        s.append(", direccion=").append(direccion);
        s.append(", capacidadMaxGeneracion=").append(capacidadMaxGeneracion);
        s.append(", fechaPuestaEnFuncionamiento=").append(fechaPuestaEnFuncionamiento);
        s.append(", activa=").append(activa);
        s.append(", historialInactividades=").append(historialInactividades);
        s.append(", registros=").append(registros);
        return s.toString();
    }
    
    // Recibe la fechaHoraAComparar
    // Recorro el historial de inactividad y verifico si estuvo inactiva en la fechaHoraAComparar
    // Devuelve true si estuvo inactiva en el lapso de esa fechaHora
    public boolean verificarEstabaInactiva(LocalDateTime fechaHoraAComparar) {
    	
    	for(HistorialInactividad inactividad : this.historialInactividades) {
    		
    		// Con que una vez se cumpla, ya está, no hace falta seguir recorriendo
    		if(inactividad.inactivaEn(fechaHoraAComparar)) {
    			return true;
    		}
    		
    	}
    	
    	return false;
    	
    }

    public int[] histogramaCumplimiento(LocalDateTime fechaDesde, LocalDateTime fechaHasta) {
        int[] histogramaCumplimiento = new int[24];
        for(Registro registro : this.registros) {
            if(registro.getFechaYHora().isAfter(fechaDesde) && registro.getFechaYHora().isBefore(fechaHasta)) {
                int indexHistograma = registro.getFechaYHora().getHour();
                if(this.registroCumpleCuota(registro)){
                    histogramaCumplimiento[indexHistograma]++;
                }
            }
        }
        return histogramaCumplimiento;
    }
}
