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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import co.edu.uniandes.valorAndes.vos.ComisionistaValue;
import co.edu.uniandes.valorAndes.vos.ComposicionValue;
import co.edu.uniandes.valorAndes.vos.InversionistaValue;
import co.edu.uniandes.valorAndes.vos.OferenteValue;
import co.edu.uniandes.valorAndes.vos.OperacionValue;
import co.edu.uniandes.valorAndes.vos.ValorAgregarValue;
import co.edu.uniandes.valorAndes.vos.ValorValue;
import co.edu.uniandes.valorAndes.vos.VideosValue;

/**
 * Clase ConsultaDAO, encargada de hacer las consultas basicas para el cliente
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
	
	
	private ArrayList<ComposicionValue> composicion;

	/**
	 * constructor de la clase. No inicializa ningun atributo.
	 */
	public ConsultaDAO() 
	{		
		composicion = new ArrayList<ComposicionValue>();
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
	public void establecerConexion(String url, String usuario, String clave) throws SQLException
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


	public String darUsuario()
	{
		return usuario;
	}

	public String darClave()
	{
		return clave;
	}

	public String darCadenaConexion()
	{
		return cadenaConexion;
	}
	// ---------------------------------------------------
	// Métodos asociados a los casos de uso: Consulta
	// ---------------------------------------------------

	public ArrayList<ValorValue> darValoresEscogidos( String nTipoValor, String nTipoRentabilidad, String nNegociado, String nFechaExpiracion, int nIdInversionista, int nIdComisionista, int nIdOferente) throws Exception
	{
		ArrayList<ValorValue> valores = new ArrayList<ValorValue>();
		PreparedStatement prepStmt = null;

		String consulta = "SELECT * FROM INSTRUMENTO_FINANCIERO WHERE FECHA_EXPIRACION LIKE (TO_DATE ('" +nFechaExpiracion+"','dd/mm/yyyy') ) AND NEGOCIADO LIKE '" +nNegociado+"' AND TIPO_VALOR LIKE (SELECT ID FROM TIPO_VALOR WHERE NOMBRE LIKE '"+nTipoValor+"') AND ID_RENTABILIDAD LIKE (SELECT ID FROM RENTABILIDAD WHERE NOMBRE LIKE'"+nTipoRentabilidad+"')";



		ValorValue  valorV = new ValorValue();
		try 
		{
			establecerConexion(cadenaConexion, usuario, clave);

			// -------- Aquí se extrae el ID del tipo del valor
			//PreparedStatement prepStmt1 = null;
			//String consulta1 = "SELECT ID FROM TIPO_VALOR WHERE NOMBRE LIKE '" +nTipoValor+ "'";
			//prepStmt1 = conexion.prepareStatement(consulta1);
			//ResultSet rs1 = prepStmt1.executeQuery();
			//rs1.next();
			//int tipoValor = rs1.getInt("ID");



			prepStmt = conexion.prepareStatement(consulta);
			System.out.println(consulta);

			ResultSet rs = prepStmt.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt("ID");
				String nombre = rs.getString("NOMBRE");
				int valor = rs.getInt("VALOR");
				Date fechaExp= rs.getDate("FECHA_EXPIRACION");
				int idUsuario = rs.getInt("ID_USUARIO");
				int tipo = rs.getInt("TIPO_VALOR");
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

	/**
	 * 
	 * @param nTipoUsuario
	 * @param nTipoOperacion
	 * @param nFechaInicial
	 * @param nFechaFinal
	 * @param nCosto
	 * @param nRentabilidad
	 * @return
	 * @throws Exception
	 */
	public ArrayList<OperacionValue> darOperaciones( String nTipoUsuario, String nTipoOperacion, String nFechaInicial, String nFechaFinal, int nCosto, String nRentabilidad ) throws Exception
	{
		ArrayList<OperacionValue> operaciones = new ArrayList<OperacionValue>();
		PreparedStatement prepStmt = null;

		//(FECHA_INICIAL BETWEEN (TO_DATE ('"+nFechaInicial+"','dd/mm/yyyy') ) AND (TO_DATE ('"+nFechaFinal+"','dd/mm/yyyy'))) AND (FECHA_FINAL BETWEEN (TO_DATE ('"+nFechaInicial+"','dd/mm/yyyy')) AND (TO_DATE ('"+nFechaFinal+"','dd/mm/yyyy')))
		String consulta = "SELECT * FROM OPERACION_BURSATIL WHERE TIPO LIKE '"+nTipoOperacion+"' AND VALOR LIKE "+ nCosto + " AND (FECHA_INICIAL BETWEEN (TO_DATE ('"+nFechaInicial+"','dd/mm/yyyy') ) AND (TO_DATE ('"+nFechaFinal+"','dd/mm/yyyy'))) AND (FECHA_FINAL BETWEEN (TO_DATE ('"+nFechaInicial+"','dd/mm/yyyy')))" ;
		OperacionValue  operacionV = new OperacionValue();
		try 
		{
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement(consulta);
			System.out.println(consulta);

			ResultSet rs = prepStmt.executeQuery();
			while(rs.next())
			{

				int id = rs.getInt("ID");
				String tipo = rs.getString("TIPO");
				int valor = rs.getInt("VALOR");
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


	public boolean retirarIntermediario( int idUsuario, String nNombreComisionista, int idComisionista ) throws Exception
	{
		PreparedStatement prepStmt = null;

		try {
			establecerConexion(cadenaConexion, usuario, clave);

			String query = "";
			String query2 = " ";

			System.out.println(query);	
			System.out.println(query2);	


			prepStmt = conexion.prepareStatement(query );

			System.out.println(prepStmt);


			ResultSet rs = prepStmt.executeQuery();

			prepStmt = conexion.prepareStatement(query2 );
			prepStmt.executeQuery();



		} catch (SQLException e) {
			e.printStackTrace();


			throw new Exception("ERROR = ConsultaDAO: " + e.getMessage());
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexion.");
				}
			}
			closeConnection(conexion);

		}
		return true;

	}

	public boolean recomponerPortafolio( ArrayList<String> decisiones, ArrayList<Integer> porcentajes, ArrayList<ValorAgregarValue> valores ) throws Exception
	{
		PreparedStatement prepStmt = null;

		try {
			establecerConexion(cadenaConexion, usuario, clave);

			String query = "begin";
			prepStmt = conexion.prepareStatement(query);
			prepStmt.executeQuery();
			int totalidad = 0;
			for(int i = 0; i < composicion.size(); i++)
			{
				String decision = (String)decisiones.get(i);
				Integer porcentaje = (Integer)porcentajes.get(i);
				ComposicionValue actual = (ComposicionValue)composicion.get(i);
				if(decision.startsWith("ordenarCompra"))
				{
					Integer anterior = actual.getPorcentaje();
					
					String query3 = "SELECT PORCENTAJE FROM COMPOSICION WHERE PORCENTAJE = "+ anterior +" FOR UPDATE";
					prepStmt = conexion.prepareStatement(query3);
					prepStmt.executeQuery();
					
					actual.sumarPorcentaje(porcentaje);
					totalidad += actual.getPorcentaje();
					
					String query4 = "UPDATE COMPOSICION SET PORCENTAJE = "+actual.getPorcentaje()+" WHERE PORCENTAJE = " + anterior;
					prepStmt = conexion.prepareStatement(query4);
					prepStmt.executeUpdate();
				}
				else if(decision.startsWith("ordenarVentaParcial"))
				{
					Integer anterior = actual.getPorcentaje();
					
					String query3 = "SELECT PORCENTAJE FROM COMPOSICION WHERE PORCENTAJE = "+ anterior +" FOR UPDATE";
					prepStmt = conexion.prepareStatement(query3);
					prepStmt.executeQuery();
					
					actual.restarPorcentaje(porcentaje);
					totalidad += actual.getPorcentaje();
					
					String query4 = "UPDATE COMPOSICION SET PORCENTAJE = "+actual.getPorcentaje()+" WHERE PORCENTAJE = " + anterior;
					prepStmt = conexion.prepareStatement(query4);
					prepStmt.executeUpdate();
				}
				else if(decision.startsWith("ordenarVentaTotal"))
				{
					int idValor = actual.getIdValor();
					
					String query3 = "SELECT VALOR, ID_USUARIO FROM INSTRUMENTO_FINANCIERO WHERE ID = "+idValor;
					prepStmt = conexion.prepareStatement(query3);
					ResultSet rs = prepStmt.executeQuery();
					
					rs.next();
					Double valor = rs.getDouble("VALOR");
					int idUsuario1 = rs.getInt("ID_USUARIO");
					
					String query4 = "SELECT ID_COMISIONISTA FROM COMISIONISTA_INVERSIONISTA WHERE ID_INVERSIONISTA = (SELECT ID FROM INVERSIONISTA WHERE ID_USUARIO = "+idUsuario1+")";
					prepStmt = conexion.prepareStatement(query4);
					rs = prepStmt.executeQuery();
					rs.next();
					int idComisionista1 = rs.getInt("ID_COMISIONISTA");
					
					String query5 = "SELECT ID_COMISIONISTA FROM COMISIONISTA_INVERSIONISTA WHERE ID_INVERSIONISTA = (SELECT ID FROM INVERSIONISTA WHERE ID_USUARIO = "+idUsuario1+")";
					prepStmt = conexion.prepareStatement(query4);
					rs = prepStmt.executeQuery();
					rs.next();
					idComisionista1 = rs.getInt("ID_COMISIONISTA");
					
					Calendar fecha = new GregorianCalendar();
			        
			        int a�o = fecha.get(Calendar.YEAR);
			        int mes = fecha.get(Calendar.MONTH) + 1;
			        int dia = fecha.get(Calendar.DAY_OF_MONTH);
			        int hora = fecha.get(Calendar.HOUR_OF_DAY);
			        int minuto = fecha.get(Calendar.MINUTE);
			        
			       
			        
			        String fechaInic = a�o +  String.format("%02d",mes) +  String.format("%02d",dia)  + String.format( "%02d%02d",hora, minuto);
					
					ordenarOperacion(200, "Venta", valor, idUsuario1, idComisionista1, idValor, fechaInic);
				}
				else
				{
					continue;
				}
			}
			
			String query2 = "end";
			prepStmt = conexion.prepareStatement(query2);
			prepStmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();


			throw new Exception("ERROR = ConsultaDAO: " + e.getMessage());
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexion.");
				}
			}
			closeConnection(conexion);
		}
		return true;
	}


	public ArrayList<ComposicionValue> darComposicionPortafolio( int nIdPortafolio ) throws Exception
	{
		PreparedStatement prepStmt = null;
		composicion = new ArrayList<ComposicionValue>();

		try {
			establecerConexion(cadenaConexion, usuario, clave);

			String query = "SELECT C.ID_PORTAFOLIO, C.ID_VALOR, INF.NOMBRE, C.PORCENTAJE FROM COMPOSICION C INNER JOIN INSTRUMENTO_FINANCIERO INF ON C.ID_VALOR = INF.ID WHERE C.ID_PORTAFOLIO = '"+nIdPortafolio+"'";
			ComposicionValue nueva = new ComposicionValue();
			prepStmt = conexion.prepareStatement(query );
			System.out.println(prepStmt);
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next())
			{
				int idPortafolio = rs.getInt("ID_PORTAFOLIO");
				nueva.setIdPortafolio(nIdPortafolio);
				int idValor = rs.getInt("ID_VALOR");
				nueva.setIdValor(idValor);
				String nombreValor = rs.getString("NOMBRE");
				nueva.setNombreValor(nombreValor);
				int porcentaje = rs.getInt("PORCENTAJE");
				nueva.setPorcentaje(porcentaje);
				composicion.add(nueva);
				nueva = new ComposicionValue();
			}
			return composicion;


		} catch (SQLException e) {
			e.printStackTrace();


			throw new Exception("ERROR = ConsultaDAO: " + e.getMessage());
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexion.");
				}
			}
			closeConnection(conexion);

		}

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
			prepStmt = conexion.prepareStatement("");

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
			String query2 = " UPDATE COMISIONISTA SET ID_USUARIO = " + idUsuario1 + " WHERE NUM_REGISTRO LIKE '"+ idComisionista1+  "' AND ( ID_USUARIO IS NULL OR ID_USUARIO LIKE '" + idUsuario1+ "')";

			System.out.println(query);	
			System.out.println(query2);	


			prepStmt = conexion.prepareStatement(query );

			System.out.println(prepStmt);


			ResultSet rs = prepStmt.executeQuery();

			prepStmt = conexion.prepareStatement(query2 );
			prepStmt.executeQuery();



		} catch (SQLException e) {
			e.printStackTrace();


			throw new Exception("ERROR = ConsultaDAO: " + e.getMessage());
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexion.");
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

			String query = " UPDATE OPERACION_BURSATIL  SET ID_COMISIONISTA_2 =" + idComisionista2 +" ,  FECHA_FINAL = TO_DATE('" + fechaFin + "', 'YYYYMMDDHH24MI')  WHERE ID LIKE " + id ;
			System.out.println(query);			


			prepStmt = conexion.prepareStatement(query );

			System.out.println(prepStmt);


			ResultSet rs = prepStmt.executeQuery();

			String query2 = "SELECT ID_USUARIO FROM COMISIONISTA WHERE NUM_REGISTRO LIKE " + idComisionista2;
			System.out.println(query2);
			prepStmt = conexion.prepareStatement( query2);

			rs = prepStmt.executeQuery();


			String idUsuario2 = "2";

			while(rs.next()){
				idUsuario2 = rs.getString("ID_USUARIO");
				System.out.println(idUsuario2);

			}

			String query3 = "UPDATE OPERACION_BURSATIL  SET ID_USUARIO_2 = " + idUsuario2 + " WHERE ID LIKE " + id;
			System.out.println(query3);

			prepStmt = conexion.prepareStatement(query3);
			prepStmt.executeQuery();


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




	/**
	 * Metodo que se retornar todos los comisionistas dentro de la bolsa de valores
	 * @return true si se pudo, false de lo contrario
	 * @throws Exception se lanza una excepcion si ocurre un error en
	 * la conexion o en la consulta. 
	 */


	public ArrayList darComisionistas() throws Exception
	{
		PreparedStatement prepStmt = null;

		ArrayList comisionistas = new ArrayList<VideosValue>();


		try {
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement("SELECT * FROM COMISIONISTA");

			ResultSet rs = prepStmt.executeQuery();

			while(rs.next()){
				String numRegistro = rs.getString("NUM_REGISTRO");
				String nombreEntidad = rs.getString("NOMBRE");
				String ciudad = rs.getString("CIUDAD");
				String direccion = rs.getString("DIRECCION");
				String telefono = rs.getString("TELEFONO");
				String nomRepresentante = rs.getString("NOM_REPRESENTANTE");

				ComisionistaValue nuevo = new ComisionistaValue(numRegistro, nombreEntidad, ciudad, direccion, telefono, nomRepresentante);



				comisionistas.add(nuevo);


			}

		} catch (SQLException e) {
			e.printStackTrace();

			throw new Exception("ERROR = ConsultaDAO: " + e.getMessage());
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: cerrando la conexion.");
				}
			}
			closeConnection(conexion);
		}		
		return comisionistas;
	}




	/**
	 * Metodo que se retornar todos los Oferentes dentro de la bolsa de valores
	 * @return true si se pudo, false de lo contrario
	 * @throws Exception se lanza una excepcion si ocurre un error en
	 * la conexion o en la consulta. 
	 */


	public ArrayList darOferentes() throws Exception
	{
		PreparedStatement prepStmt = null;

		ArrayList oferentes = new ArrayList<VideosValue>();


		try {
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement("SELECT * FROM OFERENTE");

			ResultSet rs = prepStmt.executeQuery();

			while(rs.next()){
				String id = rs.getString("ID");
				String nombre = rs.getString("NOMBRE");
				String tipo = rs.getString("TIPO");
				String direccion = rs.getString("DIRECCION");
				String telefono = rs.getString("TELEFONO");
				String ciudad = rs.getString("CIUDAD");
				String nomRepresentante = rs.getString("NOM_REPRESENTANTE");




				OferenteValue nuevo = new OferenteValue(id, nombre, tipo, direccion, telefono, ciudad, nomRepresentante);



				oferentes.add(nuevo);


			}

		} catch (SQLException e) {
			e.printStackTrace();

			throw new Exception("ERROR = ConsultaDAO: " + e.getMessage());
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO:  cerrando la conexion.");
				}
			}
			closeConnection(conexion);
		}		
		return oferentes;
	}




	/**
	 * Metodo que se retornar todos los Inversionistas dentro de la bolsa de valores
	 * @return true si se pudo, false de lo contrario
	 * @throws Exception se lanza una excepcion si ocurre un error en
	 * la conexion o en la consulta. 
	 */


	public ArrayList darInversionistas() throws Exception
	{
		PreparedStatement prepStmt = null;

		ArrayList inversionistas = new ArrayList<VideosValue>();


		try {
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement("SELECT * FROM INVERSIONISTA");

			ResultSet rs = prepStmt.executeQuery();

			while(rs.next()){
				String id = rs.getString("ID");
				String nombre = rs.getString("NOMBRE");
				String tipo = rs.getString("TIPO");
				String direccion = rs.getString("DIRECCION");
				String telefono = rs.getString("TELEFONO");
				String ciudad = rs.getString("CIUDAD");
				String nomRepresentante = rs.getString("NOM_REPRESENTANTE");




				InversionistaValue nuevo = new InversionistaValue(id, nombre, tipo, direccion, telefono, ciudad, nomRepresentante);



				inversionistas.add(nuevo);


			}

		} catch (SQLException e) {
			e.printStackTrace();

			throw new Exception("ERROR = ConsultaDAO: " + e.getMessage());
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO:  cerrando la conexion.");
				}
			}
			closeConnection(conexion);
		}		
		return inversionistas;
	}


}
