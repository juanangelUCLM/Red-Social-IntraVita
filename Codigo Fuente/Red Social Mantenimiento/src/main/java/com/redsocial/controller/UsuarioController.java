package com.redsocial.controller;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.redsocial.auxiliares.Utilidades;
import com.redsocial.modelo.MensajesPrivados;
import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOMensajesPrivados;
import com.redsocial.persistencia.DAOUsuario;

/***
 * 
 *@method Funciones para editar a un usuario, borrarlo, listar todos los usuarios
 *Funcion que permite al admin borrar a un usuario, añadir un nuevo usuario,
 *editarlo y actualizarlo
 * 
 */

@Controller
public class UsuarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@RequestMapping(value = "editarusuario", method = RequestMethod.POST)
	public String editarusuario(HttpServletRequest request,Model model) throws Exception {
		
		if (request.getSession().getAttribute("user")!=null) {
			String nombre = request.getParameter("edit-nombre");
			String email = request.getParameter("edit-email");
			Usuario aux=(Usuario) request.getSession().getAttribute("user");
			String pwdAntigua=aux.getPwd();
			
			String newpwd = request.getParameter("edit-new-pwd");
			int pwdModificada=0;
			if(!pwdAntigua.equals(newpwd))
				pwdModificada=1;
			Usuario usuario = new Usuario(((Usuario)request.getSession().getAttribute("user")).getid(),nombre, email, DigestUtils.md5Hex(newpwd));
			
			DAOUsuario.update(usuario, pwdModificada);
			return "redirect:wall";
		}else {
			return "home";
		}
		
	}
	
	@RequestMapping(value = "renovarPwd", method = RequestMethod.POST)
	public String renovarPwd(HttpServletRequest request,Model model) throws Exception {
		Usuario usuario =(Usuario) request.getSession().getAttribute("user");

		if (usuario!=null) {
			String newpwd = request.getParameter("txtUsuarioClave");
			String newpwd1 = request.getParameter("txtUsuarioClave1");
			if(!newpwd1.equals(newpwd))
				return "nuevaPwd";
			usuario.setPwd(DigestUtils.md5Hex(newpwd));
			
			DAOUsuario.updatePwd(usuario);
			return "redirect:wall";
		}else {
			return "home";
		}
		
	}
	@RequestMapping(value = "borrarusuario", method = RequestMethod.GET)
	public String borrarusuario(HttpServletRequest request,Model model) throws Exception {
		
		if (request.getSession().getAttribute("user")!=null) {
			
			Usuario usuario = (Usuario) request.getSession().getAttribute("user");
			DAOUsuario.delete(usuario.getid());
			request.getSession().invalidate();
			return "home";
		}
		return "home";
	}
	
	@RequestMapping(value = "listadousuarios", method = RequestMethod.GET)
	public String listadousuarios(HttpServletRequest request,Model model) {
		
		if (request.getSession().getAttribute("user")!=null) {
			ArrayList<Usuario> users = new ArrayList<Usuario>();
			ArrayList<MensajesPrivados> mensajes = DAOMensajesPrivados.selectMsgUser(((Usuario) request.getSession().getAttribute("user")).getemail());
			users = DAOUsuario.selectAll();
			
			model.addAttribute("users",users);
			model.addAttribute("totalMensajes", mensajes.size());
			model.addAttribute("body","listadousuarios");
			
			return "wall";
		}else {
			return "home";
		}

		
		
	}
	
	@RequestMapping(value = "admineditaruser", method = RequestMethod.GET)
	public String admineditaruser(@RequestParam String id,HttpServletRequest request,Model model) throws Exception {
		
		if (request.getSession().getAttribute("user")!=null) {
			Usuario user = DAOUsuario.selectWithID(id);
			ArrayList<MensajesPrivados> mensajes = DAOMensajesPrivados.selectMsgUser(((Usuario) request.getSession().getAttribute("user")).getemail());
			
			model.addAttribute("usuario",user);
			model.addAttribute("totalMensajes", mensajes.size());
			model.addAttribute("body","editarUsuario");
			
			return "wall";
		}else {
			return "home";
		}
	}
	
	@RequestMapping(value = "updateuser", method = RequestMethod.POST)
	public String updateuser(HttpServletRequest request,Model model) throws Exception {
		
		if (request.getSession().getAttribute("user")!=null) {
			String idUsuario = request.getParameter("idUsuario");
			String nombre = request.getParameter("update-name");
			String email = request.getParameter("update-email");
			String pwd = request.getParameter("update-pwd");
			
			Usuario user = new Usuario(idUsuario, nombre, email, DigestUtils.md5Hex(pwd));
			ArrayList<MensajesPrivados> mensajes = DAOMensajesPrivados.selectMsgUser(((Usuario) request.getSession().getAttribute("user")).getemail());
			
			DAOUsuario.update(user, 1);
			ArrayList<Usuario> users = new ArrayList<Usuario>();
			users = DAOUsuario.selectAll();
			
			model.addAttribute("users",users);
			model.addAttribute("totalMensajes", mensajes.size());
			model.addAttribute("body","listadousuarios");
			return "wall";
		}else {
			return "home";
		}

	}
	
	@RequestMapping(value = "admindeleteuser", method = RequestMethod.GET)
	public String admindeleteuser(@RequestParam String id,HttpServletRequest request,Model model) throws Exception {
		
		if (request.getSession().getAttribute("user")!=null) {
			Usuario usuario = DAOUsuario.selectWithID(id);
			DAOUsuario.delete(usuario.getid());
			
			
			ArrayList<Usuario> users = new ArrayList<Usuario>();
			users = DAOUsuario.selectAll();
			
			model.addAttribute("users",users);
			model.addAttribute("body","listadousuarios");
			
			return "wall";
		}else {
			return "home";
		}
		
	}
	
	@RequestMapping(value = "adminadduser", method = RequestMethod.POST)
	public String adminadduser(HttpServletRequest request,Model model) throws Exception {
		
		if (request.getSession().getAttribute("user")!=null) {
			String nombre = request.getParameter("add-nombre");
			String email = request.getParameter("add-email");
			String pwd = request.getParameter("add-pwd");
			
			Usuario usuario = new Usuario(nombre, email, DigestUtils.md5Hex(pwd));
			
			Usuario usuarioInsertado = DAOUsuario.select(usuario);
			
			if (usuarioInsertado==null) {
				usuario = DAOUsuario.insert(usuario);
				model.addAttribute("message","Usuario insertado correctamente");
				model.addAttribute("status",1);
			}else {
				model.addAttribute("message","Este usuario ya existe.");
				model.addAttribute("status",0);
			}
			ArrayList<Usuario> users = new ArrayList<Usuario>();
			users = DAOUsuario.selectAll();
			
			model.addAttribute("users",users);
			model.addAttribute("body","listadousuarios");
			
			return "wall";
		}else {
			return "home";
		}

	}

	
	@RequestMapping(value = "/mostrarNotificaciones", method = RequestMethod.GET)
	public String mostrarNotificaciones(HttpServletRequest request, Model model){
		return "redirect:vistaAmigos";
	}
	
	/**
	  * 
	  * @return aceptar solicitud
	  * @throws Exception
	  */
	@RequestMapping(value = "/aceptarSolicitud", method = RequestMethod.POST)
	public String aceptarSolicitud(HttpServletRequest request, Model model) throws Exception {
		String emisor = request.getParameter("txtNombre");
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute("user");
		try {
			Usuario aux=new Usuario();
			aux.setemail(emisor);
			Utilidades.aceptarSolicitud(aux, usuario);
		} catch (Exception e) {
			model.addAttribute("alerta", e.getMessage());
		}
		model.addAttribute("body","vistaAmigos");
		mostrarNotificaciones(request, model);
		return "redirect:vistaAmigos";
	}
	/**
	 * 
	 * @return rechazar solicitud
	 * @throws Exception
	 */
	@RequestMapping(value = "/rechazarSolicitud", method = RequestMethod.POST)
	public String rechazarSolicitud(HttpServletRequest request, Model model) throws Exception {
		String emisor = request.getParameter("txtNombre");
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute("user");
		try {
			Usuario aux=new Usuario();
			aux.setemail(emisor);
			Utilidades.rechazarSolicitud(aux, usuario);
		} catch (Exception e) {
			model.addAttribute("alerta", e.getMessage());
		}
		model.addAttribute("body","vistaAmigos");
		mostrarNotificaciones(request, model);
		return "redirect:vistaAmigos";
	}

	/**
	 * 
	 * @return enviar solicitud a la persona seleccionado
	 * @throws Exception
	 */
	@RequestMapping(value = "/enviarSolicitud", method = RequestMethod.POST)
	public String enviarSolicitud(HttpServletRequest request, Model model) throws Exception {
		String receptor = request.getParameter("txtNombreEnviar");
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute("user");
		try {
			Usuario aux=new Usuario();
			aux.setemail(receptor);
			Utilidades.enviarSolicitud(usuario, aux);
		} catch (Exception e) {
			model.addAttribute("alerta", e.getMessage());
		}
		model.addAttribute("body","vistaAmigos");
		mostrarNotificaciones(request, model);
		return "redirect:vistaAmigos";
	}
	
	/**
	 * 
	 * @return eliminar el amigo seleccionado
	 * @throws Exception
	 */
	@RequestMapping(value = "/eliminarAmigo", method = RequestMethod.POST)
	public String eliminarAmigo(HttpServletRequest request, Model model) throws Exception {
		String receptor = request.getParameter("txtNombreEliminar");
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute("user");
		try {
			Usuario aux=new Usuario();
			aux.setemail(receptor);
			Utilidades.borrarAmistad(usuario, aux);
		} catch (Exception e) {
			model.addAttribute("alerta", e.getMessage());
		}
		model.addAttribute("body","vistaAmigos");
		mostrarNotificaciones(request, model);
		return "redirect:vistaAmigos";
	}
}
