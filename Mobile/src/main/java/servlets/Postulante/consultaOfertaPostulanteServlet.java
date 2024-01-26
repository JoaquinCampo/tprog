package servlets.Postulante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import webservices.UsuarioWebService;
import webservices.UsuarioWebServiceService;
import webservices.OfertaWebService;
import webservices.OfertaWebServiceService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Element;

import webservices.DtOferta;

@WebServlet("/consultaOfertaPostulante")
public class consultaOfertaPostulanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public consultaOfertaPostulanteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
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
		request.setCharacterEncoding("UTF-8");
		String nomOferta = (String) request.getParameter("nombreOferta");
		if (nomOferta == null)
			nomOferta = (String) request.getAttribute("nombreOferta");
		
		OfertaWebServiceService serviceOf = new OfertaWebServiceService();
		OfertaWebService portOf = serviceOf.getOfertaWebServicePort();
		
		List<DtOferta> listaOfertas = portOf.getOfertasValidas().getOferta();
		for (DtOferta dtOf : listaOfertas) {
			if (dtOf.getNombre().equals(nomOferta)) {
				request.setAttribute("nombreOferta", nomOferta);
				request.setAttribute("descripcion", dtOf.getDescripcion());
				request.setAttribute("departamento", dtOf.getDepartamento());
				request.setAttribute("ciudad", dtOf.getCiudad());
				request.setAttribute("horario", dtOf.getHorario());
				request.setAttribute("sueldo", dtOf.getSueldo());

				String fechaPublicacion = portOf.obtenerFechaOfertaString(nomOferta);
				request.setAttribute("fechaPublicacion", fechaPublicacion);
				request.setAttribute("imagenOferta", dtOf.getImagen());
				break;
			}
		}

		// Fijarse si esta postulado
		if (tipo == 1) {
			String nickname = (String) session.getAttribute("nickname");
			
			List<String> postulantes = portOf.getnickPostulantes(nomOferta).getItem();
			
			boolean postulado = false;
			for (String post : postulantes) {
				if (post.equals(nickname)) {
					postulado = true;
					break;
				}
			}
			request.setAttribute("postulado", postulado);
		}
		request.getRequestDispatcher("/WEB-INF/jsp/consultaOfertaPostulante.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

