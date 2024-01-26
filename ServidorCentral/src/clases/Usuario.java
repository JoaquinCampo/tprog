package clases;

import java.util.ArrayList;
import java.util.List;

import datatypes.DTUsuario;

public abstract class Usuario {
    private String nickname;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasenia;
    private byte[] imagen;
    private int cantFollowers;
    private List<String> followers;

    public Usuario(String nickname, String nombre, String apellido, String password, String correo) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasenia=password;
        this.cantFollowers = 0;
        this.followers = new ArrayList<>();
    }

    public void setCantFollowers(int cant) {
    	this.cantFollowers = cant;
    }
    
    public int getCantFollowers() {
    	return this.cantFollowers;
    }
    
    public List<String> getFollowers() {
    	return this.followers;
    }
    
    public void setFollowers(List<String> followers) {
    	this.followers = followers;
    }

    public void addFollower(String nick) {
    	this.followers.add(nick);
    }
    
    public void removeFollower(String nick) {
    	this.followers.remove(nick);
    }
    
    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String password){
        this.contrasenia = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void setImagen(byte[] imagen) {
    	this.imagen = imagen;
    }
    
    public byte[] getImagen() {
    	return this.imagen;
    }
    
    public DTUsuario getDTUsuario() {
        if (this instanceof Postulante) {
            Postulante postulante = (Postulante) this;
            return postulante.getDTPostulante();
        } else if (this instanceof Empresa) {
            Empresa empresa = (Empresa) this;
            return empresa.getDTEmpresa();
        }
        return null;
    }

    public abstract void function();
}