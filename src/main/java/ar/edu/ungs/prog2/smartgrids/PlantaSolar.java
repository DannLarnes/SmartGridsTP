package ar.edu.ungs.prog2.smartgrids;

import java.util.ArrayList;
import java.util.Date;

public class PlantaSolar extends PlantaEnergiaRenovable {
    private double superficioCubierta;
    private int cantPanelesInstalados;
    private double horasRadiacionDiaria;

    public PlantaSolar(double superficioCubierta, int cantPanelesInstalados, double horasRadiacionDiaria, String nombre, String direccion, double capacidadMaxGeneracion, boolean activa, Date fechaPuestaEnFuncionamiento, boolean cumplioAlgunaVezConCuota) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidadMaxGeneracion = capacidadMaxGeneracion;
        this.activa = activa;
        this.fechaPuestaEnFuncionamiento = fechaPuestaEnFuncionamiento;
        this.registros = new ArrayList<Registro>();
        this.cumplioAlgunaVezConCuota = cumplioAlgunaVezConCuota;
        this.superficioCubierta = superficioCubierta;
        this.cantPanelesInstalados = cantPanelesInstalados;
        this.horasRadiacionDiaria = horasRadiacionDiaria;
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
}
