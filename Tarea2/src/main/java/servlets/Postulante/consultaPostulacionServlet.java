package servlets.Postulante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import webservices.UsuarioWebService;
import webservices.UsuarioWebServiceService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.w3c.dom.Element;

import webservices.DtPostulacion;
import webservices.OfertaWebService;
import webservices.OfertaWebServiceService;

@WebServlet("/consultaPostulacion")
public class consultaPostulacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public consultaPostulacionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		UsuarioWebServiceService service = new UsuarioWebServiceService();
		UsuarioWebService port = service.getUsuarioWebServicePort();
		
		HttpSession session = request.getSession(false);
		int tipo = 0; // {0,1,2} = {visitante, postulante, empresa}
		if(session != null) {
			String nickname = (String) session.getAttribute("nickname");
			if(nickname != null) {
				if (port.esEmpresa(nickname))
					tipo = 2; // Empresa
				else tipo = 1; // Postulante
			}
		}		
		request.setAttribute("tipoUsuario", tipo); // Defino el tipo de usuario
		
		String nombrePostulante = (String) request.getParameter("usuario");
		request.setAttribute("nombrePostulante", nombrePostulante);
		
		String nombreOferta = (String) request.getParameter("nombreOferta");
		request.setAttribute("nombreOferta", nombreOferta);

		DtPostulacion dtPostulacion = port.getPostulacion(nombrePostulante, nombreOferta);
		request.setAttribute("cv", dtPostulacion.getCv());
		request.setAttribute("motivacion", dtPostulacion.getMotivacion());
		request.setAttribute("video", dtPostulacion.getVideoid());
		
        String fechaPublicacion = dtPostulacion.getStringFecha();    
        request.setAttribute("fechaPostulacion", fechaPublicacion);

        OfertaWebServiceService serviceOf = new OfertaWebServiceService();
		OfertaWebService portOf = serviceOf.getOfertaWebServicePort();
		
		request.getRequestDispatcher("WEB-INF/jsp/consultaPostulacion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
