package com.redsocial.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.redsocial.modelo.Usuario;


/**
 * 
 * @author Usuario
 *
 */
@Component
public class DAOUsuario {
	
	private final static String nombre = "nombre";
	private final static String pwdd = "pwd";
	private final static String emaill = "email";
	private final static String Usuarioss = "Usuarios";
	private final static String idd = "_id";
	private final static String modPwd= "modificacionPwd";
	private final static String solicitudes="solicitudes";
	private final static String amigos="amigos";
	private final static String enviadas="solicitudesEnviadas";
	/**
	 * 
	 * @param email
	 * @param pwd
	 * @return Usuario si es correcto, null si sus credenciales no son correctas
	 */
	public static Usuario login(String email,String pwd) throws Exception {
		Usuario result = null;
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> usuarios=broker.getCollection("Usuarios");
		Document criterio=new Document();
		criterio.append("email", email);
		criterio.append("pwd", DigestUtils.md5Hex(pwd));
		
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document usuario=resultado.first();
		
		if (usuario!=null) {
			ObjectId id = (ObjectId)usuario.get( idd );
			result = new Usuario (usuario.getString(nombre), usuario.getString(emaill), usuario.getString(pwdd));
			result.setid(id.toString());
			result.setFechaModPwd(usuario.getLong(modPwd));
		}
		
		return result;
	}
	
	/**
	 * @param usuario (email)
	 * @return Usuario asociado a ese email o null si el email no existe
	 */
	public static Usuario select(Usuario usuario) throws Exception {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> usuarios=broker.getCollection("Usuarios");
		Document criterio=new Document();
		criterio.append(emaill, usuario.getemail());
		
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document doc=resultado.first();
		Usuario result=null;
		if (doc!=null) {
			ObjectId id = (ObjectId)doc.get( idd );
			result = new Usuario (id.toString(),doc.getString(nombre), doc.getString(emaill), doc.getString(pwdd));
			result.setid(id.toString());
			result.setFechaModPwd(doc.getLong(modPwd));
		}
		
		return result;
		
	}
	
	/**
	 * 
	 * @param idUsuario
	 * @return El usuario asociado a ese id o null en caso de no existir
	 */
	public static Usuario selectWithID(String idUsuario) throws Exception {
		Usuario result = null;
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> usuarios=broker.getCollection("Usuarios");
		Document criterio=new Document();
		criterio.append("_id", new ObjectId(idUsuario));
		
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document doc=resultado.first();
		
		if (doc!=null) {
			ObjectId id = (ObjectId)doc.get("_id");
			result = new Usuario(id.toString(), doc.getString("nombre"), doc.getString("email"), doc.getString("pwd"));
			result.setFechaModPwd(doc.getLong(modPwd));
		}
		
		return result;
	}

	/**
	 * 
	 * @param id
	 * @throws Exception 
	 * @method borra el usuario asociado a el id
	 */
	public static void delete(String id) throws Exception {
		Usuario borrar=selectWithID(id);
		DAOPublicacion.borradoUsuario(borrar);
		
		Usuario aux;
		ArrayList<String> amigos=obtenerAmigos(borrar);
		Iterator <String> itAmigos=amigos.iterator();
		while(itAmigos.hasNext()) {
			aux=new Usuario();
			aux.setemail(itAmigos.next());
			borrarAmistad(borrar, aux);
		}
		ArrayList<String> solicitudesEnviadas=obtenerEnviosSolicitud(borrar);
		Iterator <String> itSolicitudesEnviadas=solicitudesEnviadas.iterator();
		Usuario receptor;
		while(itSolicitudesEnviadas.hasNext()) {
			receptor=new Usuario();
			receptor.setemail(itSolicitudesEnviadas.next());
			rechazarSolicitud(borrar, receptor);
		}
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<Document>usuarios=broker.getCollection(Usuarioss);
		usuarios.deleteOne(new Document(idd, new ObjectId(id)));
		
		
	}
	/**
	 * 
	 * @param id
	 * @throws Exception 
	 * @method borra el usuario asociado a el elmail
	 */
	public static void deleteConEmail(String email) throws Exception {
		Usuario borrar=new Usuario();
		borrar.setemail(email);
		borrar=select(borrar);
		DAOPublicacion.borradoUsuario(borrar);
		
		Usuario aux;
		ArrayList<String> amigos=obtenerAmigos(borrar);
		Iterator <String> itAmigos=amigos.iterator();
		while(itAmigos.hasNext()) {
			aux=new Usuario();
			aux.setemail(itAmigos.next());
			borrarAmistad(borrar, aux);
		}
		ArrayList<String> solicitudesEnviadas=obtenerEnviosSolicitud(borrar);
		Iterator <String> itSolicitudesEnviadas=solicitudesEnviadas.iterator();
		Usuario receptor;
		while(itSolicitudesEnviadas.hasNext()) {
			receptor=new Usuario();
			receptor.setemail(itSolicitudesEnviadas.next());
			rechazarSolicitud(borrar, receptor);
		}
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<Document>usuarios=broker.getCollection(Usuarioss);
		usuarios.deleteOne(new Document(idd, new ObjectId(borrar.getid())));
	}
	
