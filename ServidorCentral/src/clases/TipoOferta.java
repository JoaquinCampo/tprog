package clases;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import datatypes.DTTipoOferta;

import java.util.Iterator;

public class TipoOferta {
	private String nombre;
	private String descripcion;
	private int orden;
	private float costo;
	private int duracion;
	private LocalDate alta;
	private Set<Paquete_TipoOferta> paquetes;
	
	public TipoOferta(String nombre, String descripcion, int orden, float costo, int duracion, LocalDate alta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.orden = orden;
		this.costo = costo;
		this.duracion = duracion;		
		this.alta = alta;
		this.paquetes = new HashSet<Paquete_TipoOferta>();
	}
	
	public void setPaquete(Paquete_TipoOferta paquete_tipo) {
		paquetes.add(paquete_tipo);
	}
	
	//devuelve 1 si no es de Paquete por lo que no aplica descuento
	public float getDescuento(String oferta) {
		 float resu = 1;
		 Iterator<Paquete_TipoOferta> iter = paquetes.iterator();
		 boolean encontre = false;
		 while (iter.hasNext() && !encontre) {
			 Paquete_TipoOferta actual = iter.next();
			 encontre = actual.perteneceAPquete(oferta);
			 if (encontre) resu *= actual.getDescuento();
		 }
		 return resu;
	}
	
	//get and set
	public String getNombre() {
		return nombre;
	}
	//public void setNombre(String nombre) {
	//	this.nombre = nombre;
	//}
	public String getDescripcion() {
		return descripcion;
	}
	//public void setDescripcion(String descripcion) {
	//	this.descripcion = descripcion;
	//}
	public int getOrden() {
		return orden;
	}
	//public void setOrden(int orden) {
	//	this.orden = orden;
	//}
	public int getDuracion() {
		return duracion;
	}
	//public void setDuracion(float duracion) {
	//	this.duracion = duracion;
	//}
	public float getCosto() {
		return costo;
	}
	//public void setCosto(float costo) {
	//	this.costo = costo;
	//}
	//public Date getAlta() {
	//	return alta;
	//}
	//public void setAlta(Date alta) {
	//	this.alta = alta;
	//}
	
	public DTTipoOferta getDTTipoOferta(){
		DTTipoOferta dtOferta = new DTTipoOferta(this.nombre, this.getDescripcion(), this.getOrden(), this.duracion, this.costo, this.alta);
		return dtOferta;
	}
	
}






