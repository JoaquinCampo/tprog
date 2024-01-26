package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Set;
import excepciones.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datatypes.*;
import logica.ControllerFactory;
import logica.IConUsuario;

class Consulta_Usuario_Test {

	private static IConUsuario conUser;
	
	@BeforeAll
	public static void inicializar() {
		ControllerFactory fac = ControllerFactory.getInstance();
		conUser = fac.getIControladorUsuario();
		try {
		conUser.altaPostulante("Homer", "Homero","Simpson","qwerty","home3@gmai.com",LocalDate.now(), "Spriengfield");
		conUser.altaEmpresa("Loli", "Lorena", "Liarte","1234" ,"hola@gmail.com", "Compartida la vida es mas", "https//movistar.com.uy");}
		catch (UsuarioNickExiste | UsuarioCorreoExiste es) {
			System.out.println(es.getMessage());
		}
	}
	
	@Test
	void ConsultaUsuario() {
		
		DTUsuario infoHomero = conUser.elegirUsuario("Homer");
		assertEquals(infoHomero.getNickname(),"Homer");
		assertEquals(infoHomero.getNombre(),"Homero");
		assertEquals(infoHomero.getApellido(),"Simpson");
		assertEquals(infoHomero.getCorreo(),"home3@gmai.com");
		Set<DTPostulante> listaPostulados = conUser.listarPostulantesConDatos();
		assertTrue(!listaPostulados.isEmpty());
		DTEmpresa infoMovistar_Consulta_usuario = (DTEmpresa) conUser.elegirUsuario("Loli");
		DTEmpresa infoMovistar_comparar_COnsulta_Usuario = (DTEmpresa) conUser.elegirUsuario("Loli");
		DTUsuario novalido = conUser.elegirUsuario("noesNickname");
		assertTrue(novalido == null);
	}
}
