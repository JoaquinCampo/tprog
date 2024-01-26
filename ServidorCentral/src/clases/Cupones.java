package clases;

import java.util.Map;

import datatypes.ParejaCantNombre;

public class Cupones {
	private String paquete;
	private Map<String,ParejaCantNombre> cantidadDeCupones; // identificados por el tipo

	public Cupones(String paquete, Map<String,ParejaCantNombre> cantidadDeCupones)
	{
		this.paquete = paquete;
		this.cantidadDeCupones = cantidadDeCupones;
	}

	public void restarCupo(String nombre) {
		ParejaCantNombre pareja = cantidadDeCupones.get(nombre);
		int cantidad = pareja.getCantidad();
		cantidad--;
		pareja.setCantidad(cantidad);
	}

	public Boolean tieneCupo(String tipo) {
		Boolean resu = false;
		if (cantidadDeCupones.containsKey(tipo)) {
		ParejaCantNombre pareja = cantidadDeCupones.get(tipo);	
		if (pareja.getCantidad() > 0) 
		{
			resu = true;
		}}
		return resu;
	}
}

