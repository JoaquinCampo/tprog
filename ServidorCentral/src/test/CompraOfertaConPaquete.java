package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.EmpresaNoExiste;
import excepciones.OfertaYaExiste;
import excepciones.PaqueteYaExiste;
import excepciones.TipoOfertaExiste;
import excepciones.TipoOfertaNoExiste;
import excepciones.UsuarioCorreoExiste;
import excepciones.UsuarioNickExiste;
import logica.ControllerFactory;
import logica.IConOferta;
import logica.IConPaquete;
import logica.IConUsuario;
import datatypes.DTOferta;
import datatypes.DTPaquete;

class CompraOfertaConPaquete {

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
			//crearPaquete(String nombre,String descripcion,int valides,float descuento,LocalDate fecha)
			conPaquete.crearPaquete("Paquete_test_compra","Es para probar descuentos", 1000, (float) 0.90, LocalDate.of(2020,11,11));
		}
		catch (PaqueteYaExiste e) {
			System.out.println(e.getMessage());
		}
		try {
			//altaEmpresa(String nickname, String nombre, String apellido,String contraseña, String correo, String descripcion, String web)
			conUsuario.altaEmpresa("Empresa_test_compra", "Lorena", "Liarte", "Movistar", "Empresa_test_comrpa@email.com", "Compartida la vida es mas", "httts//:movistar.com.uy");
			} catch (UsuarioNickExiste | UsuarioCorreoExiste e) {
				
				System.out.println(e.getMessage());
			}
		try {
			//altaTipoDeOfertaLaboral(String nombre, String descripcion, int orden, int dias, float costo, LocalDate fecha)
			conOferta.altaTipoDeOfertaLaboral("Tipo_test_compra", "poco relevante", 3,1000 , 1000,LocalDate.of(2023,7,25));
			conPaquete.agregarTipoDeOfertaAPaquete("Paquete_test_compra","Tipo_test_compra", 1);
		} catch (TipoOfertaExiste | TipoOfertaNoExiste tp) {
			System.out.println(tp.getMessage());
		} 
		
		Set<String> listaKey = new HashSet<String>();
		listaKey.add("XRL8");
		conOferta.altaKeyWord("XRL8");
		//altaOfertaLaboral(String nomEmpresa, String tipoPublicacion, String nombre, String descripcion,String horario, float remuneracion, String ciudad, String departamento, LocalDate fechaAlta,Set<String> keywords)
		try {
			conOferta.altaOfertaLaboral("Empresa_test_compra","Tipo_test_compra","Oferta_test_oferta", "Trabajo bien pagado",
					"Lunes a Sabado: 8-16 hs", 13000, "Montevideo", "Oficinas Centro", LocalDate.of(2023,7,25), listaKey);	
		} catch (OfertaYaExiste e) {
			System.out.println(e.getMessage());
		} catch (TipoOfertaNoExiste tpne) {
			System.out.println(tpne.getMessage());
		} catch (EmpresaNoExiste em) {
			System.out.println(em.getMessage());
		}
		conUsuario.comprarPaquete("Empresa_test_compra", "Paquete_test_compra", LocalDate.now());
		}
	
	@Test
	//comprarOfertaConPaquete(String nombre,String tipo,String paquete);
	void CompraOfertaConPaquete() {
		conOferta.comprarOfertaConPaquete("Empresa_test_compra","Oferta_test_oferta","Tipo_test_compra", "Paquete_test_compra");
		DTOferta datoOferta = conOferta.seleccionarOferta("Oferta_test_oferta");
		int valorAprobar = (int) datoOferta.getCosto();
		System.out.println("Costo es " + valorAprobar);
		assertTrue(valorAprobar == 1);
		String nombre_paquete = conOferta.obtenerNombrePaqueteOferta("Oferta_test_oferta");
		assertTrue(nombre_paquete == "Paquete_test_compra");
		List<DTPaquete> paquetesdisp = conUsuario.PaquetesDisponiblesParaPagar("Empresa_test_compra", "Tipo_test_compra");
		assertTrue(paquetesdisp.isEmpty());
		
		
	}
}
