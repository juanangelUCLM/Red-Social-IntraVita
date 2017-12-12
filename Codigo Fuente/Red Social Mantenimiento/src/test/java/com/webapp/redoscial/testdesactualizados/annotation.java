package com.webapp.redoscial.testdesactualizados;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.codec.digest.DigestUtils;

import com.redsocial.auxiliares.Utilidades;
import com.redsocial.modelo.MensajesPrivados;
import com.redsocial.modelo.Publicacion;
import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOMensajesPrivados;
import com.redsocial.persistencia.DAOPublicacion;
import com.redsocial.persistencia.DAOUsuario;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

/**
 * 
 * @author Usuario
 *
 */
public class annotation {
	
	private DAOUsuario usuario;
	private Usuario user;
	private Publicacion publi;
	private MensajesPrivados msgprivate;
	
	@Given("^Un usuario y password$")
	public void Un_usuario_y_password() {
		usuario= new DAOUsuario(); 
		user=new Usuario();
	}

	@When("^usuario correcto y password correcta$")
	public void usuario_correcto_y_password_correcta() {
		String email="asdf@gmail.com";
		String pwd="1234";
		user.setemail(email);
		user.setPwd(pwd);
		try {
			user=DAOUsuario.select(user);
		} catch (Exception e) {
			assertFalse(true);
		}
		
	}
	
	@Then("^se loguea$")
	public void se_loguea() throws Throwable {
		if(user!=null) {
			assertFalse(null==user);
		}
	}
	
	
	@SuppressWarnings("static-access")
	@When("^Usuario incorrecto y una password incorrecta$")
	public void Usuario_incorrecto_y_una_password_incorrecta() {
	   String email="pascasia@hotmail.com";
	   String pwd="1";
	   user.setemail(email);
	   user.setPwd(pwd);
	   try {
		   user=usuario.select(user);
	   }catch(Exception e) {
		   assertFalse(true);
	   }
	   
	}

	@Then("^no se loguea$")
	public void no_se_loguea() {
	    if(user==null)
	    	assertTrue(null==user);
	}
	
	@Given("^Un nombre, un email, una pwduno y una pwddos$")
	public void Un_nombre_un_email_una_pwduno_y_una_pwddos() {
		usuario= new DAOUsuario(); 
		user=new Usuario();
	}

	
	@When("^pwduno es igual a pwddos$")
	public void pwduno_es_igual_a_pwddos() throws Exception {
		String pwd1= "1234";
		String pwd2= "1234";
		String email="emailinsertado@hotmail.com";
		Usuario user1 = new Usuario("Josefa");
		user1.setPwd(pwd1);
		user1.setemail(email);
		user1=DAOUsuario.select(user1);
		if(user1!=null)
			DAOUsuario.delete(user1.getid());
		
		user1 = new Usuario("Josefa");
		user1.setPwd(pwd1);
		user1.setemail(email);
		
		if(pwd1.equals(pwd2)) {
			user=DAOUsuario.insert(user1);
		}
	}

	@Then("^se registra$")
	public void se_registra() {
		assertTrue(null!=user);
	}
	
	@When("^pwduno es distinto a pwddos$")
	public void pwduno_es_distinto_a_pwddos() {
		String pwd1 = "1234";
		String pwd2= "123456";
		if(!pwd1.equals(pwd2)) {
			user= null;
		}	
	    
	}
	
	@When("^email ya esta en la base de datos$")
	public void email_ya_esta_en_la_base_de_datos() throws Exception {
	  String email = "Prueba24@gmail.com";  
	  Usuario user1= new Usuario("Prueba24");
	  user1.setemail(email);
	  user=DAOUsuario.select(user1);
	}
	
	@Then("^no se registra$")
	public void no_se_registra() {
		assertTrue(user==null);
	}
	
	@Given("^Un idUsuario$")
	public void Un_idUsuario() {
		usuario= new DAOUsuario(); 
		user=new Usuario();
	}

	@When("^datos correctos$")
	public void datos_correctos() throws Exception {
	    String nombre="cachopo2";
	    String pwd="1234";
	    String email="cachopo2@yopmail.com";
	    Usuario user1 = new Usuario(nombre);
	    user1.setemail(email);
	    user1.setPwd(pwd);
	    user=DAOUsuario.select(user1);
	    if(user==null)
	    	user=DAOUsuario.insert(user1);
	}

