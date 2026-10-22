package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class HistorialInactividad {
    LocalDateTime inicioInactividad;
    LocalDateTime finInactividad;

    public HistorialInactividad(LocalDateTime inicioInactividad) {
    	
    	if(inicioInactividad == null) {
    		throw new RuntimeException("La fecha de inicio de inactividad no puede ser null");
    	}
    	
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
    
    // Recibe una fecha y hora a comparar
    // Devuelve true si la planta se encontraba inactiva en el rango de esa fechaHora
    public boolean inactivaEn(LocalDateTime fechaHora) {
    	
    	if(fechaHora == null) {
    		throw new RuntimeException("Fecha a comparar no debe ser null");
    	}
  
    	if(inicioInactividad.isBefore(fechaHora) || inicioInactividad.equals(fechaHora)) {
    		if(finInactividad == null || finInactividad.isAfter(fechaHora) ) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
}
