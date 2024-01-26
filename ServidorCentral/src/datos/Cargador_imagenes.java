package datos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import gui.TrabajoUY;
import logica.ColeccionUsuario;

public class Cargador_imagenes {
	private static Cargador_imagenes instancia = null;
	private byte[] empresa;
	private byte[] oferta;
	private byte[] paquete;
	private byte[] postulante;
	
	public static byte[] combertir(String imagePath) throws IOException {
        byte[] byteArray = null;
        try {
            ClassLoader classLoader = Cargador_imagenes.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(imagePath);

            if (inputStream != null) {
                byteArray = inputStream.readAllBytes();
            } else {
                // Si la imagen no se encuentra en recursos, puedes manejar el caso de otra manera
                // Por ejemplo, lanzar una excepción o proporcionar una imagen predeterminada.
                System.err.println("No se pudo cargar la imagen: " + imagePath);
            }
        } catch (IOException e) {
            throw e;
        }
        return byteArray;
    }
	
    private Cargador_imagenes() {
    	try {
			empresa = combertir("Imagenes/empresa.png");
	    	oferta = combertir("Imagenes/oferta.png");
	    	paquete = combertir("Imagenes/paquete.png");
	    	postulante = combertir("Imagenes/usr.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static Cargador_imagenes getInstance() {
        if (instancia == null) {
        	instancia = new Cargador_imagenes();
        }
        return instancia;
    }
    
    public byte[] getEmpresaPredefinida() {
    	return this.empresa;
    }
    
    public byte[] getOfertaPredefinida() {
    	return this.oferta;
    }
    
    public byte[] getPaquetePredefinido() {
    	return this.paquete;
    }
    
    public byte[] getPostulantePredefinido() {
    	return this.postulante;
    }
}
