
package co.edu.uniandes.valorAndes.fachada;

import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import co.edu.uniandes.valorAndes.dao.ConsultaDAO;
import co.edu.uniandes.valorAndes.vos.ValorValue;
import co.edu.uniandes.valorAndes.vos.VideosValue;

/**
 * Clase ValorAndes, que representa la fachada de comunicación entre
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
    
	
	public ArrayList<ValorValue> darValoresEscogidos(String nTipoValor, String nTipoRentabilidad, String nNegociado, Date nFechaExpiracion, int nIdInversionista, int nIdComisionista, int nIdOferente)throws Exception
	{
		return dao.darValoresEscogidos(nTipoValor, nTipoRentabilidad, nNegociado, nFechaExpiracion, nIdInversionista, nIdComisionista, nIdOferente);
	}
	
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
    	ValorAndes.darInstancia().inicializarRuta("F:/Users/Gustavo/SkyDrive/SisTrans/Esqueletos/JBoss/Esqueleto_VideoAndes_Nivel1/WebContent");

		
	}
	
}
