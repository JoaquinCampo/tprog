package clases;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import datatypes.ParejaCantNombre;

public class Empresa_Paquete {
	private LocalDate fecha;
	private Paquete paquete;
	private Cupones controladorCupos;
	
	public Empresa_Paquete(LocalDate fech, Paquete paq){
		this.fecha = fech;
		this.paquete = paq;
		Map<String, ParejaCantNombre> cupos = new HashMap<String, ParejaCantNombre>();
		Set<Paquete_TipoOferta> coleccion = paq.getColeccionPaqueteTipo();
		for (Paquete_TipoOferta iterador : coleccion) 
		{
			ParejaCantNombre pareja = iterador.getParejaCantNombre();
			cupos.put(pareja.getNombre(), pareja);
		}
		this.controladorCupos = new Cupones(paq.getNombre(),cupos);
	}
	
	public Paquete getPaquete() {
		return this.paquete;
	}
	
	public LocalDate getFecha() {
		return this.fecha;
	}
	
	public LocalDate getVencimiento(){
		return this.getFecha().plusDays((int) this.paquete.getValides());
	}
	
	public boolean estaVencido() {
		int comparacion = LocalDate.now().compareTo(this.getVencimiento());
		return comparacion >= 0;
	}
	

	public void consumirCupo(TipoOferta tipoOferta) {
		controladorCupos.restarCupo(tipoOferta.getNombre());
	}

	public Boolean tieneCupo(String tipo) {
		Boolean resu = controladorCupos.tieneCupo(tipo);
		return resu;
	}
	
	
	
}