package logica;


public class CollectionFactory {
    private static CollectionFactory instance = null;

    private CollectionFactory() {}

    public static CollectionFactory getInstance() {
        if (instance == null) {
            instance = new CollectionFactory();
        }
        return instance;
    }

    public IColUsuario getIColUsuario() {
        return ColeccionUsuario.getInstance();
    }
    
    public IColPaquete getIColPaquete() {
    	return ColeccionPaquete.getInstance();
    }

	public IColTipoOferta getIColTipoOferta() {
		return ColeccionOferta.getInstance();
	}
	
	public IColOferta getIColOferta() {
		return ColeccionOferta.getInstance();
	}
}