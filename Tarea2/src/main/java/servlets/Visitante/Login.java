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


@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        
        
        UsuarioWebServiceService service = new UsuarioWebServiceService();
        UsuarioWebService port = service.getUsuarioWebServicePort();

        
        String nickname = port.buscarUsuario(id);
        System.out.println("el nickname es:_"+nickname);
        
        if (nickname.equals("null")) {
            request.setAttribute("error", "No existe un usuario con tal nickname o correo");
            request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
        } else {
            boolean equal = port.compararPassword(nickname, password);

            if (equal) {
                HttpSession session = request.getSession();

                DtUsuario dtUsr = port.elegirUsuario(nickname);
                session.setAttribute("imagen", dtUsr.getImagen());

                session.setAttribute("nickname", nickname);

                if (port.esEmpresa(nickname))
                    request.getRequestDispatcher("WEB-INF/jsp/indexEmpresa.jsp").forward(request, response);
                else request.getRequestDispatcher("WEB-INF/jsp/indexPostulante.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Contraseña incorrecta");
                request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
            }
        }
    } 
}
