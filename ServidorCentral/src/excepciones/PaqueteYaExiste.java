package excepciones;

public class PaqueteYaExiste extends Exception {
	public PaqueteYaExiste(String mensaje) {
		super(mensaje);
	}
}