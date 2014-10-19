package co.edu.uniandes.valorAndes.vos;

import java.util.Date;


public class ValorValue 
{
	//Atributos
	
	private String nombre;
	
	private Date fechaExpiracion;
	
	private int id;
	
	private double valor;
	
	private int idUsuario;
	
	private int tipoValor;
	
	private String negociado;
	
	private int idRentabilidad;
	
	public ValorValue()
	{
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(Date fechaExp) {
		this.fechaExpiracion = fechaExp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNegociado() {
		return negociado;
	}

	public void setNegociado(String negociado) {
		this.negociado = negociado;
	}

	public int getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(int tipoValor) {
		this.tipoValor = tipoValor;
	}

	public int getIdRentabilidad() {
		return idRentabilidad;
	}

	public void setIdRentabilidad(int idRentabilidad) {
		this.idRentabilidad = idRentabilidad;
	}
	
	
	
}
