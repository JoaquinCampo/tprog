package WebServices;

import logica.ControllerFactory;
import logica.IConPaquete;
import datatypes.DTPaquete;
import excepciones.PaqueteYaExiste;
import excepciones.TipoOfertaNoExiste;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.xml.datatype.DatatypeFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Set;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PaqueteWebService {
    
    private Endpoint endpoint = null;
    private IConPaquete controladorPaquete = ControllerFactory.getInstance().getIControladorPaquete();
    
    @WebMethod(exclude = true)
    public void publicar() {
        Properties prop = new Properties();
        String propFilePath = "/ens/home01/j/joaquin.campo/Descargas/config.properties"; // Ruta al archivo de propiedades
        String url = "http://localhost:8081/PaqueteWebService"; // Valor por defecto

        try (FileInputStream input = new FileInputStream(propFilePath)) {
            // Carga el archivo de propiedades
            prop.load(input);
            // Obtiene la propiedad webservice.url
            url = prop.getProperty("webservice.url", url)+"/PaqueteWebService";
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
    public DTPaqueteSetWrapper listarPaquete() {
        Set<DTPaquete> paquetes = controladorPaquete.listarPaquete();
        return new DTPaqueteSetWrapper(paquetes); // Utiliza la clase de envoltorio
    }
    
    @WebMethod
    public void crearPaquete(String nombre, String descripcion, int valides, float descuento, String fecha) throws Exception, PaqueteYaExiste {
        LocalDate localDate = LocalDate.parse(fecha);;
        controladorPaquete.crearPaquete(nombre, descripcion, valides, descuento, localDate);
    }
    
    @WebMethod
    public void agregarTipoDeOfertaAPaquete(String nombre, String tipoPaquete, int cant) throws TipoOfertaNoExiste {
        controladorPaquete.agregarTipoDeOfertaAPaquete(nombre, tipoPaquete, cant);
    }
    
    @WebMethod
    public StringSetWrapper getNombresTipoOferta(String paq) {
        Set<String> nombresTipoOferta = controladorPaquete.getNombresTipoOferta(paq);
        return new StringSetWrapper(nombresTipoOferta); // Utiliza la clase de envoltorio
    }
    
    @WebMethod
    public void agregarImagen(String nombre, byte[] imagen) {
        controladorPaquete.agregarImagen(nombre, imagen);
    }
    
    @WebMethod
    public DTPaquete getDTPaquete(String nombre) {
        return controladorPaquete.getDTPaquete(nombre);
    }
    
    @WebMethod
	public byte[] getImagen(String id) {
		return controladorPaquete.getImagen(id);
	}
    
    @WebMethod
    public String obtenerFechaPaqueteString(String nombre) 
    {
    	return controladorPaquete.ObtenerFechaPaqueteString(nombre);
    	
    }

}
