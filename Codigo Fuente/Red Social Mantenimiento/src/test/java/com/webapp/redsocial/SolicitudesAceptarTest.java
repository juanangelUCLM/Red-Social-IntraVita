package com.webapp.redsocial;

import static org.junit.Assert.assertTrue;

import com.redsocial.auxiliares.Utilidades;
import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOUsuario;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class SolicitudesAceptarTest {
	
	private Usuario emisor, receptor;
	
	@Given("^Usuario conectado para aceptar$")
	public void Usuario_conectado_para_aceptar() {
		emisor=new Usuario();
		receptor=new Usuario();
	}

	@When("^\"([^\"]*)\" es aceptado por \"([^\"]*)\"$")
	public void es_aceptado_por(String emisor, String receptor) throws Exception {
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

	@Then("^Aceptar como amigo$")
	public void Aceptar_como_amigo() throws Exception {
		Utilidades.aceptarSolicitud(emisor, receptor);
		
		DAOUsuario.deleteConEmail(emisor.getemail());
		DAOUsuario.deleteConEmail(receptor.getemail());
	}

	@When("^\"([^\"]*)\" es aceptado por \"([^\"]*)\" pero da error por no envio de solicitud$")
	public void es_aceptado_por_pero_da_error_por_no_envio_de_solicitud(String emisor, String receptor) throws Exception {
		this.emisor.setemail(emisor);
		this.emisor.setNombre(emisor);
		this.emisor.setPwd(emisor);
		DAOUsuario.insert(this.emisor);
		
		this.receptor.setemail(receptor);
		this.receptor.setNombre(receptor);
		this.receptor.setPwd(receptor);
		DAOUsuario.insert(this.receptor);
	}

	@Then("^Mensaje de error a la aceptacion$")
	public void Mensaje_de_error_a_la_aceptacion() throws Exception {
		try {
			Utilidades.aceptarSolicitud(emisor, receptor);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("No te ha mandado solicitud"));
		}
		
		DAOUsuario.deleteConEmail(emisor.getemail());
		DAOUsuario.deleteConEmail(receptor.getemail());
	}
}
