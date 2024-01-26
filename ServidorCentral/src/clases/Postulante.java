package clases;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import datatypes.DTOferta;
import datatypes.DTPostulante;
import datos.Cargador_imagenes;


public class Postulante extends Usuario {
    private LocalDate fechaNacimiento;
    private String nacionalidad;
    private Set<Postulacion> postulaciones;
    private Set<String> favoritas;

    public Postulante(String nickname, String nombre, String apellido, String contraseña,  String correo,  LocalDate fechaNacimiento,  String nacionalidad) {
        super(nickname, nombre, apellido, contraseña, correo);
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.postulaciones = new HashSet<>();
        this.favoritas = new HashSet<>();
        this.setImagen(Cargador_imagenes.getInstance().getPostulantePredefinido());
    }

    public Set<String> getFavoritas() {
    	return this.favoritas;
    }
    
    public void addFavorita (String nomOferta) {
    	this.favoritas.add(nomOferta);
    }
    
    public void removeFavorita(String nomOferta) {
    	this.favoritas.remove(nomOferta);
    }
    
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
    public DTPostulante getDTPostulante() {
    	Set<DTOferta> DTS_ofertas = new HashSet<>();
    	if (!postulaciones.isEmpty()) {
	    	for (Postulacion post : postulaciones) {
	    		Oferta oferta = post.getOferta();
	    		DTS_ofertas.add(oferta.getDTOferta());
	    	}
    	}
    	    	
        return new DTPostulante(getNickname(), getNombre(), getApellido(), getCorreo(), fechaNacimiento, nacionalidad, DTS_ofertas, getImagen(), getFavoritas(), getCantFollowers(), getFollowers());
    }
    
    public void agregarPostulacion(Postulacion postu) {
    	postulaciones.add(postu);
    }

    // ------------ AGREGO A PARTIR DE ACA ------------
    public Set<Postulacion> getPostulaciones(){
    	return this.postulaciones;
    }
    
	@Override
	public void function() {}
}