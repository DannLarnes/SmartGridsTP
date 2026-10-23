package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // Devuelve un array de double que representa las 24hs
    //Si no hay registro en la hora queda en 0
    //Si se cumplio la velocidadMinima queda en -1
    //En caso de que no se cumpla la velocidadMinima queda la velocidad a la que llego
    public Map<Integer,Double> obtenerHorasSinVelocidadMinima(){

        //Parecido al histograma creo un array de ints de 24hs
        Map<Integer,Double> horasSinVelocidadMinima = new HashMap<>();

        //Recorro los registros
        for(Registro registro : this.registros){
            //Casteo a RegistroPlantaEolica
            RegistroPlantaEolica registroPlantaEolica = (RegistroPlantaEolica) registro;

            //Obtengo un index basandome en la hora del registro
            int indexHora = registroPlantaEolica.getFechaYHora().getHour();

            double velActual= registroPlantaEolica.getVelocidadVientoPromedio();
            //Veo si el registro no llego a velocidad minima en ese momento
            if(velActual<this.velocidadMinViento){
                // Si el registro no llego a la velocidad minima veo
                //      si tengo algun registro de velocidad ya guardado mayor o igual a 0
                //      y si el registro de velocidad actual es mayor al guardado
                // con esto decido si guardar la velocidad del registro actual
                Double horaRegistrada=horasSinVelocidadMinima.get(indexHora);
                if(horaRegistrada==null || (horaRegistrada >= 0 && velActual >horaRegistrada)){
                    horasSinVelocidadMinima.put(indexHora,velActual);
                }
            }else{
                //si se llego a cumplir la velocidad minima guardo un -1
                horasSinVelocidadMinima.put(indexHora,-1.0);
            }
        }
        return horasSinVelocidadMinima;
    }

    public void guardarRegistro( LocalDateTime fechaYHora, double totalEnergiaGenerado, int cantIncidenciasDetectadas, double velocidadVientoPromedio){
        super.validarGuardadoRegistro(fechaYHora);
        this.fechasRegistradas.add(fechaYHora);
        RegistroPlantaEolica nuevoRegistro = new RegistroPlantaEolica(this.nombre,fechaYHora, totalEnergiaGenerado, cantIncidenciasDetectadas, velocidadVientoPromedio);
        if(!this.cumplioAlgunaVezConCuota){
            this.cumplioAlgunaVezConCuota = this.registroCumpleCuota(nuevoRegistro);
        }
        this.registros.add(nuevoRegistro);
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
