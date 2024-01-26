package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datatypes.*;
import logica.IConPaquete;
import excepciones.PaqueteYaExiste;
import logica.ControllerFactory;

class Consulta_Paquete_Test {

	private static IConPaquete conPac;
	
	@SuppressWarnings("deprecation")
	@BeforeAll
	public static void inicializar() {
		ControllerFactory fac = ControllerFactory.getInstance();
		conPac = fac.getIControladorPaquete();
		String nombre1 = "packA";
		String descripcion1 = "El paquete A";
		int valides1 = 3;
		float descuento1 = 0.03f;
		LocalDate fecha1 = LocalDate.of(2003,3,3);
		try {
			conPac.crearPaquete(nombre1, descripcion1, valides1, descuento1, fecha1);
		} catch (PaqueteYaExiste e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	void test() {
		Set<DTPaquete> colpac = conPac.listarPaquete();
		Iterator<DTPaquete> iter = colpac.iterator();
		boolean encontre = false;
		while(iter.hasNext() && !encontre) {
			DTPaquete actual = iter.next();
			encontre = "packA" == actual.getNombre();
		}
		assertTrue(encontre);
	}
	
	
}