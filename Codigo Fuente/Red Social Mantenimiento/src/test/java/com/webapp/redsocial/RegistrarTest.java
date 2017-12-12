package com.webapp.redsocial;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.codec.digest.DigestUtils;

import com.redsocial.auxiliares.Utilidades;
import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOUsuario;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.annotation.After;

public class RegistrarTest {
	
	private Usuario user;
	private static ArrayList<String> emails=new ArrayList<String>();;
	
	@Given("^Un nombre, email, dos pwd para registrar$")
	public void Un_nombre_email_dos_pwd_para_registrar() {
		user=new Usuario();
	}
	
	@When("^\"([^\"]*)\" \"([^\"]*)\"  y \"([^\"]*)\" \"([^\"]*)\" se quiere registrar$")
	public void y_se_quiere_registrar(String nombre, String email, String pwd, String pwd2) throws Exception {
		emails.add(email);
		user=new Usuario();
		user.setNombre(nombre);
		user.setemail(email);
		if(DAOUsuario.select(user)==null) {
			if(pwd.equals(pwd2)) {
				user.setPwd(DigestUtils.md5Hex(pwd2));
				try {
					user=DAOUsuario.insert(user);
				}catch(Exception e) {
					user=null;
				};
			}else 
				user=null;
		}else
			user=null;
	}
	
	@Then("^se registra correctamente$")
	public void se_registra_correctamente() {
		assertTrue(user!=null);
	}
	
	@Then("^no consigue registrarse$")
	public void no_consigue_registrarse() {
		assertTrue(user==null);
	}
	
	@After
	public static void despuesAll() throws Exception{
		if(emails.size()==4) {
			Iterator <String> it=emails.iterator();
			while(it.hasNext())
				DAOUsuario.deleteConEmail(it.next());
		}
	}
}
