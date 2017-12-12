package com.webapp.redsocial;

import static org.junit.Assert.assertTrue;

import org.apache.commons.codec.digest.DigestUtils;

import com.redsocial.auxiliares.Utilidades;
import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOUsuario;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class ModificarCuentaTest {
	
	private Usuario user;
	
	@Given("^Un Usuario quiere modificar su cuenta$")
	public void Un_Usuario_quiere_modificar_su_cuenta() {
		user=new Usuario();
	}

	@When("^\"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" usuario correctos$")
	public void usuario_correctos(String nombre, String email, String pwd) throws Exception {
		Usuario aux=new Usuario();
		aux.setNombre("whatever");
		aux.setemail("whatever@hotmail.com");
		aux.setPwd(DigestUtils.md5Hex("whatever"));
		user=DAOUsuario.insert(aux);
		assertTrue(user.getNombre().equals("whatever"));
		assertTrue(user.getemail().equals("whatever@hotmail.com"));
		assertTrue(user.getPwd().equals(DigestUtils.md5Hex("whatever")));
		
		
		user.setNombre(nombre);
		user.setemail(email);
		user.setPwd(DigestUtils.md5Hex(pwd));
		DAOUsuario.update(user, 1);
	}

	@Then("^\"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" modificado correctamente$")
	public void modificado_correctamente(String nombre, String email, String pwd) throws Exception {
		assertTrue(user.getNombre().equals(nombre));
		assertTrue(user.getemail().equals(email));
		assertTrue(user.getPwd().equals(DigestUtils.md5Hex(pwd)));
		DAOUsuario.deleteConEmail(email);
	}


	@When("^\"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" usuario incorrectos$")
	public void usuario_incorrectos(String nombre, String email, String pwd) throws Exception {
		Usuario user1=new Usuario(nombre);
		user1.setemail(email);
		user1.setPwd(pwd);
		user=DAOUsuario.select(user1);
	}

	@Then("^usuario no sufre cambios$")
	public void usuario_no_sufre_cambios() {
		assertTrue(user==null);
	}
}
