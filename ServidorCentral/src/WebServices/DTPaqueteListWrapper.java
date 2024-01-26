package WebServices;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

import datatypes.DTPaquete;

@XmlRootElement
public class DTPaqueteListWrapper {

    private List<DTPaquete> paquetes;

    public DTPaqueteListWrapper() {} // JAXB necesita un constructor sin argumentos

    public DTPaqueteListWrapper(List<DTPaquete> paquetes) {
        this.paquetes = paquetes;
    }

    @XmlElement(name = "paquete")
    public List<DTPaquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<DTPaquete> paquetes) {
        this.paquetes = paquetes;
    }
}
