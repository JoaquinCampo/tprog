package servlets.Empresa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import webservices.DtOferta;
import webservices.OfertaWebService;
import webservices.OfertaWebServiceService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class listarofertasvencidas
 */
@WebServlet("/listarofertasvencidas")
public class listarofertasvencidas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public listarofertasvencidas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String nick = (String) session.getAttribute("nickname");
		
		OfertaWebServiceService serviceOf = new OfertaWebServiceService();
		OfertaWebService portOf = serviceOf.getOfertaWebServicePort();
		
		List<DtOferta> ofertas = portOf.getOfertasVencidasConfirmadas(nick).getOferta();
		
		request.setAttribute("ofertas", ofertas);
		
		request.getRequestDispatcher("WEB-INF/jsp/listaofertasvencidas.jsp").forward(request, response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
