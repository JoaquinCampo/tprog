package datatypes;

import java.util.List;
import java.util.Objects;

public class DTUsuario {
    private String nickname;
    private String nombre;
    private String apellido;
    private String correo;
	private byte[] imagen;
	private int cantFollwers;
	private List<String> followers;

    public DTUsuario(String nickname, String nombre, String apellido, String correo, byte[] imagen, int cantFollowers, List<String> followers) {
        this.setNickname(nickname);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCorreo(correo);
        this.setImagen(imagen);
        this.setCantFollowers(cantFollowers);
        this.setFollowers(followers);
    }
    
    public DTUsuario() {}

    public int getCantFollowers() {
    	return this.cantFollwers;
    }
    
    public void setCantFollowers(int cant) {
    	this.cantFollwers = cant;
    }
    
    public List<String> getFollowers() {
    	return this.followers;
    }
    
    public void setFollowers(List<String> followers) {
    	this.followers = followers;
    }
    
    public String getNickname() {
        return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }
    public byte[] getImagen() {
    	return this.imagen;
    }
    
    //
    public void setNickname(String nickname) {
        this.nickname= nickname;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setImagen(byte[] imagen) {
    	this.imagen = imagen;
    }
    
    public boolean isEqual(DTUsuario comparator) {
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
    	return true;
    }

}
