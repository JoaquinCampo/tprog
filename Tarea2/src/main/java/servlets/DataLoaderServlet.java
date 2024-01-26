package servlets;

import java.io.IOException;
import java.util.List;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webservices.DtOferta;
import webservices.OfertaWebService;
import webservices.OfertaWebServiceService;

@WebServlet("/index")
public class DataLoaderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	OfertaWebServiceService service = new OfertaWebServiceService();
		OfertaWebService port = service.getOfertaWebServicePort();
    	
		List<String> keywords = port.listarKeyWord().getItem();
        
		List<DtOferta> ofertas = port.getOfertasValidas().getOferta();

		
		getServletContext().setAttribute("keywords", keywords);
		getServletContext().setAttribute("ofertas", ofertas);
        request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
    }
    
}