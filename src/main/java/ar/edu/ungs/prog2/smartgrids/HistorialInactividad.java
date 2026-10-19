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

    @Override
    public String toString() {
        StringBuilder s= new StringBuilder();
        s.append("HistorialInactividad{");
        s.append("inicioInactividad=").append(inicioInactividad);
        s.append("finInactividad=").append(finInactividad);
        s.append("}");
        return s.toString();
    }
}
