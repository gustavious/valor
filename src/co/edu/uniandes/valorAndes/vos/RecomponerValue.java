package co.edu.uniandes.valorAndes.vos;

public class RecomponerValue 
{
	public final static String VENTA = "VENTA";
	
	public final static String COMPRA = "COMPRA";
	
	
	private int idValor;
	
	private String decision;
	
	private int valorVenta;
	
	public RecomponerValue( )
	{
	
	}

	public int getIdValor() {
		return idValor;
	}

	public void setIdValor(int idValor) {
		this.idValor = idValor;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public int getValorVenta() {
		return valorVenta;
	}

	public void setValorVenta(int valorVenta) {
		this.valorVenta = valorVenta;
	}
}
