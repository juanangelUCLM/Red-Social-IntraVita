package com.redsocial.modelo;

/**
 * 
 * @author Usuario
 *
 */
public class Publicacion {
	
	private String idPublicacion;
	private String email;
	private String nombre;
	private String fecha;
	private byte[] imagen;
	private String imagenCodificada;
	private String mensaje;
	private String privacidad; //Publica, Amigos
	public Publicacion() {
		
	}
	
	public Publicacion(String idPublicacion, String email, String nombre, String fecha, byte[] imagen, String mensaje) {
		
		this.idPublicacion= idPublicacion;
		this.email=email;
		this.nombre=nombre;
		this.fecha=fecha;
		this.imagen=imagen;
		this.mensaje=mensaje;	
		this.privacidad="Publica";
	}
	
	public Publicacion(String idPublicacion, String email, String nombre, String fecha, String imagenCodificada, String mensaje) {
		
		this.idPublicacion= idPublicacion;
		this.email=email;
		this.nombre=nombre;
		this.fecha=fecha;
		this.imagenCodificada=imagenCodificada;
		this.mensaje=mensaje;
		this.privacidad="Publica";

	}
	public Publicacion(String idPublicacion, String email, String nombre, String fecha, String imagenCodificada, String mensaje, String privacidad) {
		
		this.idPublicacion= idPublicacion;
		this.email=email;
		this.nombre=nombre;
		this.fecha=fecha;
		this.imagenCodificada=imagenCodificada;
		this.mensaje=mensaje;
		this.privacidad=privacidad;

	}
	public Publicacion(String idPublicacion, String email, String nombre, String fecha, byte[] imagen, String mensaje, String privacidad) {
		
		this.idPublicacion= idPublicacion;
		this.email=email;
		this.nombre=nombre;
		this.fecha=fecha;
		this.imagen=imagen;
		this.mensaje=mensaje;	
		this.privacidad=privacidad;
	}
	public String getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(String idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	public String getImagenCodificada() {
		return imagenCodificada;
	}

	public void setImagenCodificada(String imagen) {
		this.imagenCodificada = imagen;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public String getPrivacidad() {
		return privacidad;
	}

	public void setPrivacidad(String privacidad) {
		this.privacidad = privacidad;
	}
	

}
