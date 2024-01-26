package WebServices;

import logica.CollectionFactory;
import logica.ControllerFactory;
import logica.IColUsuario;
import logica.IConUsuario;
import datatypes.DTEmpresa;
import datatypes.DTPaquete;
import datatypes.DTPostulacion;
import datatypes.DTPostulante;
import datatypes.DTUsuario;
import excepciones.UsuarioCorreoExiste;
import excepciones.UsuarioNickExiste;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;


@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class UsuarioWebService {

    private Endpoint endpoint = null;
   
    
    private IConUsuario controladorUsuario = ControllerFactory.getInstance().getIControladorUsuario();
    private IColUsuario coleccionUsuario = CollectionFactory.getInstance().getIColUsuario();
        
    @WebMethod(exclude = true)
    public void publicar() {
        Properties prop = new Properties();
        String propFilePath = "/ens/home01/j/joaquin.campo/Descargas/config.properties"; // Ruta al archivo de propiedades
        String url = "http://localhost:8081/UsuarioWebService"; // Valor por defecto

        try (FileInputStream input = new FileInputStream(propFilePath)) {
            // Carga el archivo de propiedades
            prop.load(input);
            // Obtiene la propiedad webservice.url
            url = prop.getProperty("webservice.url", url)+"/UsuarioWebService";
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
    public DTPostulanteListWrapper listarPostulantesConDatos() {
    	Set<DTPostulante> postulantesSet = controladorUsuario.listarPostulantesConDatos();
        return new DTPostulanteListWrapper(new ArrayList<>(postulantesSet));
    }

   
    @WebMethod
    public StringListWrapper listarEmpresas() {
        Set<String> empresasSet = controladorUsuario.listarEmpresas();
        List<String> aaa = new ArrayList<>(empresasSet);
        return new StringListWrapper(aaa);
    }
    
    @WebMethod
    public DTUsuario mostrarDatos(String nickname) {
        return controladorUsuario.mostrarDatos(nickname);
    }
    
    @WebMethod
    public StringListWrapper listarUsuarios() {
        List<String> usuariosList = new ArrayList<>(controladorUsuario.listarUsuarios());
        return new StringListWrapper(usuariosList);
    }
    
    @WebMethod
    public void modificarEmpresa(String nickname, String Nombre, String Apellido, String Descripcion, String Web) {
        controladorUsuario.modificarEmpresa(nickname, Nombre, Apellido, Descripcion, Web);
    }
    
    @WebMethod
    public void modificarPostulante(String nickname, String Nombre, String Apellido, String fechaNacimiento, String Nacionalidad) throws Exception {
        LocalDate localDateNacimiento = LocalDate.parse(fechaNacimiento);
        controladorUsuario.modificarPostulante(nickname, Nombre, Apellido, localDateNacimiento, Nacionalidad);
    }

    
    @WebMethod
    public void altaEmpresa(String nickname, String nombre, String apellido, String contraseña, String correo, String descripcion, String web) throws UsuarioNickExiste, UsuarioCorreoExiste {
    	controladorUsuario.altaEmpresa(nickname, nombre, apellido, contraseña, correo, descripcion, web);
    }
    
    @WebMethod
    public void altaPostulante(String nickname, String Nombre, String Apellido, String contraseña, String correo,
            String fechaNacimiento, String Nacionalidad) throws Exception, UsuarioNickExiste, UsuarioCorreoExiste {
        LocalDate localDateNacimiento = LocalDate.parse(fechaNacimiento);
        controladorUsuario.altaPostulante(nickname, Nombre, Apellido, contraseña, correo, localDateNacimiento, Nacionalidad);
    }

    @WebMethod
    public DTUsuario elegirUsuario(String nickname) {
        return controladorUsuario.elegirUsuario(nickname);
    }
    
    @WebMethod
    public StringListWrapper listarOfertaLaboral(String empresa) {
        List<String> usuariosList = new ArrayList<>(controladorUsuario.listarOfertaLaboral(empresa));
        return new StringListWrapper(new ArrayList<>(usuariosList));
    }
    
    @WebMethod
    public void comprarPaquete(String empresa, String paquete, String fecha) throws Exception {
        LocalDate localDate = LocalDate.parse(fecha);
        controladorUsuario.comprarPaquete(empresa, paquete, localDate);
    }
    
    @WebMethod
    public void agregarImagen(String nombre,byte[] imagen) {
        controladorUsuario.agregarImagen(nombre, imagen);
    }
    
    @WebMethod
    public String buscarUsuario(String idUsr) {
    	return controladorUsuario.buscarUsuario(idUsr);
    }
    
    @WebMethod
    public boolean compararPassword(String nickname, String password) {
        return controladorUsuario.compararPassword(nickname, password);
    }
    
    @WebMethod
    public boolean esEmpresa(String nickname) {
        return controladorUsuario.esEmpresa(nickname);
    }
    
    @WebMethod
    public DTPaqueteListWrapper devolverPaquetesComprados(String nickname) {
        Set<DTPaquete> paquetesSet = controladorUsuario.devolverPaquetesComprados(nickname);
        List<DTPaquete> paquetesList = new ArrayList<>(paquetesSet);
        return new DTPaqueteListWrapper(paquetesList);
    }
    
    @WebMethod
    public DTEmpresa getDTEmpresa(String nickEmpresa) {
        return controladorUsuario.getDTEmpresa(nickEmpresa);
    }
    
    @WebMethod
    public DTPostulante getDTPostulante(String nickPostulante) {
        return controladorUsuario.getDTPostulante(nickPostulante);
    }
    
    @WebMethod
    public DTPostulacion getPostulacion(String nickPostulante, String nombreOferta) {
        return controladorUsuario.getPostulacion(nickPostulante, nombreOferta);
    }
    
    @WebMethod
    public boolean existeUsuarioNickname(String nickname) {
    	return coleccionUsuario.existeUsuarioNickname(nickname);
    }
    
    @WebMethod
    public boolean existeUsuarioCorreo(String correo) {
    	return coleccionUsuario.existeUsuarioCorreo(correo);
    }
    
    @WebMethod
    public boolean tienePostulacion(String nickname, String nomOferta) {
		return controladorUsuario.getPostulacion(nickname, nomOferta) != null;
    	
    }
    
    @WebMethod
	public void addFav(String nickname, String nomOferta) {
    	controladorUsuario.addFav(nickname, nomOferta);
    }
    
    
    @WebMethod
    public void removeFav(String nickname, String nomOferta) {
    	controladorUsuario.removeFav(nickname, nomOferta);
    }
	
    
    @WebMethod
    public boolean esFavorita(String nickname, String nomOferta) {
    	return controladorUsuario.esFavorita(nickname, nomOferta);
    }
    
    @WebMethod
    public StringListWrapper getFavorites(String nickname) {
    	List<String> usuariosList = new ArrayList<>(controladorUsuario.getFavorites(nickname));
        return new StringListWrapper(usuariosList);
    }
    
    
    @WebMethod
	public int getCantFollowers(String nickname) {
    	return controladorUsuario.getCantFollowers(nickname);
    }
	
    @WebMethod
    public StringListWrapper getFollowers(String nickname) {
    	List<String> usuariosList = new ArrayList<>(controladorUsuario.getFavorites(nickname));
        return new StringListWrapper(usuariosList);
    }
	
    @WebMethod
	public void addFollower(String follower, String followed) {
    	controladorUsuario.addFollower(follower, followed);
    }
	
    @WebMethod
	public void removeFollower(String follower, String followed) {
    	controladorUsuario.removeFollower(follower, followed);
    }
    
    @WebMethod
	public byte[] getImagen(String id) {
		return controladorUsuario.getImagen(id);
	}
    
    @WebMethod
    public DTPaqueteListWrapper PaquetesDisponiblesParaPagar(String nickname, String tipo) {
        List<DTPaquete> paquetesList = controladorUsuario.PaquetesDisponiblesParaPagar(nickname, tipo);
        return new DTPaqueteListWrapper(paquetesList);
    }
    
    @WebMethod
    public String obtenerFechaPostulanteString(String nombre) 
    {
    	return controladorUsuario.ObtenerFechaPostulanteString(nombre);
    	
    }
}
