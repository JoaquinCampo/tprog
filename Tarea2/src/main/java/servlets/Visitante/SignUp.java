package servlets.Visitante;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import webservices.UsuarioWebService;
import webservices.UsuarioWebServiceService;

import java.io.IOException;

@WebServlet("/signUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
        String nickname = request.getParameter("username");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("confirmPassword");
        
        
        // chequear que sean iguales las contraseñas
        if (!password.equals(confirmpassword)) {
        	request.setAttribute("errorMessage", "Las contraseñas no coinciden");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
            dispatcher.forward(request, response);
        }
        
        UsuarioWebServiceService service = new UsuarioWebServiceService();
        UsuarioWebService port = service.getUsuarioWebServicePort();
        
        
        boolean userExists = port.existeUsuarioNickname(nickname);
        if (userExists) {
        	// Ya existe un usuario con el nickname
            request.setAttribute("errorMessage", "El nickname ya está en uso. Por favor, elige otro.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
            dispatcher.forward(request, response);
        }
        else {
        	userExists = port.existeUsuarioCorreo(email);
            if (userExists) {
            	// Ya existe un usuario con el correo
            	request.setAttribute("errorMessage", "El correo ya está en uso. Por favor, elige otro.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
                dispatcher.forward(request, response);
            }
            else {
            	String action = request.getParameter("action");

    	        HttpSession session = request.getSession(true);
      	        
    	        session.setAttribute("nickname", nickname);
    	        session.setAttribute("nombre", nombre);
    	        session.setAttribute("apellido", apellido);
    	        session.setAttribute("email", email);
    	        session.setAttribute("password", password);
    	        
                if ("postulante".equals(action)) {

                	request.setAttribute("nombre", nombre);
                	request.setAttribute("apellido", apellido);
                	request.setAttribute("nickname", nickname);
                	request.setAttribute("email", email);
                	request.setAttribute("password", password);
                	
                    request.getRequestDispatcher("/WEB-INF/jsp/signUpPostulante.jsp").forward(request, response);
                } 
                else if ("empresa".equals(action)) {
                	request.setAttribute("nombre", nombre);
                	request.setAttribute("apellido", apellido);
                	request.setAttribute("nickname", nickname);
                	request.setAttribute("email", email);
                	request.setAttribute("password", password);
                	
                    request.getRequestDispatcher("/WEB-INF/jsp/signUpEmpresa.jsp").forward(request, response);
                } 
                else {
                    request.setAttribute("errorMessage", "Acción no reconocida");
                    request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp").forward(request, response);
                }
            }
        }
		
	}

}




