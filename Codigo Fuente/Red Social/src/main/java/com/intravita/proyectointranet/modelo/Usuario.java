package com.intravita.proyectointranet.modelo;

/**
 * Usuario- Modelo de los usuarios del sistema
 *
 * @author Intravita
 * @since sprint 1
 */

public class Usuario {
	private String nombre;
	private String clave;
	private String email;
	private String respuesta;
	
	//Para la imagen
	private String nombreImagen;
	private byte[]imagen;

	/*Para la imagen*/
	public Usuario(String nombre, String clave, String email, String respuesta,String nombreImagen,byte[]imagen) {
		this.nombre=nombre;
		this.clave=clave;
		this.email=email;
		this.respuesta=respuesta;
		this.nombreImagen=nombreImagen;
		this.imagen=imagen;
	}
	
	
	public Usuario(String nombre, String clave, String email, String respuesta) {
		this.nombre=nombre;
		this.clave=clave;
		this.email=email;
		this.respuesta=respuesta;
	}
	//Constructor de pass
	public Usuario(String nombre, String clave) {
		this.nombre=nombre;
		this.clave=clave;
	}
	
	//Constructor sin pass
	public Usuario(String nombre) {
		this.nombre=nombre;
	}
	
	//Constructor vacio
	public Usuario() {
		super();
	}	
	
	//Get atributos
	public String getNombre() {
		return this.nombre;
	}
	public String getClave() {
		return this.clave;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public String getEmail() {
		return email;
	}
	//Set atributos
	public void setNombre(String nuevoNombre) {
		this.nombre=nuevoNombre;
	}
	public void setClave(String nuevaClave) {
		this.clave=nuevaClave;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	
	
	/*Para la imagen*/
	public String getNombreImagen() {
		return this.nombreImagen;
	}
	
	public byte[] getImagen() {
		return this.imagen;
	}
	
	
	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen=nombreImagen;
	}
	
	public void setImagen(byte[] imagen) {
		this.imagen=imagen;
	}
	
	
	
	
}