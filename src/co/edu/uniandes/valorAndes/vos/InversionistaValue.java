package co.edu.uniandes.valorAndes.vos;

import java.util.ArrayList;
import java.util.Date;

public class InversionistaValue
{

	
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------


	
	private String id;
	
	private String nombre;
	
	private String tipo;
	
	private String direccion;
	
	private String telefono;
	
	private String ciudad;
	
	private String nomRepresentante;
	
	private String idUsuario;
	
	private ArrayList valores;
	


	
	
	

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	
	


	
	






	public InversionistaValue()
	{
		
	}







	/**
	 * @param id
	 * @param nombre
	 * @param tipo
	 * @param direccion
	 * @param telefono
	 * @param ciudad
	 * @param nomRepresentante
	 */
	public InversionistaValue(String id, String nombre, String tipo,
			String direccion, String telefono, String ciudad,
			String nomRepresentante, String idUsuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.direccion = direccion;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.nomRepresentante = nomRepresentante;
		this.idUsuario = idUsuario;
		
		valores = new ArrayList();
	}




	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------




	public String getId() {
		return id;
	}







	public void setId(String id) {
		this.id = id;
	}







	public String getNombre() {
		return nombre;
	}







	public void setNombre(String nombre) {
		this.nombre = nombre;
	}







	public String getTipo() {
		return tipo;
	}







	public void setTipo(String tipo) {
		this.tipo = tipo;
	}







	public String getDireccion() {
		return direccion;
	}







	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}







	public String getTelefono() {
		return telefono;
	}







	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}







	public String getCiudad() {
		return ciudad;
	}







	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}







	public String getNomRepresentante() {
		return nomRepresentante;
	}







	public void setNomRepresentante(String nomRepresentante) {
		this.nomRepresentante = nomRepresentante;
	}







	public ArrayList getValores() {
		return valores;
	}

	public String stringValores() {
		String mensaje = "";
		
		for(int i = 0; i < valores.size() ; i++){
			
			mensaje += valores.get(i).toString() + "<BR>";
			
		}
		
		return mensaje;
	}





	public void setValores(ArrayList valores) {
		this.valores = valores;
	}

	
	public void addValores(Object e) {
		this.valores.add(e);
	}
	
	
	public String toString(){
		return nombre;
	}


	


	
	
}
