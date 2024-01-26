package WebServices;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

import datatypes.DTPostulacion;

@XmlRootElement
public class DTPostulacionListWrapper {
    
    private List<DTPostulacion> postulaciones;

    // JAXB necesita un constructor sin argumentos
    public DTPostulacionListWrapper() {}

    public DTPostulacionListWrapper(List<DTPostulacion> postulaciones) {
        this.postulaciones = postulaciones;
    }

    @XmlElement(name = "postulacion")
    public List<DTPostulacion> getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(List<DTPostulacion> postulaciones) {
        this.postulaciones = postulaciones;
    }
}