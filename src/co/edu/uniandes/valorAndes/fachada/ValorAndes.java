
package co.edu.uniandes.valorAndes.fachada;

import java.util.ArrayList;

import co.edu.uniandes.valorAndes.dao.ConsultaDAO;
import co.edu.uniandes.valorAndes.vos.VideosValue;

/**
 * Clase VideoAndes, que representa la fachada de comunicación entre
 * la interfaz y la conexión con la base de datos. Atiende todas
 * las solicitudes.
 */
public class ValorAndes 
{
	/**
	 * Conexión con la clase que maneja la base de datos
	 */
	private ConsultaDAO dao;
	

    
    // -----------------------------------------------------------------
    // Singleton
    // -----------------------------------------------------------------


    /**
     * Instancia única de la clase
     */
    private static ValorAndes instancia;
    
    /**
     * Devuelve la instancia única de la clase
     * @return Instancia única de la clase
     */
    public static ValorAndes darInstancia( )
    {
        if( instancia == null )
        {
            instancia = new ValorAndes( );
        }
        return instancia;
    }
	
	/**
	 * contructor de la clase. Inicializa el atributo dao.
	 */
	private ValorAndes()
	{
		dao = new ConsultaDAO();
	}
	
	/**
	 * inicializa el dao, dándole la ruta en donde debe encontrar
	 * el archivo properties.
	 * @param ruta ruta donde se encuentra el archivo properties
	 */
	public void inicializarRuta(String ruta)
	{
		dao.inicializar(ruta);
	}
	
    // ---------------------------------------------------
    // Métodos asociados a los casos de uso: Consulta
    // ---------------------------------------------------
    
	/**
	 * método que retorna los videos en orden alfabético.
	 * invoca al DAO para obtener los resultados.
	 * @return ArrayList lista con los videos ordenados alfabeticamente.
	 * @throws Exception pasa la excepción generada por el DAO
	 */
	public ArrayList<VideosValue> darVideosDefault() throws Exception
	{
	    return dao.darVideosDefault();
	}
	
	
	public static void main( String[] args )
	{
		
		
	}
	
}
