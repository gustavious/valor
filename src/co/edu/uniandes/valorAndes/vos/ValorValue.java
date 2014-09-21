package co.edu.uniandes.valorAndes.vos;

import java.sql.Date;

public class ValorValue 
{
	//Atributos
	
	public String nombre;
	
	public Date fechaExpiracion;
	
	public int id;
	
	public double valor;
	
	public int idUsuario;
	
	public int tipoValor;
	
	public String negociado;
	
	public int idRentabilidad;
	
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

	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
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
