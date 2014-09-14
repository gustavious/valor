/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: VideosValue.java,v 1.0 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 *
 * Ejercicio: VideoAndes
 * Autor: Juan Diego Toro - 10-Feb-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package co.edu.uniandes.valorAndes.vos;

/**
 * Clase VideosValue, que representa un Value Object para un video
 */
public class VideosValue 
{
	/**
	 * Identificador universal del video
	 */
	private String isan;
	
	/**
	 * titulo original del video
	 */
	private String tituloOriginal;
	
	/**
	 * a�o de lanzamiento
	 */
	private int anyo;
	
	/**
	 * duracion en minutos
	 */
	private int duracion;
	
	/**
	 * clasificacion del video
	 */
	private String clasificacion;
	
	/**
	 * descripcion de la clasificacion
	 */
	private String descripcionClasificacion;
	
	/**
	 * Constructor de la clase. No inicializa ningun atributo.
	 */
	public VideosValue()
	{
		
	}

	/**
	 * retorna el isan (identificador universal) del video
	 * @return isan 
	 */
	public String getIsan() {
		return isan;
	}

	/**
	 * Modifica el isan de un video
	 * @param isan el nuevo isan del video. isan != null
	 */
	public void setIsan(String isan) {
		this.isan = isan;
	}

	/**
	 * retorna el titulo original del video
	 * @return tituloOriginal
	 */
	public String getTituloOriginal() {
		return tituloOriginal;
	}

	/**
	 * Modifica el titulo original del video
	 * @param tituloOriginal el nuevo titulo del video. isan != null
	 */
	public void setTituloOriginal(String tituloOriginal) {
		this.tituloOriginal = tituloOriginal;
	}

	/**
	 * retorna el a�o de lanzamiento del video
	 * @return anyo
	 */
	public int getAnyo() {
		return anyo;
	}
	
	/**
	 * Modifica el a�o de lanzamiento del video
	 * @param anyo el nuevo a�o de lanzamiento. anyo > 0;
	 */
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	/**
	 * retorna la duraci�n en minutos del video
	 * @return duracion
	 */
	public int getDuracion() {
		return duracion;
	}

	/**
	 * Modifica la duraci�n en minutos del video
	 * @param duracion la nueva duraci�n del video. duracion >= 0
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	/**
	 * retorna la clasificaci�n del video
	 * @return clasificacion
	 */
	public String getClasificacion() 
	{
		return clasificacion;
	}

	/**
	 * Modifica la clasificaci�n de un video
	 * @param clasificacion la nueva clasificacion del video. clasificacion != null
	 */
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	/**
	 * retorna la descripci�n de clasificaci�n del video
	 * @return descripcionClasificacion
	 */
	public String getDescripcionClasificacion() {
		return descripcionClasificacion;
	}

	/**
	 * Modifica la descripci�n de la clasificaci�n de un video
	 * @param descripcionClasificacion la nueva descripci�n de la 
	 * clasificaci�n del video. descripcionClasificaci�n != null
	 */
	public void setDescripcionClasificacion(String descripcionClasificacion) {
		this.descripcionClasificacion = descripcionClasificacion;
	}
	
	

}
