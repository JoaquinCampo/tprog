package logica;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import clases.KeyWord;
import clases.Oferta;
import clases.Postulacion;
import clases.TipoOferta;
import datatypes.DTOferta;
import datatypes.DTTipoOferta;
import datatypes.Estados;

public class ColeccionOferta implements IColTipoOferta, IColOferta {

	private Map<String, TipoOferta> colTipoOferta;
	private Map<String, Oferta> colOferta;
	private Map<String, Oferta> ofertasIngresadas;
	private Map<String, Oferta> ofertasAceptadas;
	private Map<String, Oferta> ofertasRechazadas;
	private Map<String, Oferta> ofertasFinalisadas;
	private Map<String, KeyWord> keyword;
	private static ColeccionOferta instancia = null;
	
	private ColeccionOferta() {
		this.colTipoOferta = new HashMap<String, TipoOferta>();
		this.colOferta = new HashMap<String, Oferta>();
		this.ofertasAceptadas = new HashMap<String, Oferta>();
		this.ofertasRechazadas = new HashMap<String, Oferta>();
		this.ofertasIngresadas = new HashMap<String, Oferta>();
		this.ofertasFinalisadas = new HashMap<String, Oferta>();
		this.keyword = new HashMap<String, KeyWord>();
	}
	
	public static String quitarTildes(String textoConTildes) {
        // Usamos Normalizer para descomponer y eliminar las tildes
        String textoNormalizado = Normalizer.normalize(textoConTildes, Normalizer.Form.NFD);
        
        // Usamos una expresión regular para eliminar los caracteres no ASCII
        Pattern patron = Pattern.compile("[^\\p{ASCII}]");
        
        // Sustituimos los caracteres acentuados por sus equivalentes sin tilde
        return patron.matcher(textoNormalizado).replaceAll("");
    }

	
	@Override
	public TipoOferta buscarTipoOferta(String tipoOferta) {
		String clave = tipoOferta;
    	clave = quitarTildes(clave);
    	clave = clave.toLowerCase();
		return this.colTipoOferta.get(clave);
	}
	
	public static ColeccionOferta getInstance() {
		if (instancia == null)  instancia = new ColeccionOferta();
		return instancia;
	}

	@Override
	public Oferta buscarOferta(String nombre) {
		String clave = nombre;
    	clave = quitarTildes(clave);
    	clave = clave.toLowerCase();
		return this.colOferta.get(clave);
	}

	@Override
	public void agregarOferta(Oferta oferta) {
		String clave = oferta.getNombre();
    	clave = quitarTildes(clave);
    	clave = clave.toLowerCase();
		colOferta.put(clave, oferta);
		ofertasIngresadas.put(clave, oferta);
	}
	@Override
	public void aceptarOferta(String nombre) {
		Oferta oferta=buscarOferta(nombre);
		oferta.setEstado(Estados.Confirmada);
    	nombre = quitarTildes(nombre);
    	nombre = nombre.toLowerCase();
        ofertasIngresadas.remove(nombre);
		ofertasAceptadas.put(nombre, oferta);
	}
	@Override
	public void rechazarOferta(String nombre) {
		Oferta oferta=buscarOferta(nombre);
		oferta.setEstado(Estados.Rechazada);
    	nombre = quitarTildes(nombre);
    	nombre = nombre.toLowerCase();
        ofertasIngresadas.remove(nombre);
		ofertasRechazadas.put(nombre, oferta);
	}
	
	@Override
	public void finalizarOferta(String nombre) {
		Oferta oferta=buscarOferta(nombre);
		oferta.setEstado(Estados.Finalizada);
    	nombre = quitarTildes(nombre);
    	nombre = nombre.toLowerCase();
        ofertasAceptadas.remove(nombre);
		ofertasFinalisadas.put(nombre, oferta);
	}
	          
	@Override
	public Set<String> listarKeyWord(){
		return keyword.keySet();
	}
	
	@Override
	public boolean existeOferta(String nombre) {
		String clave = nombre;
    	clave = quitarTildes(clave);
    	clave = clave.toLowerCase();
		return colOferta.containsKey(clave);
	}
	
	@Override
	public KeyWord getKeyWord(String keyy) {
		return keyword.get(keyy);
	}
	
	@Override
	public boolean existeKey(String keyy) {
		return keyword.containsKey(keyy);
	}
	
	@Override
	public void anadirKey(KeyWord key) {
		keyword.put(key.getNombre(), key);
	}

	@Override
	public Set<String> listarTipoOferta() {
    	Set<String> listaTipoOferta= new HashSet<String>();
    	for (String agregar : colTipoOferta.keySet()) {
    		listaTipoOferta.add(colTipoOferta.get(agregar).getNombre());
    	}
    	return listaTipoOferta;
	}
	
