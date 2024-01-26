
package webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtPostulanteListWrapper complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtPostulanteListWrapper">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="postulante" type="{http://WebServices/}dtPostulante" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPostulanteListWrapper", propOrder = {
    "postulante"
})
public class DtPostulanteListWrapper {

    protected List<DtPostulante> postulante;

    /**
     * Gets the value of the postulante property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the postulante property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPostulante().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtPostulante }
     * 
     * 
     * @return
     *     The value of the postulante property.
     */
    public List<DtPostulante> getPostulante() {
        if (postulante == null) {
            postulante = new ArrayList<>();
        }
        return this.postulante;
    }

}
