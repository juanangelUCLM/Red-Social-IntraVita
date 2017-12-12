package com.webapp.redsocial;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;

import com.redsocial.auxiliares.Utilidades;
import com.redsocial.modelo.Like;
import com.redsocial.modelo.Publicacion;
import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOLike;
import com.redsocial.persistencia.DAOPublicacion;

import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class DarLikeTest {
	
	private Like like;
	private static ArrayList<String> emails=new ArrayList<String>();
	private static Publicacion publicacion;
	private Usuario usuario;
	private static int insertarPublicacion=0;
	
	@Before
	public void beforeAll() {
		if(insertarPublicacion==0) {
			publicacion=new Publicacion();
			publicacion.setNombre("YOLO");
			publicacion.setEmail("YOLO");
			publicacion.setFecha(Utilidades.obtenerFecha());
			publicacion.setMensaje("YOLO");
			publicacion=DAOPublicacion.insert(publicacion);
			insertarPublicacion=1;
		}
	}
	@Given("^Usuario conectado quiere dar me gusta$")
	public void Usuario_conectado_quiere_dar_me_gusta() {
		usuario=new Usuario();
	}

	@When("^No le gusta a \"([^\"]*)\"$")
	public void No_le_gusta_a(String email) throws Exception {
		String idPublicacion=publicacion.getIdPublicacion();
		Like aux=DAOLike.checkLike(idPublicacion, email);
		if(aux!=null) {
			DAOLike.delete(aux);
			aux=null;
		}
		assertTrue(aux==null);
		like=new Like(email, idPublicacion);
		emails.add(email);
	}

	@Then("^Le gusta publicacion$")
	public void Le_gusta_publicacion() throws Exception {
		DAOLike.insert(like);
		Like aux=DAOLike.checkLike(like.getIdPublicacion(), like.getEmailUsuario());
		assertTrue(aux!=null);
	}

	@When("^Le gusta a \"([^\"]*)\"$")
	public void Le_gusta_a(String email) throws Exception {
		String idPublicacion=publicacion.getIdPublicacion();
		Like aux=DAOLike.checkLike(idPublicacion, email);
		assertTrue(aux!=null);
		like=new Like(email, idPublicacion);
		emails.add(email);
	}

	@Then("^No dar me gusta$")
	public void No_dar_me_gusta() throws Exception {
		Like aux=DAOLike.checkLike(like.getIdPublicacion(), like.getEmailUsuario());
		assertTrue(aux!=null);
	}

	@Given("^Usuario conectado quiere quitar me gusta$")
	public void Usuario_conectado_quiere_quitar_me_gusta() {
		usuario=new Usuario();
	}

	@Then("^Dejar de gustar publicacion$")
	public void Dejar_de_gustar_publicacion() throws Exception {
		DAOLike.delete(like);
		Like aux=DAOLike.checkLike(like.getIdPublicacion(), like.getEmailUsuario());
		assertTrue(aux==null);
	}

	@Then("^No quitar me gusta$")
	public void No_quitar_me_gusta() throws Exception {
		Like aux=DAOLike.checkLike(like.getIdPublicacion(), like.getEmailUsuario());
		assertTrue(aux==null);
	}
	
	@After
	public void afterAll() {
		if(emails.size()==8) {
			DAOPublicacion.delete(publicacion.getIdPublicacion());
			Iterator <String> it=emails.iterator();
			while(it.hasNext())
				DAOLike.delete(new Like(it.next(), publicacion.getIdPublicacion()));
		}		
	}
}
