package logica;

import excepciones.PostulacionYaExiste;
import excepciones.EmpresaNoExiste;
import excepciones.OfertaNoExiste;
import excepciones.OfertaNoValidaParaEsaFecha;
import excepciones.OfertaYaExiste;
import excepciones.TipoOfertaExiste;
import excepciones.TipoOfertaNoExiste;
import excepciones.VideoNoValido;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import datatypes.DTOferta;
import datatypes.DTPostulacion;
import datatypes.DTTipoOferta;

public interface IConOferta {

	public void altaPostulacion(String oferta, String post, String CVRR, String mot, LocalDate fecha) throws PostulacionYaExiste, OfertaNoExiste, OfertaNoValidaParaEsaFecha;
	public DTOferta seleccionarOferta(String oferta);
	public DTTipoOferta informacionTipoOferta(String nombre);
	Set<String> listarTipoOferta();
	public void altaTipoDeOfertaLaboral(String nombre, String descripcion, int orden, int dias, float costo, LocalDate fecha)
			throws TipoOfertaExiste;
	Set<String> listarKeyWord();
	void altaOfertaLaboral(String nomEmpresa, String tipoPublicacion, String nombre, String descripcion, String horario,
			float remuneracion, String ciudad, String departamento, LocalDate fechaAlta, Set<String> keywords)
			throws TipoOfertaNoExiste, OfertaYaExiste, EmpresaNoExiste;
	void altaKeyWord(String nombre);
	
	void aceptarOferta(String nombre);
	void rechazarOferta(String nombre);
	public void comprarOfertaConPaquete(String nickname,String nombre, String tipo, String paquete);
	
	public Set<String> getPostulantes(String ofert);
	void agregarImagen(String nombre, byte[] imagen);

	
	public List<DTOferta> getOfertas();
	public List<DTOferta> getAllofertas();
	public String getEmpresa(String nombreOferta);
	Set<DTTipoOferta> getTipoOfertas();
	
	public List<DTOferta> getOfertasKeword(String keyword);
	
	public Set<String> getnickPostulantes(String ofert);
	
	public List<DTOferta> getOfertasValidas();
	public List<DTOferta> getOfertasRechazadas();
	public List<DTOferta> getOfertasIngresadas();
	public List<DTOferta> getOfertasFinalisadas();
	public List<DTPostulacion> getPostulaciones(String nombreOferta);
	
	public int getCantFavs(String nomOferta);
	byte[] getImagen(String id);
	public void finalizarOferta(String nomOferta);
	public List<DTOferta> getOfertasVencidasConfirmadas(String nick);
	void agregarOrden(String nomOferta, List<String> orden, LocalDate altafecha);
	String obtenerNombrePaqueteOferta(String ofertaNombre);
	
	public void agregarVideo(String oferta,String post,String video) throws VideoNoValido;
	String obtenerFechaOfertaString(String nombre);
	String obtenerFechaTipoOfertaString(String nombre);
}

