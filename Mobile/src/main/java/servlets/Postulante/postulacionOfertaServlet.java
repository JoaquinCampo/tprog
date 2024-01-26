package servlets.Postulante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import webservices.OfertaWebService;
import webservices.OfertaWebServiceService;
import webservices.PostulacionYaExiste;
import webservices.PostulacionYaExiste_Exception;
import webservices.VideoNoValido_Exception;

import java.io.IOException;
import java.time.LocalDate;

import webservices.DtOferta;
import webservices.Exception_Exception;
import webservices.OfertaNoExiste_Exception;
import webservices.OfertaNoValidaParaEsaFecha_Exception;



@WebServlet("/postulacionOferta")
public class postulacionOfertaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public postulacionOfertaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nombreOferta = (String) request.getParameter("nombreOferta");
		
        HttpSession session = request.getSession();
        session.setAttribute("nombreOferta", nombreOferta);
        
		request.getRequestDispatcher("WEB-INF/jsp/postulacionOferta.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String oferta = (String) session.getAttribute("nombreOferta");
		String cvreducido = (String) request.getParameter("cvreducido");
		String motivacion = (String) request.getParameter("motivacion");
		String video = (String) request.getParameter("video");

		
		request.setAttribute("nombreOferta", oferta);
		request.setAttribute("postulado", true);
		
		OfertaWebServiceService serviceOf = new OfertaWebServiceService();
		OfertaWebService portOf = serviceOf.getOfertaWebServicePort();		
		DtOferta dtOf = portOf.seleccionarOferta(oferta);
		
		
		request.setAttribute("descripcion", dtOf.getDescripcion());
		request.setAttribute("departamento",dtOf.getDepartamento());
		request.setAttribute("ciudad", dtOf.getCiudad());
		request.setAttribute("horario", dtOf.getHorario());
		request.setAttribute("sueldo", dtOf.getSueldo());


		
		
			String now=LocalDate.now().toString();
			try {
				portOf.altaPostulacion(oferta, (String) session.getAttribute("nickname"),cvreducido,motivacion,now);
			} catch (Exception_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OfertaNoExiste_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OfertaNoValidaParaEsaFecha_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PostulacionYaExiste_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(video != null && !video.equals("")) {
				try {
					portOf.agregarVideo(oferta, (String) session.getAttribute("nickname"), video);
				} catch (VideoNoValido_Exception e) {
					request.setAttribute("error","Enlace no valido");
					request.getRequestDispatcher("WEB-INF/jsp/postulacionOferta.jsp").forward(request, response);		
				}
			}

			request.getRequestDispatcher("/consultaOfertaPostulante").forward(request, response);
		
	}
}

