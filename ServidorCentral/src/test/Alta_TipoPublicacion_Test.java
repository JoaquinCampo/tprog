package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datatypes.*;
import excepciones.TipoOfertaExiste;
import excepciones.PaqueteYaExiste;
import excepciones.TipoOfertaNoExiste;
import logica.ControllerFactory;
import logica.IConOferta;
import logica.IConPaquete;

class Alta_TipoPublicacion_Test {

	private static IConOferta conOferta;
	
	@BeforeAll
	public static void inicializar(){
		ControllerFactory fac = ControllerFactory.getInstance();
		conOferta = fac.getIControladorOferta();
		try {
			conOferta.altaTipoDeOfertaLaboral("tipito1", "soy el tipo menos importante", 2, 11, 23, LocalDate.of(2000,11,11));
			conOferta.altaTipoDeOfertaLaboral("tipito2", "soy el tipo menos importante", 2, 11, 23, LocalDate.of(2000,11,11));
			conOferta.altaTipoDeOfertaLaboral("tipito3", "soy el tipo menos importante", 2, 11, 23, LocalDate.of(2000,11,11));
			conOferta.altaTipoDeOfertaLaboral("tipito4", "soy el tipo menos importante", 2, 11, 23, LocalDate.of(2000,11,11));
		} catch (TipoOfertaExiste e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	void AgregarTipoDeOfertaAPaquete() {
		Set<DTTipoOferta> listaTipos = conOferta.getTipoOfertas();
		assertTrue(!listaTipos.isEmpty());
		DTTipoOferta t1 = conOferta.informacionTipoOferta("tipito1");
		assertTrue(t1!=null);
	}
}