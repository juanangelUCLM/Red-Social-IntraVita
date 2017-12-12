package com.webapp.redsocial;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;

import com.redsocial.auxiliares.Utilidades;
import com.redsocial.modelo.Publicacion;
import com.redsocial.persistencia.DAOPublicacion;

import cucumber.annotation.After;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class ModificarPublicacionTest {
	private Publicacion publi;
	private static ArrayList<String> ids=new ArrayList<String>();
	
	@Given("^Usuario en el muro quiere editar$")
	public void Usuario_en_el_muro_quiere_editar() {
		publi= new Publicacion();
	}

	@When("^Cuando coinciden \"([^\"]*)\" \"([^\"]*)\" y \"([^\"]*)\" \"([^\"]*)\"  para modificar \"([^\"]*)\"$")
	public void Cuando_coinciden_y_para_modificar(String nombre, String email, String nombre2, String email2, String mensaje) {
		Publicacion aux=new Publicacion();
		aux.setNombre(nombre);
		aux.setEmail(email);
		aux.setMensaje(mensaje);
		aux.setFecha(Utilidades.obtenerFecha());
		
		publi=DAOPublicacion.insert(aux);
		ids.add(publi.getIdPublicacion());
		assertTrue(nombre.equals(nombre2) && email.equals(email2));
	}

	@Then("^sustituir la publicacion por \"([^\"]*)\"$")
	public void sustituir_la_publicacion_por(String mensaje2) throws Exception {
		publi.setMensaje(mensaje2);
		DAOPublicacion.update(publi);
		assertTrue(publi.getMensaje().equals(mensaje2));
	}

	@When("^Cuando no coinciden \"([^\"]*)\" \"([^\"]*)\" y \"([^\"]*)\" \"([^\"]*)\"  para modificar \"([^\"]*)\"$")
	public void Cuando_no_coinciden_y_para_modificar(String nombre, String email, String nombre2, String email2, String mensaje) {
		Publicacion aux=new Publicacion();
		aux.setNombre(nombre);
		aux.setEmail(email);
		aux.setMensaje(mensaje);
		aux.setFecha(Utilidades.obtenerFecha());
		
		publi=DAOPublicacion.insert(aux);
		ids.add(publi.getIdPublicacion());
		assertFalse(nombre.equals(nombre2) || email.equals(email2));
	}

	@Then("^no sustituir la publicacion por \"([^\"]*)\"$")
	public void no_sustituir_la_publicacion_por(String mensaje2) {
		assertFalse(publi.getMensaje().equals(mensaje2));
	}
	@After
	public static void despuesAll(){
		if(ids.size()==4) {
			Iterator <String> it=ids.iterator();
			while(it.hasNext())
				DAOPublicacion.delete(it.next());
		}
	}
}
