package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datatypes.*;
import logica.IConOferta;
import logica.IConUsuario;
import excepciones.EmpresaNoExiste;
import excepciones.OfertaYaExiste;
import excepciones.TipoOfertaExiste;
import excepciones.TipoOfertaNoExiste;
import excepciones.UsuarioCorreoExiste;
import excepciones.UsuarioNickExiste;
import logica.ControllerFactory;

class Alta_Oferta {

	private static IConOferta conOferta;
	private static IConUsuario conUsuario;
	
	@BeforeAll
	public static void inicializar(){
		ControllerFactory fac = ControllerFactory.getInstance();
		conOferta = fac.getIControladorOferta();
		conUsuario = fac.getIControladorUsuario();
		try {
			conUsuario.altaEmpresa("Loli", "Lorena", "Liarte", "hola@gmail.com", "Movistar", "Compartida la vida es mas", "httts//:movistar.com.uy");
		} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	void AltaTipoOfertaLaboral() {
		try {
			conOferta.altaTipoDeOfertaLaboral("Verde", "poco relevante", 3,20 , 1200,LocalDate.of(2023,7,25));
		} catch (TipoOfertaExiste tp) {
			System.out.println(tp.getMessage());
		}
	}
	
	@Test
	void AltaOfertaLaboral() {
		Set<String> listaKey = new HashSet<String>();
		listaKey.add("XRL8");
		conOferta.altaKeyWord("XRL8");
		try {
			conOferta.altaTipoDeOfertaLaboral("Verde1", "poco relevante", 3,20 , 1200,LocalDate.of(2023,7,25));
			conOferta.altaOfertaLaboral("Loli","Verde1","Telefonista", "Trabajo bien pagado",
					"Lunes a Sabado: 8-16 hs", 1000, "Montevideo", "Oficinas Centro", LocalDate.of(2023,7,25), listaKey);
		} catch (TipoOfertaExiste tp) {
			System.out.println(tp.getMessage());
		} catch (OfertaYaExiste e) {
			System.out.println(e.getMessage());
		} catch (TipoOfertaNoExiste tpne) {
			System.out.println(tpne.getMessage());
		} catch (EmpresaNoExiste em) {
			System.out.println(em.getMessage());
		}
		
		try {
			conOferta.altaOfertaLaboral("LoliVieja","Verde1","TelefonistaVieja", "Trabajo bien pagado",
					"Lunes a Sabado: 8-16 hs", 1000, "Montevideo", "Oficinas Centro", LocalDate.of(2023,7,25), listaKey);
		} catch (OfertaYaExiste e) {
			System.out.println(e.getMessage());
		} catch (TipoOfertaNoExiste tpne) {
			System.out.println(tpne.getMessage());
		} catch (EmpresaNoExiste em) {
			System.out.println(em.getMessage());
		}
		
	DTOferta datoOferta = conOferta.seleccionarOferta("Telefonista");	
	//test de las funciones Equal
	DTOferta datoOferta2 = conOferta.seleccionarOferta("Telefonista");
	assertTrue(datoOferta.isEqual(datoOferta2));
	//segundo intento con el mismo nombre
	try {
		conOferta.altaOfertaLaboral("Loli","Verde1","Telefonista", "Trabajo bien pagado",
				"Lunes a Sabado: 8-16 hs", 1000, "Montevideo", "Oficinas Centro", LocalDate.of(2023,7,25), listaKey);
	}
	  catch (OfertaYaExiste e) {
		System.out.println(e.getMessage());
	} catch (TipoOfertaNoExiste tpne) {
		System.out.println(tpne.getMessage());
	} catch (EmpresaNoExiste em) {
		System.out.println(em.getMessage());
		}
	}
}

