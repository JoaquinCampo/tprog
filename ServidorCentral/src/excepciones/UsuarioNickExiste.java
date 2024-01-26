package excepciones;

import jakarta.xml.ws.WebFault;

@WebFault(name = "UsuarioNickExiste")
public class UsuarioNickExiste extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNickExiste(String mensaje) {
		super(mensaje);
	}
}
