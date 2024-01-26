package datatypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DTPostulacion {
    private String postulante;
    private LocalDate fecha;
    private String cvRR;
    private String motivacion;
    private String oferta;
    private String empresa;
    private String video_id;
    private String string_fecha;

    // Constructor
    public DTPostulacion(String postulante, String oferta,  LocalDate fecha,  String cvPP,  String motivacion, String video) {
        this.setPostulante(postulante);
        this.setFecha(fecha);
        this.setCv(cvPP);
        this.setMotivacion(motivacion);
        this.setOferta(oferta);
        this.setVideoid(video);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.string_fecha = fecha.format(formatter);
    }
    
     public DTPostulacion() {
    	 
     }
   
    public String getPostulante() {
        return postulante;
    }

    public void setPostulante(String postulante) {
        this.postulante = postulante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCv() {
        return cvRR;
    }

    public void setCv(String cvPP) {
        this.cvRR = cvPP;
    }

    public String getMotivacion() {
        return motivacion;
    }

    public void setMotivacion(String motivacion) {
        this.motivacion = motivacion;
    }

    public String getOferta() {
        return oferta;
    }

    public void setOferta(String oferta) {
        this.oferta = oferta;
    }
    
    public void setVideoid(String video) {
        this.video_id = video;
    }
    
    public String getVideoid() {
    	return this.video_id;
    }
    
    public void setEmpresa(String empresa) {
    	this.empresa=empresa;
    }
    public String getEmpresa() {
    	return this.empresa;
    }
    
    public void setStringFecha(String fecha) {
    	this.string_fecha=fecha;
    }
    public String getStringFecha() {
    	return this.string_fecha;
    }
    
	public boolean isEqual(DTPostulacion comparator) {
    	if (this == comparator) {
    		return true;
    	}
    	if (comparator == null) {
    		return false;
    	}
    	
    	if (!Objects.equals(this.getPostulante(), comparator.getPostulante())) {
    		return false;
    	}
    	if (!Objects.equals(this.getCv(), comparator.getCv())) {
    		return false;
    	}
    	if (!Objects.equals(this.getMotivacion(), comparator.getMotivacion())) {
    		return false;
    	}
    	if (!Objects.equals(this.getFecha(), comparator.getFecha())) {
    		return false;
    	}
    	if (!Objects.equals(this.getEmpresa(), comparator.getEmpresa())) {
    		return false;
    	}
    	return true;
    }
}



