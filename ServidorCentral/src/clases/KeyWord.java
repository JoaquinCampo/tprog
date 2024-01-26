package clases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import datatypes.DTOferta;

public class KeyWord {
	private Set<Oferta> ofertas;
	private String nombre;
	

	public KeyWord(String name) {
		this.nombre = name;
		this.ofertas = new HashSet<>();
		}
	
	public String getNombre() {
		return this.nombre;
	}
	
	/*public void setNombre(String nom) {
		nombre = nom;
	}*/
	
	public void agregarOferta(Oferta oferta) {
		ofertas.add(oferta);
	}
	
	public List<DTOferta> getDTOfertas() {
		List<DTOferta> dtsOferta= new ArrayList<>();
		Set<Oferta> ofertas = this.ofertas;
		for (Oferta oferta : ofertas)
			dtsOferta.add(oferta.getDTOferta());
		return dtsOferta;
	}
}
