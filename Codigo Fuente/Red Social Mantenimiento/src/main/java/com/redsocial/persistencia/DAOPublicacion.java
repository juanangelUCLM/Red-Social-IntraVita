package com.redsocial.persistencia;


import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.bind.DatatypeConverter;


import org.bson.Document;
import org.bson.types.Binary;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.redsocial.auxiliares.Utilidades;
import com.redsocial.modelo.Publicacion;
import com.redsocial.modelo.Usuario;;
/**
 * 
 * @author Usuario
 *
 */
public class DAOPublicacion {

	private final static String name = "name";
	private final static String emaill = "email";
	private final static String fechaa= "fecha";
	private final static String sms = "mensaje";
	private final static String img = "imagen";
	private final static String pub = "Publicaciones";
	private final static String idd = "_id";
	private final static String privacidad="privacidad";
	
	public static Publicacion insert(Publicacion publicacion) {
		Document doc=new Document();
		doc.append(name, publicacion.getNombre());
		doc.append(emaill, publicacion.getEmail());
		doc.append(fechaa, publicacion.getFecha());
		doc.append(sms, publicacion.getMensaje());
		doc.append(img, publicacion.getImagen());
		doc.append(privacidad, publicacion.getPrivacidad());
		MongoBroker broker= MongoBroker.get();
		MongoCollection<Document>publicaciones=broker.getCollection(pub);
		publicaciones.insertOne(doc);
		
		 ObjectId id = (ObjectId)doc.get( idd );
		 Publicacion result = null;
		 if (id!=null) {
			 result = publicacion;
			 result.setIdPublicacion(id.toString());
		 }
		 return result;
	}
	

	
	public static void update (Publicacion publicacion) throws Exception {
		
		String fechaPublicacion = Utilidades.obtenerFecha();
		
		Document filter = new Document("_id", new ObjectId(publicacion.getIdPublicacion()));
		Document newValue = new Document();
		newValue.append("mensaje", publicacion.getMensaje());
		newValue.append("fecha", fechaPublicacion);
		//newValue.append("imagen", publicacion.getImagen());
		Document updateOperationDocument = new Document("$set", newValue);
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<Document>publicaciones=broker.getCollection("Publicaciones");
		publicaciones.updateOne(filter, updateOperationDocument);
		
	}

	public static Publicacion select(String idPublicacion) throws Exception {
		Publicacion result = null;
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> publicaciones=broker.getCollection("Publicaciones");
		Document criterio=new Document();
		criterio.append("_id", new ObjectId(idPublicacion));
		
		FindIterable<Document> resultado=publicaciones.find(criterio);
		Document publicacion=resultado.first();
		
		
		Binary imagen = publicacion.get(img, org.bson.types.Binary.class);
		byte[]imagenFinal=imagen.getData();
		String imagenCodificada= DatatypeConverter.printBase64Binary(imagenFinal);
		
		if (publicacion!=null) {
			result = new Publicacion(publicacion.getString("idPublicacion"), publicacion.getString("email"), 
			publicacion.getString("name"), publicacion.getString("fecha"), imagenCodificada, publicacion.getString("mensaje"));
		}
		
		return result;
	}
	
	public static ArrayList<Publicacion> selectAll () {
		
		ArrayList<Publicacion> result = new ArrayList<Publicacion>();
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> publicaciones=broker.getCollection(pub);
		FindIterable<Document> it = publicaciones.find().sort(new BasicDBObject(idd,-1));
		MongoCursor<Document> cursor = it.iterator();
	
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			ObjectId id = (ObjectId)doc.get( idd );
						
			Binary imagen = doc.get(img, org.bson.types.Binary.class);
			byte[]imagenFinal=imagen.getData();
			String imagenCodificada= DatatypeConverter.printBase64Binary(imagenFinal);
			
			Publicacion publi = new Publicacion(id.toString(), doc.getString(emaill), doc.getString(name), doc.getString(fechaa), imagenCodificada, doc.getString(sms));
			publi.setIdPublicacion(id.toString());
			result.add(publi);
		}

		return result;
	}
	

	public static ArrayList<Publicacion> selectPublicas() {
		ArrayList<Publicacion> result = new ArrayList<Publicacion>();
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> publicaciones=broker.getCollection(pub);
		Document criterio=new Document();
		criterio.append(privacidad, "Publica");
		FindIterable<Document> it = publicaciones.find(criterio).sort(new BasicDBObject(idd,-1));
		MongoCursor<Document> cursor = it.iterator();
	
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			ObjectId id = (ObjectId)doc.get( idd );
						
			Binary imagen = doc.get(img, org.bson.types.Binary.class);
			byte[]imagenFinal=imagen.getData();
			String imagenCodificada= DatatypeConverter.printBase64Binary(imagenFinal);
			
			Publicacion publi = new Publicacion(id.toString(), doc.getString(emaill), doc.getString(name), doc.getString(fechaa), imagenCodificada, doc.getString(sms));
			publi.setIdPublicacion(id.toString());
			result.add(publi);
		}

		return result;
	}
	public static ArrayList<Publicacion> selectPrivadas(String email) {
		ArrayList<Publicacion> result = new ArrayList<Publicacion>();
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> publicaciones=broker.getCollection(pub);
		Document criterio=new Document();
		criterio.append(privacidad, "Amigos");
		criterio.append(emaill, email);
		FindIterable<Document> it = publicaciones.find(criterio).sort(new BasicDBObject(idd,-1));
		MongoCursor<Document> cursor = it.iterator();
	
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			ObjectId id = (ObjectId)doc.get( idd );
						
			Binary imagen = doc.get(img, org.bson.types.Binary.class);
			byte[]imagenFinal=imagen.getData();
			String imagenCodificada= DatatypeConverter.printBase64Binary(imagenFinal);
			
			Publicacion publi = new Publicacion(id.toString(), doc.getString(emaill), doc.getString(name), doc.getString(fechaa), imagenCodificada, doc.getString(sms));
			publi.setIdPublicacion(id.toString());
			result.add(publi);
		}

		return result;
	}
	public static void delete(String id) {
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<Document>publicaciones=broker.getCollection(pub);
		publicaciones.deleteOne(new Document(idd, new ObjectId(id)));
		
	}



	public static void borradoUsuario(Usuario borrar) {
		Publicacion result = null;
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> publicaciones=broker.getCollection("Publicaciones");
		Document criterio=new Document();
		criterio.append(emaill, borrar.getemail());
		
		FindIterable<Document> resultado=publicaciones.find(criterio);
		Iterator <Document> it=resultado.iterator();
		Document publicacion;
		while(it.hasNext()) {
			publicacion=it.next();
			result = new Publicacion(publicacion.getString("idPublicacion"), publicacion.getString("email"), 
			publicacion.getString("name"), publicacion.getString("fecha"), publicacion.getString("imagen"), publicacion.getString("mensaje"));
			DAOLike.borrarPublicacion(result);
			DAORespuesta.borrarPublicacion(result);
			
			DAOLike.borrarUsuario(result);
			DAORespuesta.borrarUsuario(result);
			publicaciones.deleteOne(new Document(idd, new ObjectId(result.getIdPublicacion())));
			
		}	
	}




}
