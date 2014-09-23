/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ConsultaDAO.java,v 1.10 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 *
 * Ejercicio: VideoAndes
 * Autor: Juan Diego Toro - 1-Marzo-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package co.edu.uniandes.valorAndes.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;








import co.edu.uniandes.valorAndes.vos.OperacionValue;
import co.edu.uniandes.valorAndes.vos.ValorValue;
import co.edu.uniandes.valorAndes.vos.VideosValue;

/**
 * Clase ConsultaDAO, encargada de hacer las consultas básicas para el cliente
 */
public class ConsultaDAO {

	//----------------------------------------------------
	//Constantes
	//----------------------------------------------------
	/**
	 * ruta donde se encuentra el archivo de conexión.
	 */
	private static final String ARCHIVO_CONEXION = "/conexion.properties";
	
	
	//----------------------------------------------------
	//Consultas
	//----------------------------------------------------
	
	/**
	 * Consulta que devuelve isan, titulo, y año de los videos en orden alfabetico
	 */
	private static final String consultaVideosDefault="SELECT *, FROM ";
	
	


	//----------------------------------------------------
	//Atributos
	//----------------------------------------------------
	/**
	 * conexion con la base de datos
	 */
	public Connection conexion;
	
	/**
	 * nombre del usuario para conectarse a la base de datos.
	 */
	private String usuario;
	
	/**
	 * clave de conexión a la base de datos.
	 */
	private String clave;
	
	/**
	 * URL al cual se debe conectar para acceder a la base de datos.
	 */
	private String cadenaConexion;
	
	/**
	 * constructor de la clase. No inicializa ningun atributo.
	 */
	public ConsultaDAO() 
	{		
		
	}
	
	// -------------------------------------------------
    // Métodos
    // -------------------------------------------------

	/**
	 * obtiene los datos necesarios para establecer una conexion
	 * Los datos se obtienen a partir de un archivo properties.
	 * @param path ruta donde se encuentra el archivo properties.
	 */
	public void inicializar(String path)
	{
		try
		{
			File arch= new File(path+ARCHIVO_CONEXION);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream( arch );

	        prop.load( in );
	        in.close( );

			cadenaConexion = prop.getProperty("url");	// El url, el usuario y passwd deben estar en un archivo de propiedades.
												// url: "jdbc:oracle:thin:@chie.uniandes.edu.co:1521:chie10";
			usuario = prop.getProperty("usuario");	// "s2501aXX";
			clave = prop.getProperty("clave");	// "c2501XX";
			final String driver = prop.getProperty("driver");
			Class.forName(driver);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	
	

	/**
	 * Método que se encarga de crear la conexión con el Driver Manager
	 * a partir de los parametros recibidos.
	 * @param url direccion url de la base de datos a la cual se desea conectar
	 * @param usuario nombre del usuario que se va a conectar a la base de datos
	 * @param clave clave de acceso a la base de datos
	 * @throws SQLException si ocurre un error generando la conexión con la base de datos.
	 */
    private void establecerConexion(String url, String usuario, String clave) throws SQLException
    {
    	try
        {
			conexion = DriverManager.getConnection(url,usuario,clave);
        }
        catch( SQLException exception )
        {
            throw new SQLException( "ERROR: ConsultaDAO obteniendo una conexion." );
        }
    }
    
    /**
 	 *Cierra la conexión activa a la base de datos. Además, con=null.
     * @param con objeto de conexión a la base de datos
     * @throws SistemaCinesException Si se presentan errores de conexión
     */
    public void closeConnection(Connection connection) throws Exception {        
		try {
			connection.close();
			connection = null;
		} catch (SQLException exception) {
			throw new Exception("ERROR: ConsultaDAO: closeConnection() = cerrando una conexión.");
		}
    } 
    
    // ---------------------------------------------------
    // Métodos asociados a los casos de uso: Consulta
    // ---------------------------------------------------
    
    public ArrayList<ValorValue> darValoresEscogidos( String nTipoValor, String nTipoRentabilidad, String nNegociado, Date nFechaExpiracion, int nIdInversionista, int nIdComisionista, int nIdOferente) throws Exception
    {
    	ArrayList<ValorValue> valores = new ArrayList<ValorValue>();
    	PreparedStatement prepStmt = null;
    	String consultaR = "(SELECT ID FROM RENTABILIDAD WHERE NOMBRE="+ nTipoRentabilidad+")";
    	String tipoValor = "(SELECT ID FROM TIPO_VALOR WHERE NOMBRE="+ nTipoValor+")";
    	String idUsuarioOferente = "(SELECT ID_USUARIO FROM OFERENTE WHERE ID="+nIdOferente+")";
    	String idUsuarioComisionista = "(SELECT ID_USUARIO FROM COMISIONISTA WHERE ID="+nIdComisionista+")";
    	String idUsuarioInversionista = "(SELECT ID_USUARIO FROM INVERSIONISTA WHERE ID="+nIdInversionista+")";
    	String idsValor = "(SELECT ID_INS_FIN FROM OPERACION_BURSATIL WHERE (ID_USUARIO_1="+idUsuarioOferente +" OR ID_USUARIO_2="+idUsuarioOferente+") AND (ID_USUARIO_1="+idUsuarioInversionista+" OR ID_USUARIO_2="+idUsuarioInversionista+") AND (ID_COMISIONISTA_1= "+idUsuarioComisionista+" OR ID_COMISIONISTA_2="+idUsuarioComisionista+"))";
    	String consultaG = "(SELECT * FROM INSTRUMENTO_FINANCIERO WHERE TIPO_VALOR="+tipoValor+" AND NEGOCIADO=" +nNegociado+ " AND FECHA_EXPIRACION=" +nFechaExpiracion + " AND ID_RENTABILIDAD="+ consultaR+")" ;
    	
    	String consulta = "SELECT * FROM "+consultaG+" CG INNER JOIN " + idsValor+ " IDS ON IDS.ID_INS_FIN=CG.ID GROUP BY CG.ID; ";
    	
    	ValorValue  valorV = new ValorValue();
    	try 
    	{
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement(consulta);
			
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt("ID");
				String nombre = rs.getString("NOMBRE");
				int valor = rs.getInt("VALOR");
				Date fechaExp= rs.getDate("FECHA_EXPIRACION");
				int idUsuario = rs.getInt("ID_USUARIO");
				int tipo = rs.getInt("TIPO");
				String negociado = rs.getString("NEGOCIADO");
				int idRentabilidad = rs.getInt("ID_RENTABILIDAD");
				
				valorV.setId(id);
				valorV.setNombre(nombre);
				valorV.setValor(valor);
				valorV.setFechaExpiracion(fechaExp);
				valorV.setIdUsuario(idUsuario);
				valorV.setTipoValor(tipo);
				valorV.setNegociado(negociado);
				valorV.setIdRentabilidad(idRentabilidad);
				
				valores.add(valorV);
				
				valorV = new ValorValue();
							
			}
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
			System.out.println(consulta);
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
    	}
    	finally
    	{
    		if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					
					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
				}
			}
			closeConnection(conexion);
    	}
    	
