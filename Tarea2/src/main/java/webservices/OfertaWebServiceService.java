
package webservices;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebServiceClient(name = "OfertaWebServiceService", targetNamespace = "http://WebServices/")
public class OfertaWebServiceService extends Service {

    private static URL OFERTAWEBSERVICESERVICE_WSDL_LOCATION;
    private static WebServiceException OFERTAWEBSERVICESERVICE_EXCEPTION;
    private static final QName OFERTAWEBSERVICESERVICE_QNAME = new QName("http://WebServices/", "OfertaWebServiceService");

    static {
        Properties prop = new Properties();
        String propFilePath = "/ens/home01/j/joaquin.campo/Descargas/config.properties"; // Ruta al archivo de propiedades
        try (FileInputStream input = new FileInputStream(propFilePath)) {
            // Carga el archivo de propiedades
            prop.load(input);
            // Lee la propiedad wsdlLocation para el servicio Oferta
            String wsdlLocation = prop.getProperty("ofertaWsdlLocation", "http://localhost:8086/OfertaWebService?wsdl");
            OFERTAWEBSERVICESERVICE_WSDL_LOCATION = new URL(wsdlLocation);
        } catch (IOException e) {
            OFERTAWEBSERVICESERVICE_EXCEPTION = new WebServiceException("No se pudo leer la ubicación del WSDL del servicio Oferta.", e);
        }
    }


    public OfertaWebServiceService() {
        super(__getWsdlLocation(), OFERTAWEBSERVICESERVICE_QNAME);
    }

    public OfertaWebServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), OFERTAWEBSERVICESERVICE_QNAME, features);
    }

    public OfertaWebServiceService(URL wsdlLocation) {
        super(wsdlLocation, OFERTAWEBSERVICESERVICE_QNAME);
    }

    public OfertaWebServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, OFERTAWEBSERVICESERVICE_QNAME, features);
    }

    public OfertaWebServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OfertaWebServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns OfertaWebService
     */
    @WebEndpoint(name = "OfertaWebServicePort")
    public OfertaWebService getOfertaWebServicePort() {
        return super.getPort(new QName("http://WebServices/", "OfertaWebServicePort"), OfertaWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OfertaWebService
     */
    @WebEndpoint(name = "OfertaWebServicePort")
    public OfertaWebService getOfertaWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://WebServices/", "OfertaWebServicePort"), OfertaWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (OFERTAWEBSERVICESERVICE_EXCEPTION!= null) {
            throw OFERTAWEBSERVICESERVICE_EXCEPTION;
        }
        return OFERTAWEBSERVICESERVICE_WSDL_LOCATION;
    }

}
