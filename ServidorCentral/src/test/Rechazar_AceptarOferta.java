package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;

import excepciones.EmpresaNoExiste;
import excepciones.OfertaYaExiste;
import excepciones.TipoOfertaExiste;
import excepciones.TipoOfertaNoExiste;
import excepciones.UsuarioCorreoExiste;
import excepciones.UsuarioNickExiste;
import logica.ControllerFactory;
import logica.IConUsuario;
import logica.IConOferta;

import org.junit.jupiter.api.Test;

import datatypes.DTOferta;
import datatypes.Estados;

class Rechazar_AceptarOferta {
	
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
		try {
			conOferta.altaTipoDeOfertaLaboral("Verde", "poco relevante", 3,20 , 1200,LocalDate.of(2023,7,25));
		} catch (TipoOfertaExiste tp) {
			System.out.println(tp.getMessage());
			}
		Set<String> listaKey = new HashSet<String>();
		listaKey.add("XRL8");
		conOferta.altaKeyWord("XRL8");
		try {
			conOferta.altaTipoDeOfertaLaboral("Verde1", "poco relevante", 3,20 , 1200,LocalDate.of(2023,7,25));
			conOferta.altaOfertaLaboral("Loli","Verde1","Telefonista", "Trabajo bien pagado",
					"Lunes a Sabado: 8-16 hs", 1000, "Montevideo", "Oficinas Centro", LocalDate.of(2023,7,25), listaKey);
			conOferta.altaOfertaLaboral("Loli","Verde1","Recepcionista", "Trabajo bien pagado",
					"Lunes a Sabado: 8-16 hs", 1000, "Montevideo", "Oficinas Centro", LocalDate.of(2023,7,25), listaKey);
			conOferta.altaOfertaLaboral("Loli","Verde1","taxista_Finalizar", "Trabajo bien pagado",
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
	void AceptarOferta() {
		DTOferta oferta = conOferta.seleccionarOferta("Telefonista");
		Estados estado = oferta.getEstado();
		assertTrue(estado == Estados.Ingresada);
		List<DTOferta> ofertasIngresadas = conOferta.getOfertasIngresadas();
		boolean encontre = false;
		for(DTOferta actual1 : ofertasIngresadas) {
			if(actual1.getNombre() == "Telefonista") {
				encontre = true;
				break;
			}
		}
		assertTrue(encontre);
		conOferta.aceptarOferta("Telefonista");
		DTOferta ofertaActualizada = conOferta.seleccionarOferta("Telefonista");
		estado = ofertaActualizada.getEstado();
		assertTrue(estado == Estados.Confirmada);
		List<DTOferta> ofertas = conOferta.getAllofertas();
		encontre = false;
		for(DTOferta actual2 : ofertas) {
			if(actual2.getNombre() == "Telefonista") {
				encontre = true;
				break;
			}
		}
		assertTrue(encontre);
		conOferta.finalizarOferta("Telefonista");
		List<DTOferta> ofertas_finalizadas = conOferta.getOfertasFinalisadas();
		assertTrue(!ofertas_finalizadas.isEmpty());
		List<DTOferta> ofertas_vencidas = conOferta.getOfertasVencidasConfirmadas("Loli");
		assertTrue(ofertas_vencidas.isEmpty());
		conOferta.getCantFavs("Telefonista");
		conOferta.getImagen("Telefonista");
		List<DTOferta> ofertas_validas = conOferta.getOfertasValidas();
		assertTrue(ofertas_validas.isEmpty());
	}
	
	@Test
	void RechazarOferta() {
		conOferta.rechazarOferta("Recepcionista");
		DTOferta ofertarechazada = conOferta.seleccionarOferta("Recepcionista");
		Estados estado = ofertarechazada.getEstado();
		assertTrue(estado == Estados.Rechazada);
		List<DTOferta> ofertasRechazadas = conOferta.getOfertasRechazadas();
		assertTrue(!ofertasRechazadas.isEmpty());
	}
	
}
