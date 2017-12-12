package com.redsocial.auxiliares;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import com.redsocial.modelo.Usuario;
import com.redsocial.persistencia.DAOUsuario;
 
/**
 * 
 * @author Usuario
 *
 */
public class Utilidades {
	
    
    /**
     * 
     * @return fecha actual en dia/mes/año horas:minutos
     */
    public static String obtenerFecha() {
		 Calendar fecha = new GregorianCalendar();
		 String fechaEnvio = "";
	     int year = fecha.get(Calendar.YEAR);
	     // Se le suma uno, porque calendar.month devuelve de 0-11
	     int month = fecha.get(Calendar.MONTH)+1;
	     int day = fecha.get(Calendar.DAY_OF_MONTH);
	     int hour = fecha.get(Calendar.HOUR_OF_DAY);
	     int minute = fecha.get(Calendar.MINUTE);
	     String monthS = (month<10)?"0"+month:""+month;
	     String dayS = (day<10)?"0"+day:""+day;
	     fechaEnvio = dayS+"/"+monthS+"/"+year+" "+hour+":"+minute;
	     return fechaEnvio;
    }
    
    
    public static boolean comprobarAmistad(Usuario usuarioA, Usuario usuarioB) {
    	ArrayList<String> amigosA=DAOUsuario.obtenerAmigos(usuarioA);
    	return amigosA.contains(usuarioB.getemail());
    }
    
    public static boolean comprobarSolicitudes(Usuario emisor, Usuario receptor) {
    	ArrayList<String> solicitudesReceptor=DAOUsuario.obtenerSolicitudes(receptor);
    	return solicitudesReceptor.contains(emisor.getemail());
    }
    
    public static void enviarSolicitud (Usuario emisor, Usuario receptor) throws Exception{
    	if (comprobarSolicitudes(emisor, receptor))
    		throw new Exception("Ya has enviado una solicitud a ese usuario.");
    	if (comprobarSolicitudes(receptor, emisor))
    		throw new Exception("Ya tienes una solicitud de ese usuario.");
    	if (comprobarAmistad(emisor, receptor))
    		throw new Exception("Ya sois amigos");
    	if (comprobarAmistad(receptor, emisor))
    		throw new Exception("Ya sois amigos");

    	DAOUsuario.enviarSolicitud(emisor, receptor);
    }
    
    public static void aceptarSolicitud(Usuario emisor, Usuario receptor) throws Exception {
    	if (!comprobarSolicitudes(emisor, receptor))
    		throw new Exception("No te ha mandado solicitud");
    	DAOUsuario.aceptarSolicitud(emisor, receptor);
    } 
    
    public static void rechazarSolicitud(Usuario emisor, Usuario receptor) throws Exception {
    	if (!comprobarSolicitudes(emisor, receptor))
    		throw new Exception("No te ha mandado solicitud");
    	DAOUsuario.rechazarSolicitud(emisor, receptor);
    }
    
    /**
     * 
     * @param borrador(persona que inicia el borrado de amigos)
     * @param borrado(persona que va a ser borrada de amigos)
     * @throws Exception
     */
    public static void borrarAmistad(Usuario borrador, Usuario borrado) throws Exception {
    	if (!comprobarAmistad(borrador, borrado))
    		throw new Exception("No puedes eliminar a alguien que no es tu amigo");
    	if (!comprobarAmistad(borrado, borrador))
    		throw new Exception("No puedes eliminar a alguien que no es tu amigo");
    	DAOUsuario.borrarAmistad(borrador, borrado);

    }