	/**
	 * 
	 * @return una lista de usuarios completos
	 */
	public static ArrayList<Usuario> selectAll () {
		
		ArrayList<Usuario> result = new ArrayList<Usuario>();
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> usuarios=broker.getCollection(Usuarioss);
		FindIterable<Document> it = usuarios.find();
		MongoCursor<Document> cursor = it.iterator();
	
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			ObjectId id = (ObjectId)doc.get("_id");
			Usuario usu = new Usuario(id.toString(),doc.getString(nombre), doc.getString(emaill), doc.getString(pwdd));
			usu.setFechaModPwd(doc.getLong(modPwd));
			result.add(usu);
		}

		return result;
	}
	
	/**
	 * 
	 * @param usuario (nombre, email, pwd)
	 * @return el usuario si todo va bien, null en caso contrario
	 * @throws Exception 
	 */
	public static Usuario insert(Usuario usuario) throws Exception{
		Document doc=new Document();
		Usuario aux= select(usuario);
		if(aux!=null) return null;
		doc.append(nombre, usuario.getNombre());
		doc.append(emaill, usuario.getemail());
		doc.append("pwd", usuario.getPwd());
		doc.append(modPwd, new Date().getTime());
		doc.append(solicitudes, new ArrayList<String>());
		doc.append(amigos, new ArrayList<String>());
		doc.append(enviadas, new ArrayList<String>());
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<Document>usuarios=broker.getCollection(Usuarioss);
		usuarios.insertOne(doc);

		ObjectId id = (ObjectId)doc.get( idd );
		Usuario result = null;
		if (id!=null) {
			result = usuario;
			result.setid(id.toString());
		}

		return result;
	}
	
	/**
	 * 
	 * @param usuario (lo identificamos con el id)
	 * @method modifica el resto de campos del usuario con el id en cuestion
	 */
	public static void update (Usuario usuario, int pwdModificada) throws Exception {
		
		Document filter = new Document("_id", new ObjectId(usuario.getid()));
		Document newValue = new Document();
		newValue.append(nombre, usuario.getNombre());
		newValue.append(emaill, usuario.getemail());
		if(pwdModificada==1) {
			newValue.append("pwd", usuario.getPwd());
			newValue.append(modPwd, new Date().getTime());
		}

		Document updateOperationDocument = new Document("$set", newValue);
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<Document>usuarios=broker.getCollection("Usuarios");
		usuarios.updateOne(filter, updateOperationDocument);
		
	}
	
	/**
	 * 
	 * @param usuario (lo identificamos con el id)
	 * @method modifica la pwd del usuario
	 */
	public static void updatePwd (Usuario usuario) throws Exception {
		
		Document filter = new Document("_id", new ObjectId(usuario.getid()));
		Document newValue = new Document();
		newValue.append("pwd", usuario.getPwd());
		newValue.append(modPwd, new Date().getTime());
		Document updateOperationDocument = new Document("$set", newValue);
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<Document>usuarios=broker.getCollection("Usuarios");
		usuarios.updateOne(filter, updateOperationDocument);
		
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<String> obtenerAmigos(Usuario usuario) {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> usuarios=broker.getCollection("Usuarios");
		Document criterio=new Document();
		criterio.append(emaill, usuario.getemail());
		
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document doc=resultado.first();
	
		ArrayList<String> retorno=null;
		if (doc!=null) {
			retorno= (ArrayList<String>)doc.get(amigos);
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<String> obtenerSolicitudes(Usuario usuario) {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> usuarios=broker.getCollection("Usuarios");
		Document criterio=new Document();
		criterio.append(emaill, usuario.getemail());
		
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document doc=resultado.first();
	
		ArrayList<String> retorno=null;
		if (doc!=null) {
			retorno= (ArrayList<String>)doc.get(solicitudes);
		}
		return retorno;
	}
	
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> obtenerEnviosSolicitud(Usuario usuario) {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> usuarios=broker.getCollection("Usuarios");
		Document criterio=new Document();
		criterio.append(emaill, usuario.getemail());
		
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document doc=resultado.first();
	
		ArrayList<String> retorno=null;
		if (doc!=null) {
			retorno= (ArrayList<String>)doc.get(enviadas);
		}
		return retorno;
	}
	
	
	public static void enviarSolicitud(Usuario emisor, Usuario receptor) {
		ArrayList<String> solicitudesReceptor=obtenerSolicitudes(receptor);
		solicitudesReceptor.add(emisor.getemail());
		
		ArrayList<String> enviosEmisor=obtenerEnviosSolicitud(emisor);
		enviosEmisor.add(receptor.getemail());
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<Document>usuarios=broker.getCollection("Usuarios");
		
		
		Document filter = new Document(emaill, receptor.getemail());
		Document newValue = new Document();
		newValue.append(solicitudes, solicitudesReceptor);
		Document updateOperationDocument = new Document("$set", newValue);
		usuarios.updateOne(filter, updateOperationDocument);
		
		filter=new Document(emaill, emisor.getemail());
		newValue= new Document();
		newValue.append(enviadas, enviosEmisor);
		updateOperationDocument = new Document("$set", newValue);
		usuarios.updateOne(filter, updateOperationDocument);
	}

	public static void aceptarSolicitud(Usuario emisor, Usuario receptor) {
		ArrayList<String> solicitudesReceptor=obtenerSolicitudes(receptor);
		solicitudesReceptor.remove(emisor.getemail());
		
		ArrayList<String> amigosReceptor=obtenerAmigos(receptor);
		amigosReceptor.add(emisor.getemail());
		
		ArrayList<String> amigosEmisor=obtenerAmigos(emisor);
		amigosEmisor.add(receptor.getemail());
		
		ArrayList<String> enviosEmisor=obtenerEnviosSolicitud(emisor);
		enviosEmisor.remove(receptor.getemail());
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<Document>usuarios=broker.getCollection("Usuarios");
		
		
		Document filter = new Document(emaill, receptor.getemail());
		Document newValue = new Document();
		newValue.append(solicitudes, solicitudesReceptor);
		newValue.append(amigos, amigosReceptor);
		Document updateOperationDocument = new Document("$set", newValue);
		usuarios.updateOne(filter, updateOperationDocument);
		
		
		filter=new Document(emaill, emisor.getemail());
		newValue=new Document();
		newValue.append(amigos, amigosEmisor);
		newValue.append(enviadas, enviosEmisor);
		updateOperationDocument = new Document("$set", newValue);
		usuarios.updateOne(filter, updateOperationDocument);
	}

	public static void rechazarSolicitud(Usuario emisor, Usuario receptor) {
		ArrayList<String> solicitudesReceptor=obtenerSolicitudes(receptor);
		solicitudesReceptor.remove(emisor.getemail());
		
		ArrayList<String> enviosEmisor=obtenerEnviosSolicitud(emisor);
		enviosEmisor.remove(receptor.getemail());
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<Document>usuarios=broker.getCollection("Usuarios");
		
		
		Document filter = new Document(emaill, receptor.getemail());
		Document newValue = new Document();
		newValue.append(solicitudes, solicitudesReceptor);
		Document updateOperationDocument = new Document("$set", newValue);
		usuarios.updateOne(filter, updateOperationDocument);
		
		filter=new Document(emaill, emisor.getemail());
		newValue= new Document();
		newValue.append(enviadas, enviosEmisor);
		updateOperationDocument = new Document("$set", newValue);
		usuarios.updateOne(filter, updateOperationDocument);
		
	}

	public static void borrarAmistad(Usuario borrador, Usuario borrado) {
		ArrayList<String> amigosBorrador=obtenerAmigos(borrador);
		amigosBorrador.remove(borrado.getemail());
		
		ArrayList<String> amigosBorrado=obtenerAmigos(borrado);
		amigosBorrado.remove(borrador.getemail());
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<Document>usuarios=broker.getCollection("Usuarios");
		
		
		Document filter = new Document(emaill, borrador.getemail());
		Document newValue = new Document();
		newValue.append(amigos, amigosBorrador);
		Document updateOperationDocument = new Document("$set", newValue);
		usuarios.updateOne(filter, updateOperationDocument);
		
		
		filter=new Document(emaill, borrado.getemail());
		newValue=new Document();
		newValue.append(amigos, amigosBorrado);
		updateOperationDocument = new Document("$set", newValue);
		usuarios.updateOne(filter, updateOperationDocument);
	}
	
	/**
	 * @result devuelve una lista de usuarios que tengan en su nombre el filtro indicado
	 */
	public static ArrayList<Usuario> buscador(String filtro){
		ArrayList<Usuario> result = new ArrayList<Usuario>();
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> usuarios=broker.getCollection(Usuarioss);
		FindIterable<Document> it = usuarios.find();
		MongoCursor<Document> cursor = it.iterator();
	
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			if(doc.getString(emaill).contains(filtro)) {
				ObjectId id = (ObjectId)doc.get("_id");
				Usuario usu = new Usuario(id.toString(),doc.getString(nombre), doc.getString(emaill), doc.getString(pwdd));
				result.add(usu);
			}
		}

		return result;
	}
}


