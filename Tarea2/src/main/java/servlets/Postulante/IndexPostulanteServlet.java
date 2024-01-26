package servlets.Postulante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webservices.OfertaWebService;
import webservices.OfertaWebServiceService;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import webservices.DtOferta;

@WebServlet("/indexPostulante")

public class IndexPostulanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexPostulanteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		OfertaWebServiceService service = new OfertaWebServiceService();
		OfertaWebService port = service.getOfertaWebServicePort();
		
		List<String> keywords = port.listarKeyWord().getItem();
		
		List<DtOferta> ofertas = port.getOfertasValidas().getOferta();
		
		getServletContext().setAttribute("keywords", keywords);
		getServletContext().setAttribute("ofertas", ofertas);
		
		request.getRequestDispatcher("WEB-INF/jsp/indexPostulante.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
