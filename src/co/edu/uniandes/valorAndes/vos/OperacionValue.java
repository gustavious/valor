package co.edu.uniandes.valorAndes.vos;

import java.util.Date;

public class OperacionValue
{

	private int id;
	
	private String tipo;
	
	private int valor;
	
	private int cantidad;
	
	private int idUsuario1;
	
	private int idComisionista1;
	
	private int idUsuario2;
	
	private int idComisionista2;
	
	private int idInstrumento;
		
	private Date fechaInic;
	
	private Date fechaFin;
	
	private String nomComisionista;
	
	private String nomInstrumento;
	
	private String rentabilidad;
	
	private String valorIns;
	
	private String nomOferente;
	
	private String nomInversionista;
	
	private String numRegistro;
	
	private String tipoOP;
	
	private String fecha;
	
	private String tipoValor;
	


	public OperacionValue(int id, String tipo, int valor,
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


	public OperacionValue(String numRegistro, String tipoOP, String fecha,
			String nomInstrumento2, String valorIns2, String tipoValor,
			String rentabilidad2, String nomComisionista2,
			String nomInversionista2, String nomOferente2) {
		
		this.numRegistro = numRegistro;
		this.tipoOP = tipoOP;
		this.fecha = fecha;
		this.nomInstrumento = nomInstrumento2;
		this.valorIns = valorIns2;
		this.tipoValor = tipoValor;
		this.rentabilidad = rentabilidad2;
		this.nomComisionista = nomComisionista2;
		this.nomInversionista = nomInversionista2;
		this.nomOferente = nomOferente2;
				
		
		
		
		
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


	public int getValor() {
		return valor;
	}


	public void setValor(int valor) {
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

	public String getNomComisionista() {
		return nomComisionista;
	}

	public void setNomComisionista(String nomComisionista) {
		this.nomComisionista = nomComisionista;
	}

	public String getNomInstrumento() {
		return nomInstrumento;
	}

	public void setNomInstrumento(String nomInstrumento) {
		this.nomInstrumento = nomInstrumento;
	}

	public String getRentabilidad() {
		return rentabilidad;
	}

	public void setRentabilidad(String rentabilidad) {
		this.rentabilidad = rentabilidad;
	}

	public String getValorIns() {
		return valorIns;
	}

	public void setValorIns(String valorIns) {
		this.valorIns = valorIns;
	}

	public String getNomOferente() {
		return nomOferente;
	}

	public void setNomOferente(String nomOferente) {
		this.nomOferente = nomOferente;
	}

	public String getNomInversionista() {
		return nomInversionista;
	}

	public void setNomInversionista(String nomInversionista) {
		this.nomInversionista = nomInversionista;
	}

	public String getNumRegistro() {
		return numRegistro;
	}

	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}

	public String getTipoOP() {
		return tipoOP;
	}

	public void setTipoOP(String tipoOP) {
		this.tipoOP = tipoOP;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}
	
	
	
}
