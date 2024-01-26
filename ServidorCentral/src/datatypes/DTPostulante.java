package datatypes;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class DTPostulante extends DTUsuario {
    private String nacionalidad;
    private LocalDate fechaNacimiento;
    private Set<DTOferta> ofDepost;
    private Set<String> favoritas;

    public DTPostulante(String nickname, String nombre, String apellido, String correo, LocalDate localDate, String nacionalidad, Set<DTOferta> of_de_, byte[] imagen, Set<String> favs, int cantFollowers, List<String> followers) {
        super(nickname, nombre, apellido, correo, imagen, cantFollowers, followers);
        this.setNacionalidad(nacionalidad);
        this.setFechaNacimiento(localDate);
        this.setOfertas(of_de_);
        this.setFavoritas(favs);
    }
    
    public DTPostulante() {}

    public Set<String> getFavoritas() {
    	return this.favoritas;
    }
    
    public void setFavoritas(Set<String> favs) {
    	this.favoritas = favs;
    }
    
    public String getNacionalidad() {
        return nacionalidad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public Set<DTOferta> getOfertas() {
    	return ofDepost;
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
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setFechaNacimiento(LocalDate fecha) {
        this.fechaNacimiento = fecha;
    }
    
    public void setOfertas(Set<DTOferta> ofDepost) {
    	this.ofDepost = ofDepost;
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

    public boolean isEqual(DTPostulante comparator) {
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
    	if (!Objects.equals(this.getNacionalidad(), comparator.getNacionalidad())) {
    		return false;
    	}
       	if (!Objects.equals(this.getFechaNacimiento(), comparator.getFechaNacimiento())) {
    		return false;
    	}
       	if (!Objects.equals(this.getOfertas().size(), comparator.getOfertas().size())) {
    		return false;
    	}
       	else 
       	{
       		Iterator<DTOferta> iterador = this.getOfertas().iterator();
       		Iterator<DTOferta> comparar = comparator.getOfertas().iterator();
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
