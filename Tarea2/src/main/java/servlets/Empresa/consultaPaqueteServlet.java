package servlets.Empresa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import webservices.DtPaquete;
import webservices.PaqueteWebService;
import webservices.PaqueteWebServiceService;
import webservices.UsuarioWebService;
import webservices.UsuarioWebServiceService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.w3c.dom.Element;


@WebServlet("/consultaPaquete")
public class consultaPaqueteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public consultaPaqueteServlet() {
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
		
		// TODO Auto-generated method stub
		String nombrePaquete = (String) request.getParameter("nombrePaquete");

		PaqueteWebServiceService servicePaq = new PaqueteWebServiceService();
		PaqueteWebService portPaq = servicePaq.getPaqueteWebServicePort();
		
		DtPaquete paquete = portPaq.getDTPaquete(nombrePaquete);
		
		String fechaPaquete = portPaq.obtenerFechaPaqueteString(nombrePaquete);
		
        request.setAttribute("fechaPaquete", fechaPaquete);
		request.setAttribute("paquete", paquete);
		
    	if (tipo == 2) {
    		List<DtPaquete> paquetesEmpresa = portUsr.devolverPaquetesComprados((String) session.getAttribute("nickname")).getPaquete();
    		request.setAttribute("paquetesEmpresa", paquetesEmpresa);
    	}
    	
    	request.getRequestDispatcher("/WEB-INF/jsp/consultaPaquete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
