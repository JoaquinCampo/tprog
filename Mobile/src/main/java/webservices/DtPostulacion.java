
package webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtPostulacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtPostulacion">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="cv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="empresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fecha" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/>
 *         <element name="motivacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="oferta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="postulante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="stringFecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="videoid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPostulacion", propOrder = {
    "cv",
    "empresa",
    "fecha",
    "motivacion",
    "oferta",
    "postulante",
    "stringFecha",
    "videoid"
})
public class DtPostulacion {

    protected String cv;
    protected String empresa;
    protected Object fecha;
    protected String motivacion;
    protected String oferta;
    protected String postulante;
    protected String stringFecha;
    protected String videoid;

    /**
     * Obtiene el valor de la propiedad cv.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCv() {
        return cv;
    }

    /**
     * Define el valor de la propiedad cv.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCv(String value) {
        this.cv = value;
    }

    /**
     * Obtiene el valor de la propiedad empresa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Define el valor de la propiedad empresa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmpresa(String value) {
        this.empresa = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setFecha(Object value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad motivacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivacion() {
        return motivacion;
    }

    /**
     * Define el valor de la propiedad motivacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivacion(String value) {
        this.motivacion = value;
    }

    /**
     * Obtiene el valor de la propiedad oferta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOferta() {
        return oferta;
    }

    /**
     * Define el valor de la propiedad oferta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOferta(String value) {
        this.oferta = value;
    }

    /**
     * Obtiene el valor de la propiedad postulante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostulante() {
        return postulante;
    }

    /**
     * Define el valor de la propiedad postulante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostulante(String value) {
        this.postulante = value;
    }

    /**
     * Obtiene el valor de la propiedad stringFecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringFecha() {
        return stringFecha;
    }

    /**
     * Define el valor de la propiedad stringFecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringFecha(String value) {
        this.stringFecha = value;
    }

    /**
     * Obtiene el valor de la propiedad videoid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideoid() {
        return videoid;
    }

    /**
     * Define el valor de la propiedad videoid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideoid(String value) {
        this.videoid = value;
    }

}
