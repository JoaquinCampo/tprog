package servlets.Visitante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import webservices.UsuarioCorreoExiste_Exception;
import webservices.UsuarioNickExiste_Exception;
import webservices.UsuarioWebService;
import webservices.UsuarioWebServiceService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/signUpEmpresa")
@MultipartConfig
public class signUpEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signUpEmpresaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
	
		String nombre = (String) session.getAttribute("nombre");
	    String apellido = (String) session.getAttribute("apellido");
	    String nickname = (String) session.getAttribute("nickname");
	    String email = (String) session.getAttribute("email");
	    String password = (String) session.getAttribute("password");
	    
	    String descripcion = request.getParameter("descripcion");
	    session.setAttribute("descripcion", descripcion);
	    
	    String website = request.getParameter("website");
	    session.setAttribute("website", website);
	    	 
		boolean con_imagen = false;
	    Part part = request.getPart("image");
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
	    // Ahora, imageBytes contiene los bytes de la imagen
	    
        UsuarioWebServiceService service = new UsuarioWebServiceService();
        UsuarioWebService port = service.getUsuarioWebServicePort();
	    
	    try {
			port.altaEmpresa(nickname, nombre, apellido, password, email, descripcion, website);
		    if(con_imagen) {
				port.agregarImagen(nickname, imageBytes);
		    }
		} catch (UsuarioCorreoExiste_Exception e) {
	        request.setAttribute("errorMessage", "El correo ya está en uso. Por favor, elige otro.");
	        request.getRequestDispatcher("/WEB-INF/jsp/signUpEmpresa.jsp").forward(request, response);
		} catch (UsuarioNickExiste_Exception e) {
			request.setAttribute("errorMessage", "El nickname ya está en uso. Por favor, elige otro.");
	        request.getRequestDispatcher("/WEB-INF/jsp/signUpEmpresa.jsp").forward(request, response);
		}
	    
		request.getRequestDispatcher("/WEB-INF/jsp/indexEmpresa.jsp").forward(request, response);
		
	}

}
