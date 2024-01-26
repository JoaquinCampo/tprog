
package webservices;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservices package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DtPaqueteListWrapper_QNAME = new QName("http://WebServices/", "dtPaqueteListWrapper");
    private final static QName _DtPostulanteListWrapper_QNAME = new QName("http://WebServices/", "dtPostulanteListWrapper");
    private final static QName _StringListWrapper_QNAME = new QName("http://WebServices/", "stringListWrapper");
    private final static QName _Exception_QNAME = new QName("http://WebServices/", "Exception");
    private final static QName _UsuarioCorreoExiste_QNAME = new QName("http://WebServices/", "UsuarioCorreoExiste");
    private final static QName _UsuarioNickExiste_QNAME = new QName("http://WebServices/", "UsuarioNickExiste");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DtPaqueteListWrapper }
     * 
     * @return
     *     the new instance of {@link DtPaqueteListWrapper }
     */
    public DtPaqueteListWrapper createDtPaqueteListWrapper() {
        return new DtPaqueteListWrapper();
    }

    /**
     * Create an instance of {@link DtPostulanteListWrapper }
     * 
     * @return
     *     the new instance of {@link DtPostulanteListWrapper }
     */
    public DtPostulanteListWrapper createDtPostulanteListWrapper() {
        return new DtPostulanteListWrapper();
    }

    /**
     * Create an instance of {@link StringListWrapper }
     * 
     * @return
     *     the new instance of {@link StringListWrapper }
     */
    public StringListWrapper createStringListWrapper() {
        return new StringListWrapper();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     * @return
     *     the new instance of {@link Exception }
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link UsuarioCorreoExiste }
     * 
     * @return
     *     the new instance of {@link UsuarioCorreoExiste }
     */
    public UsuarioCorreoExiste createUsuarioCorreoExiste() {
        return new UsuarioCorreoExiste();
    }

    /**
     * Create an instance of {@link UsuarioNickExiste }
     * 
     * @return
     *     the new instance of {@link UsuarioNickExiste }
     */
    public UsuarioNickExiste createUsuarioNickExiste() {
        return new UsuarioNickExiste();
    }

    /**
     * Create an instance of {@link DtEmpresa }
     * 
     * @return
     *     the new instance of {@link DtEmpresa }
     */
    public DtEmpresa createDtEmpresa() {
        return new DtEmpresa();
    }

    /**
     * Create an instance of {@link DtOferta }
     * 
     * @return
     *     the new instance of {@link DtOferta }
     */
    public DtOferta createDtOferta() {
        return new DtOferta();
    }

    /**
     * Create an instance of {@link DtPaquete }
     * 
     * @return
     *     the new instance of {@link DtPaquete }
     */
    public DtPaquete createDtPaquete() {
        return new DtPaquete();
    }

    /**
     * Create an instance of {@link DtPostulacion }
     * 
     * @return
     *     the new instance of {@link DtPostulacion }
     */
    public DtPostulacion createDtPostulacion() {
        return new DtPostulacion();
    }

    /**
     * Create an instance of {@link DtPostulante }
     * 
     * @return
     *     the new instance of {@link DtPostulante }
     */
    public DtPostulante createDtPostulante() {
        return new DtPostulante();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     * @return
     *     the new instance of {@link DtUsuario }
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link ParejaCantNombre }
     * 
     * @return
     *     the new instance of {@link ParejaCantNombre }
     */
    public ParejaCantNombre createParejaCantNombre() {
        return new ParejaCantNombre();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DtPaqueteListWrapper }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DtPaqueteListWrapper }{@code >}
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "dtPaqueteListWrapper")
    public JAXBElement<DtPaqueteListWrapper> createDtPaqueteListWrapper(DtPaqueteListWrapper value) {
        return new JAXBElement<>(_DtPaqueteListWrapper_QNAME, DtPaqueteListWrapper.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DtPostulanteListWrapper }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DtPostulanteListWrapper }{@code >}
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "dtPostulanteListWrapper")
    public JAXBElement<DtPostulanteListWrapper> createDtPostulanteListWrapper(DtPostulanteListWrapper value) {
        return new JAXBElement<>(_DtPostulanteListWrapper_QNAME, DtPostulanteListWrapper.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringListWrapper }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StringListWrapper }{@code >}
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "stringListWrapper")
    public JAXBElement<StringListWrapper> createStringListWrapper(StringListWrapper value) {
        return new JAXBElement<>(_StringListWrapper_QNAME, StringListWrapper.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioCorreoExiste }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioCorreoExiste }{@code >}
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "UsuarioCorreoExiste")
    public JAXBElement<UsuarioCorreoExiste> createUsuarioCorreoExiste(UsuarioCorreoExiste value) {
        return new JAXBElement<>(_UsuarioCorreoExiste_QNAME, UsuarioCorreoExiste.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNickExiste }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioNickExiste }{@code >}
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "UsuarioNickExiste")
    public JAXBElement<UsuarioNickExiste> createUsuarioNickExiste(UsuarioNickExiste value) {
        return new JAXBElement<>(_UsuarioNickExiste_QNAME, UsuarioNickExiste.class, null, value);
    }

}
