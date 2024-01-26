package datatypes;

import java.time.LocalDate;
import java.util.Objects;

public class DTTipoOferta {
	private String nombre;
	private String descripcion;
	private int orden;
	private int duracion;
	private float costo;
	private LocalDate fecha;

	public DTTipoOferta(String nombre, String descripcion, int orden, int duracion, float costo, LocalDate fecha) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setOrden(orden);
		this.setDuracion(duracion);
		this.setCosto(costo);
		this.setFecha(fecha);
	}
	
	public DTTipoOferta() {
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}

	public int getDuracion() {
		return duracion;
	}

	public int getOrden() {
		return orden;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public float getCosto() {
		return costo;
	}
	//
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion =  descripcion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}


	public void setOrden(int orden) {
		this.orden = orden;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public boolean isEqual(DTTipoOferta comparator) {
    	if (this == comparator) {
    		return true;
    	}
    	if (comparator == null) {
    		return false;
    	}
    	
    	if (!Objects.equals(this.getNombre(), comparator.getNombre())) {
    		return false;
    	}
    	if (!Objects.equals(this.getOrden(), comparator.getOrden())) {
    		return false;
    	}
    	if (!Objects.equals(this.getDescripcion(), comparator.getDescripcion())) {
    		return false;
    	}
    	if (!Objects.equals(this.getDuracion(), comparator.getDuracion())) {
    		return false;
    	}
    	if (!Objects.equals(this.getFecha(), comparator.getFecha())) {
    		return false;
    	}
    	if (!Objects.equals(this.getCosto(), comparator.getCosto())) {
    		return false;
    	}
    	return true;
    }
}
