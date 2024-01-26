package clases;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import datatypes.DTEmpresa;
import datatypes.DTOferta;
import datatypes.DTPaquete;
import datos.Cargador_imagenes;

public class Empresa extends Usuario {
    private String descripcion;
    private String webSite;
    private Map<String, Oferta> ofertas;
    private Map<String, Empresa_Paquete> paquetes;

    public Empresa(String nickname, String nombre, String apellido, String contraseña, String correo, String descripcion, String webUrl) {
        super(nickname, nombre, apellido, contraseña, correo);
        this.descripcion = descripcion;
        this.webSite = webUrl;
        this.ofertas = new HashMap<String, Oferta>();
        this.paquetes = new HashMap<String, Empresa_Paquete>();
        this.setImagen(Cargador_imagenes.getInstance().getEmpresaPredefinida());
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getWeb() {
        return webSite;
    }
    
    public void setDescripcion(String desc) {
    	descripcion = desc;
    }
    
    public void setWeb(String webUrl) {
    	webSite = webUrl;
    }
    
    public DTEmpresa getDTEmpresa() {
    	Set<DTOferta> DTS_ofertas = new HashSet<>();
    	if (ofertas != null && !ofertas.isEmpty()) {
    	    Set<Oferta> conjuntoOfertas = new HashSet<>(ofertas.values());
    	    for (Oferta of : conjuntoOfertas) {
    	        if (of == null) {
    	            continue;
    	        }
    	        DTS_ofertas.add(of.getDTOferta());
    	    }
    	}
   
    	return new DTEmpresa(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(), this.webSite,  this.descripcion, DTS_ofertas, this.getImagen(), getCantFollowers(), getFollowers());
    	
    }
    
    public void agregarOferta(Oferta oferta) {
    	ofertas.put(oferta.getNombre(), oferta);
    }
    
    public void removerOferta(String ofName) {
    	ofertas.remove(ofName);
    }
    
    public Oferta getOferta(String ofName) {
    	return ofertas.get(ofName);
    }
    
    public Set<String> obtenerOfertas() {
    	Set<String> prueba = new HashSet<>(ofertas.keySet());
    	return prueba;
    }

    public void comprarPaquete(LocalDate fecha, Paquete paquete) {
    	Empresa_Paquete nuevacompra = new Empresa_Paquete(fecha, paquete);
    	paquetes.put(paquete.getNombre(), nuevacompra);
    	paquete.comprar();
    }
    
	public Set<DTPaquete> getColeccionPaquetes() {
		Set<DTPaquete> resu = new HashSet<>();
		Map<String, Empresa_Paquete> auxiliar =  this.paquetes;
		for (String paquete : auxiliar.keySet()) {
			Empresa_Paquete empresa_paquete =  auxiliar.get(paquete);
			if (!empresa_paquete.estaVencido()) {
				DTPaquete dtpaquete =  empresa_paquete.getPaquete().obtenerDTPaquete();
				resu.add(dtpaquete);
			}
		}
		return resu;
	}
	
	public Set<DTPaquete> getPaquetesDisponiblesParaPagar(String tipo)
	{
		Set<DTPaquete> paquetesDisponibles = new HashSet<DTPaquete>();
		Map<String, Empresa_Paquete> auxiliar =  this.paquetes;
		for (String paquete : auxiliar.keySet()) {
			Empresa_Paquete empresa_paquete =  auxiliar.get(paquete);
			if (!empresa_paquete.estaVencido()) {
				Boolean sirve =  empresa_paquete.tieneCupo(tipo);
				if (sirve) {
				DTPaquete dtpaquete =  empresa_paquete.getPaquete().obtenerDTPaquete();
				paquetesDisponibles.add(dtpaquete);}
			}
		}
		return paquetesDisponibles;
	}
    
    // ------------ AGREGO A PARTIR DE ACA ------------
	
	public Set<String> getNombresOfertas(){
		return this.ofertas.keySet();
	}
	
	@Override
	public void function() {}

	public void comprarOfertaConPaquete(Oferta oferta, TipoOferta tipoOferta, Paquete paq) {
		Map<String, Empresa_Paquete> auxiliar =  this.paquetes;
		Empresa_Paquete empresa_paquete =  auxiliar.get(paq.getNombre());
		oferta.setPaquete(paq.getNombre());
		empresa_paquete.consumirCupo(tipoOferta);
	}
}