	@Override
	public boolean existeTipo(String tipo) {
		String clave = tipo;
    	clave = quitarTildes(clave);
    	clave = clave.toLowerCase();
		return colTipoOferta.containsKey(clave);
	}

	@Override
	public void agregarTipoOferta(TipoOferta tipo) {
		String clave = tipo.getNombre();
    	clave = quitarTildes(clave);
    	clave = clave.toLowerCase();
		this.colTipoOferta.put(clave, tipo);
	}

	@Override
	public List<DTOferta> getOfertas() {
	    List<DTOferta> listaOfertas = new java.util.ArrayList<DTOferta>();
	    for (String agregar : ofertasAceptadas.keySet()) {
	        Oferta oferta = ofertasAceptadas.get(agregar);
	        if (oferta != null) {
	            DTOferta dtoOferta = oferta.getDTOferta();
	            if (dtoOferta != null) {
	                listaOfertas.add(dtoOferta);
	            }
	        }
	    }
	    return listaOfertas;
	}
	
	@Override
	public List<DTOferta> getOfertasValidas() {
	    List<DTOferta> listaOfertas = new java.util.ArrayList<DTOferta>();
	    for (String agregar : ofertasAceptadas.keySet()) {
	        Oferta oferta = ofertasAceptadas.get(agregar);
	        if (oferta != null && 0 <= oferta.getFechaVencimiento().compareTo(LocalDate.now())) {
	            DTOferta dtoOferta = oferta.getDTOferta();
	            if (dtoOferta != null) {
	                listaOfertas.add(dtoOferta);
	            }
	        }
	    }
	    return listaOfertas;
	}
	
	//devuelve las que no tienen orden echo
	@Override
	public List<DTOferta> getOfertasVencidasConfirmadas(String nick) {
	    List<DTOferta> listaOfertas = new ArrayList<DTOferta>();
	    for (String agregar : ofertasAceptadas.keySet()) {
	        Oferta oferta = ofertasAceptadas.get(agregar);
	        if (oferta != null && 0 > oferta.getFechaVencimiento().compareTo(LocalDate.now())) {
	            List<Postulacion> orden = oferta.getOrden();        
	            if(orden.isEmpty()) {
	        		DTOferta dtoOferta = oferta.getDTOferta();
		            if(nick.equals(dtoOferta.getEmpresa())) {
	        			listaOfertas.add(dtoOferta);
		            }
	            }
	        }
	    }
	    return listaOfertas;
	}

	@Override
	public Set<DTTipoOferta> getTipoOfertas() {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColTipoOferta col = fac.getIColTipoOferta();
		return col.listarTipoOfertaConDatos();
	}
	
	@Override
    public Set<DTTipoOferta> listarTipoOfertaConDatos() {
        Set<DTTipoOferta> listaTipoOferta= new HashSet<DTTipoOferta>();

        for (String agregar : colTipoOferta.keySet()) {
            TipoOferta tipo = colTipoOferta.get(agregar);
            if (tipo != null) {
                DTTipoOferta dtTipoOferta = tipo.getDTTipoOferta();
                if (dtTipoOferta != null)
                    listaTipoOferta.add(dtTipoOferta);
            }
        }
        return listaTipoOferta;
    }
	
	@Override
	public List<DTOferta> getOfertasRechazadas(){
		List<DTOferta> listaOfertas = new java.util.ArrayList<DTOferta>();
	    for (String agregar : ofertasRechazadas.keySet()) {
	        Oferta oferta = ofertasRechazadas.get(agregar);
	        if (oferta != null) {
	            DTOferta dtoOferta = oferta.getDTOferta();
	            if (dtoOferta != null) {
	                listaOfertas.add(dtoOferta);
	            }
	        }
	    }
	    return listaOfertas;
	}
	
	@Override
	public List<DTOferta> getOfertasFinalisadas(){
		List<DTOferta> listaOfertas = new java.util.ArrayList<DTOferta>();
	    for (String agregar : ofertasFinalisadas.keySet()) {
	        Oferta oferta = ofertasFinalisadas.get(agregar);
	        if (oferta != null) {
	            DTOferta dtoOferta = oferta.getDTOferta();
	            if (dtoOferta != null) {
	                listaOfertas.add(dtoOferta);
	            }
	        }
	    }
	    return listaOfertas;
	}
	
	@Override
	public List<DTOferta> getOfertasIngresadas(){
		List<DTOferta> listaOfertas = new java.util.ArrayList<DTOferta>();
	    for (String agregar : ofertasIngresadas.keySet()) {
	        Oferta oferta = ofertasIngresadas.get(agregar);
	        if (oferta != null) {
	            DTOferta dtoOferta = oferta.getDTOferta();
	            if (dtoOferta != null) {
	                listaOfertas.add(dtoOferta);
	            }
	        }
	    }
	    return listaOfertas;
	}
	
}


