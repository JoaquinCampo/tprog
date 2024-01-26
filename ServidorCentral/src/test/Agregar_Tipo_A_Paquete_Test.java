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

class Agregar_Tipo_A_Paquete_Test {

	private static IConPaquete conPaquete;
	private static IConOferta conOferta;
	
	@BeforeAll
	public static void inicializar(){
		ControllerFactory fac = ControllerFactory.getInstance();
		conPaquete = fac.getIControladorPaquete();
		conOferta = fac.getIControladorOferta();
		try {
			conPaquete.crearPaquete("paqueteVariopinto1","contendra tipo azulzafiroy rojorubi", 11, (float) 0.23, LocalDate.of(2023,11,11));
			conOferta.altaTipoDeOfertaLaboral("azulzafiro", "soy el tipo menos importante", 2, 11, 23, LocalDate.of(2000,11,11));
			conOferta.altaTipoDeOfertaLaboral("rojorubi", "soy el tipo menos importante", 2, 11, 23, LocalDate.of(2000,11,11));
		} catch (PaqueteYaExiste | TipoOfertaExiste e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	void AgregarTipoDeOfertaAPaquete() {
		Set<DTPaquete> listapaquete = conPaquete.listarPaquete();
		assertTrue(!listapaquete.isEmpty());
		Set<String> listaTipos = conOferta.listarTipoOferta();
		assertTrue(!listaTipos.isEmpty());
		try {
			conPaquete.agregarTipoDeOfertaAPaquete("paqueteVariopinto1", "azulzafiro", 3);
			conPaquete.agregarTipoDeOfertaAPaquete("paqueteVariopinto1","rojorubi", 2);
			Set<String> listatipos =  conPaquete.getNombresTipoOferta("paqueteVariopinto1");
			assertTrue(!listatipos.isEmpty());
		} catch (TipoOfertaNoExiste e) {
			System.out.println(e.getMessage());
		}
		// dandole un tipo que no existe
		try {
			conPaquete.agregarTipoDeOfertaAPaquete("paqueteVariopinto1", "este no existe", 1);
		} catch (TipoOfertaNoExiste otro) {
			System.out.println(otro.getMessage());
		}
	}
}
