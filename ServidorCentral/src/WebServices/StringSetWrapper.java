package WebServices;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Set;

@XmlRootElement
public class StringSetWrapper {
    private Set<String> items;

    public StringSetWrapper() {}

    public StringSetWrapper(Set<String> items) {
        this.items = items;
    }

    @XmlElement(name = "item")
    public Set<String> getItems() {
        return items;
    }

    public void setItems(Set<String> items) {
        this.items = items;
    }
}