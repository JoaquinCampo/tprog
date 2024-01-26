package excepciones;

import jakarta.xml.ws.WebFault;

@WebFault(name = "VideoNoValido")
public class VideoNoValido extends Exception {
	private static final long serialVersionUID = 1L;
	public VideoNoValido(String mensaje) {
		super(mensaje);
	}
}