    	return valores;
    }
    
    
    public ArrayList<OperacionValue> darOperaciones( String nTipoUsuario, String nTipoOperacion, Date nFechaInicial, Date nFechaFinal, double nCosto, String nRentabilidad ) throws Exception
    {
    	ArrayList<OperacionValue> operaciones = new ArrayList<OperacionValue>();
    	PreparedStatement prepStmt = null;
    	String consulta = "SELECT * FROM OPERACION_BURSATIL WHERE TIPO LIKE "+nTipoOperacion+" AND COSTO LIKE "+ nCosto + "AND (FECHA_INICIAL BETWEEN + "+nFechaInicial+" AND "+nFechaFinal+" ) AND (FECHA_FINAL BETWEEN + "+nFechaInicial+" AND "+nFechaFinal+" ) ";
    	OperacionValue  operacionV = new OperacionValue();
    	try 
    	{
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement(consulta);
			
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next())
			{
			
				
				int id = rs.getInt("ID");
				String tipo = rs.getString("TIPO");
				Double valor = rs.getDouble("VALOR");
				int idUsuario1 = rs.getInt("ID_USUARIO_1");
				int idComisionista1 = rs.getInt("ID_COMISIONISTA_1");
				int idUsuario2 = rs.getInt("ID_USUARIO_2");
				int idComisionista2 = rs.getInt("ID_COMISIONISTA_2");
				int idValor = rs.getInt("ID_INS_FIN");
				Date fechaInicial= rs.getDate("FECHA_INICIAL");
				Date fechaFinal= rs.getDate("FECHA_FINAL");
				
				operacionV.setId(id);
				operacionV.setTipo(tipo);
				operacionV.setValor(valor);
				operacionV.setIdUsuario1(idUsuario1);
				operacionV.setIdUsuario2(idUsuario2);
				operacionV.setIdComisionista1(idComisionista1);
				operacionV.setIdComisionista2(idComisionista2);
				operacionV.setIdInstrumento(idValor);
				operacionV.setFechaFin(fechaFinal);
				operacionV.setFechaInic(fechaInicial);
				
				operaciones.add(operacionV);
				operacionV = new OperacionValue();
							
			}
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
			System.out.println(consulta);
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
    	}
    	finally
    	{
    		if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					
					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
				}
			}
			closeConnection(conexion);
    	}
    	
    	return operaciones;
    }
    
    
    
    /**
     * Método que se encarga de realizar la consulta en la base de datos
     * y retorna un ArrayList de elementos tipo VideosValue.
     * @return ArrayList lista que contiene elementos tipo VideosValue.
     * La lista contiene los videos ordenados alfabeticamente
     * @throws Exception se lanza una excepción si ocurre un error en
     * la conexión o en la consulta. 
     */
    public ArrayList<VideosValue> darVideosDefault() throws Exception
    {
    	PreparedStatement prepStmt = null;
    	
    	ArrayList<VideosValue> videos = new ArrayList<VideosValue>();
		VideosValue vidValue = new VideosValue();
    	
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement(consultaVideosDefault);
			
			ResultSet rs = prepStmt.executeQuery();
			
			while(rs.next()){
				String titVid = rs.getString("titulo");
				int anyoVid = rs.getInt("anio");
				
				vidValue.setTituloOriginal(titVid);
				vidValue.setAnyo(anyoVid);	
			
				videos.add(vidValue);
				vidValue = new VideosValue();
							
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(consultaVideosDefault);
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					
					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
				}
			}
			closeConnection(conexion);
		}		
		return videos;
    }
    
    /**
     * Método que se encarga de ordenar la compra o venta de una nueva operacion bursatil
     * @return true si se pudo, false de lo contrario
     * @throws Exception se lanza una excepción si ocurre un error en
     * la conexión o en la consulta. 
     */
    public boolean ordenarOperacion( int id, String tipo, Double valor, int idUsuario1, int idComisionista1, int idInstrumento, String fechaInic) throws Exception
    {
    	PreparedStatement prepStmt = null;
    	
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			
			String query = " INSERT INTO OPERACION_BURSATIL (ID, TIPO, VALOR,  ID_USUARIO_1, ID_COMISIONISTA_1, ID_INS_FIN,  FECHA_INICIAL) VALUES ( " + id + ", '" + tipo + "', "+ valor +", "+ idUsuario1 +", "+ idComisionista1+", "+ idInstrumento+ ", TO_DATE('" +  fechaInic +"', 'YYYYMMDDHH24MI'))  ";
			
		System.out.println(query);			
			
			
			prepStmt = conexion.prepareStatement(query );
			
			System.out.println(prepStmt);
			
			
			ResultSet rs = prepStmt.executeQuery();
			
			
			
			
			
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(consultaVideosDefault);
			
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					
					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
				}
			}
			closeConnection(conexion);
			
		}
		return true;
		
    }
    
    
    
    /**
     * Método que se encarga de cancelar la compra de una operacion bursatil
     * @return true si se pudo, false de lo contrario
     * @throws Exception se lanza una excepción si ocurre un error en
     * la conexión o en la consulta. 
     */
    public boolean cancelarOperacion( int id,  int idComisionista1) throws Exception
    {
    	PreparedStatement prepStmt = null;
    	
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			
			String query = " DELETE OPERACION_BURSATIL WHERE ID LIKE '" + id + "' AND ID_COMISIONISTA_1 LIKE '" + idComisionista1+"' ";
			
		System.out.println(query);			
			
			
			prepStmt = conexion.prepareStatement(query );
			
			System.out.println(prepStmt);
			
			
			ResultSet rs = prepStmt.executeQuery();
			
			
			
			
			
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(consultaVideosDefault);
			
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					
					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
				}
			}
			closeConnection(conexion);
			
		}
		return true;
		
    }
    
    
    /**
     * Método que se encarga de registrar una operacion bursatil entre dos comisionistas
     * @return true si se pudo, false de lo contrario
     * @throws Exception se lanza una excepción si ocurre un error en
     * la conexión o en la consulta. 
     */
    public boolean registrarOperacion( int id,  int idComisionista1, int idComisionista2,Double valor, String fechaFin) throws Exception
    {
    	PreparedStatement prepStmt = null;
    	
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			
			String query = " UPDATE OPERACION_BURSATIL  SET ID_COMISIONISTA_2 =" + idComisionista2 +",  FECHA_FINAL = TO_DATE('" + fechaFin + "', 'YYYYMMDDHH24MI')  WHERE ID LIKE" + id ;
		System.out.println(query);			
			
			
			prepStmt = conexion.prepareStatement(query );
			
			System.out.println(prepStmt);
			
			
			ResultSet rs = prepStmt.executeQuery();
			
			

			
		
		} catch (SQLException e) {
			e.printStackTrace();
			
			
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					
					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
				}
			}
			closeConnection(conexion);
			
		}
		return true;
		
    }
    
    
}