    public static String buscadorUsuario(Usuario busca, String filtro) {
    	  ArrayList<Usuario> coincidencias = DAOUsuario.buscador(filtro);
    	  String retorno = "";
    	  Iterator<Usuario> it = coincidencias.iterator();
    	  if (!it.hasNext())
    	   return "No se encontraron resultados";
    	  Usuario aux;
    	  while (it.hasNext()) {
    	   aux = it.next();
    	   if (!aux.getNombre().equals(busca.getNombre())) {
    	    if (!comprobarAmistad(busca, aux) && !comprobarAmistad(busca, aux)) {
    	     retorno+="  <form action=\"enviarSolicitud\" method=\"POST\"> \r\n" + 
    	       "   <div class=\"row\">\r\n" + 
    	       "          <div class=\"col-md-10\">\r\n" +
    	       "     <input name=\"noSirve\" class=\"form-control\" value=\""+aux.getemail()+"\" id=\"usr\" placeholder=\"usuario\" disabled>"+ 
    	       "     <input name=\"txtNombreEnviar\" type=\"hidden\" class=\"form-control\" value=\""+aux.getemail()+"\" id=\"usr\" placeholder=\"usuario\">"+
    	       "    </div>\r\n" + 
    	       "    <div class=\"col-md-2\">\r\n" + 
    	       "     <button class=\"btn btn-primary btn-outline\" type=\"submit\" title=\"Enviar Solicitud de Amistad\">" +
    	       "     	<strong><i class=\"fa fa-user\" aria-hidden=\"true\"></i><i class=\"fa fa-plus\" aria-hidden=\"true\"></i></strong>" +
    	       "     </button>\r\n" +
    	       "     <br>\r\n" + 
    	       "    </div>\r\n" +
    	       "   </div>\r\n" +
    	       "	 <hr>	"+
    	       "  </form>";
    	    }else {
    	     retorno+="  <form action=\"eliminarAmigo\" method=\"POST\"> \r\n" + 
    	       "   <div class=\"row\">\r\n" + 
    	       "          <div class=\"col-md-10\">\r\n" +
    	       "     <input name=\"noSirve\" class=\"form-control\" value=\""+aux.getemail()+"\" id=\"usr\" placeholder=\"usuario\" disabled>"+ 
    	       "     <input name=\"txtNombreEliminar\" type=\"hidden\" class=\"form-control\" value=\""+aux.getemail()+"\" id=\"usr\" placeholder=\"usuario\">"+
    	       "    </div>\r\n" + 
    	       "    <div class=\"col-md-2\">\r\n" +
    	       "     <button class=\"btn btn-primary btn-outline\" type=\"submit\" title=\"Eliminar Amigo\">" +
    	       "      <strong><i class=\"fa fa-user\" aria-hidden=\"true\"></i><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></strong>" +
    	       "     </button>\r\n" +
    	       "     <br>\r\n" + 
    	       "    </div>\r\n" +
    	       "   </div>\r\n" +
    	       "	 <hr>	"+
    	       "  </form>";
    	    }
    	   }
    	  }
    	  return retorno;
    	 }

    /**
     * 
     * @param usuario
     *            (solo el nombre)
     * @return devuelve las peticiones de amistad pendientes
     */
    public static String mostrarNotificaciones(Usuario usuario) {
    	ArrayList<String> notificacionesPendientes = DAOUsuario.obtenerSolicitudes(usuario);
    	Iterator<String> it = notificacionesPendientes.iterator();
    	String retorno = "";
    	String aux;
    	if (!it.hasNext())
    		return "No tienes notificaciones pendientes";
    	while (it.hasNext()) {
    		aux = it.next();
    		retorno+="    <form action=\"aceptarSolicitud\" method=\"POST\">\r\n" +
    				"  <br/> "+
    				"      <div class=\"row\">\r\n" + 
    				"        <div class=\"col-md-8\">\r\n" +
    				"          <input name=\"noSirve\" type=\"text\" class=\"form-control\" value=\""+aux+"\" id=\"usr\" placeholder=\"usuario\" disabled>\r\n" + 
    				"          <input name=\"txtNombre\" type=\"hidden\" class=\"form-control\" value=\""+aux+"\" id=\"usr\" placeholder=\"usuario\" >\r\n" +
    				"        </div>\r\n" + 
    				"        <div class=\"col-md-2\">\r\n" + 
    				"          <button class=\"btn btn-primary btn-outline\" type=\"submit\" title=\"Aceptar Solicitud\">" +
    				"    <i class=\"fa fa-plus-square\" aria-hidden=\"true\"></i>" +
    				"   </button>\r\n" +
    				"        </div>\r\n" + 
    				"        <div class=\"col-md-2\">\r\n" +
    				"          <button class=\"btn btn-primary btn-outline\"  formaction=\"rechazarSolicitud\" type=\"submit\" title=\"Rechazar Solicitud\">"+
    				"      <i class=\"fa fa-minus-square\" aria-hidden=\"true\"></i>"+
    				"    </button>\r\n" +
    				"   <br>\r\n" +
    				"      </div></div>\r\n" +  
    				"    </form>";
    	}
    	return retorno;
    }


}