package logica;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.HashSet;

import clases.Paquete;
import datatypes.DTPaquete;

public class ColeccionPaquete implements IColPaquete {
	private Map<String, Paquete> colPaquete;
	private static ColeccionPaquete instancia = null;
	
	// Singleton
	private ColeccionPaquete() {
		colPaquete = new HashMap<String, Paquete>();
	}
	public static ColeccionPaquete getInstance() {
		if (instancia == null)  instancia = new ColeccionPaquete();
			return instancia;
	}
	
	public static String quitarTildes(String textoConTildes) {
        // Usamos Normalizer para descomponer y eliminar las tildes
        String textoNormalizado = Normalizer.normalize(textoConTildes, Normalizer.Form.NFD);
        
        // Usamos una expresión regular para eliminar los caracteres no ASCII
        Pattern patron = Pattern.compile("[^\\p{ASCII}]");
        
        // Sustituimos los caracteres acentuados por sus equivalentes sin tilde
        return patron.matcher(textoNormalizado).replaceAll("");
    }
	//operaciones

	/*
	public Paquete[] getPaquetes() {
		ArrayList<Paquete> retorno = new ArrayList<Paquete>();
		int i = 0;
		if (this.colPaquete.isEmpty()){
			return null;
		}
		else {
			for(Paquete paq : this.colPaquete.values()) 
			{
				retorno.add(paq);
			}
			return retorno.toArray(new Paquete[retorno.size()]);
		}
	}*/
	
	@Override
	public Set<DTPaquete> listarPaquete() {
		Set<DTPaquete> retorno = new HashSet<DTPaquete>();
		if (this.colPaquete.isEmpty()){
			return null;
		}
		else {
			for (Paquete paq : this.colPaquete.values()) {
				DTPaquete dtpaq = paq.obtenerDTPaquete();
				retorno.add(dtpaq);      
			}
			return retorno;
		}
	}
	@Override
	public void aniadirPaquete(Paquete paq) {
		String clave = paq.getNombre();
    	clave = quitarTildes(clave);
    	clave = clave.toLowerCase();
		this.colPaquete.put(clave, paq);
		
	}
	@Override
	public boolean existeNombre(String nombre) {
		String clave = nombre;
    	clave = quitarTildes(clave);
    	clave = clave.toLowerCase();
		return this.colPaquete.containsKey(clave);
	}
	@Override
	public Paquete getPaquete(String nombre) {
		String clave = nombre;
    	clave = quitarTildes(clave);
    	clave = clave.toLowerCase();
		return this.colPaquete.get(clave);
	}
	
}




