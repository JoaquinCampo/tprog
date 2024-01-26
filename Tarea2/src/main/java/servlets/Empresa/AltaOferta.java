package servlets.Empresa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import net.java.dev.jaxb.array.StringArray;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import webservices.DtPaquete;
import webservices.EmpresaNoExiste_Exception;
import webservices.Exception_Exception;
import webservices.TipoOfertaNoExiste_Exception;
import webservices.OfertaWebService;
import webservices.OfertaWebServiceService;
import webservices.OfertaYaExiste_Exception;
import webservices.UsuarioWebService;
import webservices.UsuarioWebServiceService;

@WebServlet(name = "AltaOferta",urlPatterns = {"/altaOferta"})
@MultipartConfig
public class AltaOferta extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaOferta() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	OfertaWebServiceService serviceOf = new OfertaWebServiceService();
		OfertaWebService portOf = serviceOf.getOfertaWebServicePort();
		//lista de tipoOferta
		List<String>  listaTipoOferta = portOf.listarTipoOferta().getItem();
		// se setea en el JSp
		request.setAttribute("listaTipoOferta", listaTipoOferta);
		
		// lista de keywords
		List<String> listaKeywords =portOf.listarKeyWord().getItem();
		// se setea en el jsp
		if (listaKeywords != null) {
			request.setAttribute("listaKeywords", listaKeywords);
		}
		String error = (String) request.getAttribute("error");
		request.setAttribute("error", error);
		
		request.getRequestDispatcher("/WEB-INF/jsp/altaOferta.jsp").forward(request, response);

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("mostrarError", false);
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OfertaWebServiceService serviceOf = new OfertaWebServiceService();
		OfertaWebService portOf = serviceOf.getOfertaWebServicePort();
	
		
		String nombre = (String) request.getParameter("nombre");
		String tipoOferta = (String) request.getParameter("tipoOferta");
		String descripcion = (String) request.getParameter("descripcion");
		String horario = (String) request.getParameter("horario");
		String remuneracionSTR = (String) request.getParameter("remuneracion");
		
		float remuneracion;
		try {
		    remuneracion = Float.parseFloat(remuneracionSTR);
		} catch (NumberFormatException e) {
		    remuneracion = 0.0f;
		}

		String departamento = (String) request.getParameter("departamento");
		String ciudad = (String)request.getParameter("ciudad");
		String[] keywordsSeleccionadas = request.getParameterValues("keywords[]");
		String conPaquete = request.getParameter("comprapaquete");
		
		HttpSession session = request.getSession();
		String nombreEmpresa = (String) session.getAttribute("nickname");
		
		//convierto el keywords seleccionadas en StringArrays para el webservice
		StringArray keywebservice = new StringArray();

		// Agregar elementos al StringArray
		if (keywordsSeleccionadas != null) {
		for (String keyword : keywordsSeleccionadas) {
			keywebservice.getItem().add(keyword);
		}}
		
		//obtengo los DT de los paquetes para crear la pagina de metodo de pago, esto hacerlo por controlador
		UsuarioWebServiceService service = new UsuarioWebServiceService();
		UsuarioWebService port = service.getUsuarioWebServicePort();
		
		List<DtPaquete> listaPaquete = port.paquetesDisponiblesParaPagar(nombreEmpresa,tipoOferta).getPaquete();
		
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

		if (listaPaquete.isEmpty()) {
			System.out.print("la listaPaquete esta vacia");
		}
		if ("true".equals(conPaquete)) {
			
			String fecha = LocalDate.now().toString();
			//Mantengo los datos en la sesion
			session.setAttribute("nombre_oferta", nombre);
			session.setAttribute("tipoOferta_oferta", tipoOferta);
			session.setAttribute("descripcion_oferta", descripcion);
			session.setAttribute("horario_oferta", horario);
			session.setAttribute("remuneracion_oferta", remuneracionSTR);
			session.setAttribute("departamento_oferta", departamento);
			session.setAttribute("ciudad_oferta", ciudad);
			session.setAttribute("keywords_oferta", keywordsSeleccionadas);
			session.setAttribute("fecha_oferta", fecha);
			session.setAttribute("con_imagen", con_imagen);
			session.setAttribute("imageBytes", imageBytes);
			
			request.setAttribute("listaDTpaquete",listaPaquete);
			request.getRequestDispatcher("/WEB-INF/jsp/metodoDePago.jsp").forward(request, response);
			return;
		} else {
				String fecha = LocalDate.now().toString();
				try {
					portOf.altaOfertaLaboral(nombreEmpresa, tipoOferta, nombre, descripcion, horario, remuneracion, ciudad, departamento, fecha, keywebservice);
					if(con_imagen) {
						portOf.agregarImagen(nombre, imageBytes);
					}
				} catch (EmpresaNoExiste_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OfertaYaExiste_Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("error", "Ya existe una oferta con el mismo nombre");
					request.getRequestDispatcher("/WEB-INF/jsp/ofertaYaExiste.jsp").forward(request, response);
				} catch (TipoOfertaNoExiste_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				
				request.setAttribute("nombreOferta", nombre);
				request.setAttribute("descripcion", descripcion);
		        request.setAttribute("departamento", departamento);
		        request.setAttribute("ciudad", ciudad);
		        request.setAttribute("horario", horario);
				request.setAttribute("sueldo", remuneracion);
				request.setAttribute("keywords", keywordsSeleccionadas);
				String fecha_pasar = portOf.obtenerFechaOfertaString(nombre);
		        request.setAttribute("fechaPublicacion", fecha_pasar);;
				
				request.getRequestDispatcher("/WEB-INF/jsp/consultaOfertaEmpresa.jsp").forward(request, response);
		
		}
		//"/indexEmpresa"
	}

}
