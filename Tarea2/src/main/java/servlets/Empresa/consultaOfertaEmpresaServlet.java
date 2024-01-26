package servlets.Empresa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import webservices.DtOferta;
import webservices.DtPostulacion;
import webservices.DtPostulante;
import webservices.Estados;
import webservices.UsuarioWebService;
import webservices.UsuarioWebServiceService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Element;

import webservices.OfertaWebService;
import webservices.OfertaWebServiceService;

@WebServlet("/consultaOfertaEmpresa")
public class consultaOfertaEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public consultaOfertaEmpresaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		UsuarioWebServiceService service = new UsuarioWebServiceService();
		UsuarioWebService port = service.getUsuarioWebServicePort();
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String nomOferta = (String) request.getParameter("nombreOferta");
	
		OfertaWebServiceService serviceOf = new OfertaWebServiceService();
		OfertaWebService portOf = serviceOf.getOfertaWebServicePort();
		DtOferta dtOf = portOf.seleccionarOferta(nomOferta);
		request.setAttribute("nombreOferta", nomOferta);
		request.setAttribute("descripcion", dtOf.getDescripcion());
		request.setAttribute("departamento", dtOf.getDepartamento());
		request.setAttribute("ciudad", dtOf.getCiudad());
		request.setAttribute("horario", dtOf.getHorario());
		request.setAttribute("sueldo", dtOf.getSueldo());
		
		String fechaPublicacion = portOf.obtenerFechaOfertaString(nomOferta);
		request.setAttribute("fechaPublicacion", fechaPublicacion);
		String usuario = (String) session.getAttribute("nickname");
		String empresa = dtOf.getEmpresa();
		
		request.setAttribute("confirmada",0);
		
		if (usuario.equals(empresa)) {
			
	        Estados estado = dtOf.getEstado();
	        String paquete = portOf.obtenerNombrePaqueteOferta(dtOf.getNombre());
	
			List<DtPostulacion> postulaciones = portOf.getPostulaciones(nomOferta).getPostulacion();
			List<DtPostulante> postulantes = new ArrayList<>();
			
			for (DtPostulacion dtPostu : postulaciones)
				postulantes.add(port.getDTPostulante(dtPostu.getPostulante()));
			
			request.setAttribute("postulantes", postulantes);
			request.setAttribute("postulaciones", postulaciones);
			request.setAttribute("confirmada", estado.ordinal());
			request.setAttribute("paquete", paquete);
		}
		request.getRequestDispatcher("/WEB-INF/jsp/consultaOfertaEmpresa.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	OfertaWebServiceService serviceOf = new OfertaWebServiceService();
		OfertaWebService portOf = serviceOf.getOfertaWebServicePort();
		
		String nomOferta = (String) request.getParameter("nombreOferta");
		portOf.finalizarOferta(nomOferta);
		
		request.getRequestDispatcher("/WEB-INF/jsp/indexEmpresa.jsp").forward(request, response);
	}

}
