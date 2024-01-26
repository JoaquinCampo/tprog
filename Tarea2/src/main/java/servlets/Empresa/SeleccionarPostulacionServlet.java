package servlets.Empresa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.java.dev.jaxb.array.StringArray;
import webservices.DtOferta;
import webservices.DtPostulacion;
import webservices.DtPostulante;
import webservices.Estados;
import webservices.OfertaWebService;
import webservices.OfertaWebServiceService;
import webservices.UsuarioWebService;
import webservices.UsuarioWebServiceService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.w3c.dom.Element;

/**
 * Servlet implementation class SeleccionarPostulacionServlet
 */
@WebServlet("/seleccionarpostulacion")
public class SeleccionarPostulacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeleccionarPostulacionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UsuarioWebServiceService service = new UsuarioWebServiceService();
		UsuarioWebService port = service.getUsuarioWebServicePort();
		
		String nomOferta = (String) request.getParameter("nombreOferta");
		
		OfertaWebServiceService serviceOf = new OfertaWebServiceService();
		OfertaWebService portOf = serviceOf.getOfertaWebServicePort();
		
		DtOferta dtOf = portOf.seleccionarOferta(nomOferta);
		String usuario = null;
		usuario = dtOf.getEmpresa();
		request.setAttribute("nombreOferta", nomOferta);
		request.setAttribute("descripcion", dtOf.getDescripcion());
		request.setAttribute("departamento", dtOf.getDepartamento());
		request.setAttribute("ciudad", dtOf.getCiudad());
		request.setAttribute("horario", dtOf.getHorario());
		request.setAttribute("sueldo", dtOf.getSueldo());
		String fecha = portOf.obtenerFechaOfertaString(nomOferta);
		        
		Estados estado = dtOf.getEstado();
		request.setAttribute("estado", estado.ordinal());   
		
   
		request.setAttribute("fechaPublicacion", fecha);
		List<DtPostulacion> postulaciones = portOf.getPostulaciones(nomOferta).getPostulacion();
		List<DtPostulante> postulantes = new ArrayList<>();
			
		for (DtPostulacion dtPostu : postulaciones)
			postulantes.add(port.getDTPostulante(dtPostu.getPostulante()));
			
		request.setAttribute("postulantes", postulantes);
		request.setAttribute("postulaciones", postulaciones);
		request.getRequestDispatcher("/WEB-INF/jsp/seleccionarpostulacion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
    	OfertaWebServiceService serviceOf = new OfertaWebServiceService();
		OfertaWebService portOf = serviceOf.getOfertaWebServicePort();	
		String nomOferta = (String) request.getParameter("nombreOferta");
		System.out.println(nomOferta);
		List<DtPostulacion> postulaciones = portOf.getPostulaciones(nomOferta).getPostulacion();
		String[] aux = new String[postulaciones.size()];
		for (DtPostulacion actual : postulaciones) {
			String nombre = actual.getPostulante();
			String ordenstring = request.getParameter(nombre);
			int lugar = Integer.parseInt(ordenstring);
			aux[lugar-1] = nombre;
		}
		StringArray orden = new StringArray();
		String alta = LocalDate.now().toString();
		for(String a : aux) {
			orden.getItem().add(a);
		}
		portOf.agregarOrden(nomOferta,orden,alta);
		request.getRequestDispatcher("/WEB-INF/jsp/indexEmpresa.jsp").forward(request, response);
	}

}
