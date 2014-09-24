
package co.edu.uniandes.valorAndes.fachada;



import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import co.edu.uniandes.valorAndes.dao.ConsultaDAO;
import co.edu.uniandes.valorAndes.vos.OperacionValue;
import co.edu.uniandes.valorAndes.vos.ValorValue;
import co.edu.uniandes.valorAndes.vos.VideosValue;

/**
 * Clase ValorAndes, que representa la fachada de comunicaci�n entre
 * la interfaz y la conexi�n con la base de datos. Atiende todas
 * las solicitudes.
 */
public class ValorAndes 
{
	/**
	 * Conexi�n con la clase que maneja la base de datos
	 */
	private ConsultaDAO dao;
	
//	Constrante que se debe cambiar
	/**
	 * Cosntante que indica la ruta del archivo conexion
	 */
	
	public static final String RUTA = "F:/Users/Gustavo/SkyDrive/SisTrans/Esqueletos/JBoss/Esqueleto_VideoAndes_Nivel1/WebContent";

    
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
	
	
	public ConsultaDAO dao()
	{
		return dao;
	}
	
    // ---------------------------------------------------
    // M�todos asociados a los casos de uso: Consulta
    // ---------------------------------------------------
    
	
	public ArrayList<ValorValue> darValoresEscogidos(String nTipoValor, String nTipoRentabilidad, String nNegociado, String nFechaExpiracion, int nIdInversionista, int nIdComisionista, int nIdOferente)throws Exception
	{
		return dao.darValoresEscogidos(nTipoValor, nTipoRentabilidad, nNegociado, nFechaExpiracion, nIdInversionista, nIdComisionista, nIdOferente);
	}
	
	
	public ArrayList<OperacionValue> darOperaciones(String nTipoUsuario, String nTipoOperacion, String nFechaInicial, String nFechaFinal, double nCosto, String nRentabilidad ) throws Exception
	{
		return dao.darOperaciones(nTipoUsuario, nTipoOperacion, nFechaInicial, nFechaFinal, nCosto, nRentabilidad);
	}
	
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
	
	
	@SuppressWarnings("deprecation")
	public static void main( String[] args )
	{
    	ValorAndes.darInstancia().inicializarRuta("F:/Users/Gustavo/SkyDrive/SisTrans/Esqueletos/JBoss/Esqueleto_VideoAndes_Nivel1/WebContent");
    	
    	
    	Calendar fecha = new GregorianCalendar();
        
        int a�o = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        
       
        
        String fechainic = a�o +  String.format("%02d",mes) +   dia  + String.format( "%02d%02d",hora, minuto);
        
      
        
      

		
		
		
    	
//    	try {
//			ValorAndes.darInstancia().dao.ordenarOperacion(4, "Compra", (double) 6000, 1,1, 2, "200203151215");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
	}
	
}
