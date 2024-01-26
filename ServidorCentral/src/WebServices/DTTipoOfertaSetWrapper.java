package WebServices;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Set;

import datatypes.DTTipoOferta;


@XmlRootElement
public class DTTipoOfertaSetWrapper {
    private Set<DTTipoOferta> tipoOfertas;

    public DTTipoOfertaSetWrapper() {}

    public DTTipoOfertaSetWrapper(Set<DTTipoOferta> tipoOfertas) {
        this.tipoOfertas = tipoOfertas;
    }

    @XmlElement(name = "tipoOferta")
    public Set<DTTipoOferta> getTipoOfertas() {
        return tipoOfertas;
    }

    public void setTipoOfertas(Set<DTTipoOferta> tipoOfertas) {
        this.tipoOfertas = tipoOfertas;
    }
}