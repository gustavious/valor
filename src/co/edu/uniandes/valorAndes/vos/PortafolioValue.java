/**
 * 
 */
package co.edu.uniandes.valorAndes.vos;

/**
 * @author Gustavo
 *
 */
public class PortafolioValue {
	
	
	private String idUsuario;
	
	private String niveRiesgo;
	
	private String id;
	
	private String fechaInicio;
	
	private String fechaFin;

	private String nombre;

	private String fechaExp;
	

	public PortafolioValue(String idUsuario, String niveRiesgo, String id, String nombre, String fechaExp) {
		super();
		this.idUsuario = idUsuario;
		this.niveRiesgo = niveRiesgo;
		this.id = id;
		this.nombre = nombre;
		this.fechaExp = fechaExp; 
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaExp() {
		return fechaExp;
	}

	public void setFechaExp(String fechaExp) {
		this.fechaExp = fechaExp;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNiveRiesgo() {
		return niveRiesgo;
	}

	public void setNiveRiesgo(String niveRiesgo) {
		this.niveRiesgo = niveRiesgo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
	
	
	
	


	/**
	 * @param idUsuario 
	 * @param nivelRiesgo 
	 * @param id 
	 * 
	 */
	

}
