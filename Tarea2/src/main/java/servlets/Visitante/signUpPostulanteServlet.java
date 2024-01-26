package servlets.Visitante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import webservices.Exception_Exception;
import webservices.UsuarioCorreoExiste_Exception;
import webservices.UsuarioNickExiste_Exception;
import webservices.UsuarioWebService;
import webservices.UsuarioWebServiceService;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

@WebServlet("/signUpPostulante")
@MultipartConfig
public class signUpPostulanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signUpPostulanteServlet() {
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
		
		String nickname = (String) session.getAttribute("nickname");
		String nombre = (String) session.getAttribute("nombre");
	    String apellido = (String) session.getAttribute("apellido");
	    String email = (String) session.getAttribute("email");
	    String password = (String) session.getAttribute("password");
	    
	    String nacionalidad = request.getParameter("nationality");
	    session.setAttribute("nacionalidad", nacionalidad);

	    String birthDateString = request.getParameter("birthDate");
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
	    session.setAttribute("birthDate", birthDate);
	    
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

        UsuarioWebServiceService service = new UsuarioWebServiceService();
        UsuarioWebService port = service.getUsuarioWebServicePort();
	    
	    try { // Or any other LocalDate
	    	String dateString = LocalDate.now().toString(); // Adjust the pattern as necessary
	    	port.altaPostulante(nickname, nombre, apellido, password, email, dateString, nacionalidad);
	    	
	    	if(con_imagen) {
	    		port.agregarImagen(nickname, imageBytes);
	    	}
	    	
	        request.getRequestDispatcher("/WEB-INF/jsp/indexPostulante.jsp").forward(request, response);


	    } catch (UsuarioNickExiste_Exception e) {
	        
	        request.setAttribute("errorMessage", "El nickname ya está en uso. Por favor, elige otro.");
	        request.getRequestDispatcher("/WEB-INF/jsp/signUpPostulante.jsp").forward(request, response);
	    } catch (UsuarioCorreoExiste_Exception e) {
	       
	        request.setAttribute("errorMessage", "El correo ya está en uso. Por favor, elige otro.");
	        request.getRequestDispatcher("/WEB-INF/jsp/signUpPostulante.jsp").forward(request, response);
	    } catch (Exception_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    		
	    
    }
        
    
}

