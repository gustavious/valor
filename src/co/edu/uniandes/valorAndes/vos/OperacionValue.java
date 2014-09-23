package co.edu.uniandes.valorAndes.vos;

import java.util.Date;

public class OperacionValue
{

	private int id;
	
	private String tipo;
	
	private Double valor;
	
	private int cantidad;
	
	private int idUsuario1;
	
	private int idComisionista1;
	
	private int idUsuario2;
	
	private int idComisionista2;
	
	private int idInstrumento;
		
	private Date fechaInic;
	
	private Date fechaFin;
	
	
	

	


	public OperacionValue(int id, String tipo, Double valor,
			int idUsuario1, int idComisionista1, int idInstrumento,
			Date fechaInic) {
		super();
		this.id = id;
		this.tipo = tipo;

		this.valor = valor;
		this.idUsuario1 = idUsuario1;
		this.idComisionista1 = idComisionista1;
		this.idInstrumento = idInstrumento;
		this.fechaInic = fechaInic;
	}
	
	public OperacionValue()
	{
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public int getIdUsuario1() {
		return idUsuario1;
	}


	public void setIdUsuario1(int idUsuario1) {
		this.idUsuario1 = idUsuario1;
	}


	public int getIdComisionista1() {
		return idComisionista1;
	}


	public void setIdComisionista1(int idComisionista1) {
		this.idComisionista1 = idComisionista1;
	}


	public int getIdUsuario2() {
		return idUsuario2;
	}


	public void setIdUsuario2(int idUsuario2) {
		this.idUsuario2 = idUsuario2;
	}


	public int getIdInstrumento() {
		return idInstrumento;
	}


	public void setIdInstrumento(int idInstrumento) {
		this.idInstrumento = idInstrumento;
	}


	public Date getFechaInic() {
		return fechaInic;
	}


	public void setFechaInic(Date fechaInic) {
		this.fechaInic = fechaInic;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public int getIdComisionista2() {
		return idComisionista2;
	}


	public void setIdComisionista2(int idComisionista2) {
		this.idComisionista2 = idComisionista2;
	}
	
	
	
}
