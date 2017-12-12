package com.webapp.redoscial.testdesactualizados;

import static org.junit.Assert.assertTrue;

import com.redsocial.modelo.Publicacion;
import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOPublicacion;
import com.redsocial.persistencia.DAOUsuario;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class mantenimientoTest {
	

	private Usuario user;
	private Publicacion publi;
	
	@Given("^Un idUsuario para borrar$")
	public void Un_idUsuario_para_borrar() {
		user=new Usuario();
	}

	@When("^datos incorrectos para borrar$")
	public void datos_incorrectos_para_borrar() throws Exception {
		String nombre="aslkdjfsakjldf";
		String pwd="añsdljfalksdfj";
		String email="noexisto@noexisto.com";
		Usuario user1=new Usuario(nombre);
		user1.setemail(email);
		user1.setPwd(pwd);
		user=DAOUsuario.select(user1);
	}

	@Then("^no se borra$")
	public void no_se_borra() {
		assertTrue(user==null);
	}

	@Given("^Un idUsuario para modificar$")
	public void Un_idUsuario_para_modificar() {
		user=new Usuario();
	}

	@When("^datos incorrectos para modificar$")
	public void datos_incorrectos_para_modificar() throws Exception {
		String nombre="asdklfjaskf";
		String email="noexiste@noexiste.com";
		Usuario user1=new Usuario(nombre);
		user1.setemail(email);
		user=DAOUsuario.select(user1);
	}

	@Then("^no se modifica usuario$")
	public void no_se_modifica_usuario() {
		assertTrue(user==null);
	}

	@Given("^Un idPublicacion para borrar$")
	public void Un_idPublicacion_para_borrar() {
		publi=new Publicacion();
	}

	@When("^datos de publicacion incorrectos$")
	public void datos_de_publicacion_incorrectos(){
		Publicacion publi2=new Publicacion();
		publi2.setIdPublicacion("esteesunidquenoexiste");
		try {
			publi=DAOPublicacion.select(publi2.getIdPublicacion());
		} catch (Exception e) {
			publi=null;
		}
	}

	@Then("^no se borra la publicacion$")
	public void no_se_borra_la_publicacion() {
		assertTrue(publi==null);
	}

	@Given("^Una publicacion para editar$")
	public void Una_publicacion_para_editar() {
		publi=new Publicacion();
	}

	@When("^Publicacion para editar es incorrecta$")
	public void Publicacion_para_editar_es_incorrecta() {
		Publicacion publi2=new Publicacion();
		publi2.setIdPublicacion("esteesunidquenoexiste");
		try {
			publi=DAOPublicacion.select(publi2.getIdPublicacion());
		} catch (Exception e) {
			publi=null;
		}
	}

	@Then("^no se modifica la publicacion$")
	public void no_se_modifica_la_publicacion() {
		assertTrue(publi==null);
	}

}
