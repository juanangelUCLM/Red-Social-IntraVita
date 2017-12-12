package com.webapp.redsocial;

import static org.junit.Assert.assertTrue;

import com.redsocial.auxiliares.Utilidades;
import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOUsuario;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class SolicitudesEnvioTest {
	
	private Usuario emisor, receptor;
	
	@Given("^Usuario conectado para enviar solicitud$")
	public void Usuario_conectado_para_enviar_solicitud() {
		emisor=new Usuario();
		receptor=new Usuario();
	}

	@When("^\"([^\"]*)\" quiere enviar solicitud a \"([^\"]*)\"$")
	public void quiere_enviar_solicitud_a(String emisor, String receptor) throws Exception {
		this.emisor.setemail(emisor);
		this.emisor.setNombre(emisor);
		this.emisor.setPwd(emisor);
		DAOUsuario.insert(this.emisor);
		
		this.receptor.setemail(receptor);
		this.receptor.setNombre(receptor);
		this.receptor.setPwd(receptor);
		DAOUsuario.insert(this.receptor);
		
		assertTrue(!DAOUsuario.obtenerSolicitudes(this.receptor).contains(emisor));
		assertTrue(!DAOUsuario.obtenerEnviosSolicitud(this.emisor).contains(receptor));
	}

	@Then("^Envio solicitud al segundo$")
	public void Envio_solicitud_al_segundo() throws Exception {
		DAOUsuario.enviarSolicitud(this.emisor, this.receptor);
		assertTrue(DAOUsuario.obtenerSolicitudes(this.receptor).contains(emisor.getemail()));
		assertTrue(DAOUsuario.obtenerEnviosSolicitud(this.emisor).contains(receptor.getemail()));
		
		DAOUsuario.deleteConEmail(emisor.getemail());
		DAOUsuario.deleteConEmail(receptor.getemail());
	}

	@When("^\"([^\"]*)\" quiere enviar solicitud a \"([^\"]*)\" pero son amigos$")
	public void quiere_enviar_solicitud_a_pero_son_amigos(String emisor, String receptor) throws Exception {
		this.emisor.setemail(emisor);
		this.emisor.setNombre(emisor);
		this.emisor.setPwd(emisor);
		DAOUsuario.insert(this.emisor);
		
		this.receptor.setemail(receptor);
		this.receptor.setNombre(receptor);
		this.receptor.setPwd(receptor);
		DAOUsuario.insert(this.receptor);
		
		DAOUsuario.enviarSolicitud(this.emisor, this.receptor);
		DAOUsuario.aceptarSolicitud(this.emisor, this.receptor);
		assertTrue(DAOUsuario.obtenerAmigos(this.emisor).contains(receptor));
	}

	@Then("^No envio de solicitud$")
	public void No_envio_de_solicitud() throws Exception {
		try {
			Utilidades.enviarSolicitud(emisor, receptor);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("Ya sois amigos")
					|| e.getMessage().equals("Ya sois amigos"));
		}
		
		DAOUsuario.deleteConEmail(emisor.getemail());
		DAOUsuario.deleteConEmail(receptor.getemail());
	}

	@When("^\"([^\"]*)\" quiere enviar solicitud a \"([^\"]*)\" pero ya tiene solicitudes suyas$")
	public void quiere_enviar_solicitud_a_pero_ya_tiene_solicitudes_suyas(String emisor, String receptor) throws Exception {
		this.emisor.setemail(emisor);
		this.emisor.setNombre(emisor);
		this.emisor.setPwd(emisor);
		DAOUsuario.insert(this.emisor);
		
		this.receptor.setemail(receptor);
		this.receptor.setNombre(receptor);
		this.receptor.setPwd(receptor);
		DAOUsuario.insert(this.receptor);
		
		DAOUsuario.enviarSolicitud(this.emisor, this.receptor);
	}

	@Then("^Solicitud ya enviada$")
	public void Solicitud_ya_enviada() throws Exception {
		try {
			Utilidades.enviarSolicitud(emisor, receptor);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("Ya has enviado una solicitud a ese usuario.")
					|| e.getMessage().equals("Ya tienes una solicitud de ese usuario."));
		}
		
		DAOUsuario.deleteConEmail(emisor.getemail());
		DAOUsuario.deleteConEmail(receptor.getemail());
	}
}
