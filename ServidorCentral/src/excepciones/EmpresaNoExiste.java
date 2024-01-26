package excepciones;

public class EmpresaNoExiste extends Exception {
	public EmpresaNoExiste(String mensaje) {
		super(mensaje);
	}
}
