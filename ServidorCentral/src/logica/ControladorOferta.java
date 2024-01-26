package logica;

import clases.KeyWord;
import clases.Oferta;
import clases.TipoOferta;
import clases.Usuario;
import datatypes.DTOferta;
import datatypes.DTPostulacion;
import datatypes.DTTipoOferta;
import datatypes.Estados;
import clases.Paquete;
import excepciones.TipoOfertaExiste;
import excepciones.OfertaYaExiste;
import excepciones.PostulacionYaExiste;
import excepciones.EmpresaNoExiste;
import excepciones.OfertaNoExiste;
import excepciones.OfertaNoValidaParaEsaFecha;
import excepciones.TipoOfertaNoExiste;
import excepciones.VideoNoValido;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import clases.Postulante;
import clases.Postulacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import clases.Empresa;

public class ControladorOferta implements IConOferta {

	@Override
	public DTOferta seleccionarOferta(String oferta) {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		Oferta oooo = col.buscarOferta(oferta);
		return oooo.getDTOferta();
	}

	@Override
	public DTTipoOferta informacionTipoOferta(String nombre) {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColTipoOferta col = fac.getIColTipoOferta();
		TipoOferta tipoOferta = col.buscarTipoOferta(nombre);
		return tipoOferta.getDTTipoOferta();
	}

	@Override
	public void altaPostulacion(String oferta, String post, String CVRR, String mot, LocalDate fecha)
			throws PostulacionYaExiste, OfertaNoExiste, OfertaNoValidaParaEsaFecha{
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		if (!col.existeOferta(oferta))
			throw new OfertaNoExiste("La oferta " + oferta + "no existe en el sistema");
		Oferta ofertaaaaa = col.buscarOferta(oferta);
		LocalDate vence = ofertaaaaa.getFechaVencimiento();
		if (fecha.isAfter(vence))
			throw new OfertaNoValidaParaEsaFecha("La oferta vence " + vence.toString());
		if (ofertaaaaa.esPostulante(post))
			throw new PostulacionYaExiste("El usuario " + post + " ya es postulante de " + oferta);
		IColUsuario colu = fac.getIColUsuario();
		Postulante postu = colu.obtenerPostulantePorNickname(post);
		Postulacion pos = new Postulacion(postu, fecha, CVRR, mot, ofertaaaaa);
		ofertaaaaa.setPostulante(pos);
		postu.agregarPostulacion(pos);
	}
	
	@Override
	public void agregarVideo(String oferta,String post,String video) throws VideoNoValido{
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		Oferta ofe = col.buscarOferta(oferta);
		Set<Postulacion> portulaciones = ofe.getPostulaciones();
        for (Postulacion elemento : portulaciones) {
            if (elemento.getPostulante().getNickname().equals(post)) {
                 // Puedes salir del bucle si encuentras el elemento
				String re = "(?:youtube\\.com\\/(?:[^\\/\\n\\s]+\\/\\S+\\/|e(?:mbed)\\/|(?:v|\\S*?[?&]v=))|youtu\\.be\\/)([a-zA-Z0-9_-]{11})";
		        Pattern pattern = Pattern.compile(re);
		        Matcher matcher = pattern.matcher(video);
		        if(matcher.find()) {
		        	String id = matcher.group(1);
		        	elemento.setVideoid(id);
		        }else{
		        	throw new VideoNoValido("Emlace no valido");
		        }
		        break;
            }
        }
	}

	@Override
	public Set<String> listarTipoOferta() {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColTipoOferta col = fac.getIColTipoOferta();
		return col.listarTipoOferta();
	}

