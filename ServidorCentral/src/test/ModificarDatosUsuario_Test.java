package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datatypes.*;
import excepciones.UsuarioCorreoExiste;
import excepciones.UsuarioNickExiste;
import logica.ControllerFactory;
import logica.IConUsuario;

class ModificarDatosUsuario_Test {

	private static IConUsuario conUser;
	
	@BeforeAll
	public static void inicializar() {
		ControllerFactory fac = ControllerFactory.getInstance();
		conUser = fac.getIControladorUsuario();
		try {
			conUser.altaPostulante("FedeT14", "Federico","Tuna","onepice","FTMG@gmai.com",LocalDate.now(), "Uruguay");
		} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		try {
			conUser.altaEmpresa("Antilur", "Roney", "Iht","roney", "roney@gmail.com", "Levante cajas por 5 pesos", "https//antilur.com.uy");
		} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	void ModifcarPosulanteYEmpresa() {
		DTUsuario datosprevios = conUser.elegirUsuario("FedeT14");
		DTUsuario datosempresaprevio = conUser.elegirUsuario("Antilur");
		conUser.modificarPostulante(datosprevios.getNickname(), "Marge", "Simpson",LocalDate.of(2121, 11, 21) ,"Spriengfield");
		conUser.modificarEmpresa(datosempresaprevio.getNickname(),"Laura", "Lorenzo", "Es simplre es Claro", "htttps//claro.com.uy");
		DTUsuario datosnuevo = conUser.elegirUsuario("FedeT14");
		DTUsuario datosempresanuevo = conUser.elegirUsuario("Antilur");
		assertTrue(datosempresaprevio.getNombre() != datosempresanuevo.getNombre());
		assertTrue(datosprevios.getNombre() != datosnuevo.getNombre());
	}
}
