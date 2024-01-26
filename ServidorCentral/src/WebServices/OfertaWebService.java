package WebServices;

import logica.*;
import datatypes.*;
import excepciones.*;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.xml.datatype.DatatypeFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class OfertaWebService {

	private Endpoint endpoint = null;
    private IConOferta controladorOferta = ControllerFactory.getInstance().getIControladorOferta();
    private IColOferta coleccionOferta = CollectionFactory.getInstance().getIColOferta();
    
    public OfertaWebService() {
        // Constructor
    }

    @WebMethod(exclude = true)
    public void publicar() {
        Properties prop = new Properties();
        String propFilePath = "/ens/home01/j/joaquin.campo/Descargas/config.properties"; // Ruta al archivo de propiedades
        String url = "http://localhost:8081/OfertaWebService"; // Valor por defecto

        try (FileInputStream input = new FileInputStream(propFilePath)) {
            // Carga el archivo de propiedades
            prop.load(input);
            // Obtiene la propiedad webservice.url
            url = prop.getProperty("webservice.url", url)+"/OfertaWebService";
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de configuración.");
            e.printStackTrace();
        }

        endpoint = Endpoint.publish(url, this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public void altaPostulacion(String oferta, String post, String CVRR, String mot, String fecha) throws Exception, PostulacionYaExiste, OfertaNoExiste, OfertaNoValidaParaEsaFecha {
        LocalDate localDate = LocalDate.parse(fecha);
        controladorOferta.altaPostulacion(oferta, post, CVRR, mot, localDate);
    }

    @WebMethod
    public DTOferta seleccionarOferta(String oferta) {
        return controladorOferta.seleccionarOferta(oferta);
    }

    @WebMethod
    public DTTipoOferta informacionTipoOferta(String nombre) {
        return controladorOferta.informacionTipoOferta(nombre);
    }

    @WebMethod
    public StringSetWrapper listarTipoOferta() {
        Set<String> tipos = controladorOferta.listarTipoOferta();
        return new StringSetWrapper(tipos);
    }

    @WebMethod
    public void altaTipoDeOfertaLaboral(String nombre, String descripcion, int orden, int dias, float costo, String fecha) throws Exception, TipoOfertaExiste {
        LocalDate localDate = LocalDate.parse(fecha);
        controladorOferta.altaTipoDeOfertaLaboral(nombre, descripcion, orden, dias, costo, localDate);
    }

    @WebMethod
    public StringSetWrapper listarKeyWord() {
        Set<String> keyWords = controladorOferta.listarKeyWord();
        return new StringSetWrapper(keyWords); // Utiliza la clase de envoltorio
    }

    @WebMethod
    public void altaOfertaLaboral(String nomEmpresa, String tipoPublicacion, String nombre, String descripcion, String horario,
                                  float remuneracion, String ciudad, String departamento,String fechaAlta,String[] keywords) throws Exception, TipoOfertaNoExiste, OfertaYaExiste, EmpresaNoExiste {
        LocalDate altafecha = LocalDate.parse(fechaAlta);
        Set<String> keys = new HashSet<>();
        for(String actual : keywords) {
        	keys.add(actual);
        }
        controladorOferta.altaOfertaLaboral(nomEmpresa, tipoPublicacion, nombre, descripcion, horario, remuneracion, ciudad, departamento, altafecha, keys);
    }

    @WebMethod
    public void altaKeyWord(String nombre) {
        controladorOferta.altaKeyWord(nombre);
    }

    @WebMethod
    public void aceptarOferta(String nombre) {
        controladorOferta.aceptarOferta(nombre);
    }

    @WebMethod
    public void rechazarOferta(String nombre) {
        controladorOferta.rechazarOferta(nombre);
    }

    @WebMethod
    public void comprarOfertaConPaquete(String nickname,String nombre, String tipo, String paquete) {
        controladorOferta.comprarOfertaConPaquete(nickname,nombre, tipo, paquete);
    }

    @WebMethod
    public StringSetWrapper getPostulantes(String ofert) {
        Set<String> postulantes = controladorOferta.getPostulantes(ofert);
        return new StringSetWrapper(postulantes); // Utiliza la clase de envoltorio
    }

    @WebMethod
    public void agregarImagen(String nombre, byte[] imagen) {
        controladorOferta.agregarImagen(nombre, imagen);
    }
    
    @WebMethod
    public DTOfertaListWrapper getOfertas() {
        List<DTOferta> ofertas = controladorOferta.getOfertas();
        return new DTOfertaListWrapper(ofertas); // Utiliza la clase de envoltorio
    }
    
    @WebMethod
    public DTOfertaListWrapper getAllofertas() {
        List<DTOferta> allOfertas = controladorOferta.getAllofertas();
        return new DTOfertaListWrapper(allOfertas); // Utiliza la clase de envoltorio
    }
    
    @WebMethod
    public String getEmpresa(String nombreOferta) {
        return controladorOferta.getEmpresa(nombreOferta);
    }
    
    @WebMethod
    public DTTipoOfertaSetWrapper getTipoOfertas() {
        Set<DTTipoOferta> tipoOfertas = controladorOferta.getTipoOfertas();
        return new DTTipoOfertaSetWrapper(tipoOfertas); // Utiliza la clase de envoltorio
    }
    
    @WebMethod
    public DTOfertaListWrapper getOfertasKeword(String keyword) {
        List<DTOferta> ofertasKeyword = controladorOferta.getOfertasKeword(keyword);
        return new DTOfertaListWrapper(ofertasKeyword); // Utiliza la clase de envoltorio
    }
    
    @WebMethod
    public StringSetWrapper getnickPostulantes(String ofert) {
        Set<String> nickPostulantes = controladorOferta.getnickPostulantes(ofert);
        return new StringSetWrapper(nickPostulantes); // Utiliza la clase de envoltorio
    }
    
    @WebMethod
    public DTOfertaListWrapper getOfertasValidas() {
        List<DTOferta> ofertasValidas = controladorOferta.getOfertasValidas();
        return new DTOfertaListWrapper(ofertasValidas); // Utiliza la clase de envoltorio
    }
    
    @WebMethod
    public DTOfertaListWrapper getOfertasRechazadas() {
        List<DTOferta> ofertasRechazadas = controladorOferta.getOfertasRechazadas();
        return new DTOfertaListWrapper(ofertasRechazadas); // Utiliza la clase de envoltorio
    }
    
    @WebMethod
    public DTOfertaListWrapper getOfertasIngresadas() {
        List<DTOferta> ofertasIngresadas = controladorOferta.getOfertasIngresadas();
        return new DTOfertaListWrapper(ofertasIngresadas); // Utiliza la clase de envoltorio
    }
    
    @WebMethod
    public DTOfertaListWrapper getOfertasFinalisadas() {
        List<DTOferta> ofertasFinalizadas = controladorOferta.getOfertasFinalisadas();
        return new DTOfertaListWrapper(ofertasFinalizadas); // Utiliza la clase de envoltorio
    }
    
    @WebMethod
    public DTPostulacionListWrapper getPostulaciones(String nombreOferta) {
        List<DTPostulacion> postulaciones = controladorOferta.getPostulaciones(nombreOferta);
        return new DTPostulacionListWrapper(postulaciones); // Utiliza la clase de envoltorio
    }

    @WebMethod
    public byte[] getImagen(String id) {
    	return controladorOferta.getImagen(id);
    }
    
    @WebMethod
    public int getCantFavs(String nomOferta) {
    	return controladorOferta.getCantFavs(nomOferta);
    }
    
    @WebMethod
    public void finalizarOferta(String nomOferta) {
    	controladorOferta.finalizarOferta(nomOferta);
    }
    
    @WebMethod
    public DTOfertaListWrapper getOfertasVencidasConfirmadas(String nick) {
        List<DTOferta> ofertasVencidasConfirmadas = controladorOferta.getOfertasVencidasConfirmadas(nick);
        return new DTOfertaListWrapper(ofertasVencidasConfirmadas); // Utiliza la clase de envoltorio
    }    
    
    @WebMethod
    public void agregarOrden(String nomOferta,String[] orden,String alta) {
    	LocalDate altafecha = LocalDate.parse(alta);
    	List<String> ordenlist = new ArrayList<>(Arrays.asList(orden));
    	controladorOferta.agregarOrden(nomOferta,ordenlist,altafecha);
    }
    
    @WebMethod
    public String obtenerNombrePaqueteOferta(String ofertaNombre) 
    {
        return controladorOferta.obtenerNombrePaqueteOferta(ofertaNombre);
    }
    
    @WebMethod
    public void agregarVideo(String oferta,String post,String video) throws VideoNoValido {
    	controladorOferta.agregarVideo(oferta, post, video);
    }
    
    @WebMethod
    public String obtenerFechaOfertaString(String nombre) 
    {
    	return controladorOferta.obtenerFechaOfertaString(nombre);
    	
    }
    
    @WebMethod
    public String obtenerTipoFechaOfertaString(String nombre) 
    {
    	return controladorOferta.obtenerFechaTipoOfertaString(nombre);
    	
    }
}