	@Override
	public void altaOfertaLaboral(String nomEmpresa, String tipoPublicacion, String nombre, String descripcion,
			String horario, float remuneracion, String ciudad, String departamento, LocalDate fechaAlta,
			Set<String> keywords) throws TipoOfertaNoExiste, OfertaYaExiste, EmpresaNoExiste {
		System.out.println("ENTRO A ALTAOFERTA CON NOMBRE"+ nombre + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		// se eliminan los espacios de adelante y atras
		nombre = nombre.trim();
		if (col.existeOferta(nombre))
			throw new OfertaYaExiste("La oferta " + nombre + " ya existe en el sistema");
		IColUsuario colu = fac.getIColUsuario();
		if (!colu.existeUsuarioNickname(nomEmpresa))
			throw new EmpresaNoExiste("La empresa " + nomEmpresa + " ya existe en el sitema");
		Empresa empresa = colu.obtenerEmpresaPorNickname(nomEmpresa);
		IColTipoOferta colt = fac.getIColTipoOferta();
		if (!colt.existeTipo(tipoPublicacion))
			throw new TipoOfertaNoExiste("El tipo " + tipoPublicacion + " no existe");
		TipoOferta tipooferta = colt.buscarTipoOferta(tipoPublicacion);
		Oferta offer = new Oferta(nombre, descripcion, ciudad, departamento, horario, remuneracion, fechaAlta, tipooferta);
		col.agregarOferta(offer);
		empresa.agregarOferta(offer);
		
		if (!keywords.isEmpty() && keywords != null) {
			for (String actual : keywords) {
				KeyWord keyw = col.getKeyWord(actual);
				keyw.agregarOferta(offer);
			}
		}
	}

	@Override
	public void altaTipoDeOfertaLaboral(String nombre, String descripcion, int orden, int dias, float costo, LocalDate fecha)
			throws TipoOfertaExiste {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColTipoOferta col = fac.getIColTipoOferta();
		boolean comprobante = col.existeTipo(nombre);
		if (col.existeTipo(nombre))
			throw new TipoOfertaExiste("El tipo de oferta con el nombre: " + nombre + " ya existe en el sistema");
		TipoOferta tipo = new TipoOferta(nombre, descripcion, orden, costo, dias, fecha);
		col.agregarTipoOferta(tipo);
	}

	@Override
	public Set<String> listarKeyWord() {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		return col.listarKeyWord();
	}

	@Override
	public void altaKeyWord(String nombre) {
		KeyWord keyw = new KeyWord(nombre);
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		col.anadirKey(keyw);
	}
	
	@Override
	public Set<String> getPostulantes(String oferta) {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		Oferta feeee = col.buscarOferta(oferta);
		return feeee.getNombresPostulantes();
	}

	@Override
	public void aceptarOferta(String nombre) {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		col.aceptarOferta(nombre);		
	}

	@Override
	public void rechazarOferta(String nombre) {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		col.rechazarOferta(nombre);		
	}
	
	@Override
	public void comprarOfertaConPaquete(String nickname, String nombre, String tipo, String paquete) {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		IColTipoOferta coltipos = fac.getIColTipoOferta();
		IColPaquete colpaquetes = fac.getIColPaquete();
		IColUsuario colUsuario = fac.getIColUsuario();
		Empresa user = colUsuario.obtenerEmpresaPorNickname(nickname);
		Paquete paq = colpaquetes.getPaquete(paquete);
		Oferta oferta = col.buscarOferta(nombre);
		TipoOferta tipoOferta = coltipos.buscarTipoOferta(tipo);
		user.comprarOfertaConPaquete(oferta,tipoOferta,paq);
	}

	
	@Override
	public void agregarImagen(String nombre, byte[] imagen) {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		Oferta oferta = col.buscarOferta(nombre);
		if (oferta != null) {
			oferta.setImagen(imagen);
	    } else {
	        // Lógica para manejar la situación cuando 'o' es null
	        System.err.println("No se pudo encontrar una oferta con el nombre: " + nombre);
	    }
	}
	
	@Override
	public List<DTOferta> getOfertas() {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		return col.getOfertas();
	}
	
	@Override
	public List<DTOferta> getOfertasValidas() {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		return col.getOfertasValidas();
		
	}
	
	@Override
	public String getEmpresa(String nombreOferta) {
		
		IConUsuario iconUsr = ControllerFactory.getInstance().getIControladorUsuario();
		
		Set<String> nombresEmpresas = iconUsr.listarEmpresas();
	    for (String nombreEmpresa : nombresEmpresas) {
	        Set<String> ofertasLaborales = iconUsr.listarOfertaLaboral(nombreEmpresa);
	        if (ofertasLaborales.contains(nombreOferta)) {
	            return nombreEmpresa;
	        }
	    }
	    return null;
	}
	
	@Override
	public Set<DTTipoOferta> getTipoOfertas() {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColTipoOferta col = fac.getIColTipoOferta();
		return col.listarTipoOfertaConDatos();
	}
	
	@Override
	public List<DTOferta> getOfertasKeword(String keyword) {
		KeyWord key = CollectionFactory.getInstance().getIColOferta().getKeyWord(keyword);
		return key.getDTOfertas();
	}

	@Override
	public Set<String> getnickPostulantes(String oferta){
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		
		
		if (col.existeOferta(oferta)) {
			Oferta ofer = col.buscarOferta(oferta);
			return ofer.getNicknamesPostulantes();
		}
		else return null;
	}
	@Override
	public List<DTOferta> getOfertasRechazadas(){
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		return col.getOfertasRechazadas();
	}
	
	@Override
	public List<DTOferta> getOfertasFinalisadas(){
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		return col.getOfertasFinalisadas();
	}
	
	
	
	@Override
	public List<DTOferta> getOfertasIngresadas(){
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		return col.getOfertasIngresadas();
	}
	
	@Override
	public List<DTOferta> getAllofertas(){
		List<DTOferta> ingresadas = this.getOfertasIngresadas();
		List<DTOferta> rechazadas = this.getOfertasRechazadas();
		List<DTOferta> aceptadas = this.getOfertas();
		
		List<DTOferta> todasLasOfertas = new ArrayList<>();

		todasLasOfertas.addAll(ingresadas);
		todasLasOfertas.addAll(rechazadas);
		todasLasOfertas.addAll(aceptadas);
		
		return todasLasOfertas;
	}

	@Override
	public List<DTPostulacion> getPostulaciones(String nombreOferta) {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		
		Oferta offer = col.buscarOferta(nombreOferta);
		Set<Postulacion> postulaciones = offer.getPostulaciones();
		List<DTPostulacion> res = new ArrayList<>();
		
		for (Postulacion postu : postulaciones) {
			DTPostulacion dtPost = new DTPostulacion(postu.getPostulante().getNickname(), offer.getNombre(), postu.getFecha(), postu.getCv(), postu.getDescripcion(), postu.getVideoid());
			res.add(dtPost);
		}
		return res;
	}
	
	@Override
	public int getCantFavs(String nomOferta) {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		
		Oferta offer = col.buscarOferta(nomOferta);
		return offer.getCantFavs();
		
	}
	
	@Override
	public byte[] getImagen(String id) {
		IColOferta colOft = CollectionFactory.getInstance().getIColOferta();
		Oferta oft = colOft.buscarOferta(id);
		return oft.getImagen();
	}
	
	@Override
	public void finalizarOferta(String nomOferta) {
		IColOferta colOft = CollectionFactory.getInstance().getIColOferta();
		colOft.finalizarOferta(nomOferta);
	}
	
	@Override
	public List<DTOferta> getOfertasVencidasConfirmadas(String nick){
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		return col.getOfertasVencidasConfirmadas(nick);
	}//Devuelve las que ni tienen oreden
	
	@Override
	public void agregarOrden(String nomOferta,List<String> orden, LocalDate altafecha) {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		Oferta oferta = col.buscarOferta(nomOferta);
		oferta.agregarOrden(orden,altafecha);
	}
	@Override
	public String obtenerNombrePaqueteOferta(String ofertaNombre) 
	{
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		Oferta offer = col.buscarOferta(ofertaNombre);
		return offer.getPaquete();
	}
	
	@Override
	public String obtenerFechaOfertaString(String nombre) {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColOferta col = fac.getIColOferta();
		Oferta offer = col.buscarOferta(nombre);
		LocalDate fecha = offer.getDTOferta().getFecha();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaFormateada = fecha.format(formatter);
		return fechaFormateada;
	}
	
	@Override
	public String obtenerFechaTipoOfertaString(String nombre) {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColTipoOferta col = fac.getIColTipoOferta();
		TipoOferta offer = col.buscarTipoOferta(nombre);
		LocalDate fecha = offer.getDTTipoOferta().getFecha();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaFormateada = fecha.format(formatter);
		return fechaFormateada;
	}
}





