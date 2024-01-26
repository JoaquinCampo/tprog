package logica;

import excepciones.PaqueteYaExiste;
import excepciones.TipoOfertaNoExiste;
import clases.Paquete;
import clases.TipoOferta;
import datatypes.DTPaquete;
import datatypes.DTTipoOferta;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class ControladorPaquete implements IConPaquete {
	
	@Override
	public Set<DTPaquete> listarPaquete(){
		Set<DTPaquete> lista = new HashSet<DTPaquete>();
		CollectionFactory fac = CollectionFactory.getInstance();
		IColPaquete col = fac.getIColPaquete();
		lista = col.listarPaquete();
		return lista;
	}
	
	@Override
	public void crearPaquete(String nombre, String descripcion, int valides, float descuento, LocalDate fecha)  throws PaqueteYaExiste {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColPaquete col = fac.getIColPaquete();
		// elimina los espacios adelante y atras
		nombre = nombre.trim();
		if (col.existeNombre(nombre)) throw new PaqueteYaExiste("El nombre del paquete "+ nombre+" ya existe");
		Paquete paq = new Paquete(nombre, descripcion, valides, descuento, fecha);
		col.aniadirPaquete(paq);
	}
	
	@Override
	public void agregarTipoDeOfertaAPaquete(String nombre, String tipoPaquete, int cant) throws TipoOfertaNoExiste{
		CollectionFactory fac = CollectionFactory.getInstance();
		IColPaquete colP = fac.getIColPaquete();
		IColTipoOferta colO = fac.getIColTipoOferta();
		if (!colO.existeTipo(tipoPaquete)) throw new TipoOfertaNoExiste("No existe un Tipo de oferta con el nombre: "+tipoPaquete);
		Paquete paq = colP.getPaquete(nombre);
		TipoOferta tipoOferta = colO.buscarTipoOferta(tipoPaquete);
		paq.agregarTipoOferta(tipoOferta, cant);
	}
	
	@Override
	public Set<String> getNombresTipoOferta(String paq) {
	    CollectionFactory fac = CollectionFactory.getInstance();
	    IColPaquete colP = fac.getIColPaquete();
	    Paquete paket = colP.getPaquete(paq);
	    
	    Set<DTTipoOferta> set_tp = paket.getTiposOferta();
	    
	    Set<String> nombres = new HashSet<>();
	    for (DTTipoOferta tipo : set_tp) {
	        nombres.add(tipo.getNombre()); 
	    
	    }
	    return nombres;
	}
	
	@Override
	public void agregarImagen(String nombre, byte[] imagen) {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColPaquete col = fac.getIColPaquete();
		Paquete paket = col.getPaquete(nombre);
		paket.setImagen(imagen);
	}
	
	@Override
	public DTPaquete getDTPaquete(String nombre) {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColPaquete col = fac.getIColPaquete();
		
		Paquete paq = col.getPaquete(nombre);
		
		return paq.obtenerDTPaquete();
	}
	
	@Override
	public byte[] getImagen(String id) {
		IColPaquete colPaq = CollectionFactory.getInstance().getIColPaquete();
		Paquete paq = colPaq.getPaquete(id);
		return paq.getImagen();
	}
	
	@Override
	public String ObtenerFechaPaqueteString(String id) {
		IColPaquete colPaq = CollectionFactory.getInstance().getIColPaquete();
		Paquete paq = colPaq.getPaquete(id);
		LocalDate fecha = paq.obtenerDTPaquete().getFecha();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaFormateada = fecha.format(formatter);
		return fechaFormateada;
	}
}
