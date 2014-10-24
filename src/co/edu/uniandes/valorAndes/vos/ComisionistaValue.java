package co.edu.uniandes.valorAndes.vos;

import java.util.ArrayList;
import java.util.Date;

public class ComisionistaValue
{

	
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------


	
	private String numRegistro;
	
	private String nombreEntidad;
	
	private String ciudad;
	
	private String direccion;
	
	private String telefono;
	
	private String nomRepresentante;
	

		
	private ArrayList inversionistas;
	

	
	private ArrayList valores;
	
	
	

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	
	


	
	/**
	 * @param numRegistro
	 * @param nombreEntidad
	 * @param ciudad
	 * @param direccion
	 * @param telefono
	 * @param nomRepresentante
	 */
	public ComisionistaValue(String numRegistro, String nombreEntidad,
			String ciudad, String direccion, String telefono,
			String nomRepresentante) {
		super();
		this.numRegistro = numRegistro;
		this.nombreEntidad = nombreEntidad;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.telefono = telefono;
		this.nomRepresentante = nomRepresentante;
		
		inversionistas = new ArrayList();
		valores = new ArrayList();
	}






	public ComisionistaValue()
	{
		
	}



	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------




	public String getNumRegistro() {
		return numRegistro;
	}






	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}






	public String getNombreEntidad() {
		return nombreEntidad;
	}






	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}






	public String getCiudad() {
		return ciudad;
	}






	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
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






	public String getNomRepresentante() {
		return nomRepresentante;
	}






	public void setNomRepresentante(String nomRepresentante) {
		this.nomRepresentante = nomRepresentante;
	}






	public ArrayList getInversionistas() {
		return inversionistas;
	}






	public void setInversionistas(ArrayList inversionistas) {
		this.inversionistas = inversionistas;
	}













	public ArrayList getValoresEnNegociacion() {
		return valores;
	}






	public void setValoresEnNegociacion(ArrayList valoresEnNegociacion) {
		this.valores = valoresEnNegociacion;
	}

   
	
	public void addValoresEnNegociacion(Object e) {
		valores.add(e);
	}
	
	
	public void addInversionista(Object e) {
		inversionistas.add(e);
	}






	public void obtenerValores() {
		
		ArrayList valores = new ArrayList();
		
		
		for(int i = 0; i < inversionistas.size() ; i++){
			
			InversionistaValue actual = (InversionistaValue) inversionistas.get(i);
			
			valores.addAll(actual.getValores());
			
		}
		
		this.valores = valores;
		
		
		
	}
	
	public String stringValores() {
		String mensaje = "";
		
		for(int i = 0; i < valores.size() ; i++){
			
			mensaje += ((ComposicionValue) valores.get(i)).getNombreValor() + "<BR>";
			
		}
		
		return mensaje;
	}
	
	
	public String stringInversionistas() {
		String mensaje = "";
		
		for(int i = 0; i < inversionistas.size() ; i++){
			
			mensaje += inversionistas.get(i).toString() + "<BR>";
			
		}
		
		return mensaje;
	}
	
	public String toString() {
		
		
		return nombreEntidad;
	}
	
	
	
	
}
