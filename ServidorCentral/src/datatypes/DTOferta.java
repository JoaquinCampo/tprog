package datatypes;

import java.time.LocalDate;
import java.util.Objects;

public class DTOferta {
	private String nombre;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private String horario;
	private float sueldo;
	private LocalDate fecha;
	private float costo;
	private Estados estado;
	private byte[] imagen;
	private String empresa;
	private int cantFavs;


	public DTOferta(String nombre, String descripcion, String ciudad, String departamento, String horario, float sueldo,
			LocalDate fecha, float costo, Estados estado, byte[] imagen, String empresa, int cantFavs) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setCiudad(ciudad);
		this.setDepartamento(departamento);
		this.setHorario(horario);
		this.setSueldo(sueldo);
		this.setFecha(fecha);
		this.setCosto(costo);
		this.setEstado(estado);
		this.setImagen(imagen);
		this.setEmpresa(empresa);
		this.setCantFavs(cantFavs);
	}
	
	public DTOferta() {
		
	}

	public int getCantFavs() {
		return this.cantFavs;
	}
	
	public void setCantFavs(int cant) {
		this.cantFavs = cant;
	}
	
	public String getNombre() {
		return nombre;
	}
	public Estados getEstado() {
		return estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public String getHorario() {
		return horario;
	}

	public float getSueldo() {
		return sueldo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public float getCosto() {
		return costo;
	}
	
	public byte[] getImagen() {
    	return imagen;
    }

	public String getEmpresa() {
		return this.empresa;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setEstado(Estados estado) {
		this.estado = estado;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public void setImagen(byte[] imagen) {
    	this.imagen = imagen;
    }
	
	public boolean isEqual(DTOferta comparator) {
    	if (this == comparator) {
    		return true;
    	}
    	if (comparator == null) {
    		return false;
    	}
    	
    	if (!Objects.equals(this.getNombre(), comparator.getNombre())) {
    		return false;
    	}
    	if (!Objects.equals(this.getEstado(), comparator.getEstado())) {
    		return false;
    	}
    	if (!Objects.equals(this.getDescripcion(), comparator.getDescripcion())) {
    		return false;
    	}
    	if (!Objects.equals(this.getCiudad(), comparator.getCiudad())) {
    		return false;
    	}
    	if (!Objects.equals(this.getDepartamento(), comparator.getDepartamento())) {
    		return false;
    	}
    	if (!Objects.equals(this.getHorario(), comparator.getHorario())) {
    		return false;
    	}
    	if (!Objects.equals(this.getSueldo(), comparator.getSueldo())) {
    		return false;
    	}
    	if (!Objects.equals(this.getFecha(), comparator.getFecha())) {
    		return false;
    	}
    	if (!Objects.equals(this.getCosto(), comparator.getCosto())) {
    		return false;
    	}
    	if (!Objects.equals(this.getImagen(), comparator.getImagen())) {
    		return false;
    	}
    	if (!Objects.equals(this.getEmpresa(), comparator.getEmpresa())) {
    		return false;
    	}
    	return true;
    }
}
