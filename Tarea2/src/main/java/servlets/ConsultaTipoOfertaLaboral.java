package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import webservices.DtTipoOferta;
import webservices.OfertaWebService;
import webservices.OfertaWebServiceService;
import webservices.UsuarioWebService;
import webservices.UsuarioWebServiceService;

import org.w3c.dom.Element;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@WebServlet("/consultarTipoOfertaLaboral")

/**
 * Servlet implementation class ConsultaTipoOfertaLaboral
 */
public class ConsultaTipoOfertaLaboral extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaTipoOfertaLaboral() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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
		
		// TODO Auto-generated method stub
		String nombreTipo = request.getParameter("tipooferta");
		OfertaWebServiceService serviceOf = new OfertaWebServiceService();
		OfertaWebService portOf = serviceOf.getOfertaWebServicePort();
		
		
		DtTipoOferta tipo_ = portOf.informacionTipoOferta(nombreTipo);
		String fecha = portOf.obtenerTipoFechaOfertaString(nombreTipo);
		request.setAttribute("fecha_alta", fecha);
;
		// Now you can use fechaLocalDate which is a LocalDate object

		request.setAttribute("tipo",tipo_);
		request.getRequestDispatcher("/WEB-INF/jsp/consultarTipoOfertaLaboral.jsp").forward(request, response);
	}

}
