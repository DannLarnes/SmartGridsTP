package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class PlantaTermica extends Planta{
    private String tipoCombustible;
    private double objetivoEmisionCO2;
    private double capacidadAlmacenamientoCombustible;

    public PlantaTermica(String nombre, String direccion, double capacidadMaxGeneracion, LocalDateTime fechaPuestaEnFuncionamiento, String tipoCombustible, double objetivoEmisionCO2, double capacidadAlmacenamientoCombustible) {
        super(nombre, direccion, capacidadMaxGeneracion, fechaPuestaEnFuncionamiento);
        this.validarDatosPlantaTermica(tipoCombustible,objetivoEmisionCO2,capacidadAlmacenamientoCombustible);
        this.tipoCombustible = tipoCombustible;
        this.objetivoEmisionCO2 = objetivoEmisionCO2;
        this.capacidadAlmacenamientoCombustible = capacidadAlmacenamientoCombustible;
    }

    private void validarDatosPlantaTermica(String tipoCombustible, double objetivoEmisionCO2, double capacidadAlmacenamientoCombustible) {
        if(tipoCombustible== null || tipoCombustible.isEmpty()){
            throw new RuntimeException("El tipo de combustible es obligatorio en plantas de energia termica");
        }
        if(objetivoEmisionCO2<0){
            throw new RuntimeException("El objetivo de emisionCO2 no debe ser menor a 0");
        }
        if(capacidadAlmacenamientoCombustible<=0){
            throw new RuntimeException("La capacidad de almacenamiento de combustible debe ser mayor a 0");
        }
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
    protected boolean registroCumpleCuota(Registro registro){
        boolean cumplioMinEnergia = super.registroCumpleCuota(registro);
        RegistroPlantaTermica r=(RegistroPlantaTermica)registro;
        return cumplioMinEnergia && (r.getCantCO2Generado() < this.objetivoEmisionCO2);

    }

    @Override
    public double capacidadGeneracionParaCumplirCuota() {
        return this.capacidadMaxGeneracion/2;
    }

    public void guardarRegistro(LocalDateTime fechaYHora, double totalEnergiaGenerado, int cantIncidenciasDetectadas, double cantCO2Generado, double cantCombustibleUtilizado){
        this.validarGuardadoRegistro(fechaYHora);
        this.fechasRegistradas.add(fechaYHora);
        RegistroPlantaTermica nuevoRegistro = new RegistroPlantaTermica(this.nombre,fechaYHora, totalEnergiaGenerado, cantIncidenciasDetectadas,cantCO2Generado,cantCombustibleUtilizado);
        if(!this.cumplioAlgunaVezConCuota){
            this.cumplioAlgunaVezConCuota = this.registroCumpleCuota(nuevoRegistro);
        }
        this.registros.add(nuevoRegistro);
    }

    @Override
    public String toString() {
        StringBuilder s=new StringBuilder();
        s.append(", tipoCombustible=").append(tipoCombustible);
        s.append(", objetivoEmisionCO2=").append(objetivoEmisionCO2);
        s.append(", capacidadAlmacenamientoCombustible=").append(capacidadAlmacenamientoCombustible);
        s.append("}");
        return s.toString();
    }
}
