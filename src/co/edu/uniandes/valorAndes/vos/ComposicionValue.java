package co.edu.uniandes.valorAndes.vos;

public class ComposicionValue
{
	private int idPortafolio;
	
	private int idValor;
	
	private String nombreValor;
	
	private double porcentaje;
	
	public ComposicionValue()
	{
		
	}

	public int getIdPortafolio() {
		return idPortafolio;
	}

	public void setIdPortafolio(int idPortafolio) {
		this.idPortafolio = idPortafolio;
	}

	public int getIdValor() {
		return idValor;
	}

	public void setIdValor(int idValor) {
		this.idValor = idValor;
	}

	public String getNombreValor() {
		return nombreValor;
	}

	public void setNombreValor(String nombreValor) {
		this.nombreValor = nombreValor;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	
	
}
