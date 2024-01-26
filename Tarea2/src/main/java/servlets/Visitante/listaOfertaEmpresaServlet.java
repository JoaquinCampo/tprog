package servlets.Visitante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import webservices.DtUsuario;
import webservices.UsuarioWebService;
import webservices.UsuarioWebServiceService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/listaOfertaEmpresa")
public class listaOfertaEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public listaOfertaEmpresaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		
		
		List<String> nicknameEmpresas = portUsr.listarEmpresas().getItem();
		List<DtUsuario> empresas = new ArrayList<>();
			
		if (!nicknameEmpresas.isEmpty() && nicknameEmpresas != null) {
			for (String nick : nicknameEmpresas) {
				empresas.add(portUsr.elegirUsuario(nick));
			}
		}
		request.setAttribute("empresas", empresas);
		
		request.getRequestDispatcher("/WEB-INF/jsp/listaOfertaEmpresa.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
