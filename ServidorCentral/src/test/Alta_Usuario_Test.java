package test;

import static org.junit.jupiter.api.Assertions.*;
import logica.IConUsuario;
import logica.ControllerFactory;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datatypes.*;
import excepciones.UsuarioCorreoExiste;
import excepciones.UsuarioNickExiste;

class Alta_Usuario_Test {
	
	private static IConUsuario conUser;
	
	@BeforeAll
	public static void inicializar() {
		ControllerFactory factoria = ControllerFactory.getInstance();
		conUser = factoria.getIControladorUsuario();
	}
	

	@Test
	void AgregarPostulante() {
		String nickname1 = "Igar";
		String nombre1 = "Ignacio";
		String Apellido1 = "Araujo";
		String corre1 = "Ignacio@gmail.com";
		String nacionalidad = "Uruguay";
		LocalDate fecha = LocalDate.now();
		String contra = "1234";
		try {
			conUser.altaPostulante(nickname1, nombre1, Apellido1,contra, corre1, fecha, nacionalidad);
		} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		Set<String> listaUsuario = conUser.listarUsuarios();
		assertTrue(listaUsuario.contains("Igar"));
		assertTrue(conUser.compararPassword(nickname1, contra));
		assertTrue(!conUser.compararPassword(nickname1, "12345"));
	}
	//(String nickname, String nombre, String apellido,String contraseña, String correo, String descripcion, String web)
	@Test
	void AgregarEmpresa() {
		String nickname1 = "Loli";
		String nombre1 = "Lorena";
		String apellido1 = "Liarte";
		String correo1 = "hola@gmail.com";
		String descripcion1 = "Laura es trabajadora de la empresa Movistar";
		String url = "https//Movistar.com.uy";
		String contra = "qwerty";
		try {
			conUser.altaEmpresa(nickname1, nombre1, apellido1,contra,correo1, descripcion1,url);
		} catch (UsuarioNickExiste e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (UsuarioCorreoExiste e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		Set<String> listaUsuario = conUser.listarEmpresas();
		assertTrue(!listaUsuario.isEmpty());
	}
	
	@Test
	void AgregarMultiplesUsuarios() {
		try {
			conUser.altaPostulante("nk1", "nm1", "ap1","qwerty" ,"ce1", LocalDate.of(1100,11,11), "Uy");
		} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		try {
			conUser.altaPostulante("nk2", "nm2", "ap2","qwerty" , "ce2", LocalDate.of(1200,11,11), "Uy");
		} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		try {
			conUser.altaPostulante("nk3", "nm3", "ap3","qwerty" , "ce3", LocalDate.of(1300,11,11), "Uy");
		} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		try {
			conUser.altaEmpresa("nk4", "nm4", "ap4","qwerty" , "ce4", "dc1", "url1");
		} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		try {
			conUser.altaEmpresa("nk5", "nm5", "ap5","qwerty" , "ce5", "dc5", "url5");
		} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		//estos no deberian crearse
		
		try {
			conUser.altaPostulante("nk1", "nm1", "ap1","qwerty" , "ce7", LocalDate.of(1100,11,11), "Uy");
		} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			conUser.altaPostulante("nk9", "nm1", "ap1","qwerty" , "ce1", LocalDate.of(1100,11,11), "Uy");
		} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			conUser.altaEmpresa("nk1", "nm4", "ap4","qwerty" , "ce9", "dc1", "url1");
		} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			conUser.altaEmpresa("nk7", "nm4", "ap4","qwerty" , "ce4", "dc1", "url1");
		} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		Set<String> listaUsuario = conUser.listarUsuarios();
		
		assertTrue(!listaUsuario.isEmpty());
		assertTrue(conUser.elegirUsuario("nk1").getCorreo() == "ce1");
		assertTrue(conUser.elegirUsuario("nk2").getCorreo() == "ce2");
		assertTrue(conUser.elegirUsuario("nk3").getCorreo() == "ce3");
		assertTrue(conUser.elegirUsuario("nk4").getCorreo() == "ce4");
		assertTrue(conUser.elegirUsuario("nk5").getCorreo() == "ce5");
		
		
		assertTrue(conUser.esEmpresa("nk4") == true);
		assertTrue(conUser.getDTEmpresa("nk4").getCorreo() == "ce4");
		
		
		assertTrue(conUser.getDTPostulante("nk1").getCorreo() == "ce1");
		
		assertTrue(conUser.buscarUsuario("nk1") == "nk1");
		assertTrue(conUser.buscarUsuario("ce1") == "nk1");
		}
	
}
