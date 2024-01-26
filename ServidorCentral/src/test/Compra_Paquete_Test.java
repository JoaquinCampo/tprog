package test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.ControllerFactory;
import logica.IConOferta;
import datatypes.*;
import logica.IConPaquete;
import excepciones.PaqueteYaExiste;
import excepciones.TipoOfertaExiste;
import excepciones.TipoOfertaNoExiste;
import excepciones.UsuarioCorreoExiste;
import excepciones.UsuarioNickExiste;
import logica.IConUsuario;


class Compra_Paquete_Test {

	private static IConPaquete conPaquete;
	private static IConOferta conOferta;
	private static IConUsuario conUsuario;
	
	@BeforeAll
	public static void inicializar(){
		ControllerFactory fac = ControllerFactory.getInstance();
		conPaquete = fac.getIControladorPaquete();
		conOferta = fac.getIControladorOferta();
		conUsuario = fac.getIControladorUsuario();
		try {
			conPaquete.crearPaquete("paqueteVariopinto3","contendra tipo azulzafiroy rojorubi", 11, (float) 0.23, LocalDate.of(2023,11,11));
			conUsuario.altaEmpresa("fedeT","Fede","T","onePice","fedeT@email.com","me copa lufi","web.com");
			
			conPaquete.crearPaquete("paquetevencido","lorem", 1,(float) 0.20, LocalDate.of(2023,11,11));
		} catch (PaqueteYaExiste | UsuarioNickExiste | UsuarioCorreoExiste e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	void test() {
		conUsuario.comprarPaquete("fedeT", "paqueteVariopinto3", LocalDate.of(2023, 10, 7));
		
		//no deve aparecer en el listado
		conUsuario.comprarPaquete("fedeT", "paquetevencido", LocalDate.of(2023, 9, 7));
		
		Set<DTPaquete> paquetes = conUsuario.devolverPaquetesComprados("fedeT");
		boolean encontre1 = false;
		boolean encontre2 = false;
		for(DTPaquete paquete : paquetes) {
			if(!encontre1) {
				encontre1 = (paquete.getNombre() == "paqueteVariopinto1");
			}
			if(!encontre2) {
				encontre2 = (paquete.getNombre() == "paquetevencido");
			}
		}
		assertTrue(!encontre1 | encontre2);
	}

}
