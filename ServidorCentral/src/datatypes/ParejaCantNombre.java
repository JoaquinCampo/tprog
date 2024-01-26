package datatypes;

import java.util.Objects;

public class ParejaCantNombre {
	private String nombre;
	private int cant;
	
	public ParejaCantNombre(String nombre, int cant) {
		this.setNombre(nombre);
		this.setCantidad(cant);
	}
	
	public ParejaCantNombre() {}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getCantidad() {
		return this.cant;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setCantidad(int cantidad) {
		this.cant = cantidad;
	}
	
	public boolean isEqual(ParejaCantNombre comparator) {
    	if (this == comparator) {
    		return true;
    	}
    	if (comparator == null) {
    		return false;
    	}
    	
    	if (!Objects.equals(this.getNombre(), comparator.getNombre())) {
    		return false;
    	}
    	if (!Objects.equals(this.getCantidad(), comparator.getCantidad())) {
    		return false;
    	}
    	return true;
    }
	
}
