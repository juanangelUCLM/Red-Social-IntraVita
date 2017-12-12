package com.intravita.proyectointranet.persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.BsonArray;
import org.bson.BsonDateTime;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonObjectId;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.intravita.proyectointranet.modelo.Publicacion;
import com.intravita.proyectointranet.modelo.Usuario;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;

/**
 * PublicacionDAOImpl- Implementacion del DAO de las publicaciones con los metodos CRUD
 *
 * @author Intravita
 * @since sprint 2
 */

public class PublicacionDAOImpl {	
	
	private final String ID = "_id";
	private final String text = "texto";
	private final String author = "autor";
	private final String date = "fecha";
	private final String privacy = "privacidad";
	private final String compartidopor= "compartidopor";
	
	private final String megustaUsuarios = "megustaUsuarios";
	
	public MongoCollection<BsonDocument> obtenerPublicaciones() {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> publicaciones = broker.getCollection("Publicaciones");
		return publicaciones;
	}


	public boolean existe(Publicacion publicacion) {
		
		MongoCollection<BsonDocument> usuarios = obtenerPublicaciones();
		BsonDocument criterio = new BsonDocument();
		if(publicacion.getId()!=null)
			criterio.append(ID, new BsonObjectId(new ObjectId(publicacion.getId())));
		else {
			criterio.append(author, new BsonString(publicacion.getUsuario().getNombre()));
			criterio.append(text, new BsonString(publicacion.getTexto()));
		}
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuarioBson = resultado.first();
		if (usuarioBson==null) {
			return false;
		}
		return true;
	}
	/***
	 * 
	 * @method insertar una publicacion en la base de datos
	 * 
	 */
	public void insert(Publicacion publicacion) {
		BsonDocument bso = new BsonDocument();
		bso.append(author, new BsonString(publicacion.getUsuario().getNombre()));
		bso.append(text, new BsonString(publicacion.getTexto()));
		bso.append(privacy, new BsonString(publicacion.getPrivacidad()));
		bso.append(date, new BsonDateTime(publicacion.getFecha()));
		bso.append(compartidopor, new BsonArray());
		bso.append(megustaUsuarios, new BsonArray());
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		publicaciones.insertOne(bso);
		}
	/***
	 * 
	 * @method incrementar "me gusta" en publicacion
	 * 
	 */
	public void megusta(String id, Usuario usuario) {
		BsonDocument bso = new BsonDocument();
		bso.append(ID, new BsonObjectId(new ObjectId(id)));
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument actualizacion = new BsonDocument("$addToSet",new BsonDocument(megustaUsuarios, new BsonString(usuario.getNombre())));
		publicaciones.updateOne(bso,actualizacion);
	}
	/***
	 * 
	 * @method decrementar el "me gusta" en publicacion
	 * 
	 */
	public void yanomegusta(String id, Usuario usuario) {
		BsonDocument bso = new BsonDocument();
		bso.append(ID, new BsonObjectId(new ObjectId(id)));
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument actualizacion = new BsonDocument("$pull",new BsonDocument(megustaUsuarios, new BsonString(usuario.getNombre())));
		publicaciones.updateOne(bso,actualizacion);
	}
	/***
	 * 
	 * @method consulta de los usuarios que han dado me gusta
	 * 
	 */
	public ArrayList<String> usuariosMeGusta(Publicacion publi){
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument criterio = new BsonDocument();
		if(publi.getId()!=null)
			criterio.append(ID, new BsonObjectId(new ObjectId(publi.getId())));
		else {
			criterio.append(author, new BsonString(publi.getUsuario().getNombre()));
			criterio.append(text, new BsonString(publi.getTexto()));			
		}
		FindIterable<BsonDocument> resultado=publicaciones.find(criterio);
		BsonDocument aux = resultado.first();
		
			
		
		BsonArray mgUsuariosBson=aux.getArray(megustaUsuarios);		
		ArrayList<String> mgUsuarios = new ArrayList<String>();
		for(BsonValue usuarioBson : mgUsuariosBson) {			
			BsonString usuarioString=usuarioBson.asString();
			String usuario=usuarioString.getValue();
			mgUsuarios.add(usuario);
        }
		
		
		return mgUsuarios;
		
	}
	/***
	 * 
	 * @method actualizar una publicacion en la base de datos
	 * 
	 */
	public void update(String id, String textoNuevo){		
		BsonDocument bso = new BsonDocument();
		bso.append(ID, new BsonObjectId(new ObjectId(id)));
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument(text, new BsonString(textoNuevo)));
		publicaciones.updateOne(bso,actualizacion);
	}
	/***
	 * 
	 * @method eliminar una publicacion en la base de datos
	 * 
	 */
	public void remove(String id){
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument bso = new BsonDocument();
		bso.append(ID, new BsonObjectId(new ObjectId(id)));
		FindIterable<BsonDocument> resultado=publicaciones.find(bso);
		BsonDocument publicacionBson = resultado.first();		
		publicaciones.deleteOne(publicacionBson);
	}
	/**
	 * 
	 * @param publicacion que queremos obtener (ID o AUTOR y TEXTO)
	 * @return publicacion completa
	 */
	public Publicacion selectOne(Publicacion publi) {
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument criterio = new BsonDocument();
		if(publi.getId()!=null)
			criterio.append(ID, new BsonObjectId(new ObjectId(publi.getId())));
		else {
			criterio.append(author, new BsonString(publi.getUsuario().getNombre()));
			criterio.append(text, new BsonString(publi.getTexto()));			
		}
		FindIterable<BsonDocument> resultado=publicaciones.find(criterio);
		BsonDocument aux = resultado.first();
		String autor=aux.getString(author).getValue();
		String texto=aux.getString(text).getValue();
		String privacidad=aux.getString(privacy).getValue();
		long fecha=aux.getDateTime(date).getValue();

		
		Publicacion publicacion=new Publicacion(new Usuario(autor), texto, privacidad, fecha);
		publicacion.setId(aux.getObjectId(ID).getValue().toString());
		return publicacion;
	}
	
	/**
	 * 
	 * @param usuario del que queremos obtener publicaciones publicas
	 * @return lista de publicaciones
	 */
	public ArrayList<Publicacion> selectPublicas(Usuario usuario) {
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument criterio = new BsonDocument();
		criterio.append(author, new BsonString(usuario.getNombre()));
		criterio.append(privacy,  new BsonString("Publica"));
		Bson sort = Sorts.descending(date);
		FindIterable<BsonDocument> resultado=publicaciones.find(criterio).sort(sort);
		Iterator <BsonDocument> bucle= resultado.iterator();
		ArrayList<Publicacion> lista= new ArrayList<Publicacion>();
		BsonDocument aux;
		String autor;
		String texto;
		String privacidad;
		long fecha;
		Publicacion publicacion;
		while(bucle.hasNext()) {
			aux=bucle.next();
			autor=aux.getString(author).getValue();
			texto=aux.getString(text).getValue();
			privacidad=aux.getString(privacy).getValue();
			fecha=aux.getDateTime(date).getValue();
			publicacion=new Publicacion(new Usuario(autor), texto, privacidad, fecha);
			publicacion.setId(aux.getObjectId(ID).getValue().toString());
			lista.add(publicacion);
		}
		return lista;
	}
	/**
	 * 
	 * @param usuario del que queremos obtener publicaciones privadas
	 * @return lista de publicaciones
	 */
	public ArrayList<Publicacion> selectPrivadas(Usuario usuario) {
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument criterio = new BsonDocument();
		criterio.append(author, new BsonString(usuario.getNombre()));
		criterio.append(privacy,  new BsonString("Privada"));
		Bson sort = Sorts.descending(date);
		FindIterable<BsonDocument> resultado=publicaciones.find(criterio).sort(sort);
		Iterator <BsonDocument> bucle= resultado.iterator();
		ArrayList<Publicacion> lista= new ArrayList<Publicacion>();
		BsonDocument aux;
		String autor;
		String texto;
		String privacidad;
		long fecha;
		Publicacion publicacion;
		while(bucle.hasNext()) {
			aux=bucle.next();
			autor=aux.getString(author).getValue();
			texto=aux.getString(text).getValue();
			privacidad=aux.getString(privacy).getValue();
			fecha=aux.getDateTime(date).getValue();
			publicacion=new Publicacion(new Usuario(autor), texto, privacidad, fecha);
			publicacion.setId(aux.getObjectId(ID).getValue().toString());
			lista.add(publicacion);
		}
		return lista;
	}
	/**
	 * 
	 * @param usuario
	 * @result todas las publicaciones del usuario a borrar se iran a un usuario llamado Papelera
	 */
	public void borradoUsuario(Usuario usuario) {
		BsonDocument bso = new BsonDocument();
		bso.append(author, new BsonString(usuario.getNombre()));
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument(author, new BsonString("Papelera")));
		publicaciones.updateMany(bso,actualizacion);		
	}
	

	/**
	 * 
	 * @return devuelve una lista de nombres de usuarios que comparten la publicacion
	 */
	public List<BsonValue> obtenerCompartidos(Publicacion comp) {
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument criterio = new BsonDocument();
		criterio.append(ID, new BsonObjectId(new ObjectId(comp.getId())));
		FindIterable<BsonDocument> resultado=publicaciones.find(criterio);
		BsonDocument publicacion = resultado.first();
		List <BsonValue> comparticiones= publicacion.getArray(compartidopor);
		return comparticiones;
	}

	/**
	 * 
	 * @param compartir: usuario que quiere compartir
	 * @param comp: publicacion a compartir
	 */
	public void compartir(Usuario compartir, Publicacion comp) {
		List<BsonValue> lista=obtenerCompartidos(comp);
		lista.add(new BsonString(compartir.getNombre()));
		
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument criterio = new BsonDocument();
		criterio.append(ID, new BsonObjectId(new ObjectId(comp.getId())));
		FindIterable<BsonDocument> resultado=publicaciones.find(criterio);
		BsonDocument publicacion = resultado.first();
		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument(compartidopor, new BsonArray(lista)));
		publicaciones.findOneAndUpdate(publicacion, actualizacion);
	}

	/**
	 * 
	 * @param usuario: usuario que quiere dejar de compartir
	 * @param compartida: publicacion a dejar de compartir
	 */
	public void dejarCompartir(Usuario usuario, Publicacion compartida) {
		List<BsonValue> lista=obtenerCompartidos(compartida);
		lista.remove(new BsonString(usuario.getNombre()));
		
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument criterio = new BsonDocument();
		criterio.append(ID, new BsonObjectId(new ObjectId(compartida.getId())));
		FindIterable<BsonDocument> resultado=publicaciones.find(criterio);
		BsonDocument publicacion = resultado.first();
		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument(compartidopor, new BsonArray(lista)));
		publicaciones.findOneAndUpdate(publicacion, actualizacion);
		
	}
	/***
	 * @method select que devuelve todas las publicaciones completas
	 * @return la lista de publicaciones
	 */
	public List<Publicacion> selectAll() {
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		FindIterable<BsonDocument> resultado=publicaciones.find();
		
		Usuario usuario;
		String texto;
		String privacidad;
		long fecha;
		String id;
		List<BsonValue> compartidopor;
		ArrayList<String> megusta;
		BsonDocument publicacion;
		Iterator<BsonDocument> lista=resultado.iterator();
		List<Publicacion> retorno=new ArrayList<Publicacion>();
		
		Publicacion aux;
		while(lista.hasNext()) {
			publicacion=lista.next();
			usuario=new Usuario(publicacion.getString(author).getValue());
			texto=publicacion.getString(text).getValue();
			privacidad=publicacion.getString(privacy).getValue();
			fecha=publicacion.getDateTime(date).getValue();
			compartidopor=publicacion.getArray(this.compartidopor);
			id=publicacion.getObjectId(ID).getValue().toString();

			aux=new Publicacion(usuario, texto, privacidad, fecha);

			aux.setCompartidopor(compartidopor);
			aux.setId(id);
			megusta=usuariosMeGusta(aux);
			aux.setMegustaUsuarios(megusta);
			retorno.add(aux);
		}
		return retorno;
	}
	/**
	 * 
	 * @param nombreViejo
	 * @param nuevoNombre
	 * @method todas las publicaciones de nombreViejo pasan a ser de nuevoNombre
	 */

	public void updateNombre(String nombreViejo, String nuevoNombre) {
		BsonDocument bso = new BsonDocument();
		bso.append(author, new BsonString(nombreViejo));
		
		MongoCollection<BsonDocument> publicaciones = obtenerPublicaciones();
		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument(author, new BsonString(nuevoNombre)));
		publicaciones.updateMany(bso,actualizacion);
		
	}

	/**
	 * 
	 * @param nombreViejo
	 * @param nuevoNombre
	 * @method todos los compartidos y me gusta de nombreViejo pasan a ser de nuevoNombre
	 */

	public void updateCompartidosYMegusta(String nombreViejo, String nuevoNombre) {
		List<Publicacion> todas=selectAll();
		BsonString bsoViejoNombre=new BsonString(nombreViejo);
		Publicacion aux;
		Iterator<Publicacion> it=todas.iterator();
		List<BsonValue> compartidos;
		ArrayList<String> megusta;
		
		Usuario viejo=new Usuario(nombreViejo);
		Usuario nuevo=new Usuario(nuevoNombre);
		
		while(it.hasNext()) {
			aux=it.next();
			
			if(aux.getPrivacidad().equals("Publica")) {
				megusta=aux.getMegustaUsuarios();
				if(megusta.contains(nombreViejo)) {
					yanomegusta(aux.getId(),viejo);
					megusta(aux.getId(),nuevo);
				}
				compartidos=aux.getCompartidopor();
				if(compartidos.contains(bsoViejoNombre)) {
					dejarCompartir(viejo,aux);
					compartir(nuevo, aux);
				}
			}
		}
	} 
}
