package servlets.Empresa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webservices.OfertaWebService;
import webservices.OfertaWebServiceService;

import java.io.IOException;
import java.util.List;

import webservices.DtOferta;

@WebServlet("/indexEmpresa")

public class indexEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public indexEmpresaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		OfertaWebServiceService serviceOf = new OfertaWebServiceService();
		OfertaWebService portOf = serviceOf.getOfertaWebServicePort();
		List<String> keywords = portOf.listarKeyWord().getItem();
		
		List<DtOferta> ofertas = portOf.getOfertasValidas().getOferta();
		
		getServletContext().setAttribute("keywords", keywords);
		getServletContext().setAttribute("ofertas", ofertas);
		
		request.getRequestDispatcher("WEB-INF/jsp/indexEmpresa.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
