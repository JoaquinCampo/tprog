
package webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.FaultAction;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebService(name = "UsuarioWebService", targetNamespace = "http://WebServices/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UsuarioWebService {


    /**
     * 
     * @return
     *     returns webservices.DtPostulanteListWrapper
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/listarPostulantesConDatosRequest", output = "http://WebServices/UsuarioWebService/listarPostulantesConDatosResponse")
    public DtPostulanteListWrapper listarPostulantesConDatos();

    /**
     * 
     * @param arg0
     * @return
     *     returns webservices.DtUsuario
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/mostrarDatosRequest", output = "http://WebServices/UsuarioWebService/mostrarDatosResponse")
    public DtUsuario mostrarDatos(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @return
     *     returns webservices.StringListWrapper
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/listarEmpresasRequest", output = "http://WebServices/UsuarioWebService/listarEmpresasResponse")
    public StringListWrapper listarEmpresas();

    /**
     * 
     * @param arg0
     * @return
     *     returns webservices.DtUsuario
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/elegirUsuarioRequest", output = "http://WebServices/UsuarioWebService/elegirUsuarioResponse")
    public DtUsuario elegirUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @return
     *     returns webservices.StringListWrapper
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/listarUsuariosRequest", output = "http://WebServices/UsuarioWebService/listarUsuariosResponse")
    public StringListWrapper listarUsuarios();

    /**
     * 
     * @param arg0
     * @param arg1
     */
    @WebMethod
    @Action(input = "http://WebServices/UsuarioWebService/agregarImagenRequest", output = "http://WebServices/UsuarioWebService/agregarImagenResponse")
    public void agregarImagen(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        byte[] arg1);

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @param arg4
     * @param arg5
     * @param arg6
     * @throws UsuarioCorreoExiste_Exception
     * @throws UsuarioNickExiste_Exception
     */
    @WebMethod
    @Action(input = "http://WebServices/UsuarioWebService/altaEmpresaRequest", output = "http://WebServices/UsuarioWebService/altaEmpresaResponse", fault = {
        @FaultAction(className = UsuarioNickExiste_Exception.class, value = "http://WebServices/UsuarioWebService/altaEmpresa/Fault/UsuarioNickExiste"),
        @FaultAction(className = UsuarioCorreoExiste_Exception.class, value = "http://WebServices/UsuarioWebService/altaEmpresa/Fault/UsuarioCorreoExiste")
    })
    public void altaEmpresa(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6)
        throws UsuarioCorreoExiste_Exception, UsuarioNickExiste_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @param arg4
     * @param arg5
     * @param arg6
     * @throws Exception_Exception
     * @throws UsuarioCorreoExiste_Exception
     * @throws UsuarioNickExiste_Exception
     */
    @WebMethod
    @Action(input = "http://WebServices/UsuarioWebService/altaPostulanteRequest", output = "http://WebServices/UsuarioWebService/altaPostulanteResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://WebServices/UsuarioWebService/altaPostulante/Fault/Exception"),
        @FaultAction(className = UsuarioNickExiste_Exception.class, value = "http://WebServices/UsuarioWebService/altaPostulante/Fault/UsuarioNickExiste"),
        @FaultAction(className = UsuarioCorreoExiste_Exception.class, value = "http://WebServices/UsuarioWebService/altaPostulante/Fault/UsuarioCorreoExiste")
    })
    public void altaPostulante(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6)
        throws Exception_Exception, UsuarioCorreoExiste_Exception, UsuarioNickExiste_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/buscarUsuarioRequest", output = "http://WebServices/UsuarioWebService/buscarUsuarioResponse")
    public String buscarUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/compararPasswordRequest", output = "http://WebServices/UsuarioWebService/compararPasswordResponse")
    public boolean compararPassword(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws Exception_Exception
     */
    @WebMethod
    @Action(input = "http://WebServices/UsuarioWebService/comprarPaqueteRequest", output = "http://WebServices/UsuarioWebService/comprarPaqueteResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://WebServices/UsuarioWebService/comprarPaquete/Fault/Exception")
    })
    public void comprarPaquete(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/esEmpresaRequest", output = "http://WebServices/UsuarioWebService/esEmpresaResponse")
    public boolean esEmpresa(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @param arg4
     */
    @WebMethod
    @Action(input = "http://WebServices/UsuarioWebService/modificarEmpresaRequest", output = "http://WebServices/UsuarioWebService/modificarEmpresaResponse")
    public void modificarEmpresa(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/existeUsuarioCorreoRequest", output = "http://WebServices/UsuarioWebService/existeUsuarioCorreoResponse")
    public boolean existeUsuarioCorreo(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/obtenerFechaPostulanteStringRequest", output = "http://WebServices/UsuarioWebService/obtenerFechaPostulanteStringResponse")
    public String obtenerFechaPostulanteString(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @param arg4
     * @throws Exception_Exception
     */
    @WebMethod
    @Action(input = "http://WebServices/UsuarioWebService/modificarPostulanteRequest", output = "http://WebServices/UsuarioWebService/modificarPostulanteResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://WebServices/UsuarioWebService/modificarPostulante/Fault/Exception")
    })
    public void modificarPostulante(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/existeUsuarioNicknameRequest", output = "http://WebServices/UsuarioWebService/existeUsuarioNicknameResponse")
    public boolean existeUsuarioNickname(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns webservices.StringListWrapper
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/listarOfertaLaboralRequest", output = "http://WebServices/UsuarioWebService/listarOfertaLaboralResponse")
    public StringListWrapper listarOfertaLaboral(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns webservices.DtPaqueteListWrapper
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/devolverPaquetesCompradosRequest", output = "http://WebServices/UsuarioWebService/devolverPaquetesCompradosResponse")
    public DtPaqueteListWrapper devolverPaquetesComprados(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns webservices.DtPaqueteListWrapper
     */
    @WebMethod(operationName = "PaquetesDisponiblesParaPagar")
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/PaquetesDisponiblesParaPagarRequest", output = "http://WebServices/UsuarioWebService/PaquetesDisponiblesParaPagarResponse")
    public DtPaqueteListWrapper paquetesDisponiblesParaPagar(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @param arg1
     */
    @WebMethod
    @Action(input = "http://WebServices/UsuarioWebService/addFollowerRequest", output = "http://WebServices/UsuarioWebService/addFollowerResponse")
    public void addFollower(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/tienePostulacionRequest", output = "http://WebServices/UsuarioWebService/tienePostulacionResponse")
    public boolean tienePostulacion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns webservices.DtEmpresa
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/getDTEmpresaRequest", output = "http://WebServices/UsuarioWebService/getDTEmpresaResponse")
    public DtEmpresa getDTEmpresa(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/esFavoritaRequest", output = "http://WebServices/UsuarioWebService/esFavoritaResponse")
    public boolean esFavorita(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/getCantFollowersRequest", output = "http://WebServices/UsuarioWebService/getCantFollowersResponse")
    public int getCantFollowers(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     */
    @WebMethod
    @Action(input = "http://WebServices/UsuarioWebService/removeFollowerRequest", output = "http://WebServices/UsuarioWebService/removeFollowerResponse")
    public void removeFollower(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns byte[]
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/getImagenRequest", output = "http://WebServices/UsuarioWebService/getImagenResponse")
    public byte[] getImagen(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns webservices.StringListWrapper
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/getFollowersRequest", output = "http://WebServices/UsuarioWebService/getFollowersResponse")
    public StringListWrapper getFollowers(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns webservices.DtPostulante
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/getDTPostulanteRequest", output = "http://WebServices/UsuarioWebService/getDTPostulanteResponse")
    public DtPostulante getDTPostulante(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     */
    @WebMethod
    @Action(input = "http://WebServices/UsuarioWebService/addFavRequest", output = "http://WebServices/UsuarioWebService/addFavResponse")
    public void addFav(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @param arg1
     */
    @WebMethod
    @Action(input = "http://WebServices/UsuarioWebService/removeFavRequest", output = "http://WebServices/UsuarioWebService/removeFavResponse")
    public void removeFav(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns webservices.DtPostulacion
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/getPostulacionRequest", output = "http://WebServices/UsuarioWebService/getPostulacionResponse")
    public DtPostulacion getPostulacion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns webservices.StringListWrapper
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://WebServices/UsuarioWebService/getFavoritesRequest", output = "http://WebServices/UsuarioWebService/getFavoritesResponse")
    public StringListWrapper getFavorites(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

}
