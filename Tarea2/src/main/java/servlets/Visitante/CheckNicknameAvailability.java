package servlets.Visitante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webservices.UsuarioWebService;
import webservices.UsuarioWebServiceService;

import java.io.IOException;

@WebServlet("/CheckNicknameAvailability")
public class CheckNicknameAvailability extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickname = request.getParameter("nickname");
        UsuarioWebServiceService service = new UsuarioWebServiceService();
        UsuarioWebService port = service.getUsuarioWebServicePort();
        boolean userExists = port.existeUsuarioNickname(nickname);
        
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(userExists ? "No disponible" : "Disponible");
    }
}