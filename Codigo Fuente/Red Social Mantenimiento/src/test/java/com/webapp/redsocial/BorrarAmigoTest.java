package com.webapp.redsocial;

import static org.junit.Assert.assertTrue;

import com.redsocial.auxiliares.Utilidades;
import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOUsuario;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class BorrarAmigoTest {
	
	private Usuario emisor, receptor;
	
	@Given("^Usuario conectado para eliminar un amigo$")
	public void Usuario_conectado_para_eliminar_un_amigo() {
		emisor=new Usuario();
		receptor=new Usuario();
	}

	@When("^\"([^\"]*)\" borra a  \"([^\"]*)\"$")
	public void borra_a(String emisor, String receptor) throws Exception {
		this.emisor.setemail(emisor);
		this.emisor.setNombre(emisor);
		this.emisor.setPwd(emisor);
		DAOUsuario.insert(this.emisor);
		
		this.receptor.setemail(receptor);
		this.receptor.setNombre(receptor);
		this.receptor.setPwd(receptor);
		DAOUsuario.insert(this.receptor);
		
		Utilidades.enviarSolicitud(this.emisor, this.receptor);
		Utilidades.aceptarSolicitud(this.emisor, this.receptor);
	}

	@Then("^Borrar de amigos$")
	public void Borrar_de_amigos() throws Exception {
		Utilidades.borrarAmistad(receptor, emisor);
		
		DAOUsuario.deleteConEmail(emisor.getemail());
		DAOUsuario.deleteConEmail(receptor.getemail());
	}

	@When("^\"([^\"]*)\" intenta borrar a \"([^\"]*)\" pero no son amigos$")
	public void intenta_borrar_a_pero_no_son_amigos(String emisor, String receptor) throws Exception {
		this.emisor.setemail(emisor);
		this.emisor.setNombre(emisor);
		this.emisor.setPwd(emisor);
		DAOUsuario.insert(this.emisor);
		
		this.receptor.setemail(receptor);
		this.receptor.setNombre(receptor);
		this.receptor.setPwd(receptor);
		DAOUsuario.insert(this.receptor);
		

	}

	@Then("^Mensaje de error al borrado$")
	public void Mensaje_de_error_al_borrado() throws Exception {
		try {
			Utilidades.borrarAmistad(receptor, emisor);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("No puedes eliminar a alguien que no es tu amigo"));
		}
		
		DAOUsuario.deleteConEmail(emisor.getemail());
		DAOUsuario.deleteConEmail(receptor.getemail());
	}
}
