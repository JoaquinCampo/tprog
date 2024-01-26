package servlets.Postulante;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

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

@WebServlet("/updateFavoriteStatus")
public class FavoriteOfferServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    HttpSession session = request.getSession(false);
	    
	    if (session == null) {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("Session is not valid.");
	        return;
	    }
	    
	    String nickname = (String) session.getAttribute("nickname");
	    
	    if (nickname == null) {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("User is not logged in.");
	        return;
	    }
	    
	    UsuarioWebServiceService service = new UsuarioWebServiceService();
	    UsuarioWebService port = service.getUsuarioWebServicePort();
	    
	    List<String> favorites = port.getFavorites(nickname).getItem();
	    
	    // Convert the List to JSON and send it as a response
	    String jsonFavorites = new Gson().toJson(favorites);
	    response.setContentType("application/json");
	    response.getWriter().write(jsonFavorites);
	    
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

    	UsuarioWebServiceService service = new UsuarioWebServiceService();
        UsuarioWebService port = service.getUsuarioWebServicePort();
        
        String action = request.getParameter("action");
        String offerName = request.getParameter("offerName");
        HttpSession session = request.getSession(false);
        String nickname = (String) session.getAttribute("nickname");

        if ("add".equalsIgnoreCase(action)) {
            port.addFav(nickname, offerName);
        } else if ("remove".equalsIgnoreCase(action)) {
            port.removeFav(nickname, offerName);
        }
        

    }



}
