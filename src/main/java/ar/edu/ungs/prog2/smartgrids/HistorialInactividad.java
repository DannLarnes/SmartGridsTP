package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class HistorialInactividad {
    LocalDateTime inicioInactividad;
    LocalDateTime finInactividad;

    public HistorialInactividad(LocalDateTime inicioInactividad) {
        this.inicioInactividad = inicioInactividad;
    }

    public LocalDateTime getInicioInactividad() {
        return inicioInactividad;
    }

    public LocalDateTime getFinInactividad() {
        return finInactividad;
    }

    public void setFinInactividad(LocalDateTime finInactividad) {
        this.finInactividad = finInactividad;
    }
}
