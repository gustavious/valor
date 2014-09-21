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
	
	public String tipo;
	
	public String negociado;
	
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNegociado() {
		return negociado;
	}

	public void setNegociado(String negociado) {
		this.negociado = negociado;
	}
	
	
	
}
