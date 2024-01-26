package gui;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import WebServices.OfertaWebService;
import WebServices.PaqueteWebService;
import WebServices.UsuarioWebService;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import datos.CargarDatos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;




public class TrabajoUY extends JFrame{

    private static final long serialVersionUID = 1L;
	private JFrame frame;
    private JMenuBar menuBar;
    private JDesktopPane desktop;
    private ConsultaUsuario consultaUsuarioFrame;
    private AltadeOfertaLaboral altaOfertaFrame;
   // private AltaKeywords altaKeywordsFrame;
    private PostulacionaOfertaLaboral postulacionFrame;
    private AltadeTipodePublicaciondeOfertaLaboral altaTipoFrame;
    private ModificarDatosdeUsuario modificarDatosFrame;
    private ConsultadeOfertaLaboral consultaOfertaFrame;
    private CrearPaquetedeTiposdepublicacióndeOfertasLaborales crearPaqueteFrame;
    private ConsultarPaquete consultaPaqueteFrame;
    private AgregarTipodePublicaciondeOfertaLaboralaPaquete agregarTipoAPaquete;
    private AltaUsuario altaUsuarioFrame;
    private AceptarRechazarOferta aceptarOfertaFrame;
    
    
    private Image img;
    
    private boolean datosCargados = false;
    private JTextField txtTrabajoUy;
   
    
    
