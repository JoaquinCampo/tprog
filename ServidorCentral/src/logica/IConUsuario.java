package logica;

import excepciones.UsuarioCorreoExiste;
import excepciones.UsuarioNickExiste;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import datatypes.DTEmpresa;
import datatypes.DTPaquete;
import datatypes.DTPostulacion;
import datatypes.DTPostulante;
import datatypes.DTUsuario;


public interface IConUsuario {

	public Set<DTPostulante> listarPostulantesConDatos();;
	public Set<String> listarEmpresas();
	public DTUsuario mostrarDatos(String nickname);
	
	
	
	
	// Caso de uso Modificar Usuario
	public Set<String> listarUsuarios();
	public void modificarEmpresa(String nickname, String Nombre, String Apellido, String Descripcion, String Web);
	public void modificarPostulante(String nickname, String Nombre, String Apellido, LocalDate fechaNacimiento, String Nacionalidad);

	// Caso de uso Alta Usuario
	void altaPostulante(String nickname, String Nombre, String Apellido, String contraseña, String correo,
			LocalDate fechaNacimiento, String Nacionalidad) throws UsuarioNickExiste, UsuarioCorreoExiste;
	void altaEmpresa(String nickname, String nombre, String apellido, String contraseña, String correo,
			String descripcion, String web) throws UsuarioNickExiste, UsuarioCorreoExiste;
	
	// Caso de uso Consulta de Usuario
	// public set<String> listarUsuarios();
	public DTUsuario elegirUsuario(String nickname);
	Set<String> listarOfertaLaboral(String empresa);
	void comprarPaquete(String empresa, String paquete, LocalDate fecha);
	void agregarImagen(String nombre, byte[] imagen);
	
	// Devuelve null si no lo encuentra
	public String buscarUsuario(String idUsr);
	
	public boolean compararPassword(String nickname, String password);
	
	public boolean esEmpresa(String nickname);
	public Set<DTPaquete> devolverPaquetesComprados(String nickname);
	
	// ------------ A PARTIR DE ACA SON LAS FUNCIONES AGREGADAS ------------
	
	
	public DTEmpresa getDTEmpresa(String nickEmpresa);
	public DTPostulante getDTPostulante(String nickPostulante);
	public DTPostulacion getPostulacion(String nickPostulante, String nombreOferta);
	
	public void addFav(String nickname, String nomOferta);
	public void removeFav(String nickname, String nomOferta);
	public boolean esFavorita(String nickname, String nomOferta);
	public List<String> getFavorites(String nickname);
 
	public int getCantFollowers(String nickname);
	public List<String> getFollowers(String nickname);
	
	public void addFollower(String follower, String followed);
	public void removeFollower(String follower, String followed);
	public byte[] getImagen(String id);
	List<DTPaquete> PaquetesDisponiblesParaPagar(String nickname,String tipo);
	String ObtenerFechaPostulanteString(String nombreUsuario);

}
