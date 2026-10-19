package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PlantaSolar extends PlantaEnergiaRenovable {
    private double superficioCubierta;
    private int cantPanelesInstalados;
    private double horasRadiacionDiaria;

    public PlantaSolar(double superficioCubierta, int cantPanelesInstalados, double horasRadiacionDiaria, String nombre, String direccion, double capacidadMaxGeneracion, LocalDateTime fechaPuestaEnFuncionamiento) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidadMaxGeneracion = capacidadMaxGeneracion;
        this.activa = true;
        this.fechaPuestaEnFuncionamiento = fechaPuestaEnFuncionamiento;
        this.registros = new ArrayList<Registro>();
        this.superficioCubierta = superficioCubierta;
        this.cantPanelesInstalados = cantPanelesInstalados;
        this.horasRadiacionDiaria = horasRadiacionDiaria;
        this.historialInactividades = new ArrayList<>();
    }

    public double getSuperficioCubierta() {
        return superficioCubierta;
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
