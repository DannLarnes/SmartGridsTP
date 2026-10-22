package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;
import java.util.List;

public class PlantaEolica extends PlantaEnergiaRenovable {

    private int cantAerogeneradores;
    private double alturaTorres;
    private double velocidadMinViento;

    public PlantaEolica(String nombre, String direccion, double capacidadMaxGeneracion, LocalDateTime fechaPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio, int cantAerogeneradores, double alturaTorres, double velocidadMinViento) {
        super(nombre, direccion, capacidadMaxGeneracion, fechaPuestaEnFuncionamiento, fuentePrimaria, eficienciaPromedio);
        this.validarDatosPlantaEolica(cantAerogeneradores,alturaTorres,velocidadMinViento);
        this.cantAerogeneradores = cantAerogeneradores;
        this.alturaTorres = alturaTorres;
        this.velocidadMinViento = velocidadMinViento;
    }

    private void validarDatosPlantaEolica(int cantAerogeneradores, double alturaTorres, double velocidadMinViento) {
        if(cantAerogeneradores <= 0){
            throw new RuntimeException("La cantidad de aerogeneradores debe ser mayor a 0");
        }
        if(alturaTorres <= 0){
            throw new RuntimeException("La altura de torres debe ser mayor a 0");
        }
        if(velocidadMinViento <= 0){
            throw new RuntimeException("La velocidad minima de viento debe ser mayor a 0");
        }

    }

    public int getCantAerogeneradores() {
        return cantAerogeneradores;
    }

    public double getAlturaTorres() {
        return alturaTorres;
    }

    public double getVelocidadMinViento() {
        return velocidadMinViento;
    }

    @Override
    public double capacidadGeneracionParaCumplirCuota() {
        return this.capacidadMaxGeneracion*this.eficienciaPromedio;
    }

    // Devuelve los horarios en los que no se alcanzó la velocidad mínima de viento requerida.
    public List<LocalDateTime> obtenerHorasSinVelocidadMinima(){

        return List.of();
    }

    public void guardarRegistro( LocalDateTime fechaYHora, double totalEnergiaGenerado, int cantIncidenciasDetectadas, double velocidadVientoPromedio){
        super.validarGuardadoRegistro(fechaYHora);
        this.fechasRegistradas.add(fechaYHora);
        this.registros.add(new RegistroPlantaEolica(this.nombre,fechaYHora, totalEnergiaGenerado, cantIncidenciasDetectadas, velocidadVientoPromedio));
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(super.toString());
        s.append(", alturaTorres=").append(alturaTorres);
        s.append(", velocidadMinViento=").append(velocidadMinViento);
        s.append(", cantAerogeneradores=").append(cantAerogeneradores);
        s.append("}");
        return s.toString();
    }
}
