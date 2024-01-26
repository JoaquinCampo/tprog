package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datatypes.*;
import logica.ControllerFactory;
import logica.IConOferta;
import logica.IConPaquete;
import excepciones.PaqueteYaExiste;

class CrearPaquete_Test {

	private static IConPaquete conPaquete;
	
	@BeforeAll
	public static void inicializar(){
		ControllerFactory fac = ControllerFactory.getInstance();
		conPaquete = fac.getIControladorPaquete();
	}
	//crearPaquete(String nombre,String descripcion,int valides,float descuento,LocalDate fecha)
	
	@Test
	void CrearPaquete() {
		try {
			conPaquete.crearPaquete("Pack","paquete que no tiene tipos de ofertas", 10, (float) 0.89, LocalDate.of(2020,11,11));
		}
		catch (PaqueteYaExiste e) {
			System.out.println(e.getMessage());
		}
	//intento 2, para verificar que no se crea un paquete con el mismo paquete
		try {
			conPaquete.crearPaquete("Pack","este paquete no se puede crear", 10, (float) 0.89, LocalDate.of(2020,11,11));
		}
		catch (PaqueteYaExiste e) {
			System.out.println(e.getMessage());
		}
		conPaquete.getDTPaquete("Pack");
	Set<DTPaquete> listaPaquete = conPaquete.listarPaquete();
	boolean bandera = true;
	for (DTPaquete aux : listaPaquete) {
		if (bandera) {
			bandera = (aux.getDescripcion() !=  "este paquete no se puede crear");
			}
	}
	
	assertTrue(bandera);
	}
}
