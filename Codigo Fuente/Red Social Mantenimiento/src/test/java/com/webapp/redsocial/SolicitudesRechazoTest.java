package com.webapp.redsocial;

import static org.junit.Assert.assertTrue;

import com.redsocial.auxiliares.Utilidades;
import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOUsuario;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class SolicitudesRechazoTest {
	
	private Usuario emisor, receptor;
	
	@Given("^Usuario conectado para rechazar$")
	public void Usuario_conectado_para_rechazar() {
		emisor=new Usuario();
		receptor=new Usuario();
	}

	@When("^\"([^\"]*)\" es rechazado por \"([^\"]*)\"$")
	public void es_rechazado_por(String emisor, String receptor) throws Exception {
		this.emisor.setemail(emisor);
		this.emisor.setNombre(emisor);
		this.emisor.setPwd(emisor);
		DAOUsuario.insert(this.emisor);
		
		this.receptor.setemail(receptor);
		this.receptor.setNombre(receptor);
		this.receptor.setPwd(receptor);
		DAOUsuario.insert(this.receptor);
		
		Utilidades.enviarSolicitud(this.emisor, this.receptor);
	}

	@Then("^Rechazar peticion de amistad$")
	public void Rechazar_peticion_de_amistad() throws Exception {
		Utilidades.rechazarSolicitud(emisor, receptor);
		
		DAOUsuario.deleteConEmail(emisor.getemail());
		DAOUsuario.deleteConEmail(receptor.getemail());
	}

	@When("^\"([^\"]*)\" es rechazado por \"([^\"]*)\" pero no le habia enviado solicitud$")
	public void es_rechazado_por_pero_no_le_habia_enviado_solicitud(String emisor, String receptor) throws Exception {
		this.emisor.setemail(emisor);
		this.emisor.setNombre(emisor);
		this.emisor.setPwd(emisor);
		DAOUsuario.insert(this.emisor);
		
		this.receptor.setemail(receptor);
		this.receptor.setNombre(receptor);
		this.receptor.setPwd(receptor);
		DAOUsuario.insert(this.receptor);
	}

	@Then("^Mensaje de error al rechazo de la peticion$")
	public void Mensaje_de_error_al_rechazo_de_la_peticion() throws Exception {
		try {
			Utilidades.rechazarSolicitud(emisor, receptor);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("No te ha mandado solicitud"));
		}
		DAOUsuario.deleteConEmail(emisor.getemail());
		DAOUsuario.deleteConEmail(receptor.getemail());
	}

}
