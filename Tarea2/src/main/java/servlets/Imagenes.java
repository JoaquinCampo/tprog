package servlets;

import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webservices.OfertaWebService;
import webservices.OfertaWebServiceService;
import webservices.PaqueteWebService;
import webservices.PaqueteWebServiceService;
import webservices.UsuarioWebService;
import webservices.UsuarioWebServiceService;

/**
 * Servlet implementation class Imagenes
 */
@WebServlet("/imagenes")
public class Imagenes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Imagenes() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        
        String id = (String) request.getParameter("id");
        String of = (String) request.getParameter("of");
        String pq = (String) request.getParameter("pq");
        byte[] img = null;
        
        if(id != null) {
        	UsuarioWebServiceService service = new UsuarioWebServiceService();
        	UsuarioWebService port = service.getUsuarioWebServicePort();
        	try {
        		img = port.getImagen(id);
        		response.setContentType("image/jpg");
        		response.setContentLength((int) img.length);
            	OutputStream out = response.getOutputStream();
            	//ImageIO.write(img, "png", out);
            	out.write(img);
            	out.close();
            
        	} catch (Exception ex) {            

        	}
        }
        
        if(of != null) {
    		OfertaWebServiceService service = new OfertaWebServiceService();
    		OfertaWebService port = service.getOfertaWebServicePort();
            try {
                img = port.getImagen(of);
                response.setContentType("image/jpg");
                response.setContentLength((int) img.length);
                OutputStream out = response.getOutputStream();
                //ImageIO.write(img, "png", out);
                out.write(img);
                out.close();
                
            } catch (Exception ex) {            
                ex.printStackTrace();
                System.out.println("Error imagen oferta: " + ex.getMessage());
            }
        }
        
        if(pq != null) {
        	PaqueteWebServiceService service = new PaqueteWebServiceService();
        	PaqueteWebService port = service.getPaqueteWebServicePort();
        
        	try {
        		img = port.getImagen(pq);
        		response.setContentType("image/jpg");
        		response.setContentLength((int) img.length);
            	OutputStream out = response.getOutputStream();
            	//ImageIO.write(img, "png", out);
            	out.write(img);
            	out.close();
            
        	} catch (Exception ex) {            
                ex.printStackTrace();
                System.out.println("Error imagen paquete: " + ex.getMessage());
        	}
        }
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
