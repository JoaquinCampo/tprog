
package webservices;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "OfertaYaExiste", targetNamespace = "http://WebServices/")
public class OfertaYaExiste_Exception
    extends java.lang.Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private OfertaYaExiste faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public OfertaYaExiste_Exception(String message, OfertaYaExiste faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public OfertaYaExiste_Exception(String message, OfertaYaExiste faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: webservices.OfertaYaExiste
     */
    public OfertaYaExiste getFaultInfo() {
        return faultInfo;
    }

}
