
package co.edu.uniandes.valorAndes.fachada;

import java.util.ArrayList;

import co.edu.uniandes.valorAndes.dao.ConsultaDAO;
import co.edu.uniandes.valorAndes.vos.VideosValue;

/**
 * Clase VideoAndes, que representa la fachada de comunicaci�n entre
 * la interfaz y la conexi�n con la base de datos. Atiende todas
 * las solicitudes.
 */
public class ValorAndes 
{
	/**
	 * Conexi�n con la clase que maneja la base de datos
	 */
	private ConsultaDAO dao;
	

    
    // -----------------------------------------------------------------
    // Singleton
    // -----------------------------------------------------------------


    /**
     * Instancia �nica de la clase
     */
    private static ValorAndes instancia;
    
    /**
     * Devuelve la instancia �nica de la clase
     * @return Instancia �nica de la clase
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
	 * inicializa el dao, d�ndole la ruta en donde debe encontrar
	 * el archivo properties.
	 * @param ruta ruta donde se encuentra el archivo properties
	 */
	public void inicializarRuta(String ruta)
	{
		dao.inicializar(ruta);
	}
	
    // ---------------------------------------------------
    // M�todos asociados a los casos de uso: Consulta
    // ---------------------------------------------------
    
	/**
	 * m�todo que retorna los videos en orden alfab�tico.
	 * invoca al DAO para obtener los resultados.
	 * @return ArrayList lista con los videos ordenados alfabeticamente.
	 * @throws Exception pasa la excepci�n generada por el DAO
	 */
	public ArrayList<VideosValue> darVideosDefault() throws Exception
	{
	    return dao.darVideosDefault();
	}
	
	
	public static void main( String[] args )
	{
		
		
	}
	
}
