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

public class BorrarPublicacionTest {
	
	private Publicacion publi;
	private static ArrayList<String> ids=new ArrayList<String>();
	
	@Given("^Usuario en el muro quiere borrar una publicacion$")
	public void Usuario_en_el_muro_quiere_borrar_una_publicacion() {
		publi=new Publicacion();
	}

	@When("^\"([^\"]*)\" \"([^\"]*)\" es autor de \"([^\"]*)\"$")
	public void es_autor_de(String nombre, String email, String mensaje) {
		Publicacion aux=new Publicacion();
		aux.setNombre(nombre);
		aux.setEmail(email);
		aux.setMensaje(mensaje);
		aux.setFecha(Utilidades.obtenerFecha());
		publi=DAOPublicacion.insert(aux);
		ids.add(publi.getIdPublicacion());
		assertTrue(email.equals(publi.getEmail()));
		if(email.equals(publi.getEmail()))
			DAOPublicacion.delete(publi.getIdPublicacion());
	}

	@Then("^borrado de publicacion correcto$")
	public void borrado_de_publicacion_correcto() throws Exception {
		Publicacion aux=DAOPublicacion.select(publi.getIdPublicacion());
		assertTrue(aux==null);
	}

	@When("^\"([^\"]*)\" \"([^\"]*)\" no es autor del \"([^\"]*)\"$")
	public void no_es_autor_del(String nombre, String email, String mensaje) {
		Publicacion aux=new Publicacion();
		aux.setNombre("Jose Maria");
		aux.setEmail("JoseMaria.Diezma@alu.uclm.es");
		aux.setMensaje(mensaje);
		aux.setFecha(Utilidades.obtenerFecha());
		publi=DAOPublicacion.insert(aux);
		ids.add(publi.getIdPublicacion());
		assertFalse(email.equals(publi.getEmail()));
		if(email.equals(publi.getEmail()))
			DAOPublicacion.delete(publi.getIdPublicacion());
	}

	@Then("^borrado de publicacion incorrecto$")
	public void borrado_de_publicacion_incorrecto() throws Exception {
		Publicacion aux=DAOPublicacion.select(publi.getIdPublicacion());
		assertTrue(aux!=null);
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
