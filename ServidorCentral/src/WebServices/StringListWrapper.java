package WebServices;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class StringListWrapper {

    private List<String> items;

    public StringListWrapper() {} // JAXB necesita un constructor sin argumentos

    public StringListWrapper(List<String> items) {
        this.items = items;
    }

    @XmlElement(name = "item")
    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}