package clases;

import java.time.LocalDate;

public class Postulacion {
    private Postulante postulante;
    private LocalDate fecha;
    private String curriculum;
    private String descripcion;
    private Oferta oferta;
    private String video_id;

    public Postulacion(Postulante postulante, LocalDate fecha, String cvRRR, String descripcion, Oferta ofRRR) {
        this.postulante = postulante;
        this.fecha = fecha;
        this.curriculum = cvRRR;
        this.descripcion = descripcion;
        this.oferta = ofRRR;
        this.video_id = "";
    }
    
    public boolean esDe(String post) {
    	return postulante.getNickname()==post;
    }

    public Postulante getPostulante() {
        return postulante;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    /*

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }*/
    
    public Oferta getOferta() {
    	return oferta;
    }
    
    public void setVideoid(String video) {
        this.video_id = video;
    }
    
    public String getVideoid() {
    	return this.video_id;
    }

    // --------------- AGREGO A PARTIR DE ACA ---------------
 
    public String getCv() {
        return curriculum;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
