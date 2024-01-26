package logica;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import clases.Empresa;
import clases.Postulante;
import clases.Usuario;
import datatypes.DTEmpresa;
import datatypes.DTPostulante;
import datatypes.DTUsuario;

public class ColeccionUsuario implements IColUsuario{
    private static ColeccionUsuario instance = null;
    private Map<String, Postulante> postulantesPorNickname;
    private Map<String, Postulante> postulantesPorCorreo;
    private Map<String, Empresa> empresasPorNickname;
    private Map<String, Empresa> empresasPorCorreo;
    private Map<String, Usuario> usuariosPorNickname;
    private Map<String, Usuario> usuariosPorCorreo;
    
	public static String quitarTildes(String textoConTildes) {
        // Usamos Normalizer para descomponer y eliminar las tildes
        String textoNormalizado = Normalizer.normalize(textoConTildes, Normalizer.Form.NFD);
        
        // Usamos una expresión regular para eliminar los caracteres no ASCII
        Pattern patron = Pattern.compile("[^\\p{ASCII}]");
        
        // Sustituimos los caracteres acentuados por sus equivalentes sin tilde
        return patron.matcher(textoNormalizado).replaceAll("");
    }

    private ColeccionUsuario() {
        postulantesPorNickname = new HashMap<>();
        empresasPorNickname = new HashMap<>();
        postulantesPorCorreo = new HashMap<>();
        empresasPorCorreo = new HashMap<>();
        usuariosPorNickname = new HashMap<>();
        usuariosPorCorreo = new HashMap<>();
    }

    public static ColeccionUsuario getInstance() {
        if (instance == null) {
            instance = new ColeccionUsuario();
        }
        return instance;
    }

    public void crearPostulante(String nickname, String nombre, String apellido, String contraseña, String correo, LocalDate fechaNacimiento, String nacionalidad) {
    	Postulante postulante = new Postulante(nickname, nombre, apellido, contraseña, correo, fechaNacimiento, nacionalidad);
    	agregarPostulante(postulante);
    }
    
    public void crearEmpresa(String nickname, String nombre, String apellido, String contraseña, String correo, String descripcion, String web) {
    	Empresa emp = new Empresa(nickname, nombre, apellido, contraseña, correo, descripcion, web);
    	agregarEmpresa(emp);
    }
    
    public void agregarPostulante(Postulante postulante) {
    	String clave = postulante.getNickname();
    	clave = quitarTildes(clave);
    	clave = clave.toLowerCase();
    	
    	postulantesPorNickname.put(clave, postulante);
    	postulantesPorCorreo.put(postulante.getCorreo(), postulante);
    	usuariosPorNickname.put(clave, postulante);
    	usuariosPorCorreo.put(postulante.getCorreo(), postulante);
    }
    
    public void agregarEmpresa(Empresa empresa) {
    	String clave = empresa.getNickname();
    	clave = quitarTildes(clave);
    	clave = clave.toLowerCase();
        empresasPorNickname.put(clave, empresa);
        empresasPorCorreo.put(empresa.getCorreo(), empresa);
        usuariosPorNickname.put(clave, empresa);
        usuariosPorCorreo.put(empresa.getCorreo(), empresa);
    }

    public boolean existeUsuarioNickname(String nickname) {
    	String clave = quitarTildes(nickname);
    	clave = clave.toLowerCase();
    	boolean bandera = usuariosPorNickname.containsKey(clave);
    	return bandera;
    }
    
    public boolean existeUsuarioCorreo(String correo) {
    	return usuariosPorCorreo.containsKey(correo);
    }
    
    public Postulante obtenerPostulantePorNickname(String nickname) {
    	nickname = quitarTildes(nickname);
    	nickname = nickname.toLowerCase();
        return postulantesPorNickname.get(nickname);
    }

    public Postulante obtenerPostulantePorCorreo(String correo) {
        return postulantesPorCorreo.get(correo);
    }

    public Empresa obtenerEmpresaPorNickname(String nickname) {
    	String clave = nickname;
    	clave = quitarTildes(clave);
    	clave = clave.toLowerCase();
    	return empresasPorNickname.get(clave);
    }
    
    public Empresa obtenerEmpresaPorCorreo(String correo) {
        return empresasPorCorreo.get(correo);
    }

    public Usuario obtenerUsuarioPorNickname(String nickname) {
    	String clave = nickname;
    	clave = quitarTildes(clave);
    	clave = clave.toLowerCase();
        return usuariosPorNickname.get(clave);
    }

    public Usuario obtenerUsuarioPorCorreo(String correo) {
        return usuariosPorCorreo.get(correo);
    }
    
    public Set<String> listarUsuarios() {
    	Set<String> listaUsuarios = new HashSet<String>();
    	for (String agregar : usuariosPorNickname.keySet()) {
    		listaUsuarios.add(usuariosPorNickname.get(agregar).getNickname());
    	}
    	return listaUsuarios;
    }
    
    public Set<String> listarPostulantes() {
    	Set<String> listaUsuarios = new HashSet<String>();
    	for (String agregar : postulantesPorNickname.keySet()) {
    		listaUsuarios.add(postulantesPorNickname.get(agregar).getNickname());
    	}
    	return listaUsuarios;
    }

    public Set<String> listarEmpresas() {
    	Set<String> listaUsuarios = new HashSet<String>();
    	for (String agregar : empresasPorNickname.keySet()) {
    		listaUsuarios.add(empresasPorNickname.get(agregar).getNickname());
    	}
    	return listaUsuarios;
    }
    
    public DTUsuario getDataUsuario(String nickname) {
    	String clave = nickname;
    	clave = quitarTildes(clave);
    	clave = clave.toLowerCase();
        Usuario usuario = usuariosPorNickname.get(clave);
        if (usuario != null) {
            return usuario.getDTUsuario();
        }
        return null;
    }

    public DTPostulante getDataPostulante(String nickname) {
    	String clave = nickname;
    	clave = quitarTildes(clave);
    	clave = clave.toLowerCase();
        Postulante postulante = postulantesPorNickname.get(clave);
        if (postulante != null) {
            return postulante.getDTPostulante();
        }
        return null;
    }

    public DTEmpresa getDataEmpresa(String nickname) {
    	String clave = nickname;
    	clave = quitarTildes(clave);
    	clave = clave.toLowerCase();
        Empresa empresa = empresasPorNickname.get(clave);
        if (empresa != null) {
            return empresa.getDTEmpresa();
        }
        return null;
    }

	
}
