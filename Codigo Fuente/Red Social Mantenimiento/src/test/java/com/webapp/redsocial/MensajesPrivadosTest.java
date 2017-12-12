package com.webapp.redsocial;

import static org.junit.Assert.assertTrue;

import org.bson.types.ObjectId;

import com.redsocial.auxiliares.Utilidades;
import com.redsocial.modelo.MensajesPrivados;
import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOMensajesPrivados;
import com.redsocial.persistencia.DAOUsuario;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class MensajesPrivadosTest {
	
	private MensajesPrivados mensajeprivado;
	private Usuario emisor, receptor;
	
	@Given("^Un usuario quiere enviar un mensaje privado$")
	public void Un_usuario_quiere_enviar_un_mensaje_privado() {
		mensajeprivado=new MensajesPrivados();
		emisor=new Usuario();
		receptor=new Usuario();
	}

	@When("^\"([^\"]*)\" \"([^\"]*)\" son correctos y \"([^\"]*)\" \"([^\"]*)\" existe$")
	public void son_correctos_y_existe(String emisornombre, String emisoremail, String receptornombre, String receptoremail) throws Exception {
		Usuario aux=new Usuario();
		aux.setemail(emisoremail);
		emisor=DAOUsuario.select(aux);
		aux.setemail(receptoremail);
		receptor=DAOUsuario.select(aux);
		assertTrue(receptor!=null && emisor!=null);
		assertTrue(emisornombre.equals(emisor.getNombre()) && receptornombre.equals(receptor.getNombre()));
		mensajeprivado.setDestinatario(receptoremail);
		mensajeprivado.setEmisor(emisoremail);
		mensajeprivado.setFecha(Utilidades.obtenerFecha());
	}

	@Then("^\"([^\"]*)\" enviado$")
	public void enviado(String mensaje) throws Exception {
		mensajeprivado.setMensaje(mensaje);
		ObjectId id=DAOMensajesPrivados.insert(mensajeprivado);
		DAOMensajesPrivados.delete(id.toString());
	}

	@When("^\"([^\"]*)\" \"([^\"]*)\" son correctos pero \"([^\"]*)\" \"([^\"]*)\" no existe$")
	public void son_correctos_pero_no_existe(String emisornombre, String emisoremail, String receptornombre, String receptoremail) throws Exception {
		Usuario aux=new Usuario();
		aux.setemail(emisoremail);
		emisor=DAOUsuario.select(aux);
		aux.setemail(receptoremail);
		receptor=DAOUsuario.select(aux);
		assertTrue(receptor==null && emisor!=null);
	}

	@Then("^\"([^\"]*)\" no enviado$")
	public void no_enviado(String mensaje) {
		assertTrue(true);
	}
}
