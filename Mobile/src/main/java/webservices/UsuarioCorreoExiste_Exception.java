
package webservices;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "UsuarioCorreoExiste", targetNamespace = "http://WebServices/")
public class UsuarioCorreoExiste_Exception
    extends java.lang.Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private UsuarioCorreoExiste faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public UsuarioCorreoExiste_Exception(String message, UsuarioCorreoExiste faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public UsuarioCorreoExiste_Exception(String message, UsuarioCorreoExiste faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: webservices.UsuarioCorreoExiste
     */
    public UsuarioCorreoExiste getFaultInfo() {
        return faultInfo;
    }

}
