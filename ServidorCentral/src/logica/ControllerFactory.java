package logica;

public class ControllerFactory {
    private static ControllerFactory instance = null;

    private ControllerFactory() {}

    public static ControllerFactory getInstance() {
        if (instance == null) {
            instance = new ControllerFactory();
        }
        return instance;
    }

    public IConUsuario getIControladorUsuario() {
        return new ControladorUsuario();
    }

    public IConPaquete getIControladorPaquete() {
    	return new ControladorPaquete();
    }

	public IConOferta getIControladorOferta() {
		return new ControladorOferta();
	}
    
}

