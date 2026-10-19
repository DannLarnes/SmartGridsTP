package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Planta {
    protected String nombre;
    protected String direccion;
    protected double capacidadMaxGeneracion;
    protected boolean activa;
    protected LocalDateTime fechaPuestaEnFuncionamiento;
    protected List<Registro> registros;
    protected boolean cumplioAlgunaVezConCuota;
    protected List<HistorialInactividad>  historialInactividades;

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
        //TODO implementar
        return 0;
    }


    // Devuelve el histograma de cumplimiento de cuota dentro del período indicado.
    String obtenerHistogramaCumplimientoCuotaPorHora(
            LocalDateTime fechaInicio, LocalDateTime  fechaFin){
        //TODO implementar

        return "";
    }

    // Devuelve la capacidad de generación necesaria para cumplir con la cuota.
    double capacidadGeneracionParaCumplirCuota(){
        //TODO implementar
        return 0;
    }

    public abstract boolean cumplioConCuota(LocalDateTime fechaHora);

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
        if(!this.activa){
            throw new RuntimeException("Planta inactiva, no se pueden guardar registros");
        }
        Registro ultimo= this.registros.getLast();
        if (ultimo == null || ultimo.getFechaYHora().isBefore(fechaYHora)){
            if(this.fechaPuestaEnFuncionamiento.isAfter(fechaYHora)){
                throw new RuntimeException("FechaHora de registro anterior a puesta en funcionamiento");
            }
            if( totalEnergiaGenerado>=0 && cantIncidenciasDetectadas>=0 ){
                this.registros.add(new Registro(this.nombre,fechaYHora, totalEnergiaGenerado, cantIncidenciasDetectadas));
            }else{
                throw new RuntimeException("Datos en registro invalidos");
            }
        }else{
            throw new RuntimeException("Fechahora del registro es igual o anterior al ultimo");
        }
    }


}
