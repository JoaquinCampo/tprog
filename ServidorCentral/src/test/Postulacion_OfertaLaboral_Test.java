package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datatypes.*;
import logica.ControllerFactory;
import logica.IConOferta;
import logica.IConUsuario;
import excepciones.EmpresaNoExiste;
import excepciones.OfertaNoExiste;
import excepciones.OfertaNoValidaParaEsaFecha;
import excepciones.OfertaYaExiste;
import excepciones.PostulacionYaExiste;
import excepciones.TipoOfertaExiste;
import excepciones.TipoOfertaNoExiste;
import excepciones.UsuarioCorreoExiste;
import excepciones.UsuarioNickExiste;
import excepciones.VideoNoValido;

class Postulacion_OfertaLaboral_Test {

	private static IConOferta conOferta;
	private static IConUsuario conUsuario;
	
	@BeforeAll
	public static void inicializar(){
		Set<String> listaKey = new HashSet<String>();
		listaKey.add("XRL8");
		ControllerFactory fac = ControllerFactory.getInstance();
		conOferta = fac.getIControladorOferta();
		conUsuario = fac.getIControladorUsuario();
		conOferta.altaKeyWord("XRL8");
		try {
			conUsuario.altaEmpresa("Loli", "Lorena", "Liarte","qwerty", "hola@gmail.com", "Compartida la vida es mas", "httts//:movistar.com.uy");
		} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		try {
			conUsuario.altaPostulante("pipo", "Pablo", "Cardozo","LOli123","pipo@gmail.com",LocalDate.now() , "Venezolano");
		} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		try {
			conUsuario.altaPostulante("pipo2", "Pablo", "Cardozo","LOli123","pipo2@gmail.com",LocalDate.now() , "Venezolano");
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
	void PostulacionOfertaLaboral() {
		Set<String> listaOferta = conUsuario.listarOfertaLaboral("Loli");
		Iterator<String> iterator = listaOferta.iterator();
		if (iterator.hasNext()) {
			String elem = iterator.next();
			DTOferta oferta = conOferta.seleccionarOferta(elem);
			Set<DTPostulante> listaUsuarios = conUsuario.listarPostulantesConDatos();
			Iterator<DTPostulante> iterU = listaUsuarios.iterator();
			DTPostulante nombre = iterU.next();
			try {
				try {
					conOferta.altaPostulacion(oferta.getNombre(), "pipo", "Curriculum Vitae", "Quiero trabajar porque me gusta el dinero", LocalDate.of(1999,11,11));
				} catch (OfertaNoValidaParaEsaFecha e) {
					// TODO Auto-generated catch block
					System.out.printf(e.getMessage());
				}
				try {
					conOferta.agregarVideo(oferta.getNombre(), "pipo", "el pepe");
				} catch (VideoNoValido e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Set<String> listapos = conOferta.getPostulantes(oferta.getNombre());
				List<String> orden = new ArrayList<String>();
				for(String a: listapos) {
					orden.add(a);
				}
				assertTrue(!listapos.isEmpty());
				conOferta.agregarOrden(oferta.getNombre(), orden, LocalDate.now());
				DTPostulante postulante = (DTPostulante) conUsuario.mostrarDatos("pipo");
				DTPostulacion testdt = conUsuario.getPostulacion("pipo", oferta.getNombre());
				assertTrue(testdt.getPostulante() == "pipo");
				assertTrue("Curriculum Vitae" == testdt.getCv());
				assertTrue(!postulante.getOfertas().isEmpty());
				Set<String> listapostu = conOferta.getnickPostulantes(oferta.getNombre());
				assertTrue(!listapostu.isEmpty());
				List<DTPostulacion> postulaciones = conOferta.getPostulaciones(oferta.getNombre());
				assertTrue(!postulaciones.isEmpty());
				conUsuario.addFav("pipo", "Recepcionista");
				conUsuario.esFavorita("pipo", "Recepcionista");
				List<String> ofertasFavoritas = conUsuario.getFavorites("pipo");
				conUsuario.removeFav("pipo", "Recepcionista");
				
				try {
					conOferta.altaPostulacion(oferta.getNombre(), "pipo", "Curriculum Vitae", "Quiero trabajar porque me gusta el dinero", LocalDate.of(1999,11,11));
				} catch (OfertaNoValidaParaEsaFecha e) {
					// TODO Auto-generated catch block
					System.out.printf(e.getMessage());
				}
			} catch (PostulacionYaExiste | OfertaNoExiste e) {
				System.out.println(e.getMessage());
				System.out.println(e.getMessage());
			}
			try {
				try {
					conOferta.altaPostulacion(oferta.getNombre(), "pipo2", "Curriculum Vitae", "Quiero trabajar porque me gusta el dinero", LocalDate.of(2023,11,9));
				} catch (PostulacionYaExiste | OfertaNoExiste e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (OfertaNoValidaParaEsaFecha e) {
				// TODO Auto-generated catch block
				System.out.printf(e.getMessage());
			}
			try {
				try {
					conOferta.altaPostulacion("Nacho creo esta oferta", "pipo2", "Curriculum Vitae", "Quiero trabajar porque me gusta el dinero", LocalDate.of(2023,11,9));
				} catch (PostulacionYaExiste | OfertaNoExiste e) {
					// TODO Auto-generated catch block
					System.out.printf(e.getMessage());
				}
			} catch (OfertaNoValidaParaEsaFecha e) {
				// TODO Auto-generated catch block
				System.out.printf(e.getMessage());
			}
		}
	}
}
