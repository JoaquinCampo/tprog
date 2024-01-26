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


@WebServlet("/listadoOfertasDeEmpresa")
public class listadoOfertasDeEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listadoOfertasDeEmpresaServlet() {
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
		
		String nicknameEmpresa = (String) request.getParameter("nickname");
		
		request.setAttribute("nickname", nicknameEmpresa);
		
		List<String> nombreOfertas = portUsr.listarOfertaLaboral(nicknameEmpresa).getItem();
		
		OfertaWebServiceService serviceOf = new OfertaWebServiceService();
		OfertaWebService portOf = serviceOf.getOfertaWebServicePort();
	

		List<DtOferta> ofertasAceptadas = portOf.getOfertasValidas().getOferta();
		
		List<DtOferta> ofertas = new ArrayList<>();
		if (!nombreOfertas.isEmpty() && nombreOfertas != null) {
			for (String nomOferta : nombreOfertas) {
				for (DtOferta dtOf2 : ofertasAceptadas) {
					if (nomOferta.equals(dtOf2.getNombre()))
						ofertas.add(portOf.seleccionarOferta(nomOferta));
				}
			}
		}

		request.setAttribute("ofertas",ofertas);
		request.getRequestDispatcher("/WEB-INF/jsp/listadoOfertasDeEmpresa.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
