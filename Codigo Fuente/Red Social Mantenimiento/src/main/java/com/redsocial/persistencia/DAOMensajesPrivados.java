package  com.redsocial.persistencia;


import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.redsocial.auxiliares.Utilidades;
import com.redsocial.modelo.MensajesPrivados;
/**
 * 
 * @author Usuario
 *
 */
public class DAOMensajesPrivados {
	
	public static ObjectId insert(MensajesPrivados msg) {
		Document doc=new Document();
		doc.append("fechamensaje", msg.getFecha());
		doc.append("emaildestinatario", msg.getDestinatario());
		doc.append("emailemisor", msg.getEmisor());
		doc.append("mensaje", msg.getMensaje());

		MongoBroker broker= MongoBroker.get();
		MongoCollection<Document>mensajes=broker.getCollection("MensajesPrivados");
		mensajes.insertOne(doc);

		ObjectId id = (ObjectId)doc.get("_id");

		return id;
	}
	

	
	public static void update (MensajesPrivados msg) throws Exception {
		
		String fechaMensaje = Utilidades.obtenerFecha();
		
		Document filter = new Document("_id", new ObjectId(msg.getIdmensaje()));
		Document newValue = new Document();
		newValue.append("mensaje", msg.getMensaje());
		newValue.append("fechamensaje", fechaMensaje);
		Document updateOperationDocument = new Document("$set", newValue);
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<Document>mensajes=broker.getCollection("MensajesPrivados");
		mensajes.updateOne(filter, updateOperationDocument);
		
	}

	public static MensajesPrivados select(String idmensaje) throws Exception {
		MensajesPrivados result = null;
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> mensajes=broker.getCollection("MensajesPrivados");
		Document criterio=new Document();
		criterio.append("_id", idmensaje);
		
		FindIterable<Document> resultado=mensajes.find(criterio);
		Document mensaje=resultado.first();
		
		if (mensaje!=null) {
			ObjectId id = (ObjectId) mensaje.get( "_id" );
			result = new MensajesPrivados(id.toString(), mensaje.getString("fechamensaje"), 
					mensaje.getString("emaildestinatario"), mensaje.getString("emailemisor"), mensaje.getString("mensaje"));
			
		}
		
		return result;
	}
	
	public static ArrayList<MensajesPrivados> selectAll () {
		
		ArrayList<MensajesPrivados> result = new ArrayList<MensajesPrivados>();
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> mensajes=broker.getCollection("MensajesPrivados");
		FindIterable<Document> it = mensajes.find();
		MongoCursor<Document> cursor = it.iterator();
	
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			ObjectId id = (ObjectId)doc.get("_id");
			MensajesPrivados msg = new MensajesPrivados(id.toString(), doc.getString("fechamensaje"), doc.getString("emaildestinatario"), doc.getString("emailemisor"), doc.getString("mensaje"));
			result.add(msg);
		}

		return result;
	}
	
	public static ArrayList<MensajesPrivados> selectMsgUser (String emailUser) {
		
		ArrayList<MensajesPrivados> result = new ArrayList<MensajesPrivados>();
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> mensajes=broker.getCollection("MensajesPrivados");
		
		FindIterable<Document> resultado=mensajes.find();
		MongoCursor<Document> cursor = resultado.iterator();
		
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			ObjectId id = (ObjectId)doc.get("_id");
			if (doc.getString("emaildestinatario").equals(emailUser)) {
				MensajesPrivados mensaje = new MensajesPrivados(id.toString(), doc.getString("fechamensaje"), doc.getString("emaildestinatario"), doc.getString("emailemisor"), doc.getString("mensaje"));
				result.add(mensaje);
			}
		}
				
		return result;
	}
	
	public static void delete(String id) {
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<Document>mensajes=broker.getCollection("MensajesPrivados");
		mensajes.deleteOne(new Document("_id", new ObjectId(id)));
		
	}

}
