package servlets.Visitante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import webservices.DtOferta;
import webservices.OfertaWebService;
import webservices.OfertaWebServiceService;
import webservices.UsuarioWebService;
import webservices.UsuarioWebServiceService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/listarOfertasKeywords")
public class listarOfertasKeywordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listarOfertasKeywordsServlet() {
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
		
		OfertaWebServiceService serviceOf = new OfertaWebServiceService();
		OfertaWebService portOf = serviceOf.getOfertaWebServicePort();
		
		
		List<DtOferta> ofertas =  portOf.getOfertasKeword(request.getParameter("keyword")).getOferta();
		List<DtOferta> ofertasAceptadas = portOf.getOfertasValidas().getOferta();
		List<DtOferta> res = new ArrayList<>();
		
		if (ofertas != null && ofertasAceptadas != null) {
			for (DtOferta dtOf : ofertas) {
				for (DtOferta dtOf2 : ofertasAceptadas)
					if (dtOf.getNombre().equals(dtOf2.getNombre())) {				
						res.add(dtOf2);
					}
			}
		}
		request.setAttribute("ofertas", res);
		request.setAttribute("keyword", request.getParameter("keyword"));
		
		
		request.getRequestDispatcher("/WEB-INF/jsp/listarOfertasKeywords.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
