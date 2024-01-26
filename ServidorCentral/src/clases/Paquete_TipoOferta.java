package clases;

import java.util.Map;

import datatypes.ParejaCantNombre;

public class Paquete_TipoOferta {
	
	private TipoOferta titpoOferta;
	private int cantidad;
	private Map<String, Oferta> ofertas;
	private Paquete paquete;
	
	
	public Paquete_TipoOferta(TipoOferta toPPP, int cant) {
		this.titpoOferta = toPPP;
		this.cantidad = cant;
	}
	
	public boolean perteneceAPquete(String oferta) {
		return ofertas.containsKey(oferta);
	}
	
	public float getDescuento() {
		return paquete.getDescuento();
	}
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public void setTipoOferta(TipoOferta toPPP) {
		this.titpoOferta = toPPP;
	}
	
	public float obetenerPrecioTotal() {
		return ((float) this.cantidad) * titpoOferta.getCosto();
	}
	
	public String getNombreTipoOferta() {
		return this.titpoOferta.getNombre();
	}
	
	public TipoOferta getTipoOferta() {
		return this.titpoOferta;
	}
	
	public boolean esElPaquete(String identificador) {
		return this.paquete.getNombre() == identificador;
	}
	
	public void agregarOferta(Oferta oferta) {
		this.ofertas.put(oferta.getNombre(), oferta);
		this.cantidad--;
	}
	
	public ParejaCantNombre getParejaCantNombre() {
		return new ParejaCantNombre(this.getNombreTipoOferta(), this.cantidad);
	}
}
