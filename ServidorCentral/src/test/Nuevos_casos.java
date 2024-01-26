package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import datatypes.DTEmpresa;
import datatypes.DTOferta;
import datatypes.DTPaquete;
import datatypes.Estados;
import datatypes.ParejaCantNombre;
import datatypes.DTPostulacion;
import datatypes.DTPostulante;
import datatypes.DTTipoOferta;
import datatypes.DTUsuario;

class Nuevos_casos {

	@Test
	void test() {
		List<String> lista = new ArrayList<String>();
		Set<String> favoritos = new HashSet<String>();
		Set<DTOferta> set = new HashSet<DTOferta>();
		Set<ParejaCantNombre> cant = new HashSet<ParejaCantNombre>();
		DTEmpresa dtempresa1 = new DTEmpresa("empresa1","nombreemp1","apellidoemp1","correoemp1","webemp1","descripcion1",set,null,0,lista);
		DTEmpresa dtempresa2 = new DTEmpresa("empresa1","nombreemp1","apellidoemp1","correoemp1","webemp1","descripcion1",set,null,0,lista);
		dtempresa2.isEqual(dtempresa1);
		DTOferta dtoferta1 = new DTOferta("nombofer1","descrioof1","ciudad1","departamento1","horario",0,LocalDate.now(),0,Estados.Confirmada,null,"emprof1",0);
		DTOferta dtoferta2 = new DTOferta("nombofer1","descrioof1","ciudad1","departamento1","horario",0,LocalDate.now(),0,Estados.Confirmada,null,"emprof1",0);
		dtoferta2.isEqual(dtoferta1);
		DTPaquete dtpaquete1 = new DTPaquete("paquete1","descripcion",0,0,0,LocalDate.now(),cant,true,null);
		DTPaquete dtpaquete2 = new DTPaquete("paquete1","descripcion",0,0,0,LocalDate.now(),cant,true,null);
		dtpaquete1.isEqual(dtpaquete2);
		DTPostulacion dtpostulacion1 = new DTPostulacion("postulante1","oferta1",LocalDate.now(),"curriculum","estoyMotivado","video");
		DTPostulacion dtpostulacion2 = new DTPostulacion("postulante1","oferta1",LocalDate.now(),"curriculum","estoyMotivado","video");
		dtpostulacion1.isEqual(dtpostulacion2);
		DTPostulante dtpostulante1 = new DTPostulante("nick","nombre","apellido","correo",LocalDate.now(),"nacionalidad",set,null, favoritos,0,lista);
		DTPostulante dtpostulante2 = new DTPostulante("nick","nombre","apellido","correo",LocalDate.now(),"nacionalidad",set,null,favoritos,0,lista);
		dtpostulante1.isEqual(dtpostulante2);
		DTTipoOferta dttipooferta1 = new DTTipoOferta("nombre","descripcion",0,0,0,LocalDate.now());
		DTTipoOferta dttipooferta2 = new DTTipoOferta("nombre","descripcion",0,0,0,LocalDate.now());
		dttipooferta1.isEqual(dttipooferta2);
		DTUsuario dtusuario1 = new DTUsuario("nick","nombre","apellido","correo",null,0,lista);
		DTUsuario dtusuario2 = new DTUsuario("nick","nombre","apellido","correo",null,0,lista);
		dtusuario1.isEqual(dtusuario2);
	}
}
