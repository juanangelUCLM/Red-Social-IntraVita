package com.redsocial.controller;

import java.security.SecureRandom;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOUsuario;

import com.redsocial.auxiliares.SendMail;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		return "home";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String email = request.getParameter("username");
		String password = request.getParameter("password");
		Usuario user = DAOUsuario.login(email, password);
		if (user != null) {
			model.addAttribute("user", user);
			request.getSession().setMaxInactiveInterval(600);
			request.getSession().setAttribute("user", user);
			Cookie cookieCaptcha = new Cookie("cookieCaptchaLogin", "cookieControlCaptchaLogin");
 			cookieCaptcha.setMaxAge(7776000);
 			response.addCookie(cookieCaptcha);
			Long fechaModPwd = user.getFechaModPwd();
			Long hoy = new Date().getTime();
			if (hoy > fechaModPwd + 7776000*2)
				return "nuevaPwd";
			return "redirect:wall";
		} else {
			model.addAttribute("message", "No se puede loguear");
			return "home";
		}

	}

	@RequestMapping(value = "registrar", method = RequestMethod.POST)
	public String registrar(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password-register");
		String username = request.getParameter("username");

		Usuario usuario = new Usuario(username, email, DigestUtils.md5Hex(password));
		Usuario usuarioInsertado = DAOUsuario.insert(usuario);

		if (usuarioInsertado != null) {
			request.getSession().invalidate();
			model.addAttribute("user", usuario);
			request.getSession().setMaxInactiveInterval(600);
			request.getSession().setAttribute("user", usuario);
			Cookie cookieCaptcha = new Cookie("cookieCaptchaRegistro", "cookieControlCaptchaRegistro");
			cookieCaptcha.setMaxAge(7776000);
			response.addCookie(cookieCaptcha);
			return "redirect:wall";
		} else {
			model.addAttribute("message", "No se puede registrar");
			return "home";
		}

	}

	@RequestMapping(value = "viewRecordarPass", method = RequestMethod.GET)
	public String recordarPass(Model model) throws Exception {

		return "viewRecordarPass";
	}

	@RequestMapping(value = "forgotpassword", method = RequestMethod.POST)
	public String forgotpassword(HttpServletRequest request, Model model) throws Exception {

		String email = request.getParameter("email");
		Usuario usuario = new Usuario("", email, "");
		Usuario user = DAOUsuario.select(usuario);

		// Crear pwd aleatoria
		// int pin = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
		SecureRandom rand = SecureRandom.getInstance("SHA1PRNG");
		int pin = rand.nextInt();
		String pinEmail = "redsocial" + String.valueOf(pin);

		if (user != null) {
			SendMail send = new SendMail();
			send.sendMailRecoverPwd(user.getemail(), pinEmail);
			model.addAttribute("message", "Se ha enviado correctamente la contraseña");
			user.setPwd(DigestUtils.md5Hex(pinEmail));
			DAOUsuario.update(user, 1);
		}
		return "viewRecordarPass";
	}

}
