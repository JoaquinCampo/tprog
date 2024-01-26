package logica;

import clases.Empresa;
import clases.Oferta;
import clases.Paquete;
import clases.Postulacion;
import clases.Postulante;
import clases.Usuario;
import datatypes.DTEmpresa;
import datatypes.DTPaquete;
import datatypes.DTPostulacion;
import datatypes.DTPostulante;
import datatypes.DTUsuario;
import excepciones.UsuarioCorreoExiste;
import excepciones.UsuarioNickExiste;

import java.util.Set;
import java.util.regex.Pattern;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ControladorUsuario implements IConUsuario{
	
	
	public static String quitarTildes(String textoConTildes) {
        // Usamos Normalizer para descomponer y eliminar las tildes
        String textoNormalizado = Normalizer.normalize(textoConTildes, Normalizer.Form.NFD);
        
        // Usamos una expresión regular para eliminar los caracteres no ASCII
        Pattern patron = Pattern.compile("[^\\p{ASCII}]");
        
        // Sustituimos los caracteres acentuados por sus equivalentes sin tilde
        return patron.matcher(textoNormalizado).replaceAll("");
    }

	@Override
    public Set<DTPostulante> listarPostulantesConDatos() {
        CollectionFactory fac = CollectionFactory.getInstance();
        IColUsuario col = fac.getIColUsuario();

        Set<DTPostulante> res = new HashSet<>();
        Set<String> postulantesNicks = col.listarPostulantes();

        for (String nick : postulantesNicks) {
            Postulante postu = col.obtenerPostulantePorNickname(nick);
            DTPostulante pos = postu.getDTPostulante();
            res.add(pos);
        }

        return res;
    }
		
	@Override
	public Set<String> listarOfertaLaboral(String empresa) {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColUsuario col = fac.getIColUsuario();
		
		Empresa emp = col.obtenerEmpresaPorNickname(empresa);
		return emp.obtenerOfertas();
	}
	
	@Override
	public Set<String> listarEmpresas(){
		CollectionFactory fac = CollectionFactory.getInstance();
		IColUsuario col = fac.getIColUsuario();
		return col.listarEmpresas();
	}
	
	@Override
	public DTUsuario mostrarDatos(String nickname) {
		CollectionFactory fac = CollectionFactory.getInstance();
        IColUsuario col = fac.getIColUsuario();
        
        return col.getDataUsuario(nickname);
        		
	}
	
	//Caso de Uso Modificar Datos
	
	@Override
	public Set<String> listarUsuarios() {
		CollectionFactory fac = CollectionFactory.getInstance();
        IColUsuario col = fac.getIColUsuario();
        
        return col.listarUsuarios();
	}
	
	@Override
	public void modificarEmpresa(String nickname, String Nombre, String Apellido, String Descripcion, String Web) {
		CollectionFactory fac = CollectionFactory.getInstance();
        IColUsuario col = fac.getIColUsuario();
        
        Empresa emp = col.obtenerEmpresaPorNickname(nickname);
        
        emp.setNombre(Nombre);
        emp.setApellido(Apellido);
        emp.setDescripcion(Descripcion);
        emp.setWeb(Web);
	}
	
	@Override
	public void modificarPostulante(String nickname, String Nombre, String Apellido, LocalDate fechaNacimiento, String Nacionalidad) {
		CollectionFactory fac = CollectionFactory.getInstance();
        IColUsuario col = fac.getIColUsuario();
        
        Postulante post = col.obtenerPostulantePorNickname(nickname);
        
        post.setNombre(Nombre);
        post.setApellido(Apellido);
        post.setFechaNacimiento(fechaNacimiento);
        post.setNacionalidad(Nacionalidad);
	}

	// Caso de uso Alta de Usuario
	@Override
	public void altaPostulante(String nickname, String Nombre, String Apellido, String contraseña, String correo, LocalDate fechaNacimiento, String Nacionalidad)throws UsuarioNickExiste, UsuarioCorreoExiste {
		CollectionFactory fac = CollectionFactory.getInstance();
        IColUsuario col = fac.getIColUsuario();
        
        nickname = nickname.replaceAll("\\s", "");
                
        boolean existe = col.existeUsuarioNickname(nickname) || col.existeUsuarioCorreo(correo);
        boolean excep1 = col.existeUsuarioNickname(nickname);
        if (!existe) {
        	col.crearPostulante(nickname, Nombre, Apellido, contraseña, correo, fechaNacimiento, Nacionalidad);
        }
        else {
        	if (excep1)
        	throw new UsuarioNickExiste("El nickname " + nickname + " ya existe en el sistema");
        	else
        	throw new UsuarioCorreoExiste("El correo " + correo + " ya existe en el sistema"); 
        }
	}
	
	
	@Override
	public void altaEmpresa(String nickname, String nombre, String apellido, String contraseña,  String correo,  String descripcion, String web) throws UsuarioNickExiste, UsuarioCorreoExiste {
		CollectionFactory fac = CollectionFactory.getInstance();
        IColUsuario col = fac.getIColUsuario();
        
        nickname = nickname.replaceAll("\\s", "");
        String check = quitarTildes(nickname);
        check = check.toLowerCase();
        
        boolean existe = col.existeUsuarioNickname(check) || col.existeUsuarioCorreo(correo);
        boolean excep1 = col.existeUsuarioNickname(check);
        
        if (!existe) {
        	col.crearEmpresa(nickname, nombre, apellido, contraseña, correo, descripcion, web);
        }
        else {
        	if (excep1)
        	throw new UsuarioNickExiste("El nickname " + nickname + " ya existe en el sistema");
        	else
        	throw new UsuarioCorreoExiste("El correo " + correo + " ya existe en el sistema");
}
	}
	
	// Caso de uso Consulta de Usuario

	@Override
	public DTUsuario elegirUsuario(String nickname) {
		CollectionFactory fac = CollectionFactory.getInstance();
        IColUsuario col = fac.getIColUsuario();
        
        return col.getDataUsuario(nickname);
	}
	//compra de paquetes
	@Override
	public void comprarPaquete(String empresa, String paquete, LocalDate fecha) {
		CollectionFactory fac = CollectionFactory.getInstance();
        IColUsuario col = fac.getIColUsuario();
        IColPaquete colpaqute = fac.getIColPaquete();
        Empresa emp = col.obtenerEmpresaPorNickname(empresa);
        Paquete paq = colpaqute.getPaquete(paquete);
        emp.comprarPaquete(fecha, paq);
	}
	
	@Override
	public void agregarImagen(String nombre, byte[] imagen) {
		CollectionFactory fac = CollectionFactory.getInstance();
		IColUsuario col = fac.getIColUsuario();
		Usuario usr = col.obtenerUsuarioPorNickname(nombre);
		usr.setImagen(imagen);
	}
	
	@Override
	public String buscarUsuario(String idUsr) {
		IColUsuario colUsr = CollectionFactory.getInstance().getIColUsuario();
		String nickname = "null"; // cambie para probar

		if (colUsr.existeUsuarioNickname(idUsr))
			nickname = colUsr.obtenerUsuarioPorNickname(idUsr).getNickname();
		else if (colUsr.existeUsuarioCorreo(idUsr))
				nickname = colUsr.obtenerUsuarioPorCorreo(idUsr).getNickname();
		return nickname;
	}
	
	@Override
	public boolean compararPassword(String nickname, String password) {
		IColUsuario colUsr = CollectionFactory.getInstance().getIColUsuario();
		String realPassword = colUsr.obtenerUsuarioPorNickname(nickname).getContrasenia();
		if (password.equals(realPassword))
			return true;
		else return false;
	}
	
	@Override
	public boolean esEmpresa(String nickname) {
		IColUsuario colUsr = CollectionFactory.getInstance().getIColUsuario();
		Usuario usuario = colUsr.obtenerUsuarioPorNickname(nickname);
		
		if (usuario instanceof Postulante) {
            return false;
        } else return true;
	}
	
	@Override
	public Set<DTPaquete> devolverPaquetesComprados(String nickname){
		CollectionFactory factory =  CollectionFactory.getInstance();
		IColUsuario colUsers = factory.getIColUsuario();
		Empresa empresa = colUsers.obtenerEmpresaPorNickname(nickname);
		Set<DTPaquete> resu = empresa.getColeccionPaquetes();
		return resu;
	}
	
	// ------------ A PARTIR DE ACA SON LAS FUNCIONES AGREGADAS ------------

	@Override
	public DTEmpresa getDTEmpresa(String nickEmpresa) {
		CollectionFactory factory =  CollectionFactory.getInstance();
		IColUsuario colUsers = factory.getIColUsuario();
		return colUsers.getDataEmpresa(nickEmpresa);
	}
	
	@Override
	public DTPostulante getDTPostulante(String nickPostulante) {
		CollectionFactory factory =  CollectionFactory.getInstance();
		IColUsuario colUsers = factory.getIColUsuario();
		return colUsers.getDataPostulante(nickPostulante);
	}
	
	@Override
	public DTPostulacion getPostulacion(String nickPostulante, String nombreOferta) {
		CollectionFactory factory =  CollectionFactory.getInstance();
		IColUsuario colUsers = factory.getIColUsuario();
		IColOferta colOferta = factory.getIColOferta();
		
		Oferta oferta = colOferta.buscarOferta(nombreOferta);
		Postulante postulante = colUsers.obtenerPostulantePorNickname(nickPostulante);
		
		Set<Postulacion> postulaciones = postulante.getPostulaciones();
		DTPostulacion res = null;
		for (Postulacion postu : postulaciones) {
			if (postu.getOferta().getNombre().equals(nombreOferta)) {
				res = new DTPostulacion(postulante.getNickname(), oferta.getNombre(), postu.getFecha(), postu.getCv(), postu.getDescripcion(), postu.getVideoid());
				Set<String> empresas = colUsers.listarEmpresas();
				for (String emp : empresas) {
					Empresa empresaClass = colUsers.obtenerEmpresaPorNickname(emp);
					if (empresaClass.getNombresOfertas().contains(nombreOferta)) {
						res.setEmpresa(emp);
						break;
					}
				}
 				break;
			}
		}
		return res;
	
	}
	
	@Override
	public void addFav(String nickname, String nomOferta) {
		CollectionFactory factory =  CollectionFactory.getInstance();
		IColUsuario colUsers = factory.getIColUsuario();
		Postulante postulante = colUsers.obtenerPostulantePorNickname(nickname);
		System.out.println("se agrego la oferta a los favoritos de:_"+nickname);

		postulante.addFavorita(nomOferta);
		
	}

	@Override
	public void removeFav(String nickname, String nomOferta) {
		CollectionFactory factory =  CollectionFactory.getInstance();
		IColUsuario colUsers = factory.getIColUsuario();
		Postulante postulante = colUsers.obtenerPostulantePorNickname(nickname);
		System.out.println("se quito la oferta de los favoritos de:_"+nickname);
		postulante.removeFavorita(nomOferta);
		
	}
	
	@Override
	public boolean esFavorita(String nickname, String nomOferta) {
		CollectionFactory factory =  CollectionFactory.getInstance();
		IColUsuario colUsers = factory.getIColUsuario();
		Postulante postulante = colUsers.obtenerPostulantePorNickname(nickname);
		Set<String> ofertas = postulante.getFavoritas();
		return ofertas.contains(nomOferta);
	}
	
	@Override
	public List<String> getFavorites(String nickname) {
		CollectionFactory factory =  CollectionFactory.getInstance();
		IColUsuario colUsers = factory.getIColUsuario();
		Postulante postulante = colUsers.obtenerPostulantePorNickname(nickname);
		Set<String> ofertas = postulante.getFavoritas();
		List<String> res = new ArrayList<>();
		for (String of : ofertas) {
			res.add(of);
		}
		return res;
	}
	@Override
	public int getCantFollowers(String nickname) {
		IColUsuario colUsr = CollectionFactory.getInstance().getIColUsuario();
		Usuario usuario = colUsr.obtenerUsuarioPorNickname(nickname);
		return usuario.getCantFollowers();
	}
	
	@Override
	public List<String> getFollowers(String nickname){
		IColUsuario colUsr = CollectionFactory.getInstance().getIColUsuario();
		Usuario usuario = colUsr.obtenerUsuarioPorNickname(nickname);
		return usuario.getFollowers();
	}
	@Override
	public void addFollower(String follower, String followed) {
		IColUsuario colUsr = CollectionFactory.getInstance().getIColUsuario();
		Usuario usuario = colUsr.obtenerUsuarioPorNickname(followed);
		usuario.addFollower(follower);
	}
	
	@Override
	public void removeFollower(String follower, String followed) {
		IColUsuario colUsr = CollectionFactory.getInstance().getIColUsuario();
		Usuario usuario = colUsr.obtenerUsuarioPorNickname(followed);
		usuario.removeFollower(follower);
	}
	
	@Override
	public byte[] getImagen(String id) {
		IColUsuario colUsr = CollectionFactory.getInstance().getIColUsuario();
		Usuario usr = colUsr.obtenerUsuarioPorNickname(id);
		return usr.getImagen();
	}
	
	@Override
	public List<DTPaquete> PaquetesDisponiblesParaPagar(String nickname,String tipo)
	{
		IColUsuario colUsr = CollectionFactory.getInstance().getIColUsuario();
		Empresa empresa = colUsr.obtenerEmpresaPorNickname(nickname);
		Set<DTPaquete> conjuntoPaquetes = empresa.getPaquetesDisponiblesParaPagar(tipo);
		List<DTPaquete> resu = new ArrayList<DTPaquete>(conjuntoPaquetes);
		return resu;
	}
	
	@Override
	public String ObtenerFechaPostulanteString(String nombreUsuario) {
		IColUsuario colUsr = CollectionFactory.getInstance().getIColUsuario();
		Postulante Postulante = colUsr.obtenerPostulantePorNickname(nombreUsuario);
		LocalDate fecha = Postulante.getFechaNacimiento();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaFormateada = fecha.format(formatter);
		return fechaFormateada;
	}
}
