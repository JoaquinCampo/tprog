package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import net.java.dev.jaxb.array.StringArray;
import webservices.EmpresaNoExiste_Exception;
import webservices.Exception_Exception;
import webservices.OfertaWebService;
import webservices.OfertaWebServiceService;
import webservices.OfertaYaExiste_Exception;
import webservices.TipoOfertaNoExiste_Exception;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "MetodoDePago", urlPatterns = {"/metododepagoservlet"})
/**
 * Servlet implementation class MetodoDePago
 */
public class MetodoDePago extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MetodoDePago() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		OfertaWebServiceService serviceOf = new OfertaWebServiceService();
		OfertaWebService portOf = serviceOf.getOfertaWebServicePort();
		
		String nombreEmpresa = (String) session.getAttribute("nickname");
		String nombre_oferta = (String) session.getAttribute("nombre_oferta");
		String tipoOferta_oferta = (String) session.getAttribute("tipoOferta_oferta");
		String descripcion_oferta = (String) session.getAttribute("descripcion_oferta");
		String horario_oferta = (String) session.getAttribute("horario_oferta");
		String remuneracion_oferta = (String) session.getAttribute("remuneracion_oferta");
		String departamento_oferta = (String) session.getAttribute("departamento_oferta");
		String ciudad_oferta = (String) session.getAttribute("ciudad_oferta");
		String[] keywords_oferta = (String[]) session.getAttribute("keywords_oferta");
		String fecha_oferta = (String) session.getAttribute("fecha_oferta");
		
		Boolean con_imagen = (Boolean) session.getAttribute("con_imagen");
		byte[] imageBytes = (byte[]) session.getAttribute("imageBytes");
		
		String nombre_paquete = (String) request.getParameter("nombrePaquete");
		
		//convierto los datos a algo aceptable
		float remuneracion;
		try {
		    remuneracion = Float.parseFloat(remuneracion_oferta);
		} catch (NumberFormatException e) {
		    remuneracion = 0.0f;
		}
		

		StringArray keywebservice = new StringArray();
		if (keywords_oferta != null) {
		for (String keyword : keywords_oferta) {
			keywebservice.getItem().add(keyword);
		}}
				
		try {
			portOf.altaOfertaLaboral(nombreEmpresa, tipoOferta_oferta, nombre_oferta, descripcion_oferta, horario_oferta, remuneracion, ciudad_oferta, departamento_oferta, fecha_oferta, keywebservice);
			portOf.comprarOfertaConPaquete(nombreEmpresa,nombre_oferta, tipoOferta_oferta, nombre_paquete);
		} catch (EmpresaNoExiste_Exception | Exception_Exception | OfertaYaExiste_Exception
				| TipoOfertaNoExiste_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(con_imagen) {
			portOf.agregarImagen(nombre_oferta, imageBytes);
		}
		
		session.removeAttribute("nombre_oferta");
		session.removeAttribute("tipoOferta_oferta");
		session.removeAttribute("descripcion_oferta");
		session.removeAttribute("horario_oferta");
		session.removeAttribute("remuneracion_oferta");
		session.removeAttribute("departamento_oferta");
		session.removeAttribute("ciudad_oferta");
		session.removeAttribute("keywords_oferta");
		session.removeAttribute("fecha_oferta");
		
		session.removeAttribute("con_imagen");
		session.removeAttribute("imageBytes");
		
		request.setAttribute("nombreOferta", nombre_oferta);
		request.setAttribute("descripcion", descripcion_oferta);
        request.setAttribute("departamento", departamento_oferta);
        request.setAttribute("ciudad", ciudad_oferta);
        request.setAttribute("horario", horario_oferta);
		request.setAttribute("sueldo", remuneracion);
		request.setAttribute("keywords", keywords_oferta);
		
		String fecha_pasar = portOf.obtenerFechaOfertaString(nombre_oferta);
		request.setAttribute("fechaPublicacion", fecha_pasar);
		
		request.getRequestDispatcher("/WEB-INF/jsp/consultaOfertaEmpresa.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
