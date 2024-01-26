package datatypes;

import java.time.LocalDate;
import datatypes.ParejaCantNombre;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class DTPaquete {
	private String nombre;
	private String descripcion;
	private float valides;
	private float descuento;
	private float precio;
	private LocalDate fecha;
	private Set<ParejaCantNombre> pareja;
	private boolean comprado;
	private byte[] imagen;
	
	public DTPaquete(String nombre, String descripcion, float valides, float descuento,  float precio,  LocalDate fecha, Set<ParejaCantNombre> pareja, boolean comprado,byte[] imagen) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setValides(valides);
		this.setDescuento(descuento);
		this.setPrecio(precio);
		this.setFecha(fecha);
		this.setPareja(pareja);
		this.setComprado(comprado);
		this.setImagen(imagen);
	}
	
	public DTPaquete() {
		
	}
	
	public boolean getComprado() {
		return this.comprado;
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public float getValides() {
		return valides;
	}
	public float getDescuento() {
		return descuento;
	}
	public float getPrecio() {
		return precio;
	}

	public LocalDate getFecha() {
		return fecha;
	}
	public Set<ParejaCantNombre> getPareja() {
		return this.pareja;
	}
	
    public byte[] getImagen() {
    	return this.imagen;
    }
    //
    
    public void setComprado(boolean comprado) {
		this.comprado = comprado;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setValides(float valides) {
		this.valides = valides;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public void setPareja(Set<ParejaCantNombre> pareja) {
		this.pareja = pareja;
	}
	
    public void setImagen(byte[] imagen) {
    	this.imagen = imagen;
    }
    
	public boolean isEqual(DTPaquete comparator) {
    	if (this == comparator) {
    		return true;
    	}
    	if (comparator == null) {
    		return false;
    	}
    	
    	if (!Objects.equals(this.getNombre(), comparator.getNombre())) {
    		return false;
    	}
    	if (!Objects.equals(this.getComprado(), comparator.getComprado())) {
    		return false;
    	}
    	if (!Objects.equals(this.getDescripcion(), comparator.getDescripcion())) {
    		return false;
    	}
    	if (!Objects.equals(this.getValides(), comparator.getValides())) {
    		return false;
    	}
    	if (!Objects.equals(this.getDescuento(), comparator.getDescuento())) {
    		return false;
    	}
    	if (!Objects.equals(this.getPrecio(), comparator.getPrecio())) {
    		return false;
    	}
    	if (!Objects.equals(this.getFecha(), comparator.getFecha())) {
    		return false;
    	}
    	if (!Objects.equals(this.getImagen(), comparator.getImagen())) {
    		return false;
    	}
       	if (!Objects.equals(this.getPareja().size(), comparator.getPareja().size())) {
    		return false;
    	}
       	else 
       	{
       		Iterator<ParejaCantNombre> iterador = this.getPareja().iterator();
       		Iterator<ParejaCantNombre> comparar = comparator.getPareja().iterator();
       		while (iterador.hasNext() && comparar.hasNext()) {
       			ParejaCantNombre elementoOriginal = iterador.next();
       			ParejaCantNombre elementoComparar = comparar.next();
       			if (elementoOriginal.isEqual(elementoComparar)) {
       				return false;
       			}
       		}
       	}
    	return true;
    }
	
}
