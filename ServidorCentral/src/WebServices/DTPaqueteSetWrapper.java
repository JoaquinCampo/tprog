package WebServices;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Set;

import datatypes.DTPaquete;

@XmlRootElement
public class DTPaqueteSetWrapper {
    private Set<DTPaquete> paquetes;

    public DTPaqueteSetWrapper() {}

    public DTPaqueteSetWrapper(Set<DTPaquete> paquetes) {
        this.paquetes = paquetes;
    }

    @XmlElement(name = "paquete")
    public Set<DTPaquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(Set<DTPaquete> paquetes) {
        this.paquetes = paquetes;
    }
}