package servlets.Postulante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import webservices.UsuarioWebService;
import webservices.UsuarioWebServiceService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import webservices.DtEmpresa;
import webservices.DtPostulante;
import webservices.Exception_Exception;

@WebServlet("/modificarDatos")
@MultipartConfig
public class modificarDatosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificarDatosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		
		String nickname = (String) session.getAttribute("nickname");		
		DtPostulante dtPost = null;
		DtEmpresa dtEmp = null;
		if (tipo == 1) {
			dtPost = port.getDTPostulante(nickname);
			String fecha = port.obtenerFechaPostulanteString(nickname);
			request.setAttribute("fecha_nacimiento", fecha);
			request.setAttribute("dtPost", dtPost);
		}
		
		if (tipo == 2) {
			dtEmp = port.getDTEmpresa(nickname);
			request.setAttribute("dtEmp", dtEmp);
		}
		request.getRequestDispatcher("WEB-INF/jsp/modificarDatos.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

		
		
		String nickname = (String) session.getAttribute("nickname");
		
		boolean con_imagen = false;
	    Part part = request.getPart("imagen");
	    byte[] imageBytes = null; // Declarar un arreglo de bytes para la imagen
	    
	    if (part != null) {
	    	// Leer el contenido del archivo en un arreglo de bytes
	        long fileSize = part.getSize();
	        if (fileSize > 0) {
	            imageBytes = new byte[(int) fileSize];
	            part.getInputStream().read(imageBytes);
	            con_imagen = true;
	        }
	    } 
		
		String nombre = (String) request.getParameter("nombre");
		String apellido = (String) request.getParameter("apellido");
		if (tipo == 1) {
			String birthDateString = (String) request.getParameter("birthDate");
			String cumple;
			if (birthDateString.trim().isEmpty()) 
			{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String fecha_antigua = port.obtenerFechaPostulanteString(nickname);
				LocalDate birthDate = LocalDate.parse(fecha_antigua, formatter);
				cumple = birthDate.toString();
			}
			else {
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate birthDate = LocalDate.parse(birthDateString, inputFormatter);
		    cumple=birthDate.toString();}
		    
			
			String nationality = (String) request.getParameter("nationality");
			try {
				port.modificarPostulante(nickname, nombre, apellido, cumple, nationality);
			} catch (Exception_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(con_imagen) {
				port.agregarImagen(nickname, imageBytes);
			}
		}
		
	    if (tipo == 2) {
	    	String website = (String) request.getParameter("website");
	    	String descripcion = (String) request.getParameter("descripcion");
	    	port.modificarEmpresa(nickname, nombre, apellido, descripcion, website);
			if(con_imagen) {
				port.agregarImagen(nickname, imageBytes);
			}
	    }
	    
	    
	    request.getRequestDispatcher("/transitorio").forward(request, response);
	    		
	}

}
