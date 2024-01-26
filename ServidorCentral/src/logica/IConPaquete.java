package logica;

import java.time.LocalDate;
import java.util.Set;

import datatypes.DTPaquete;
import excepciones.PaqueteYaExiste;
import excepciones.TipoOfertaNoExiste;

public interface IConPaquete {

	Set<DTPaquete> listarPaquete();

	void crearPaquete(String nombre, String descripcion, int valides, float descuento, LocalDate fecha)
			throws PaqueteYaExiste;

	void agregarTipoDeOfertaAPaquete(String nombre, String tipoPaquete, int cant) throws TipoOfertaNoExiste;
	
	public Set<String> getNombresTipoOferta(String paq);
//hacer

	void agregarImagen(String nombre, byte[] imagen);
	
	public DTPaquete getDTPaquete(String nombre);

	byte[] getImagen(String id);

	String ObtenerFechaPaqueteString(String id);
}
