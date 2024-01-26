package clases;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import datatypes.DTPaquete;
import datatypes.DTTipoOferta;
import datatypes.ParejaCantNombre;
import datos.Cargador_imagenes;

public class Paquete {
	private String nombre;
	private String descripcion;
	private float valides;
	private float descuento;
	private LocalDate fecha;
	private Set<Paquete_TipoOferta> coleccionTO;
	private boolean comprado;
	private byte[] imagen;
	

	//Constructor, se considera que el descuento esta en forma decimal, dividirlo entre 100 si lo pasa en forma procentual
	public Paquete(String nombre, String  descripcion, float valides, float descuento, LocalDate fecha) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valides = valides;
		this.descuento = descuento;
		this.fecha = fecha;
		this.coleccionTO = new HashSet<Paquete_TipoOferta>();
		this.comprado=false;
		this.imagen= Cargador_imagenes.getInstance().getPaquetePredefinido();
	}
	
	public Paquete(String nombre, String descripcion, float valides, float descuento, LocalDate fecha, boolean bool) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valides = valides;
		this.descuento = descuento;
		this.fecha = fecha;
		this.coleccionTO = new HashSet<Paquete_TipoOferta>();
		this.comprado=bool;
		this.imagen= Cargador_imagenes.getInstance().getPaquetePredefinido();
	}
	
	public void comprar() {
		this.comprado = true;
	}
	
	// Gets
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	public float getValides() {
		return this.valides;
	}
	
	//El descuento se obtiene en forma decimal, multiplicar por 100 para obtenerlo en forma porcentual ╰(°▽°)╯
	public float getDescuento() {
		return this.descuento;
	}
	
	public float getPrecio() {
		float suma = 0f;
		for (Paquete_TipoOferta costoTO : this.coleccionTO) {
			suma += costoTO.obetenerPrecioTotal();
		}
		return suma - suma* this.descuento;
	}
	
	public DTPaquete obtenerDTPaquete() {
		Set<ParejaCantNombre> pareja = new HashSet<ParejaCantNombre>();
		for (Paquete_TipoOferta par : this.coleccionTO) {
			ParejaCantNombre aux = new ParejaCantNombre(par.getNombreTipoOferta(), par.getCantidad());
			pareja.add(aux);
		}
		return new DTPaquete(this.nombre, this.descripcion, this.valides, this.descuento,  this.getPrecio(), this.fecha, pareja, this.comprado, this.imagen);
	}
	
	public void agregarTipoOferta(TipoOferta toPPP, int cant) {
		Paquete_TipoOferta ptTT = new Paquete_TipoOferta(toPPP, cant);
		this.coleccionTO.add(ptTT);
	}
	
	public Set<DTTipoOferta> getTiposOferta() {
	    Set<DTTipoOferta> resultado = new HashSet<>();

	    for (Paquete_TipoOferta paquete_tipo : coleccionTO) {
	        DTTipoOferta dtTipo = paquete_tipo.getTipoOferta().getDTTipoOferta();
	        resultado.add(dtTipo);
	    }

	    return resultado;
	}
	
    public void setImagen(byte[] imagen) {
    	this.imagen = imagen;
    }
    
    public byte[] getImagen() {
    	return this.imagen;
    }
    
    public Set<Paquete_TipoOferta> getColeccionPaqueteTipo()
    {
    	return this.coleccionTO;
    }
}
