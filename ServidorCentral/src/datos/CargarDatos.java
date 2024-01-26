package datos;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import logica.*;
import excepciones.*;
import datos.Cargador_imagenes;

public class CargarDatos {
	
	private Set<String> convertToSet(String keywords) {
        return new HashSet<>(Arrays.asList(keywords.split(", ")));
    }
	
	public void altaEmpresa(String nickname, String nombre, String apellido,String contraseña,String correo, String descripcion, String web,String imagen) {
		ControllerFactory fac = ControllerFactory.getInstance();
		IConUsuario icu = fac.getIControladorUsuario();
		
		try {
			icu.altaEmpresa(nickname, nombre, apellido,contraseña, correo, descripcion, web);
			icu.agregarImagen(nickname,Cargador_imagenes.getInstance().combertir(imagen));
		} catch (UsuarioNickExiste | UsuarioCorreoExiste | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void altaPostulante(String nickname, String Nombre, String Apellido,String contraseña,String correo, LocalDate fechaNacimiento, String Nacionalidad,String imagen) {
		ControllerFactory fac = ControllerFactory.getInstance();
		IConUsuario icu = fac.getIControladorUsuario();
		try {
			icu.altaPostulante(nickname, Nombre, Apellido, contraseña, correo, fechaNacimiento, Nacionalidad);
			icu.agregarImagen(nickname, Cargador_imagenes.getInstance().combertir(imagen));
		} catch (UsuarioNickExiste | UsuarioCorreoExiste | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void altaTipoPublicacion(String nombre, String descripcion, int orden, int dias, float costo, LocalDate fecha) throws TipoOfertaExiste {
		ControllerFactory fac = ControllerFactory.getInstance();
		IConOferta ico = fac.getIControladorOferta();
		
		ico.altaTipoDeOfertaLaboral(nombre, descripcion, orden, dias, costo, fecha);
	}
	
    public void altaPaquete(String nombre, String descripcion, int valides, float descuento, LocalDate fecha,int costo,String imagen)
            throws PaqueteYaExiste {
        ControllerFactory fac = ControllerFactory.getInstance();
        IConPaquete icp = fac.getIControladorPaquete();

        icp.crearPaquete(nombre, descripcion, valides, descuento, fecha);
        try {
			icp.agregarImagen(nombre, Cargador_imagenes.getInstance().combertir(imagen));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public CargarDatos()  {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
        // Alta de Postulantes
        altaPostulante("lgarcia", "Lucía", "García","awdrg543", "lgarcia85@gmail.com", LocalDate.parse("15/03/1985", formatter), "Uruguaya","Imagenes/Usuarios/U1.jpg");
        altaPostulante("matilo", "Matías", "López", "edrft543","matias.lopez90@hotmail.com", LocalDate.parse("21/08/1990", formatter), "Argentina","Imagenes/Usuarios/U2.jpg");
        altaPostulante("maro", "María", "Rodríguez", "r5t6y7u8","marrod@gmail.com", LocalDate.parse("10/11/1988", formatter), "Uruguaya","Imagenes/Usuarios/U3.jpg");
        altaPostulante("javierf", "Javier", "Fernández","45idgaf67", "javierf93@yahoo.com", LocalDate.parse("05/06/1993", formatter), "Mexicana","Imagenes/Usuarios/U4.jpg");
        altaPostulante("valen25", "Valentina", "Martínez", "poiuy987","vale87@gmail.com", LocalDate.parse("25/02/1987", formatter), "Uruguaya","Imagenes/Usuarios/U5.jpg");
        altaPostulante("andpe12", "Andrés", "Pérez","xdrgb657","anpe92@hotmail.com", LocalDate.parse("12/04/1992", formatter), "Chilena","Imagenes/Usuarios/U6.jpg");
        altaPostulante("sicam", "Camila", "Silva","mnjkiu89","camilasilva89@gmail.com", LocalDate.parse("30/09/1989", formatter), "Uruguaya","Imagenes/Usuarios/U7.jpg");
        altaPostulante("sebgon", "Sebastián", "González", "ytrewq10","gonza95@yahoo.com", LocalDate.parse("18/01/1995", formatter), "Colombiana","Imagenes/Usuarios/U8.jpg");
        altaPostulante("isabel", "Isabella", "López", "sbsplol1","loisa@gmail.com", LocalDate.parse("07/07/1991", formatter), "Uruguaya","Imagenes/Usuarios/U9.jpg");
        altaPostulante("marram02", "Martín", "Ramírez","okmnji98","marram@hotmail.com", LocalDate.parse("02/12/1986", formatter), "Argentina","Imagenes/Usuarios/U10.jpg");

        // Alta de Empresas
        altaEmpresa("EcoTech", "Sophia", "Johnson","qsxcdw43", "info@EcoTech.com", "EcoTech Innovations es una empresa lıder en soluciones tecnologicas sostenibles. Nuestro enfoque se centra en desarrollar y comercializar productos y servicios que aborden los "
        		+ "desafıos ambientales mas apremiantes de nuestro tiempo. "
        		+ "Desde sistemas de energıa renovable y dispositivos de monitorizacion ambiental hasta soluciones de gestion de residuos inteligentes, nuestra mision es proporcionar herramientas que permitan a las empresas y comunidades adoptar practicas mas ecologicas sin comprometer la eficiencia. "
        		+ "Creemos en la convergencia armoniosa entre la tecnologıa y "
        		+ "la naturaleza, y trabajamos incansablemente para impulsar "
        		+ "un futuro mas limpio y sostenible.", "http://www.EcoTechInnovations.com","Imagenes/Usuarios/U11.jpg");
        
        altaEmpresa("FusionTech", "William", "Smith","qpwoei586", "contacto@FusionTech.net", "FusionTech Dynamics es una empresa pionera en el ambito "
        		+ "de la inteligencia artificial y la automatizacion avanzada. "
        		+ "Nuestro equipo multidisciplinario de ingenieros, cientıficos "
        		+ "de datos y desarrolladores crea soluciones innovadoras que "
        		+ "aprovechan la potencia de la IA para transformar industrias. Desde la optimizacion de procesos industriales hasta "
        		+ "la creacion de asistentes virtuales altamente personalizados, "
        		+ "nuestro objetivo es revolucionar la forma en que las empresas operan y se conectan con sus clientes. Creemos en la "
        		+ "sinergia entre la mente humana y las capacidades de la IA, "
        		+ "y trabajamos para construir un mundo donde la tecnologıa "
        		+ "mejore y amplıe nuestras capacidades innatas.", "http://www.FusionTechDynamics.net","Imagenes/Usuarios/U12.jpg");
        
        altaEmpresa("GlobalHealth", "Isabella", "Brown","asdfg654","jobs@GlobalHealth.uy", "GlobalHealth Dynamics es una empresa comprometida con "
        		+ "el avance de la atencion medica a nivel mundial. Como "
        		+ "lıderes en el campo de la salud digital, desarrollamos "
        		+ "plataformas y herramientas que permiten a los profesionales "
        		+ "de la salud ofrecer diagnosticos mas precisos, tratamientos personalizados y seguimiento continuo de los pacientes. "
        		+ "Nuestra vision es crear un ecosistema de salud conectado en "
        		+ "el que los datos medicos se utilicen de manera etica y segura "
        		+ "para mejorar la calidad de vida de las personas. A traves "
        		+ "de la innovacion constante y la colaboracion con expertos medicos, estamos dando forma al futuro de la atencion "
        		+ "medica, donde la tecnologıa y la compasion se unen para "
        		+ "salvar vidas y mejorar el bienestar en todo el mundo.", "http://www.GlobalHealthDynamics.uy/info","Imagenes/Usuarios/U13.jpg");
        
        altaEmpresa("ANTEL", "Washington", "Rocha","2nru096", "jarrington@ANTEL.com.uy", "En Antel te brindamos servicios de vanguardia en tecnologıa de comunicacion en Telefonıa Movil, Fija, Banda "
        		+ "Ancha y Datos "
        		+ "", "ANTEL.com.uy","Imagenes/Usuarios/U14.jpg");
        
        altaEmpresa("MIEM", "Pablo", "Bengoechea","ibii4xo","eldiez@MIEM.org.uy", "Balance Energetico Nacional (BEN). La Direccion Nacional "
        		+ "de Energıa (DNE) del Ministerio de Industria, Energıa y "
        		+ "Minerıa (MIEM) presenta anualmente el BEN.", "MIEM.com.uy","Imagenes/Usuarios/U15.jpg");
        
        altaEmpresa("TechSolutions", "Mercedes", "Venn","1ngs03p","Mercedes@TechSolutions.com.uy", "”TechSolutions Inc.” es una empresa lıder en el sector de "
        		+ "tecnologıa de la informacion y el software. Se especializa "
        		+ "en el desarrollo de soluciones de software personalizadas "
        		+ "para empresas de diversos tama˜nos y sectores. Su enfoque "
        		+ "se centra en la creacion de aplicaciones empresariales innovadoras que optimizan procesos, mejoran la eficiencia y "
        		+ "brindan una ventaja competitiva a sus clientes.", "TechSolutions.com","Imagenes/Usuarios/U16.jpg");
		
		

	        try {
	            altaTipoPublicacion("Premium", "Obten maxima visibilidad", 1, 30, 4000, LocalDate.parse("10/08/2023",formatter));
	            altaTipoPublicacion("Destacada", "Destaca tu anuncio", 2, 15, 500, LocalDate.parse("05/08/2023", formatter));
	            altaTipoPublicacion("Estandar", "Mejora la posicion de tu anuncio", 3, 20, 150, LocalDate.parse("15/08/2023", formatter));
	            altaTipoPublicacion("Basica", "Publica de forma sencilla en la lista de ofertas", 4, 7, 50, LocalDate.parse("07/08/2023", formatter));
	        } catch(TipoOfertaExiste e) {
	            e.printStackTrace();
	        }
	        
	        
	        ControllerFactory fac = ControllerFactory.getInstance();
	        IConOferta ico = fac.getIControladorOferta();
			IConUsuario icu = fac.getIControladorUsuario();
			
			
			//agregarSeguidores
			icu.addFollower("lgarcia", "EcoTech");
			icu.addFollower("lgarcia", "FusionTech");
			icu.addFollower("lgarcia", "GlobalHealth");
			icu.addFollower("lgarcia", "ANTEL");
			icu.addFollower("lgarcia", "MIEM");
			icu.addFollower("matilo", "EcoTech");
			icu.addFollower("maro", "FusionTech");
			icu.addFollower("maro", "GlobalHealth");
			icu.addFollower("maro", "MIEM");
			icu.addFollower("maro", "TechSolutions");
			icu.addFollower("javierf", "FusionTech");
			icu.addFollower("javierf", "ANTEL");
			icu.addFollower("valen25", "GlobalHealth");
			icu.addFollower("valen25", "MIEM");
			icu.addFollower("valen25", "TechSolutions");
			icu.addFollower("andpe12", "FusionTech");
			icu.addFollower("andpe12", "ANTEL");
			icu.addFollower("andpe12", "MIEM");
			icu.addFollower("sicam", "EcoTech");
			icu.addFollower("sicam", "ANTEL");
			icu.addFollower("sebgon", "FusionTech");
			icu.addFollower("sebgon", "GlobalHealth");
			icu.addFollower("isabel", "lgarcia");
			icu.addFollower("isabel", "EcoTech");
			icu.addFollower("isabel", "FusionTech");
			icu.addFollower("isabel", "MIEM");
			icu.addFollower("EcoTech", "lgarcia");
			icu.addFollower("EcoTech", "FusionTech");
			icu.addFollower("FusionTech", "GlobalHealth");
			icu.addFollower("GlobalHealth", "lgarcia");
			icu.addFollower("GlobalHealth", "ANTEL");
			icu.addFollower("GlobalHealth", "MIEM");
			icu.addFollower("GlobalHealth", "TechSolutions");
			icu.addFollower("ANTEL", "MIEM");
			icu.addFollower("MIEM", "ANTEL");
			icu.addFollower("TechSolutions", "MIEM");
			
	        
	        //keywords
	        ico.altaKeyWord("Tiempo completo");
	        ico.altaKeyWord("Medio tiempo");
	        ico.altaKeyWord("Remoto");
	        ico.altaKeyWord("Freelance");
	        ico.altaKeyWord("Temporal");
	        ico.altaKeyWord("Permanente");
	        ico.altaKeyWord("Computacion");
	        ico.altaKeyWord("Administracion");
	        ico.altaKeyWord("Logística");
	        ico.altaKeyWord("Contabilidad");

	        
	        //ofertas
	        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yy");
	        try {
	        	ico.altaOfertaLaboral("EcoTech", "Premium", "Desarrollador Frontend", "Unete a nuestro equipo de desarrollo frontend y crea "
	        			+ "experiencias de usuario excepcionales"
	        			+ "", "09:00 - 18:00", (float)90000, "Montevideo", "Montevideo", LocalDate.parse("30/09/23",format), convertToSet("Tiempo completo, Medio tiempo, Remoto, Freelance, Temporal, Permanente"));
          	    try {
					ico.agregarImagen("Desarrollador Frontend", Cargador_imagenes.getInstance().combertir("Imagenes/Ofertas/O1.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
		        ico.altaOfertaLaboral("GlobalHealth", "Estandar", "Estratega de Negocios", "Forma parte de nuestro equipo de estrategia y contribuye al crecimiento de las empresas clientes.", "08:00 - 17:00", (float) 80000, "Punta del Este", "Maldonado", LocalDate.parse("29/09/23", format), convertToSet("Temporal"));
		        try {
					ico.agregarImagen("Estratega de Negocios", Cargador_imagenes.getInstance().combertir("Imagenes/Ofertas/O2.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        ico.altaOfertaLaboral("FusionTech", "Estandar", "Diseñador UX/UI", "Trabaja en colaboraci´on con nuestro talentoso"
		        		+ "equipo de dise˜no para crear soluciones impactantes.", "14:00 - 18:00", (float) 65000, "Rosario", "Colonia", LocalDate.parse("29/10/23", format), convertToSet("Medio tiempo, Remoto, Permanente"));
		        try {
					ico.agregarImagen("Diseñador UX/UI", Cargador_imagenes.getInstance().combertir("Imagenes/Ofertas/O3.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        ico.altaOfertaLaboral("ANTEL", "Premium", "Analista de Datos", "Ayuda a nuestros clientes a tomar decisiones informadas basadas en analisis y visualizaciones de datos", "09:00 - 13:00", (float) 40000, "Maldonado", "Maldonado", LocalDate.parse("19/10/23",format), convertToSet("Medio tiempo"));
		        try {
					ico.agregarImagen("Analista de Datos", Cargador_imagenes.getInstance().combertir("Imagenes/Ofertas/O4.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        ico.altaOfertaLaboral("MIEM", "Destacada", "Content Manager", "Gestiona y crea contenido persuasivo y relevante"
		        		+ "para impulsar la presencia en lınea de nuestros"
		        		+ "clientes.", "18:00 - 22:00", (float) 10000, "Montevideo", "Montevideo", LocalDate.parse("20/10/23", format), convertToSet("Freelance"));
		        try {
					ico.agregarImagen("Content Manager", Cargador_imagenes.getInstance().combertir("Imagenes/Ofertas/O5.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        ico.altaOfertaLaboral("TechSolutions", "Basica", "Soporte Técnico", "Ofrece un excelente servicio de soporte tecnico a"
		        		+ "nuestros clientes, resolviendo problemas y brindando"
		        		+ "soluciones.", "09:00 - 18:00", (float) 30000, "Minas", "Lavalleja", LocalDate.parse("02/11/23",format), convertToSet("Tiempo completo"));
		        try {
					ico.agregarImagen("Soporte Técnico", Cargador_imagenes.getInstance().combertir("Imagenes/Ofertas/O6.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        ico.altaOfertaLaboral("EcoTech", "Premium", "A. de Marketing Digital", "Unete a nuestro equipo de marketing y trabaja en "
		        		+ "estrategias digitales innovadoras", "10:00 - 19:00", (float)80000, "Flores", "Flores", LocalDate.parse("02/11/23", format), new HashSet<>());
		        try {
					ico.agregarImagen("A. de Marketing Digital", Cargador_imagenes.getInstance().combertir("Imagenes/Ofertas/O7.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        ico.altaOfertaLaboral("GlobalHealth", "Destacada", "Contador Senior", "Unete a nuestro equipo contable y ayuda en la ´\r\n"
		        		+ "gestion financiera de la empresa.", "08:30 - 17:30", (float) 100000, "Colonia Suiza", "Colonia", LocalDate.parse("04/11/23", format), new HashSet<>());
		        try {
					ico.agregarImagen("Contador Senior", Cargador_imagenes.getInstance().combertir("Imagenes/Ofertas/O8.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        ico.altaOfertaLaboral("ANTEL","Premium", "Tecnico/a Basico Red", "REGIMEN DE CONTRATO EN FUNCION PUB"
		        		+ "LICA EN UN TODO DE ACUERDO A LA"
		        		+ "NORMATIVA VIGENTE (LEY 16.127, DE 7 DE"
		        		+ "AGOSTO DE 1990, ART. 1°, LITERAL A) Y B)"
		        		+ "CON LA MODIFICACION INTRODUCIDA POR"
		        		+ "EL ART. 11 DE LA LEY 17.930 DE 19 DE DICIEM"
		        		+ "BRE DE 2005).", "09:00 - 17:00", (float) 40000, "Paysandu", "Paysandu",LocalDate.parse("29/10/23", format), new HashSet<>());
		        try {
					ico.agregarImagen("Tecnico/a Basico Red", Cargador_imagenes.getInstance().combertir("Imagenes/Ofertas/O9.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        ico.altaOfertaLaboral("EcoTech", "Destacada", "Desarrollador de Software Senior","Unete a nuestro equipo y lidera proyectos de desar" +
		        		"rollo de software sostenible y ecologico. Impulsa la innovacion y contribuye a un futuro mas verde." , "09:00 - 16:00", (float) 124000, "Montevideo", "Montevideo", LocalDate.parse("04/11/23", format), new HashSet<>());
		        try {
					ico.agregarImagen("Desarrollador de Software Senior", Cargador_imagenes.getInstance().combertir("Imagenes/Ofertas/O10.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        ico.altaOfertaLaboral("TechSolutions", "Premium", "Desarrollador de Software Full Stack","Unete a nuestro equipo para crear soluciones de soft"
		        		+ "ware personalizadas de extremo a extremo. Colabora "
		        		+ "en proyectos emocionantes y desafiantes." , "04:00 - 13:00", (float) 135000, "Rıo Negro ", "Fray Bentos", LocalDate.parse("25/10/23", format), new HashSet<>());
      	        try {
					ico.agregarImagen("Desarrollador de Software Full Stack", Cargador_imagenes.getInstance().combertir("Imagenes/Ofertas/O11.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        ico.altaOfertaLaboral("TechSolutions", "Destacada", "Gerente de Proyecto","Unete a nuestro equipo de gesti´on de proyectos y lid"
		        		+ "era la entrega exitosa de soluciones de software per"
		        		+ "sonalizadas. Colabora con equipos multidisciplinar"
		        		+ "ios y clientes exigentes." , "04:00 - 12:00", (float) 240000, "Montevideo", "Montevideo", LocalDate.parse("05/11/23", format), new HashSet<>());
		        try {
					ico.agregarImagen("Gerente de Proyecto", Cargador_imagenes.getInstance().combertir("Imagenes/Ofertas/O12.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        ico.altaOfertaLaboral("EcoTech", "Premium", "Ingeniero de Calidad de Software","Asegura la calidad de nuestros productos de software\n"
		        		+ "sostenibles. ´Unete a nosotros para garantizar un im-\n"
		        		+ "pacto positivo en el medio ambiente." , "14:00 - 18:00 ", (float) 60000, "Montevideo", "Montevideo", LocalDate.parse("01/11/23", format), new HashSet<>());
		        try {
					ico.agregarImagen("Ingeniero de Calidad de Software", Cargador_imagenes.getInstance().combertir("Imagenes/Ofertas/O13.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        ico.aceptarOferta("Desarrollador Frontend");
		        ico.aceptarOferta("Estratega de Negocios");
		        ico.aceptarOferta("Diseñador UX/UI");
		        ico.aceptarOferta("Soporte Técnico");
		        ico.aceptarOferta("A. de Marketing Digital");
		        ico.rechazarOferta("Contador Senior");
		        ico.aceptarOferta("Tecnico/a Basico Red");
		        ico.aceptarOferta("Gerente de Proyecto");
		        ico.finalizarOferta("Content Manager");
	        } catch (TipoOfertaNoExiste e) {
	            e.printStackTrace();
	        } catch (OfertaYaExiste e) {
	            e.printStackTrace();
	        } catch (EmpresaNoExiste e) {
	            e.printStackTrace();
	        }
	        
	        //ofertas favoritas
	        icu.addFav("lgarcia", "Desarrollador Frontend");
	        icu.addFav("lgarcia", "A. de Marketing Digital");
	        icu.addFav("lgarcia", "Gerente de Proyecto");
	        icu.addFav("matilo", "A. de Marketing Digital");
	        icu.addFav("maro", "Desarrollador Frontend");
	        icu.addFav("maro", "Gerente de Proyecto");
	        icu.addFav("javierf", "A. de Marketing Digital");
	        icu.addFav("valen25", "Tecnico/a B´asico Red");
	        icu.addFav("valen25","A. de Marketing Digital");


	        //postulaciones
	        try {
	            ico.altaPostulacion("Desarrollador Frontend", "lgarcia", "Licenciada en Administracion, experiencia en gestion de equipos "
	            		+ "y proyectos. Conocimientos en Microsoft Office.", "Estoy emocionada por la opor "
	            				+ "tunidad de formar parte de un equipo dinamico y contribuir con "
	            				+ "mis habilidades de liderazgo.", LocalDate.parse("01/10/23", format));
	            ico.agregarVideo("Desarrollador Frontend", "lgarcia", "https://www.youtube.com/embed/sqh77QZS0G4");
	            
	            ico.altaPostulacion("Estratega de Negocios", "matilo", "Estudiante de Comunicacion, habilidades en redaccion y manejo"
	            		+ "de redes sociales. Experiencia en practicas en medios locales.", "Me encantarıa formar parte de un equipo que me permita desar"
	            				+ "rollar mis habilidades en comunicacion y marketing.", LocalDate.parse("30/09/23", format));
	            ico.agregarVideo("Estratega de Negocios", "matilo", "https://www.youtube.com/embed/ekm1D3sKoVA");
	            
	            ico.altaPostulacion("Desarrollador Frontend", "maro", "Ingeniero en Sistemas, experiencia en desarrollo web y aplica"
	            		+ "ciones moviles. Conocimientos"
	            		+ "en JavaScript y React.", "Me entusiasma la posibilidad de"
	            				+ "trabajar en proyectos desafiantes y seguir creciendo como profe"
	            				+ "sional en el campo de la tecnologıa.", LocalDate.parse("02/10/23", format));
	            //no tiene video
	            
	            ico.altaPostulacion("Diseñador UX/UI", "javierf", "Tecnico en Electricidad, experiencia en mantenimiento indus"
	            		+ "trial. Conocimientos en lectura de planos electricos.", "Estoy interesado en formar parte de un equipo que me permita"
	            				+ "aplicar mis habilidades tecnicas y contribuir al mantenimiento eficiente.", LocalDate.parse("30/10/23", format));
	            ico.agregarVideo("Diseñador UX/UI", "javierf", "https://www.youtube.com/embed/uNCzhfQCqAs");
	            
	            ico.altaPostulacion("Estratega de Negocios", "valen25", "Musico profesional, experiencia"
	            		+ "en espectaculos en vivo. Habilidades en canto y guitarra.", "Me gustarıa combinar mi pasion por la musica con una oportu"
	            				+ "nidad laboral que me permita seguir creciendo como artista.", LocalDate.parse("30/09/23",format));
	            ico.agregarVideo("Estratega de Negocios", "valen25", "https://www.youtube.com/embed/jwiV9gbjEi8");
	        
	            ico.altaPostulacion("Estratega de Negocios", "lgarcia", "Licenciada en Administracion,"
	            		+ "me considero genia, experiencia en gestion de equipos y proyec"
	            		+ "tos. Conocimientos en Microsoft Office.", "Estoy emocionada por la opor"
	            				+ "tunidad de formar parte de un equipo dinamico y contribuir con"
	            				+ "mis habilidades de liderazgo.", LocalDate.parse("02/10/23", format));
	            //no tiene video
	            ico.altaPostulacion("Content Manager", "lgarcia", "Licenciada en Administracion, me considero la mejor menejadora de contenidos del mundo, tengo experiencia en" + 
	            				"gestion de equipos y proyectos. Conocimientos en Microsoft Office.", "Estoy emocionada por la oportunidad de formar parte de un equipo tan bonito y contribuir con mis habilidades de liderazgo.", LocalDate.parse("21/10/23", format));
	            //no tiene video
	            
	            ico.altaPostulacion("Content Manager", "valen25", "Me manejo las redes, tengo 20M de seguidores", "Me gustarıa combinar mi pasion por la musica con una oportunidad laboral que me permita seguir creciendo como artista.", LocalDate.parse("22/10/23", format));
	            ico.agregarVideo("Content Manager", "valen25", "https://www.youtube.com/embed/jwiV9gbjEi8");
	            } catch (PostulacionYaExiste e) {
	            e.printStackTrace();
	        } catch (OfertaNoExiste e) {
	            e.printStackTrace();
	        } catch (OfertaNoValidaParaEsaFecha e) {
				e.printStackTrace();
			} catch (VideoNoValido e) {
				e.printStackTrace();
			}
	        
	        /*
	         * si agregarmos los videos en las propias altas postulaciones poner los siguientes links en orden
	         * pos1: https://www.youtube.com/embed/sqh77QZS0G4
	         * pos2: https://www.youtube.com/embed/ekm1D3sKoVA
	         * pos3: ""
	         * pos4: https://www.youtube.com/embed/uNCzhfQCqAs
	         * pos5: https://www.youtube.com/embed/jwiV9gbjEi8
	         * pos6: ""
	         * pos7: ""
	         * pos8: https://www.youtube.com/embed/jwiV9gbjEi8
	         * 
	         */
	        
	        //Paquete
	        String n1 = "Basico";
	        String n2 = "Destacado";
	        String n3 = "Premium";
	        String n4 = "Express";
	        String d1 = "Publica ofertas laborales en nuestra plataforma por un periodo de 30 dias";
	        String d2 = "Publica ofertas laborales destacadas que se mostrar´a en la parte superior de los resultados de busqueda por 45 dias";
	        String d3 = "Publica ofertas laborales premium que incluye promocion en nuestras redes sociales y listado en la seccion destacada por 60 dias";
	        String d4 = "Publica ofertas laborales urgentes resaltada en color y se mostrara en la seccion de urgente por 15 dias.";
	        int p1 = 30;
	        int p2 = 45;
	        int p3 = 60;
	        int p4 = 15;
	        float ds1 = 0.20f;
	        float ds2 = 0.10f;
	        float ds3 = 0.15f;
	        float ds4 = 0.05f;
	        LocalDate f1 = LocalDate.of(2023,8,16);
	        LocalDate f2 = LocalDate.of(2023,8,15);
	        LocalDate f3 = LocalDate.of(2023,8,14);
	        LocalDate f4 = LocalDate.of(2023,8,13);
	        IConPaquete cop = fac.getIControladorPaquete();
	        try {
				cop.crearPaquete(n1, d1, p1, ds1, f1);
				try {
					cop.agregarImagen(n1, Cargador_imagenes.getInstance().combertir("Imagenes/Paquetes/paq1.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cop.crearPaquete(n2, d2, p2, ds2, f2);
				try {
					cop.agregarImagen(n2, Cargador_imagenes.getInstance().combertir("Imagenes/Paquetes/paq2.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cop.crearPaquete(n3, d3, p3, ds3, f3);
				try {
					cop.agregarImagen(n3, Cargador_imagenes.getInstance().combertir("Imagenes/Paquetes/paq3.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cop.crearPaquete(n4, d4, p4, ds4, f4);
				try {
					cop.agregarImagen(n4, Cargador_imagenes.getInstance().combertir("Imagenes/Paquetes/paq4.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } catch (PaqueteYaExiste s) {
				// TODO Auto-generated catch block
				s.printStackTrace();
			}
	        
	        //TP1 Premium
	        //TP2 Destacada
	        //TP3 Estandar
	        //TP4 Basica
	        try {
				cop.agregarTipoDeOfertaAPaquete(n1,"Premium", 1);
				cop.agregarTipoDeOfertaAPaquete(n1,"Destacada", 1);
				cop.agregarTipoDeOfertaAPaquete(n1,"Estandar", 1);
				cop.agregarTipoDeOfertaAPaquete(n2,"Estandar", 2);
	        	cop.agregarTipoDeOfertaAPaquete(n2,"Basica", 1);
	        	cop.agregarTipoDeOfertaAPaquete(n3,"Premium", 2);
	        	cop.agregarTipoDeOfertaAPaquete(n3,"Estandar", 2);
	        	cop.agregarTipoDeOfertaAPaquete(n4,"Destacada", 2);
			} catch (TipoOfertaNoExiste e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	// compra de ofertas con paquete
	        
	    	
	    	
	    	// compra de paquetes de las empresas
	        icu.comprarPaquete("EcoTech", "Basico", LocalDate.of(2023, 10, 30));
	        icu.comprarPaquete("TechSolutions", "Destacado", LocalDate.of(2023, 10, 8));
	        icu.comprarPaquete("EcoTech", "Premium", LocalDate.of(2023, 10, 31));
	        icu.comprarPaquete("FusionTech", "Destacado", LocalDate.of(2023, 10, 13));
	        icu.comprarPaquete("EcoTech", "Express", LocalDate.of(2023, 10, 1));
	        
	        
	        ico.comprarOfertaConPaquete("EcoTech","Desarrollador Frontend", "Premium", "Basico");
	        ico.comprarOfertaConPaquete("TechSolutions","Soporte Técnico", "Basica", "Destacado");
	        ico.comprarOfertaConPaquete("EcoTech","Desarrollador de Software Senior", "Destacada", "Basico");
	    }//
	}
