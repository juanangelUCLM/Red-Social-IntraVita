package com.webapp.redsocial;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOUsuario;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class LoginTest {
	
	private Usuario user;
	
	@Given("^Un usuario y password para login$")
	public void Un_usuario_y_password_para_login() {
		user=new Usuario();
	}

	@When("^\"([^\"]*)\"  y \"([^\"]*)\" se quiere loguear$")
	public void y_se_quiere_loguear(String email, String pwd) throws Exception {
		System.out.println(email + pwd);
		user.setemail(email);
		user.setPwd(pwd);
		user=DAOUsuario.select(user);
	}

	@Then("^se loguea correctamente$")
	public void se_loguea_correctamente() {
		assertFalse(user==null);
	}

	@Then("^no consigue loguearse$")
	public void no_consigue_loguearse() {
		assertTrue(user==null);
	}


}
