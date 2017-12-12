package com.webapp.redsocial;

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

public class PublicarTest {
	
	private Publicacion publi;
	private static ArrayList<String> ids=new ArrayList<String>();
	
	@Given("^Usuario en el muro quiere publicar$")
	public void Usuario_en_el_muro_quiere_publicar() {
		publi=new Publicacion();
	}

	@When("^\"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" con privacidad publica$")
	public void con_privacidad_publica(String nombre, String email, String mensaje) {
		Publicacion publi2=new Publicacion();
		publi2.setNombre(nombre);
		publi2.setEmail(email);
		publi2.setFecha(Utilidades.obtenerFecha());
		publi2.setMensaje("ESTA ES PARA BORRAR");
		publi=DAOPublicacion.insert(publi2);
		ids.add(publi.getIdPublicacion());
	}

	@Then("^publicacion publica correcta$")
	public void publicacion_publica_correcta() throws Exception {
		DAOPublicacion.delete(publi.getIdPublicacion());
		assertTrue(null==DAOPublicacion.select(publi.getIdPublicacion()));		
	}
	
	@After
	public static void despuesAll(){
		if(ids.size()==2) {
			Iterator <String> it=ids.iterator();
			while(it.hasNext())
				DAOPublicacion.delete(it.next());
		}
	}
}
