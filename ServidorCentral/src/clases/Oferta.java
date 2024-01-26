package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import datatypes.DTOferta;
import datatypes.Estados;

import java.util.Iterator;
import java.util.List;

import logica.ControllerFactory;
import logica.IConOferta;
import datos.Cargador_imagenes;

public class Oferta {
	private String nombre;
	private String descripcio;
	private String ciudad;
	private String departamento;
	private String horario;
	private float sueldo;
	private LocalDate alta;
	private TipoOferta tipo;
    private Set<Postulacion> postulaciones;
    private Estados estado;
    private byte[] imagen;
    private int cantFavs;

    private List<Postulacion> orden;
    private LocalDate altaOrden;
    private String paquete; //esto es distinto de "" cuando se utiliza la funcion comprarOfertaConPaquete

    
    // Constructor sin argumentos
    public Oferta() {
    }
	
	public Oferta(String nombre, String descripcio, String ciudad, String departamento, String horario, float sueldo,
			LocalDate alta, TipoOferta tipo) {
		this.nombre = nombre;
		this.descripcio = descripcio;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horario = horario;
		this.sueldo = sueldo;
		this.alta = alta;
		this.tipo = tipo;
		this.estado = Estados.Ingresada;
		this.postulaciones = new HashSet<Postulacion>();
		this.imagen = Cargador_imagenes.getInstance().getOfertaPredefinida();
		this.cantFavs = 0;
		this.orden = new ArrayList<>();
		this.paquete = "";
		}
    
	
    public DTOferta getDTOferta(){
		IConOferta iconOf = ControllerFactory.getInstance().getIControladorOferta();
		String empresa = iconOf.getEmpresa(this.nombre);
		
    	DTOferta resu = new DTOferta(this.nombre, this.descripcio, this.ciudad, this.departamento, this.horario, this.sueldo, this.alta, getPrecio(), this.estado, this.imagen, empresa, this.cantFavs);
		return resu;
	}
    
    
    public int getCantFavs() {
    	return this.cantFavs;
    }
    
    public void setCantFavs(int cant) {
    	this.cantFavs = cant;
    }
    
    public void addFav() {
    	this.cantFavs++;
    }
    
    public void removeFav() {
    	this.cantFavs--;
    }
    
    public LocalDate getFechaVencimiento() {
    	int duracion = (int) this.tipo.getDuracion();
    	LocalDate resu = this.alta.plusDays(duracion);
    	return resu;
    }
	
	public float getPrecio(){
		float resultado = tipo.getCosto() / tipo.getDuracion();
		float descuento = tipo.getDescuento(nombre);
		if (descuento != 1) resultado -= resultado*descuento; 
		return resultado;
	}
	
	public void setPostulante(Postulacion pos) {
		postulaciones.add(pos);
	}
	
	public boolean esPostulante(String post) {
		Iterator<Postulacion> iter = postulaciones.iterator();
		boolean encontre = false;
		while (iter.hasNext() && !encontre) {
			Postulacion actual = iter.next();
			encontre = actual.esDe(post);
		}
		return encontre;
	} 

	public String getNombre() {
		return nombre;
	}

	public Set<String> getNombresPostulantes() {
	    Set<Postulacion> postulaciones = this.postulaciones;
	    Set<String> nombresPostulantes = new HashSet<>();

	    for (Postulacion postulacion : postulaciones) {
	        nombresPostulantes.add(postulacion.getPostulante().getNombre()); 
	    }

	    return nombresPostulantes;
	}
	
	public Set<String> getNicknamesPostulantes() {
	    Set<Postulacion> postulaciones = this.postulaciones;
	    Set<String> nickPostulantes = new HashSet<>();

	    for (Postulacion postulacion : postulaciones) {
	        nickPostulantes.add(postulacion.getPostulante().getNickname()); 
	    }
	    return nickPostulantes;

	}
	
	public void setEstado(Estados estado) {
		switch (estado) {
		case Confirmada:
			this.estado = Estados.Confirmada;
			break;
		case Rechazada:
			this.estado = Estados.Rechazada;
			break;
		case Finalizada:
			this.estado = Estados.Finalizada;
			break;
		default:
			break;
		}
	}
	
    public void setImagen(byte[] imagen) {
    	this.imagen = imagen;
    }
    
    public byte[] getImagen() {
    	return this.imagen;
    }

    public Set<Postulacion> getPostulaciones() {
    	return this.postulaciones;
    }
    
    public List<Postulacion> getOrden(){
    	return this.orden;
    }
    
    public LocalDate getAltaOrden() {
    	return this.altaOrden;
    }

	public void agregarOrden(List<String> postulantesorden, LocalDate altafecha) {
		this.altaOrden = altafecha;
		//por cada postulante en orden
		for(String actual : postulantesorden) {
			//busco la postulacion que le corresponde
			for(Postulacion buscar : postulaciones) {	
				//la agrego al orden
				if(actual.equals(buscar.getPostulante().getNickname())) {
					this.orden.add(buscar);
				}
			}
		}
	}
	

    public String getTipo() {
    	return this.tipo.getNombre();
    }
    
    public String getPaquete() {
    	return paquete;
    }
    
    public void setPaquete(String paquete) {
    	this.paquete = paquete;
    }

}