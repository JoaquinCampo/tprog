package logica;

import java.util.Set;

import clases.TipoOferta;
import datatypes.DTTipoOferta;

public interface IColTipoOferta {

	public TipoOferta buscarTipoOferta(String tipoPaquete);
	public Set<String> listarTipoOferta();
	public boolean existeTipo(String tipoPaquete);
	public void agregarTipoOferta(TipoOferta tipo);
	public Set<DTTipoOferta> getTipoOfertas();
	public Set<DTTipoOferta> listarTipoOfertaConDatos();

}
