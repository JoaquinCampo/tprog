package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

class Key_Test {
	
	private static IConOferta conOferta;
	private static IConUsuario conUsuario;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ControllerFactory fac = ControllerFactory.getInstance();
		conOferta = fac.getIControladorOferta();
		conUsuario = fac.getIControladorUsuario();
		
		Set<String> listaKeyO1 = new HashSet<String>();
		Set<String> listaKeyO2 = new HashSet<String>();
		Set<String> listaKeyO3 = new HashSet<String>();
		
		try {
			conUsuario.altaEmpresa("Loli", "Lorena", "Liarte", "hola@gmail.com", "Movistar", "Compartida la vida es mas", "httts//:movistar.com.uy");
		} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
			System.out.println(e.getMessage());
		}
		
		conOferta.altaKeyWord("gato");
		conOferta.altaKeyWord("perro");
		conOferta.altaKeyWord("mapache");
		
		//key para oferta1
		listaKeyO1.add("gato");
		listaKeyO1.add("perro");
		
		//key para oferta2
		listaKeyO2.add("gato");
		
		//key para oferta3
		listaKeyO3.add("gato");
		
		//tipo
		try{
		conOferta.altaTipoDeOfertaLaboral("SKT1", "esta faker", 1, 10, 400,LocalDate.of(2000,11,11));
		}catch(TipoOfertaExiste tp){
			System.out.println(tp.getMessage());
		}
		
		try {
			conOferta.altaOfertaLaboral("Loli","SKT1","Ofertoide1","Soviet Union Music Records 1939 - relax/study to Old aesthetic sound",
					"12:00-12;00", 1000,"Montevideo", "Oficinas Centro", LocalDate.of(2023,7,25),listaKeyO1);
			conOferta.altaOfertaLaboral("Loli","SKT1","Ofertoide2","Soviet Union Music Records 1939 - relax/study to Old aesthetic sound",
					"12:00-12;00", 1000,"Montevideo", "Oficinas Centro", LocalDate.of(2023,7,25),listaKeyO2);
			conOferta.altaOfertaLaboral("Loli","SKT1","Ofertoide3","Soviet Union Music Records 1939 - relax/study to Old aesthetic sound",
					"12:00-12;00", 1000,"Montevideo", "Oficinas Centro", LocalDate.of(2023,7,25),listaKeyO3);
		}catch(OfertaYaExiste e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	void test() {
		Set<String> listaKey = conOferta.listarKeyWord();
		assertTrue(!listaKey.isEmpty());
		Iterator<String> iter = listaKey.iterator();
		boolean bandera = false;
		while(iter.hasNext() && !bandera) {
			String actual = iter.next(); 
			bandera = (actual == "gato");
		}
		assertTrue(bandera);
		List<DTOferta> ofertasGato = conOferta.getOfertasKeword("gato");
		List<DTOferta> ofertasPerro = conOferta.getOfertasKeword("perro");
		List<DTOferta> ofertasMapache = conOferta.getOfertasKeword("mapache");
		assertTrue(ofertasMapache.isEmpty());
		assertTrue(ofertasGato.size() == 3);
		assertTrue(ofertasPerro.size() == 1);
	}

}
