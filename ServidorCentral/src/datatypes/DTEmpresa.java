package datatypes;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class DTEmpresa extends DTUsuario {
    private String web;
    private String descripcion;
    private Set<DTOferta> ofertasLaborales;

    public DTEmpresa(String nickname, String nombre, String apellido, String correo, String web, String descripcion, Set<DTOferta> ofertas, byte[] imagen, int cantFollowers, List<String> followers) {
        super(nickname, nombre, apellido, correo, imagen, cantFollowers, followers);
        this.setWeb(web);
        this.setDescripcion(descripcion);
        this.setOfertasLaborales(ofertas);
    }
    
    public DTEmpresa() {
    	
    }

    public String getWeb() {
        return web;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Set<DTOferta> getOfertasLaborales() {
    	return ofertasLaborales;
    }
    public String getNickname() {
        return super.getNickname();
    }

    public String getNombre() {
        return super.getNombre();
    }

    public String getApellido() {
        return super.getApellido();
    }

    public String getCorreo() {
        return super.getCorreo();
    }
    public byte[] getImagen() {
    	return super.getImagen();
    }
    
    //
    public void setWeb(String web) {
        this.web = web;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setOfertasLaborales(Set<DTOferta> ofertasLaborales) {
    	this.ofertasLaborales = ofertasLaborales;
    }
    
    public void setNickname(String nickname) {
        super.setNickname(nickname);
    }

    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    public void setApellido(String apellido) {
        super.setApellido(apellido);
    }

    public void setCorreo(String correo) {
        super.setCorreo(correo);
    }
    public void setImagen(byte[] imagen) {
    	super.setImagen(imagen);
    }
    
    //funcion para determinar si son iguales
    public boolean isEqual(DTEmpresa comparator) {
    	if (this == comparator) {
    		return true;
    	}
    	if (comparator == null) {
    		return false;
    	}
    	
    	if (!Objects.equals(this.getNombre(), comparator.getNombre())) {
    		return false;
    	}
    	if (!Objects.equals(this.getNickname(), comparator.getNickname())) {
    		return false;
    	}
    	if (!Objects.equals(this.getApellido(), comparator.getApellido())) {
    		return false;
    	}
    	if (!Objects.equals(this.getCorreo(), comparator.getCorreo())) {
    		return false;
    	}
    	if (!Objects.equals(this.getImagen(), comparator.getImagen())) {
    		return false;
    	}
       	if (!Objects.equals(this.getWeb(), comparator.getWeb())) {
    		return false;
    	}
       	if (!Objects.equals(this.getDescripcion(), comparator.getDescripcion())) {
    		return false;
    	}
       	if (!Objects.equals(this.getImagen(), comparator.getImagen())) {
    		return false;
    	}
       	if (!Objects.equals(this.getOfertasLaborales().size(), comparator.getOfertasLaborales().size())) {
    		return false;
    	}
       	else 
       	{
       		Iterator<DTOferta> iterador = this.getOfertasLaborales().iterator();
       		Iterator<DTOferta> comparar = comparator.getOfertasLaborales().iterator();
       		while (iterador.hasNext() && comparar.hasNext()) {
       			DTOferta elementoOriginal = iterador.next();
       			DTOferta elementoComparar = comparar.next();
       			if (elementoOriginal.isEqual(elementoComparar)) {
       				return false;
       			}
       		}
       	}
    	return true;
    }
    
}
