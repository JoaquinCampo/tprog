	package servlets.Visitante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import webservices.DtEmpresa;
import webservices.DtOferta;
import webservices.DtPaquete;
import webservices.DtPostulacion;
import webservices.DtPostulante;
import webservices.Exception_Exception;
import webservices.OfertaWebService;
import webservices.OfertaWebServiceService;
import webservices.UsuarioWebService;
import webservices.UsuarioWebServiceService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.w3c.dom.Element;

@WebServlet("/consultaUsuario")
public class consultaUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public consultaUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		UsuarioWebServiceService service = new UsuarioWebServiceService();
		UsuarioWebService portUsr = service.getUsuarioWebServicePort();
		
		
		HttpSession session = request.getSession(false);
		int tipo = 0; // {0,1,2} = {visitante, postulante, empresa}
		if(session != null) {
			String nickname = (String) session.getAttribute("nickname");
			if(nickname != null) {
				if (portUsr.esEmpresa(nickname))
					tipo = 2; // Empresa
				else tipo = 1; // Postulante
			}
		}		
		request.setAttribute("tipoUsuario", tipo); // Defino el tipo de usuario

		String usuario = (String) request.getParameter("nickname"); // Este es el usuario que se consulta
		if (usuario == null) {
			usuario = (String) session.getAttribute("nickname");
		}
		request.setAttribute("usuario", usuario);
		
		boolean esEmpresa = portUsr.esEmpresa(usuario);
		request.setAttribute("esEmpresa", esEmpresa);
		if (esEmpresa) {
			DtEmpresa dtEmpresa = portUsr.getDTEmpresa(usuario);
			request.setAttribute("empresa", dtEmpresa);
			
			// Logica para comprar el paquete
			String nomPaquete = (String) request.getParameter("nombrePaquete");
			if(nomPaquete != null) {
				try {
					portUsr.comprarPaquete(usuario, nomPaquete, LocalDate.now().toString());
				} catch (Exception_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			// Ofertas
			OfertaWebServiceService serviceOf = new OfertaWebServiceService();
			OfertaWebService portOf = serviceOf.getOfertaWebServicePort();
			
			List<DtOferta> ofertas = new ArrayList<>();
			List<DtOferta> confirmadas = new ArrayList<>();
			
			List<DtOferta> allOfertas = portOf.getOfertasValidas().getOferta();
			
							
			if (allOfertas != null && !allOfertas.isEmpty())	
				for (DtOferta dtOf : allOfertas)
					if (dtOf.getEmpresa().equals(usuario))
						ofertas.add(dtOf);
			request.setAttribute("ofertas", ofertas);
			
			allOfertas = portOf.getOfertas().getOferta();
			
			if (allOfertas != null && !allOfertas.isEmpty())	
				for (DtOferta dtOf : allOfertas)
					if (dtOf.getEmpresa().equals(usuario))
						confirmadas.add(dtOf);
			request.setAttribute("confirmadas", confirmadas);
			
			if (usuario.equals((String) session.getAttribute("nickname"))) {
				allOfertas = portOf.getAllofertas().getOferta();
				
				List<DtOferta> rechazadas = new ArrayList<>();
				allOfertas = portOf.getOfertasRechazadas().getOferta();
				if (allOfertas != null && !allOfertas.isEmpty())
					for (DtOferta dtOf : allOfertas)
						if (dtOf.getEmpresa().equals(usuario))
							rechazadas.add(dtOf);
				request.setAttribute("rechazadas",rechazadas);
				
				List<DtOferta> ingresadas = new ArrayList<>();
				allOfertas = portOf.getOfertasIngresadas().getOferta();
				if (allOfertas != null && !allOfertas.isEmpty())
					for (DtOferta dtOf : allOfertas)
						if (dtOf.getEmpresa().equals(usuario))
							ingresadas.add(dtOf);
				request.setAttribute("ingresadas",ingresadas);
				
				List<DtOferta> finalizadas = new ArrayList<>();
				allOfertas = portOf.getOfertasFinalisadas().getOferta();
				if (allOfertas != null && !allOfertas.isEmpty())
					for (DtOferta dtOf : allOfertas)
						if (dtOf.getEmpresa().equals(usuario))
							finalizadas.add(dtOf);
				request.setAttribute("finalizadas",finalizadas);
				
				// Paquetes
				
				List<DtPaquete> paquetes = portUsr.devolverPaquetesComprados(usuario).getPaquete();
				for (DtPaquete dtPaq : paquetes)
					System.out.println("NOMBRE PAQUETE"+dtPaq.getNombre());
				request.setAttribute("paquetes", paquetes);
			}
			
		}
		else {
			DtPostulante postulante = portUsr.getDTPostulante(usuario);
			request.setAttribute("postulante", postulante);
			
			String fechaNacimiento = portUsr.obtenerFechaPostulanteString(usuario);	
			request.setAttribute("fechanacimiento", fechaNacimiento);
			
			OfertaWebServiceService serviceOf = new OfertaWebServiceService();
			OfertaWebService portOf = serviceOf.getOfertaWebServicePort();
			
			
			if (usuario.equals((String) session.getAttribute("nickname"))) {
				List<DtPostulacion> postulaciones = new ArrayList<>();
				List<DtOferta> allOfertas = portOf.getOfertas().getOferta();
				if (allOfertas != null && !allOfertas.isEmpty())	
					for (DtOferta offer : allOfertas) {
						if (portUsr.tienePostulacion(usuario, offer.getNombre())) {
							DtPostulacion dtPostulacion= portUsr.getPostulacion(usuario, offer.getNombre());
							postulaciones.add(dtPostulacion);
						}
					}
				List<DtOferta> finalisadas = portOf.getOfertasFinalisadas().getOferta();
				if (finalisadas != null && !finalisadas.isEmpty())
					for (DtOferta offer : finalisadas) {
						if (portUsr.tienePostulacion(usuario, offer.getNombre())) {
							DtPostulacion dtPostulacion= portUsr.getPostulacion(usuario, offer.getNombre());
							postulaciones.add(dtPostulacion);
						}
					}
				List<DtOferta> ofertasPostu = postulante.getOfertas();
				request.setAttribute("ofertasPostu", ofertasPostu);
				
				request.setAttribute("postulaciones", postulaciones);
			}
		
		}
		
		
		
		request.getRequestDispatcher("/WEB-INF/jsp/consultaUsuario.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
