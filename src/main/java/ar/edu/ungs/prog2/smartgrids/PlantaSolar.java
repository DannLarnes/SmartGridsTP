package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PlantaSolar extends PlantaEnergiaRenovable {
    private double superficieCubierta;
    private int cantPanelesInstalados;
    private double horasRadiacionDiaria;

    public PlantaSolar(String nombre, String direccion, double capacidadMaxGeneracion, LocalDateTime fechaPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio, double superficieCubierta, int cantPanelesInstalados, double horasRadiacionDiaria) {
        super(nombre, direccion, capacidadMaxGeneracion, fechaPuestaEnFuncionamiento, fuentePrimaria, eficienciaPromedio);
        this.validarDatosPlantaSolar(superficieCubierta,cantPanelesInstalados,horasRadiacionDiaria);
        this.superficieCubierta = superficieCubierta;
        this.cantPanelesInstalados = cantPanelesInstalados;
        this.horasRadiacionDiaria = horasRadiacionDiaria;
    }

    private void validarDatosPlantaSolar(double superficieCubierta, int cantPanelesInstalados, double horasRadiacionDiaria) {
        if(superficieCubierta<=0){
            throw new RuntimeException("La superficie cubierta debe ser mayor a 0");
        }
        if(cantPanelesInstalados<=0){
            throw new RuntimeException("La cantidad de paneles instalados debe ser mayor a 0");
        }
        if(horasRadiacionDiaria<=0){
            throw new RuntimeException("La cantidad de horas radiacion diaria debe ser mayor a 0");
        }
    }

    public double getSuperficieCubierta() {
        return superficieCubierta;
    }

    public int getCantPanelesInstalados() {
        return cantPanelesInstalados;
    }

    public double getHorasRadiacionDiaria() {
        return horasRadiacionDiaria;
    }

    @Override
    public boolean cumplioConCuota(LocalDateTime fechaHora) {
        //TODO implementar
        return false;
    }
}