    public TrabajoUY() {
    	
        frame = new JFrame("Trabajo.uy");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        
        try {
        	 ClassLoader classLoader = TrabajoUY.class.getClassLoader();
        	 String imagePath = "Imagenes/FONDO.jpg";
        	 InputStream inputStream = classLoader.getResourceAsStream(imagePath);
        	 if (inputStream != null) {
        	        img = ImageIO.read(inputStream);
        	    }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al cargar la imagen de fondo.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        desktop = new JDesktopPane() {
            @Override
            protected void paintComponent(Graphics grap) {
                super.paintComponent(grap);
                if (img != null) {
                    grap.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        desktop.setPreferredSize(new Dimension(1280, 720));
        desktop.setBackground(new Color(255, 255, 255));
        frame.getContentPane().add(desktop, BorderLayout.CENTER);

        desktop.setBackground(new Color(255, 255, 255));
        frame.getContentPane().add(desktop, BorderLayout.CENTER);
        
        txtTrabajoUy = new JTextField();
        txtTrabajoUy.setEditable(false);
        txtTrabajoUy.setForeground(new Color(255, 255, 255));
        txtTrabajoUy.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 99));
        txtTrabajoUy.setText("TRABAJO UY");
        txtTrabajoUy.setBounds(320, 221, 900, 155);
        txtTrabajoUy.setColumns(10);
        txtTrabajoUy.setOpaque(false);
        txtTrabajoUy.setBorder(null);
        desktop.add(txtTrabajoUy);


        menuBar = new JMenuBar();
        
        JMenu menuInicio = new JMenu("Inicio");
        JMenuItem itemInicio = new JMenuItem("Página Principal");
        menuInicio.add(itemInicio);
        menuBar.add(menuInicio);
        
        JMenu menuRegistros = new JMenu("Registros");
        
        JMenuItem itemAltaUsuario = new JMenuItem("Alta de Usuario");
        JMenuItem itemAltaOferta = new JMenuItem("Alta de Oferta Laboral");
        JMenuItem itemAltaTipoPublicacion = new JMenuItem("Alta de Tipo de Publicación");
        JMenuItem itemCrearPaquete = new JMenuItem("Crear Paquete");
        JMenuItem itemAgregarTipoPaquete = new JMenuItem("Agregar Tipo a Paquete");
        
        itemAgregarTipoPaquete.addActionListener(e -> {
            if (agregarTipoAPaquete == null || agregarTipoAPaquete.isClosed()) {
            	agregarTipoAPaquete = new AgregarTipodePublicaciondeOfertaLaboralaPaquete();
                desktop.add(agregarTipoAPaquete);
                agregarTipoAPaquete.setVisible(true);
            }
        });
        
        
        
        itemCrearPaquete.addActionListener(e -> {
            if (crearPaqueteFrame == null || crearPaqueteFrame.isClosed()) {
            	crearPaqueteFrame = new CrearPaquetedeTiposdepublicacióndeOfertasLaborales();
                desktop.add(crearPaqueteFrame);
                crearPaqueteFrame.setVisible(true);
            }
        });
        
        itemAltaTipoPublicacion.addActionListener(e -> {
            if (altaTipoFrame == null || altaTipoFrame.isClosed()) {
            	altaTipoFrame = new AltadeTipodePublicaciondeOfertaLaboral();
                desktop.add(altaTipoFrame);
                altaTipoFrame.setVisible(true);
            }
        });
        
        itemAltaUsuario.addActionListener(e -> {
            if (altaUsuarioFrame == null || altaUsuarioFrame.isClosed()) {
                altaUsuarioFrame = new AltaUsuario();
                desktop.add(altaUsuarioFrame);
                altaUsuarioFrame.setVisible(true);
            }
        });
        
        itemAltaOferta.addActionListener(e -> {
            if (altaOfertaFrame == null || altaOfertaFrame.isClosed()) {
            	altaOfertaFrame = new AltadeOfertaLaboral();
                desktop.add(altaOfertaFrame);
                altaOfertaFrame.setVisible(true);
            }
        });
        
       
        menuRegistros.add(itemAltaUsuario);
        menuRegistros.add(itemAltaOferta);
        menuRegistros.add(itemAltaTipoPublicacion);
        menuRegistros.add(itemCrearPaquete);
        JMenuItem itemPostulacion = new JMenuItem("Postulación a Oferta");
        menuRegistros.add(itemPostulacion);
        
        itemPostulacion.addActionListener(e -> {
            if (postulacionFrame == null || postulacionFrame.isClosed()) {
            	postulacionFrame = new PostulacionaOfertaLaboral();
                desktop.add(postulacionFrame);
                postulacionFrame.setVisible(true);
            }
        });
        menuRegistros.add(itemAgregarTipoPaquete);
        
        menuBar.add(menuRegistros);
     
        JMenu menuConsultas = new JMenu("Consultas");
        
        JMenuItem itemConsultaUsuario = new JMenuItem("Consulta de Usuario");
        JMenuItem itemModificarUsuario = new JMenuItem("Modificar Datos de Usuario");
        JMenuItem itemConsultaOferta = new JMenuItem("Consulta de Oferta Laboral");
        JMenuItem itemConsultaPaquete = new JMenuItem("Consulta de Paquete");
        
        menuConsultas.add(itemConsultaUsuario);
        menuConsultas.add(itemModificarUsuario);
        menuConsultas.add(itemConsultaOferta);
        menuConsultas.add(itemConsultaPaquete);
        
        menuBar.add(menuConsultas);
        
        frame.setJMenuBar(menuBar);
        
        frame.setVisible(true);
        
        
        itemConsultaUsuario.addActionListener(e -> {
            if (consultaUsuarioFrame == null || consultaUsuarioFrame.isClosed()) {
                consultaUsuarioFrame = new ConsultaUsuario();
                desktop.add(consultaUsuarioFrame);
                consultaUsuarioFrame.setVisible(true);
            }
        });
 
        
        itemConsultaPaquete.addActionListener(e -> {
            if (consultaPaqueteFrame == null || consultaPaqueteFrame.isClosed()) {
            	consultaPaqueteFrame = new ConsultarPaquete();
                desktop.add(consultaPaqueteFrame);
                consultaPaqueteFrame.setVisible(true);
            }
        });
        
        
        itemConsultaOferta.addActionListener(e -> {
            if (consultaOfertaFrame == null || consultaOfertaFrame.isClosed()) {
            	consultaOfertaFrame = new ConsultadeOfertaLaboral();
                desktop.add(consultaOfertaFrame);
                consultaOfertaFrame.setVisible(true);
            }
        });
        
        itemModificarUsuario.addActionListener(e -> {
            if (modificarDatosFrame == null || modificarDatosFrame.isClosed()) {
            	modificarDatosFrame = new ModificarDatosdeUsuario();
                desktop.add(modificarDatosFrame);
                modificarDatosFrame.setVisible(true);
            }
        });
        
        JMenu menuHerramientas = new JMenu("Herramientas");
        JMenuItem itemCargarDatos = new JMenuItem("Cargar Datos");
        JMenuItem itemAceptarOferta = new JMenuItem("Aceptar/Rechazar oferta");


        itemCargarDatos.addActionListener(e -> {
            if (!datosCargados) {
                new CargarDatos();
                datosCargados = true;
                JOptionPane.showMessageDialog(frame, "Datos cargados con éxito!", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Los datos ya han sido cargados previamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        itemAceptarOferta.addActionListener(e -> {
        	if (aceptarOfertaFrame == null || aceptarOfertaFrame.isClosed()) {
        		aceptarOfertaFrame = new AceptarRechazarOferta();
                desktop.add(aceptarOfertaFrame);
                aceptarOfertaFrame.setVisible(true);
            }
        });

        menuHerramientas.add(itemCargarDatos);
        menuHerramientas.add(itemAceptarOferta);
        menuBar.add(menuHerramientas);

        
        menuConsultas.add(itemConsultaUsuario);
    }
    
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	new TrabajoUY();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    	
    	UsuarioWebService usrWbSrv = new UsuarioWebService();
	    usrWbSrv.publicar();

	    OfertaWebService ofWbSrv = new OfertaWebService();
	    ofWbSrv.publicar();

	    PaqueteWebService paWbSrv = new PaqueteWebService();
	    paWbSrv.publicar();
    }  
}
