
package webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtTipoOfertaSetWrapper complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtTipoOfertaSetWrapper">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="tipoOferta" type="{http://WebServices/}dtTipoOferta" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtTipoOfertaSetWrapper", propOrder = {
    "tipoOferta"
})
public class DtTipoOfertaSetWrapper {

    protected List<DtTipoOferta> tipoOferta;

    /**
     * Gets the value of the tipoOferta property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the tipoOferta property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTipoOferta().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtTipoOferta }
     * 
     * 
     * @return
     *     The value of the tipoOferta property.
     */
    public List<DtTipoOferta> getTipoOferta() {
        if (tipoOferta == null) {
            tipoOferta = new ArrayList<>();
        }
        return this.tipoOferta;
    }

}
