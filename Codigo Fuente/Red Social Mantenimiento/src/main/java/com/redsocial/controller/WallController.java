package com.redsocial.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.redsocial.auxiliares.Utilidades;
import com.redsocial.modelo.Like;
import com.redsocial.modelo.MensajesPrivados;
import com.redsocial.modelo.Publicacion;
import com.redsocial.modelo.Respuesta;
import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOLike;
import com.redsocial.persistencia.DAOMensajesPrivados;
import com.redsocial.persistencia.DAOPublicacion;
import com.redsocial.persistencia.DAORespuesta;
import com.redsocial.persistencia.DAOUsuario;

/***
 * 
 * @method Diferentes muros que se necesitaran en la red social
 * Para las publicaciones, mensajes, like´s...
 * Implementacion de las funciones like y mensajes privados (incluye enviar mensajes)
 * 
 */

@Controller
public class WallController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value="wall", method = RequestMethod.GET)
	public String wall(HttpServletRequest request, Model model) throws Exception {
		
		if (request.getSession().getAttribute("user")!=null) {
			ArrayList<Publicacion> publicaciones = DAOPublicacion.selectPublicas();
			Hashtable<String,Integer> likes = new Hashtable<String,Integer>();
			Hashtable<String,Integer> checklikes = new Hashtable<String,Integer>();
			Usuario user = DAOUsuario.select((Usuario) request.getSession().getAttribute("user"));
			ArrayList<String> amigos=DAOUsuario.obtenerAmigos(user);
			amigos.add(user.getemail());
			Iterator<String> it=amigos.iterator();
			while(it.hasNext()) {
				publicaciones.addAll(DAOPublicacion.selectPrivadas(it.next()));
			}
			Collections.sort(publicaciones, new Comparator<Publicacion>() {
		        @Override
		        public int compare(Publicacion publi1, Publicacion publi2)
		        {

		            return  publi2.getIdPublicacion().compareTo(publi1.getIdPublicacion());
		        }
		    });
			Hashtable<String,ArrayList<Respuesta>> respuestas = new Hashtable<String,ArrayList<Respuesta>>();
			request.getSession().setAttribute("user", user);
			int sizePubl = publicaciones.size();
			for (int i=0;i<sizePubl;i++) {
				int totalPublicaciones = 0;
				totalPublicaciones = DAOLike.select(publicaciones.get(i).getIdPublicacion()).size();
				likes.put(publicaciones.get(i).getIdPublicacion(), totalPublicaciones);
				Like onelike = DAOLike.checkLike(publicaciones.get(i).getIdPublicacion(), user.getemail());
				if (onelike!=null) {
					checklikes.put(publicaciones.get(i).getIdPublicacion(), 1);
				}else {
					checklikes.put(publicaciones.get(i).getIdPublicacion(), 0);
				}
				ArrayList<Respuesta> resultadoRespuesta = DAORespuesta.select(publicaciones.get(i).getIdPublicacion());
				respuestas.put(publicaciones.get(i).getIdPublicacion(), resultadoRespuesta);
				
			}
			
			ArrayList<MensajesPrivados> mensajes = DAOMensajesPrivados.selectMsgUser(((Usuario) request.getSession().getAttribute("user")).getemail());
			
			model.addAttribute("publicaciones",publicaciones);
			model.addAttribute("respuestas",respuestas);
			model.addAttribute("totalMensajes", mensajes.size());
			model.addAttribute("totalNotificaciones", DAOUsuario.obtenerSolicitudes(user).size());
			model.addAttribute("likes",likes);
			model.addAttribute("user",user);
			model.addAttribute("checklikes",checklikes);
			model.addAttribute("body","publicaciones");
			
			return "wall";
		}else {
			return "home";
		}
		

	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,Model model) {
		
		if (request.getSession().getAttribute("user")!=null) {
			request.getSession().invalidate();
			return "home";
		}
		return "home";
	}
	
	@RequestMapping(value = "darlike", method = RequestMethod.POST)
	public String darlike(HttpServletRequest request,Model model) throws Exception {
		
		if (request.getSession().getAttribute("user")!=null) {
			String idPublicacion = request.getParameter("like-publicacion");
			Usuario user = (Usuario) request.getSession().getAttribute("user");
			Like like = new Like(user.getemail(), idPublicacion);
			Like resultado = DAOLike.checkLike(idPublicacion, user.getemail());
			
			if (resultado!=null) {
				DAOLike.delete(like);
			}else {
				DAOLike.insert(like);
			}
			
			return "redirect:wall";
		}else {
			return "home";
		}
	}
	
	@RequestMapping(value = "messages", method = RequestMethod.GET)
	public String messages(HttpServletRequest request,Model model) throws Exception {
		if (request.getSession().getAttribute("user")!=null) {
			Usuario user = (Usuario) request.getSession().getAttribute("user");
			ArrayList<MensajesPrivados> mensajes = DAOMensajesPrivados.selectMsgUser(user.getemail());	
			ArrayList<Usuario> usuarios = DAOUsuario.selectAll();
			
			model.addAttribute("mensajes",mensajes);
			model.addAttribute("user",user);
			model.addAttribute("usuarios",usuarios);
			model.addAttribute("totalMensajes", mensajes.size());
			model.addAttribute("totalNotificaciones", DAOUsuario.obtenerSolicitudes(user).size());
			model.addAttribute("body","mensajes");
			return "wall";
		}
		else {
			return "home";
		}
		
	}
	@RequestMapping(value = "vistaAmigos", method = RequestMethod.GET)
	public String vistaAmigos(HttpServletRequest request,Model model) throws Exception {
		if (request.getSession().getAttribute("user")!=null) {
			Usuario user = (Usuario) request.getSession().getAttribute("user");
			model.addAttribute("body","vistaAmigos");
			model.addAttribute("notificaciones",Utilidades.mostrarNotificaciones(user));
			model.addAttribute("totalNotificaciones", DAOUsuario.obtenerSolicitudes(user).size());
			model.addAttribute("totalMensajes", DAOMensajesPrivados.selectMsgUser(((Usuario) request.getSession().getAttribute("user")).getemail()).size());
			
			try{
				String filtro = request.getParameter("txtUsuarioNombre");
				Usuario usuario;
				usuario = (Usuario) request.getSession().getAttribute("user");
				model.addAttribute("amigos", Utilidades.buscadorUsuario(usuario, filtro));
			}catch (Exception e) {
				
			}

			return "wall";
		}
		else {
			return "home";
		}
		
	}
	@RequestMapping(value = "sendMessage", method = RequestMethod.POST)
	public String sendMessage(HttpServletRequest request,Model model) throws Exception {
		
		if (request.getSession().getAttribute("user")!=null) {
				
			String destinatario = request.getParameter("destinatario");
			String emisor = ((Usuario) request.getSession().getAttribute("user")).getemail();
			String mensaje = request.getParameter("mensaje");
			
			String fechaEnvio=Utilidades.obtenerFecha();
			
			MensajesPrivados message = new MensajesPrivados(fechaEnvio, destinatario, emisor, mensaje);
			
			DAOMensajesPrivados.insert(message);
			
			return "redirect:messages";
		}else {
			return "home";
		}
	}
}
