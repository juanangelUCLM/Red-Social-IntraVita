package com.webapp.redsocial;


import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

import static org.junit.Assert.assertTrue;


import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOUsuario;

public class BorrarCuentaTest {
	
	private Usuario user;

	

	@Given("^Un usuario quiere borrar su cuenta$")
	public void Un_usuario_quiere_borrar_su_cuenta() {
		user=new Usuario();
	}

	@When("^\"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" para borrar correctos$")
	public void para_borrar_correctos(String nombre, String email, String pwd) throws Exception {
		Usuario user1=new Usuario(nombre);
		user1.setemail(email);
		user1.setPwd(pwd);
		user=DAOUsuario.insert(user1);
		user=DAOUsuario.select(user1);
		assertTrue(user!=null);
	}

	@Then("^se borra al usuario$")
	public void se_borra_al_usuario() throws Exception {
		DAOUsuario.deleteConEmail(user.getemail());
		user=DAOUsuario.select(user);
		assertTrue(user==null);
	}

	@When("^\"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" para borrarincorrectos$")
	public void para_borrarincorrectos(String nombre, String email, String pwd) throws Exception {
		Usuario user1=new Usuario(nombre);
		user1.setemail(email);
		user1.setPwd(pwd);
		user=DAOUsuario.select(user1);
	}

	@Then("^no se borra al usuario$")
	public void no_se_borra_al_usuario() {
		assertTrue(user==null);
	}
}
