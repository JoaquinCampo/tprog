package logica;

import java.util.Set;

import clases.Paquete;
import datatypes.DTPaquete;

public interface IColPaquete {
	
	public Set<DTPaquete> listarPaquete();
	public void aniadirPaquete(Paquete paq);
	public boolean existeNombre(String nombre);
	public Paquete getPaquete(String nombre);
}
