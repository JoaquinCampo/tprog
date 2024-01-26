package logica;

import java.util.List;
import java.util.Set;

import clases.KeyWord;
import clases.Oferta;
import datatypes.DTOferta;

public interface IColOferta {

	public Oferta buscarOferta(String nombre);

	void agregarOferta(Oferta ofer);
	
	boolean existeOferta(String nombre);
	
	public KeyWord getKeyWord(String key);
	
	public boolean existeKey(String key);

	Set<String> listarKeyWord();

	void anadirKey(KeyWord key);
	
	void aceptarOferta(String nombre);
	void rechazarOferta(String nombre);
	void finalizarOferta(String nombre);
	
	public List<DTOferta> getOfertasRechazadas();
	public List<DTOferta> getOfertasIngresadas();
	public List<DTOferta> getOfertas();
	public List<DTOferta> getOfertasValidas();
	public List<DTOferta> getOfertasFinalisadas();
	public List<DTOferta> getOfertasVencidasConfirmadas(String nick);
	
}
