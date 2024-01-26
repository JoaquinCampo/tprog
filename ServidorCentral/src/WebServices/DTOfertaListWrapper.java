package WebServices;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

import datatypes.DTOferta;

@XmlRootElement
public class DTOfertaListWrapper {
    
    private List<DTOferta> ofertas;

    // JAXB necesita un constructor sin argumentos
    public DTOfertaListWrapper() {}

    public DTOfertaListWrapper(List<DTOferta> ofertas) {
        this.ofertas = ofertas;
    }

    @XmlElement(name = "oferta")
    public List<DTOferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<DTOferta> ofertas) {
        this.ofertas = ofertas;
    }
}