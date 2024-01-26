package logica;

import clases.Empresa;
import clases.Postulante;
import clases.Usuario;
import datatypes.DTEmpresa;
import datatypes.DTPostulante;
import datatypes.DTUsuario;

import java.time.LocalDate;
import java.util.Set;

public interface IColUsuario {
    void agregarPostulante(Postulante postulante);
    void agregarEmpresa(Empresa empresa);

    public Postulante obtenerPostulantePorNickname(String nickname);
    public Postulante obtenerPostulantePorCorreo(String correo);
    public Empresa obtenerEmpresaPorNickname(String nickname);
    public Empresa obtenerEmpresaPorCorreo(String correo);
    public Usuario obtenerUsuarioPorNickname(String nickname);
    public Usuario obtenerUsuarioPorCorreo(String correo);

    public Set<String> listarUsuarios();
    public Set<String> listarPostulantes();
    public Set<String> listarEmpresas();

    public DTUsuario getDataUsuario(String nickname);
    public DTPostulante getDataPostulante(String nickname);
    public DTEmpresa getDataEmpresa(String nickname);
    
    public boolean existeUsuarioNickname(String nickname);
    public boolean existeUsuarioCorreo(String correo);
    public void crearPostulante(String nickname, String nombre, String apellido, String contraseña, String correo, LocalDate fechaNacimiento, String nacionalidad);
    public void crearEmpresa(String nickname, String nombre, String apellido, String contraseña, String correo, String descripcion, String web);

	
}
