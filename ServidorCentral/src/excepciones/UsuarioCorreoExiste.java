package excepciones;

import jakarta.xml.ws.WebFault;

@WebFault(name = "UsuarioCorreoExiste")
public class UsuarioCorreoExiste extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioCorreoExiste(String mensaje) {
		super(mensaje);
	}
}
