package WebServices;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

import datatypes.DTPostulante;

@XmlRootElement
public class DTPostulanteListWrapper {

    private List<DTPostulante> postulantes;

    public DTPostulanteListWrapper() {} // JAXB necesita un constructor sin argumentos

    public DTPostulanteListWrapper(List<DTPostulante> postulantes) {
        this.postulantes = postulantes;
    }

    @XmlElement(name = "postulante")
    public List<DTPostulante> getPostulantes() {
        return postulantes;
    }

    public void setPostulantes(List<DTPostulante> postulantes) {
        this.postulantes = postulantes;
    }
}
