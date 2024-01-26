package servlets.Visitante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import webservices.DtOferta;
import webservices.OfertaWebService;
import webservices.OfertaWebServiceService;

import org.w3c.dom.Element;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;


@WebServlet("/consultaOfertaVisitante")
public class consultaOfertaVisitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public consultaOfertaVisitanteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		request.setCharacterEncoding("UTF-8");
		String nomOferta = (String) request.getParameter("nombreOferta");
		
		OfertaWebServiceService service = new OfertaWebServiceService();
		OfertaWebService port = service.getOfertaWebServicePort();
		
		List<DtOferta> listaOfertas = port.getOfertasValidas().getOferta();
		for (DtOferta dtOf : listaOfertas) {
			if (dtOf.getNombre().equals(nomOferta)) {
		        String fechaPubli = port.obtenerFechaOfertaString(nomOferta);
				request.setAttribute("nombreOferta", nomOferta);
				request.setAttribute("descripcion", dtOf.getDescripcion());
				request.setAttribute("departamento", dtOf.getDepartamento());
				request.setAttribute("ciudad", dtOf.getCiudad());
				request.setAttribute("horario", dtOf.getHorario());
				request.setAttribute("sueldo", dtOf.getSueldo());

		        request.setAttribute("fechaPublicacion", fechaPubli);
				request.setAttribute("imagenOferta", dtOf.getImagen());
				break;
			}
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/consultaOfertaVisitante.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
