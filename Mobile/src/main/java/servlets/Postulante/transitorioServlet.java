package servlets.Postulante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import webservices.UsuarioWebService;
import webservices.UsuarioWebServiceService;

import java.io.IOException;

@WebServlet("/transitorio")
public class transitorioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public transitorioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("PASO POR ACA");
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
		
		if (tipo == 1)
			request.setAttribute("esEmpresa", false);
		if (tipo == 2)
			request.setAttribute("esEmpresa", true);
		
		
		request.getRequestDispatcher("/consultaUsuario").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
