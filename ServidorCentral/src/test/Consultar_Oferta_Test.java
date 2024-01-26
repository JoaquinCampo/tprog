package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datatypes.*;
import logica.ControllerFactory;
import logica.IConOferta;
import logica.IConUsuario;
import excepciones.EmpresaNoExiste;
import excepciones.OfertaYaExiste;
import excepciones.TipoOfertaExiste;
import excepciones.TipoOfertaNoExiste;
import excepciones.UsuarioCorreoExiste;
import excepciones.UsuarioNickExiste;

class Consultar_Oferta_Test {

	private static IConOferta conOferta;
	private static IConUsuario conUsuario;
	
	@BeforeAll
	public static void inicializar(){
		Set<String> listaKey = new HashSet<String>();
		listaKey.add("XRL8");
		ControllerFactory fac = ControllerFactory.getInstance();
		conOferta = fac.getIControladorOferta();
		conUsuario = fac.getIControladorUsuario();
		try {
			conUsuario.altaEmpresa("Loli", "Lorena", "Liarte", "hola@gmail.com", "Movistar", "Compartida la vida es mas", "httts//:movistar.com.uy");
		} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		try {
			conOferta.altaTipoDeOfertaLaboral("Rojo", "poco relevante", 3,20 , 1200,LocalDate.of(2023,7,25));
			conOferta.altaOfertaLaboral("Loli","Rojo","Recepcionista", "Trabajo bien pagado",
					"Lunes a Sabado: 8-16 hs", 1000, "Montevideo", "Oficinas Centro", LocalDate.of(2023,7,25), listaKey);
			conOferta.altaOfertaLaboral("Loli","Rojo","Limpieza", "Trabajo bien pagado",
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
	}
	
	@Test
	void ConsultarOferta() {
		DTOferta datoOferta = conOferta.seleccionarOferta("Recepcionista");
		assertTrue(datoOferta!=null);
		assertTrue(datoOferta.getNombre() == "Recepcionista");
		Set<String> listaUsuario = conUsuario.listarEmpresas();
		String empresaSeleccionada = "Loli";
		Set<String> listaOferta = conUsuario.listarOfertaLaboral(empresaSeleccionada);
		assertTrue(!listaOferta.isEmpty());
	}

}