	@Then("^se borra$")
	public void se_borra() throws Exception {
	    DAOUsuario.delete(user.getid());
	}

	@Given("^Usuario en wall$")
	public void Usuario_en_wall() {
	    publi=new Publicacion();
	}

	@When("^escribes publicacion$")
	public void escribes_publicacion() {
	    Publicacion publi2=new Publicacion();
	    publi2.setNombre("Mariap");
	    publi2.setEmail("asdf@gmail.com");
	    publi2.setFecha("19/11/2017 16:24");
	    publi2.setMensaje("hola hola hola");
		publi=DAOPublicacion.insert(publi2);
	}

	@Then("^publicas$")
	public void publicas() {
	    assertTrue(publi!=null);
	}
	
	@Given("^Un idpublicacion$")
	public void Un_idpublicacion() {
	    publi=new Publicacion();
	}

	@When("^datos publicacion correctos$")
	public void datos_publicacion_correctos() throws Exception {
		Publicacion publi2=new Publicacion();
		publi2.setNombre("prueba30");
		publi2.setEmail("prueba30@hotmail.com");
		publi2.setFecha("03/11/2017 0:22");
		publi2.setMensaje("ESTA ES PARA BORRAR");
		publi=DAOPublicacion.insert(publi2);
	}

	@Then("^se borra publicacion$")
	public void se_borra_publicacion() throws Exception {
		DAOPublicacion.delete(publi.getIdPublicacion());
		assertTrue(null==DAOPublicacion.select(publi.getIdPublicacion()));
	}
	
	@Given("^Un Usuario$")
	public void Un_Usuario() {
		usuario= new DAOUsuario(); 
		user=new Usuario();
	}

	@When("^datos usuario correctos$")
	public void datos_usuario_correctos() throws Exception {
		String nombre="prueba30";
		String email="prueba30@hotmail.com";
	    Usuario user1 = new Usuario(nombre);
	    user1.setemail(email);
	    user=DAOUsuario.select(user1);
	}

	@Then("^se modifica usuario$")
	public void se_modifica_usuario() throws Exception {
		if(user!=null)
			user.setPwd(DigestUtils.md5Hex("PRUEBAprueba"));
	    	DAOUsuario.update(user, 1);
	}
	
	@Given("^Una publicacion$")
	public void Una_publicacion() {
	    publi=new Publicacion();
	}

	@When("^publicacion correcta$")
	public void publicacion_correcta() throws Exception {
		Publicacion publi2=new Publicacion();
		publi2.setNombre("prueba30");
		publi2.setEmail("prueba30@hotmail.com");
		publi2.setFecha(Utilidades.obtenerFecha());
		publi2.setMensaje("ESTA ES PARA EDITAR");
		publi=DAOPublicacion.insert(publi2);
	}

	@Then("^se modifica publicacion$")
	public void se_modifica_publicacion() throws Exception {
		if(publi!=null)
	    	DAOPublicacion.delete(publi.getIdPublicacion());
			Publicacion publi2=new Publicacion();
			publi2.setNombre("prueba30");
			publi2.setEmail("prueba30@hotmail.com");
			publi2.setFecha(Utilidades.obtenerFecha());
			publi2.setMensaje("mod testing");
			publi=DAOPublicacion.insert(publi2);
	}
	
	@Given("^Un mensaje privado$")
	public void Un_mensaje_privado() {
	   msgprivate=new MensajesPrivados();
	}

	@When("^mensaje privado correcto$")
	public void mensaje_privado_correcto() {
		
	    msgprivate.setEmisor("prueba30@hotmail.com");
	    msgprivate.setDestinatario("bu@hotmail.com");
	    msgprivate.setFecha(Utilidades.obtenerFecha());
	    msgprivate.setMensaje("hola esto es testing");
	    DAOMensajesPrivados.insert(msgprivate);
	}

	@Then("^se manda mensaje privado$")
	public void se_manda_mensaje_privado() {
	    assertTrue(msgprivate!=null);
	}
	
}