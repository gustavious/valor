/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ConsultaDAO.java,v 1.10 
 * Universidad de los Andes (BogotÃ¡ - Colombia)
 * Departamento de IngenierÃ­a de Sistemas y ComputaciÃ³n 
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

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;

import co.edu.uniandes.valorAndes.serveletsFinal.Sender;
import co.edu.uniandes.valorAndes.vos.ComisionistaValue;
import co.edu.uniandes.valorAndes.vos.ComposicionValue;
import co.edu.uniandes.valorAndes.vos.InversionistaValue;
import co.edu.uniandes.valorAndes.vos.OferenteValue;
import co.edu.uniandes.valorAndes.vos.OperacionValue;
import co.edu.uniandes.valorAndes.vos.PortafolioValue;
import co.edu.uniandes.valorAndes.vos.RecomponerValue;
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
	 * ruta donde se encuentra el archivo de conexiÃ³n.
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
	 * clave de conexiÃ³n a la base de datos.
	 */
	private String clave;

	/**
	 * URL al cual se debe conectar para acceder a la base de datos.
	 */
	private String cadenaConexion;


	private ArrayList<ComposicionValue> composicion;

	private Sender sender;

	private ConnectionFactory cf;

	private Connection conex;

	private Session sesion;

	private Destination destino;

	private MessageProducer mp;






	/**
	 * M�todo constructor de la clase DAO. Inicializa los atributos por medio de
	 * JNDI.
	 */
	public ConsultaDAO() {

		composicion = new ArrayList<ComposicionValue>();
		sender = new Sender();
		try {
			// Inicia el contexto seg�n la interfaz dada por JBOSS.
			InitialContext init = new InitialContext();
			this.cf = (ConnectionFactory) init.lookup("RemoteConnectionFactory");
			this.destino = (Destination) init.lookup("queue/testCola");
			this.conex = (Connection) this.cf.createConnection("sistrans", "test");
			((javax.jms.Connection) this.conex).start();
			this.sesion = ((javax.jms.Connection) this.conex).createSession(false, Session.AUTO_ACKNOWLEDGE);
			this.mp = this.sesion.createProducer(this.destino);


			System.out.println("ValorAndes - Se conecto a la fabrica de conexiones de forma adecuada..");
		} catch (Exception e) {
			// Ocurrio un error y se imprimira por consola
			e.printStackTrace();
		}

	}








	// -------------------------------------------------
	// Metodos
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
	 * MÃ©todo que se encarga de crear la conexiÃ³n con el Driver Manager
	 * a partir de los parametros recibidos.
	 * @param url direccion url de la base de datos a la cual se desea conectar
	 * @param usuario nombre del usuario que se va a conectar a la base de datos
	 * @param clave clave de acceso a la base de datos
	 * @throws SQLException si ocurre un error generando la conexiÃ³n con la base de datos.
	 */
	public void establecerConexion(String url, String usuario, String clave) throws SQLException
	{
		try
		{
			conexion = DriverManager.getConnection(url,usuario,clave);
			conexion.setAutoCommit(false);
		}
		catch( SQLException exception )
		{
			throw new SQLException( "ERROR: ConsultaDAO obteniendo una conexion." );
		}
	}



	/**
	 * M�todo que abre las conexiones para realizar transacciones.
	 */

	private void iniciarConexion() {
		try {
			System.out.println("ValorAndes 1 - Abriendo conexiones..");
			conex = (Connection) cf.createConnection();
		} catch (Exception e) {
			// Error al crear las conexiones
			e.printStackTrace();
		}
	}

	/**
	 *Cierra la conexiÃ³n activa a la base de datos. AdemÃ¡s, con=null.
	 * @param con objeto de conexiÃ³n a la base de datos
	 * @throws SistemaCinesException Si se presentan errores de conexiÃ³n
	 */
	public void closeConnection(Connection connection) throws Exception {        
		try {
			connection.close();
			connection = null;
		} catch (SQLException exception) {
			throw new Exception("ERROR: ConsultaDAO: closeConnection() = cerrando una conexion.");
		}
	} 


	/**
	 * M�todo que cierra las conexiones una vez se ha realizado la transacci�n.
	 */

	private void cerrarConexion() {
		System.out.println("WebApp 1 - Cerrando.. conexiones");
		try {
			conex.close();
		} catch (Exception e) {
			// Error al cerrar las conexiones
			e.printStackTrace();
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
	// MÃ©todos asociados a los casos de uso: Consulta
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

			// -------- AquÃ­ se extrae el ID del tipo del valor
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

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiÃ³n.");
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

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiÃ³n.");
				}
			}
			closeConnection(conexion);
		}

		return operaciones;
	}


	public boolean retirarIntermediario( int idUsuario, int idComisionista ) throws Exception
	{
		System.out.println(idUsuario);
		System.out.println(idComisionista);
		PreparedStatement prepStmt = null;

		try {
			establecerConexion(cadenaConexion, usuario, clave);

			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			int numRegistro = idComisionista;
			int idUsuarioNuevo = 0;


			String query = "SELECT ID_USUARIO FROM COMISIONISTA WHERE NUM_REGISTRO = " + (idComisionista+1);
			prepStmt = conexion.prepareStatement(query);

			ResultSet rs = prepStmt.executeQuery();
			if(rs.next())
			{
				idUsuarioNuevo = rs.getInt("ID_USUARIO");
				System.out.println(idUsuarioNuevo);
				++numRegistro;
				System.out.println(numRegistro);
			}
			else
			{
				String query2 = "SELECT ID_USUARIO FROM COMISIONISTA WHERE NUM_REGISTRO = " + (idComisionista-1);
				prepStmt = conexion.prepareStatement(query2);
				rs = prepStmt.executeQuery();
				if( rs.next())
				{
					idUsuarioNuevo = rs.getInt("ID_USUARIO");
					--numRegistro;
				}
				else
				{
					throw new Exception("No hay más intermediarios para intercambiar, no se puede retirar!");
				}
			}

			String query4 = "SELECT ID_COMISIONISTA FROM COMISIONISTA_INVERSIONISTA WHERE ID_COMISIONISTA = " + idComisionista + " FOR UPDATE";
			System.out.println(idComisionista);
			prepStmt = conexion.prepareStatement(query4);
			prepStmt.executeUpdate();

			String query5 = "UPDATE COMISIONISTA_INVERSIONISTA SET ID_COMISIONISTA = "+ numRegistro +" WHERE ID_COMISIONISTA = " + idComisionista;
			System.out.println(numRegistro);
			prepStmt = conexion.prepareStatement(query5);
			prepStmt.executeUpdate();

			String query6 = "SELECT ID_COMISIONISTA FROM COMISIONISTA_OFERENTE WHERE ID_COMISIONISTA = " + idComisionista + " FOR UPDATE";
			System.out.println(idComisionista);
			prepStmt = conexion.prepareStatement(query6);
			prepStmt.executeUpdate();

			String query7 = "UPDATE COMISIONISTA_OFERENTE SET ID_COMISIONISTA = "+ numRegistro +" WHERE ID_COMISIONISTA = " + idComisionista;
			System.out.println(numRegistro);
			prepStmt = conexion.prepareStatement(query7);
			prepStmt.executeUpdate();

			String query8 = "SELECT ID_COMISIONISTA_1 FROM OPERACION_BURSATIL WHERE ID_COMISIONISTA_1 = " + idComisionista + " FOR UPDATE";
			prepStmt = conexion.prepareStatement(query8);
			prepStmt.executeUpdate();

			String query9 = "UPDATE OPERACION_BURSATIL SET ID_COMISIONISTA_1 = "+ numRegistro +" WHERE ID_COMISIONISTA_1 = " + idComisionista ;
			prepStmt = conexion.prepareStatement(query9);
			prepStmt.executeUpdate();

			String query10 = "SELECT ID_COMISIONISTA_2 FROM OPERACION_BURSATIL WHERE ID_COMISIONISTA_2 = " + idComisionista + " FOR UPDATE";
			prepStmt = conexion.prepareStatement(query10);
			prepStmt.executeUpdate();

			String query11 = "UPDATE OPERACION_BURSATIL SET ID_COMISIONISTA_2 = "+ numRegistro +" WHERE ID_COMISIONISTA_2 = " + idComisionista;
			prepStmt = conexion.prepareStatement(query11);
			prepStmt.executeUpdate();

			String query12 = "SELECT ID_USUARIO FROM PORTAFOLIO WHERE ID_USUARIO = " + idUsuario + " FOR UPDATE";
			prepStmt = conexion.prepareStatement(query12);
			prepStmt.executeUpdate();

			String query13 = "UPDATE PORTAFOLIO SET ID_USUARIO = "+ idUsuarioNuevo +" WHERE ID_USUARIO = " + idUsuario ;
			prepStmt = conexion.prepareStatement(query13);
			prepStmt.executeUpdate();

			String query15 = "DELETE FROM COMISIONISTA WHERE ID_USUARIO = " + idUsuario;
			prepStmt = conexion.prepareStatement(query15);
			prepStmt.executeUpdate();

			String query14 = "DELETE FROM USUARIO WHERE ID = " + idUsuario;
			prepStmt = conexion.prepareStatement(query14);
			prepStmt.executeUpdate();

			conexion.commit();
			conexion.setAutoCommit(true);
		} 
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			conexion.setAutoCommit(true);
			throw new Exception("ERROR = ConsultaDAO: " + e.getMessage());
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
					conexion.setAutoCommit(true);
				} catch (SQLException exception) {

					conexion.setAutoCommit(true);
					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexion.");
				}
			}
			closeConnection(conexion);

		}
		return true;

	}

	
	public boolean recomponerPortafolioNuevo( int idPortafolio, ArrayList<RecomponerValue> valores ) throws Exception
	{
		PreparedStatement prepStmt = null;
		try
		{
			establecerConexion(cadenaConexion, usuario, clave);

			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

			
			
			for( int i = 0; i< valores.size(); i++)
			{
				RecomponerValue valor = (RecomponerValue) valores.get(i);
				if( valor.getDecision() == RecomponerValue.VENTA_PARCIAL)
				{
					String query1980 = "UPDATE COMPOSICION SET PORCENTAJE = " + valor.getValorVenta() + " WHERE ID_PORTAFOLIO = "+ idPortafolio + " AND ID_VALOR = " + valor.getIdValor() ;
					prepStmt = conexion.prepareStatement(query1980);
					prepStmt.executeUpdate();
				}
				else if( valor.getDecision() == RecomponerValue.VENTA_TOTAL)
				{
					String query1980 = "DELETE FROM COMPOSICION WHERE ID_PORTAFOLIO = "+ idPortafolio +" AND ID_VALOR = " + valor.getIdValor();
					prepStmt = conexion.prepareStatement(query1980);
					prepStmt.executeUpdate();
				}
				else if( valor.getDecision() == RecomponerValue.COMPRA_PARCIAL)
				{
					String query1980 = "UPDATE COMPOSICION SET PORCENTAJE = " + valor.getValorVenta() + " WHERE ID_PORTAFOLIO = "+ idPortafolio + " AND ID_VALOR = " + valor.getIdValor() ;
					prepStmt = conexion.prepareStatement(query1980);
					prepStmt.executeUpdate();
				}
				else if( valor.getDecision() == RecomponerValue.COMPRA_TOTAL)
				{
					String query1980 = "INSERT INTO COMPOSICION (ID_PORTAFOLIO, ID_VALOR, PORCENTAJE) VALUES ( "+ idPortafolio +" , "+ valor.getIdValor() + " , " + valor.getValorVenta()+" )" ;
					prepStmt = conexion.prepareStatement(query1980);
					prepStmt.executeUpdate();
				}
			}
			
			conexion.commit();
			conexion.setAutoCommit(true);
			
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			conexion.setAutoCommit(true);
			throw new Exception("ERROR = ConsultaDAO: " + e.getMessage());
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
					conexion.setAutoCommit(true);
				} catch (SQLException exception) {

					conexion.setAutoCommit(true);
					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexion.");
				}
			}
			closeConnection(conexion);

		}
		return true;
		
	}
	
	
	
	public boolean retirarIntermediario2( int idUsuario, int idComisionista ) throws Exception
	{
		System.out.println(idUsuario);
		System.out.println(idComisionista);
		PreparedStatement prepStmt = null;

		try {
			establecerConexion(cadenaConexion, usuario, clave);

			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

			String query1980 = "SELECT * FROM COMISIONISTA WHERE NUM_REGISTRO = " + idComisionista;
			prepStmt = conexion.prepareStatement(query1980);
			ResultSet rs1980 = prepStmt.executeQuery();

			if( !rs1980.next())
			{
				sender.enviarMensaje("RequerimientoRetirarIntermediario: el idComisionista es = "+ idComisionista);
			}
			else
			{
				int numRegistro = idComisionista;
				int idUsuarioNuevo = 0;


				String query = "SELECT ID_USUARIO FROM COMISIONISTA WHERE NUM_REGISTRO = " + (idComisionista+1);
				prepStmt = conexion.prepareStatement(query);

				ResultSet rs = prepStmt.executeQuery();
				if(rs.next())
				{
					idUsuarioNuevo = rs.getInt("ID_USUARIO");
					System.out.println(idUsuarioNuevo);
					++numRegistro;
					System.out.println(numRegistro);
				}
				else
				{
					String query2 = "SELECT ID_USUARIO FROM COMISIONISTA WHERE NUM_REGISTRO = " + (idComisionista-1);
					prepStmt = conexion.prepareStatement(query2);
					rs = prepStmt.executeQuery();
					if( rs.next())
					{
						idUsuarioNuevo = rs.getInt("ID_USUARIO");
						--numRegistro;
					}
					else
					{
						throw new Exception("No hay más intermediarios para intercambiar, no se puede retirar!");
					}
				}

				String query4 = "SELECT ID_COMISIONISTA FROM COMISIONISTA_INVERSIONISTA WHERE ID_COMISIONISTA = " + idComisionista + " FOR UPDATE";
				System.out.println(idComisionista);
				prepStmt = conexion.prepareStatement(query4);
				prepStmt.executeUpdate();

				String query5 = "UPDATE COMISIONISTA_INVERSIONISTA SET ID_COMISIONISTA = "+ numRegistro +" WHERE ID_COMISIONISTA = " + idComisionista;
				System.out.println(numRegistro);
				prepStmt = conexion.prepareStatement(query5);
				prepStmt.executeUpdate();

				String query6 = "SELECT ID_COMISIONISTA FROM COMISIONISTA_OFERENTE WHERE ID_COMISIONISTA = " + idComisionista + " FOR UPDATE";
				System.out.println(idComisionista);
				prepStmt = conexion.prepareStatement(query6);
				prepStmt.executeUpdate();

				String query7 = "UPDATE COMISIONISTA_OFERENTE SET ID_COMISIONISTA = "+ numRegistro +" WHERE ID_COMISIONISTA = " + idComisionista;
				System.out.println(numRegistro);
				prepStmt = conexion.prepareStatement(query7);
				prepStmt.executeUpdate();

				String query8 = "SELECT ID_COMISIONISTA_1 FROM OPERACION_BURSATIL WHERE ID_COMISIONISTA_1 = " + idComisionista + " FOR UPDATE";
				prepStmt = conexion.prepareStatement(query8);
				prepStmt.executeUpdate();

				String query9 = "UPDATE OPERACION_BURSATIL SET ID_COMISIONISTA_1 = "+ numRegistro +" WHERE ID_COMISIONISTA_1 = " + idComisionista ;
				prepStmt = conexion.prepareStatement(query9);
				prepStmt.executeUpdate();

				String query10 = "SELECT ID_COMISIONISTA_2 FROM OPERACION_BURSATIL WHERE ID_COMISIONISTA_2 = " + idComisionista + " FOR UPDATE";
				prepStmt = conexion.prepareStatement(query10);
				prepStmt.executeUpdate();

				String query11 = "UPDATE OPERACION_BURSATIL SET ID_COMISIONISTA_2 = "+ numRegistro +" WHERE ID_COMISIONISTA_2 = " + idComisionista;
				prepStmt = conexion.prepareStatement(query11);
				prepStmt.executeUpdate();

				String query12 = "SELECT ID_USUARIO FROM PORTAFOLIO WHERE ID_USUARIO = " + idUsuario + " FOR UPDATE";
				prepStmt = conexion.prepareStatement(query12);
				prepStmt.executeUpdate();

				String query13 = "UPDATE PORTAFOLIO SET ID_USUARIO = "+ idUsuarioNuevo +" WHERE ID_USUARIO = " + idUsuario ;
				prepStmt = conexion.prepareStatement(query13);
				prepStmt.executeUpdate();

				String query15 = "DELETE FROM COMISIONISTA WHERE ID_USUARIO = " + idUsuario;
				prepStmt = conexion.prepareStatement(query15);
				prepStmt.executeUpdate();

				String query14 = "DELETE FROM USUARIO WHERE ID = " + idUsuario;
				prepStmt = conexion.prepareStatement(query14);
				prepStmt.executeUpdate();

				conexion.commit();
				conexion.setAutoCommit(true);
			} 
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			conexion.setAutoCommit(true);
			throw new Exception("ERROR = ConsultaDAO: " + e.getMessage());
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
					conexion.setAutoCommit(true);
				} catch (SQLException exception) {

					conexion.setAutoCommit(true);
					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexion.");
				}
			}
			closeConnection(conexion);

		}
		return true;


	}

	public boolean recomponerPortafolio( ArrayList<String> decisiones, ArrayList<Integer> porcentajes, ValorAgregarValue valorNuevo ) throws Exception
	{
		PreparedStatement prepStmt = null;

		try {
			establecerConexion(cadenaConexion, usuario, clave);
			conexion.setAutoCommit(false);
			conexion.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

			//			String query = "begin";
			//			prepStmt = conexion.prepareStatement(query);
			//			prepStmt.executeQuery();
			int totalidad = 0;
			for(int i = 0; i < composicion.size(); i++)
			{
				String decision = "";


				if(decisiones.get(i) != null)
					decision = (String)decisiones.get(i);

				Integer porcentaje = (Integer)porcentajes.get(i);


				ComposicionValue actual = (ComposicionValue)composicion.get(i);


				if(decision.startsWith("ordenar Compra"))
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
				else if(decision.startsWith("ordenar Venta Parcial"))
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
				else if(decision.startsWith("ordenar Venta Total"))
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


					Calendar fecha = new GregorianCalendar();

					int anio = fecha.get(Calendar.YEAR);
					int mes = fecha.get(Calendar.MONTH) + 1;
					int dia = fecha.get(Calendar.DAY_OF_MONTH);
					int hora = fecha.get(Calendar.HOUR_OF_DAY);
					int minuto = fecha.get(Calendar.MINUTE);



					String fechaInic = anio +  String.format("%02d",mes) +  String.format("%02d",dia)  + String.format( "%02d%02d",hora, minuto);

					ordenarOperacion(((int)Math.random()), "Venta", valor, idUsuario1, idComisionista1, idValor, fechaInic);
				}
				else
				{
					continue;
				}
			}

			if( valorNuevo != null )
			{
				int idValorNuevo = valorNuevo.getIdValor();
				if(  idValorNuevo > 0 )
				{

					String query20 = "SELECT VALOR FROM INSTRUMENTO_FINANCIERO WHERE ID = "+ valorNuevo.getIdValor();
					PreparedStatement ps = conexion.prepareStatement(query20);
					ResultSet rs1 = ps.executeQuery();
					if(rs1.next( ))
					{
						Double valor2 = rs1.getDouble("VALOR");
						Calendar fecha = new GregorianCalendar();

						String query3 = "SELECT ID_USUARIO FROM INSTRUMENTO_FINANCIERO WHERE ID = "+((ComposicionValue)composicion.get(0)).getIdValor();
						prepStmt = conexion.prepareStatement(query3);
						ResultSet rs = prepStmt.executeQuery();

						rs.next();
						int idUsuario2 = rs.getInt("ID_USUARIO");

						String query4 = "SELECT ID_COMISIONISTA FROM COMISIONISTA_INVERSIONISTA WHERE ID_INVERSIONISTA = (SELECT ID FROM INVERSIONISTA WHERE ID_USUARIO = "+idUsuario2+")";
						prepStmt = conexion.prepareStatement(query4);
						rs = prepStmt.executeQuery();
						rs.next();
						int idComisionista2 = rs.getInt("ID_COMISIONISTA");

						int anio = fecha.get(Calendar.YEAR);
						int mes = fecha.get(Calendar.MONTH) + 1;
						int dia = fecha.get(Calendar.DAY_OF_MONTH);
						int hora = fecha.get(Calendar.HOUR_OF_DAY);
						int minuto = fecha.get(Calendar.MINUTE);


						String fechaInic = anio +  String.format("%02d",mes) +  String.format("%02d",dia)  + String.format( "%02d%02d",hora, minuto);
						ordenarOperacion(((int)Math.random()), "Compra", valor2, idUsuario2, idComisionista2, idValorNuevo, fechaInic);


						String query23 = "INSERT INTO COMPOSICION VALUES ( "+((ComposicionValue)composicion.get(0)).getIdPortafolio()+", "+idValorNuevo+", "+valorNuevo.getPorcentaje()+" )";
						prepStmt = conexion.prepareStatement(query23);
						prepStmt.executeUpdate();

					}
					else
					{
						conexion.setAutoCommit(true);
						throw new Exception( "La transacci&oacute;n no se pudo completar porque el valor con id que sea comprar no existe");
					}
				}
			}

			conexion.commit();
			conexion.setAutoCommit(true);
			//			String query2 = "end";
			//			prepStmt = conexion.prepareStatement(query2);
			//			prepStmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			conexion.setAutoCommit(true);

			throw new Exception("ERROR = ConsultaDAO: " + e.getMessage());
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					conexion.setAutoCommit(true);
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
	 * MÃ©todo que se encarga de realizar la consulta en la base de datos
	 * y retorna un ArrayList de elementos tipo VideosValue.
	 * @return ArrayList lista que contiene elementos tipo VideosValue.
	 * La lista contiene los videos ordenados alfabeticamente
	 * @throws Exception se lanza una excepciÃ³n si ocurre un error en
	 * la conexiÃ³n o en la consulta. 
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

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiÃ³n.");
				}
			}
			closeConnection(conexion);
		}		
		return videos;
	}

	/**
	 * MÃ©todo que se encarga de ordenar la compra o venta de una nueva operacion bursatil
	 * @return true si se pudo, false de lo contrario
	 * @throws Exception se lanza una excepciÃ³n si ocurre un error en
	 * la conexiÃ³n o en la consulta. 
	 */
	public boolean ordenarOperacion( int id, String tipo, Double valor, int idUsuario1, int idComisionista1, int idInstrumento, String fechaInic) throws Exception
	{

		conexion.setAutoCommit(false);
		conexion.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		PreparedStatement prepStmt = null;



		try {
			establecerConexion(cadenaConexion, usuario, clave);



			String query = "  INSERT INTO OPERACION_BURSATIL (ID, TIPO, VALOR,  ID_USUARIO_1, ID_COMISIONISTA_1, ID_INS_FIN,  FECHA_INICIAL) VALUES ( " + id + ", '" + tipo + "', "+ valor +", "+ idUsuario1 +", "+ idComisionista1+", "+ idInstrumento+ ", TO_DATE('" +  fechaInic +"', 'YYYYMMDDHH24MI'))  ";
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
			conexion.commit();
			closeConnection(conexion);

		}
		return true;

	}



	/**
	 * MÃ©todo que se encarga de cancelar la compra de una operacion bursatil
	 * @return true si se pudo, false de lo contrario
	 * @throws Exception se lanza una excepciÃ³n si ocurre un error en
	 * la conexiÃ³n o en la consulta. 
	 */
	public boolean cancelarOperacion( int id,  int idComisionista1) throws Exception
	{

		conexion.setAutoCommit(false);
		conexion.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
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

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiÃ³n.");
				}
			}
			conexion.commit();
			closeConnection(conexion);

		}
		return true;

	}


	/**
	 * MÃ©todo que se encarga de registrar una operacion bursatil entre dos comisionistas
	 * @return true si se pudo, false de lo contrario
	 * @throws Exception se lanza una excepciÃ³n si ocurre un error en
	 * la conexiÃ³n o en la consulta. 
	 */
	public boolean registrarOperacion( int id,  int idComisionista1, int idComisionista2,Double valor, String fechaFin) throws Exception
	{
		conexion.setAutoCommit(false);
		conexion.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
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

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiÃ³n.");
				}
			}
			conexion.commit();
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

		ArrayList inversionistas = darInversionistas();

		PreparedStatement prepStmt = null;
		PreparedStatement prepStmt2 = null;


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

				System.out.println(nuevo);

				prepStmt2 = conexion.prepareStatement("SELECT * FROM COMISIONISTA_INVERSIONISTA WHERE ID_COMISIONISTA LIKE " + numRegistro);

				ResultSet rs2 = prepStmt2.executeQuery();



				while(rs2.next()){
					String idCom = rs2.getString("ID_COMISIONISTA");
					String idInv = rs2.getString("ID_INVERSIONISTA");



					for(int i = 0; i < inversionistas.size(); i++){

						InversionistaValue actual = (InversionistaValue) inversionistas.get(i);

						if(actual.getId().equals(idInv)){

							System.out.println("paso");
							System.out.println("paso");

							System.out.println("paso");

							System.out.println("paso");



							nuevo.addInversionista(actual);



						}
					}



				}

				nuevo.obtenerValores();

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
		PreparedStatement prepStmt2 = null;

		ArrayList oferentes = new ArrayList();


		try {
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement("SELECT * FROM OFERENTE WHERE ID < 30");

			ResultSet rs = prepStmt.executeQuery();

			while(rs.next()){
				String id = rs.getString("ID");
				String nombre = rs.getString("NOMBRE");
				String tipo = rs.getString("TIPO");
				String direccion = rs.getString("DIRECCION");
				String telefono = rs.getString("TELEFONO");
				String ciudad = rs.getString("CIUDAD");
				String nomRepresentante = rs.getString("NOM_REPRESENTANTE");
				String idUsuario = rs.getString("ID_USUARIO");



				OferenteValue nuevo = new OferenteValue(id, nombre, tipo, direccion, telefono, ciudad, nomRepresentante, idUsuario);

				System.out.println(nuevo);



				prepStmt2 = conexion.prepareStatement("SELECT * FROM INSTRUMENTO_FINANCIERO WHERE ID < 100");

				ResultSet rs2 = prepStmt2.executeQuery();

				while(rs2.next()){
					String id2 = rs2.getString("ID");
					String idUser = rs2.getString("ID_USUARIO");
					String name = rs2.getString("NOMBRE");
					String value = rs2.getString("VALOR");


					int num = Integer.parseInt(id2);



					if(idUser.equals(idUsuario)){

						System.out.println(idUser + "----" + idUsuario);

						nuevo.addValores(name + " " + value);

					}



				}

				System.out.println(nombre);









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
			conexion.commit();
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
		PreparedStatement prepStmt2 = null;

		ArrayList inversionistas = new ArrayList();


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
				String nomRepresentante = rs.getString("NOMBRE_REPRESENTANTE");
				String idUsuario = rs.getString("ID_USUARIO");



				InversionistaValue nuevo = new InversionistaValue(id, nombre, tipo, direccion, telefono, ciudad, nomRepresentante, idUsuario);

				System.out.println(nuevo);



				prepStmt2 = conexion.prepareStatement("SELECT * FROM PORTAFOLIO WHERE ID_USUARIO LIKE " + idUsuario );

				ResultSet rs2 = prepStmt2.executeQuery();

				while(rs2.next()){
					String id2 = rs2.getString("ID");
					String idUser = rs2.getString("ID_USUARIO");

					int num = Integer.parseInt(id2);

					if(idUser.equals(idUsuario)){

						nuevo.setValores(darComposicionPortafolio(num));
						establecerConexion(cadenaConexion, usuario, clave);

					}

				}




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

	public ArrayList<ComposicionValue> darComposicion( )
	{
		return composicion;
	}


	/**
	 * Metodo que se retornar todos los comisionistas dentro de la bolsa de valores
	 * @return true si se pudo, false de lo contrario
	 * @throws Exception se lanza una excepcion si ocurre un error en
	 * la conexion o en la consulta. 
	 */


	public ArrayList mov(String inic, String fin, String criterio) throws Exception
	{



		PreparedStatement prepStmt = null;



		ArrayList movimientos = new ArrayList();



		try {
			establecerConexion(cadenaConexion, usuario, clave);


			String query ="SELECT * FROM ((((OPERACION_BURSATIL JOIN ((INSTRUMENTO_FINANCIERO JOIN TIPO_VALOR ON TIPO_VALOR = TIPO_VALOR.ID )"
					+ " JOIN RENTABILIDAD ON ID_RENTABILIDAD = RENTABILIDAD.ID )ON ID_INS_FIN = INSTRUMENTO_FINANCIERO.ID) JOIN COMISIONISTA ON COMISIONISTA.NUM_REGISTRO = ID_COMISIONISTA_2)"
					+ " JOIN INVERSIONISTA ON INVERSIONISTA.ID = ID_INVERSIONISTA) JOIN OFERENTE ON OFERENTE.ID = ID_OFERENTE) "
					+ "WHERE FECHA_FINAL BETWEEN TO_DATE('"+inic+"', 'dd/mm/yyyy') AND TO_DATE('"+fin+"', 'dd/mm/yyyy') AND " + criterio ;

			System.out.println(query);

			prepStmt = conexion.prepareStatement(query);

			ResultSet rs = prepStmt.executeQuery();


			ResultSetMetaData metaData = rs.getMetaData();

			int count = metaData.getColumnCount();
			for (int i = 1; i <= count; i++)
			{

				System.out.println( metaData.getColumnName(i)+" " +i);

			}


			while(rs.next()){




				String numRegistro = rs.getString("ID");
				String tipoOP = rs.getString("TIPO");
				String fecha = rs.getString("FECHA_FINAL");
				String nomInstrumento = rs.getString("NOMBRE");
				String valorIns = rs.getString(15);
				String tipoValor = rs.getString(22);
				String rentabilidad = rs.getString(25);

				String nomComisionista = rs.getString("NOM_REPRESENTANTE");
				String nomInversionista = rs.getString("NOMBRE_REPRESENTANTE");
				String nomOferente = rs.getString(53);

				System.out.println(numRegistro);


				OperacionValue nuevo = new OperacionValue(numRegistro, tipoOP, fecha, nomInstrumento, valorIns, tipoValor,rentabilidad, nomComisionista, nomInversionista, nomOferente);



				movimientos.add(nuevo);

				if(movimientos.size()>5000){
					break;
				}


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
		return movimientos;
	}

	/**
	 * Metodo que se retornar todos los comisionistas dentro de la bolsa de valores
	 * @return true si se pudo, false de lo contrario
	 * @throws Exception se lanza una excepcion si ocurre un error en
	 * la conexion o en la consulta. 
	 */


	public ArrayList portafolios(String tipo, String valor) throws Exception
	{



		PreparedStatement prepStmt = null;



		ArrayList portafolios = new ArrayList();



		try {
			establecerConexion(cadenaConexion, usuario, clave);

			int numero = Integer.parseInt(valor);
			String query ="SELECT * FROM (PORTAFOLIO JOIN COMPOSICION ON COMPOSICION.ID_PORTAFOLIO = PORTAFOLIO.ID) JOIN "
					+	"(INSTRUMENTO_FINANCIERO JOIN TIPO_VALOR ON INSTRUMENTO_FINANCIERO.TIPO_VALOR = TIPO_VALOR.ID ) "
					+ "ON INSTRUMENTO_FINANCIERO.ID = COMPOSICION.ID_VALOR WHERE TIPO_VALOR.NOMBRE  = '"+tipo+"' "
					+ "AND INSTRUMENTO_FINANCIERO.VALOR > "+numero+"" ;

			System.out.println(query);

			prepStmt = conexion.prepareStatement(query);

			ResultSet rs = prepStmt.executeQuery();


			//			ResultSetMetaData metaData = rs.getMetaData();
			//	
			//			int count = metaData.getColumnCount();
			//			for (int i = 1; i <= count; i++)
			//			{
			//			   
			//			    System.out.println( metaData.getColumnName(i)+" " +i);
			//			   
			//			}
			//			

			while(rs.next()){




				String id = rs.getString("ID");
				String nivelRiesgo = rs.getString("NIVEL_RIESGO");
				String IdUsuario = rs.getString("ID_USUARIO");
				String nombre = rs.getString("NOMBRE");
				String fechaExp = rs.getString("FECHA_EXPIRACION");
				System.out.println(id);



				PortafolioValue nuevo = new PortafolioValue(id, nivelRiesgo, IdUsuario,nombre, fechaExp);



				portafolios.add(nuevo);

				if(portafolios.size()>5000){
					break;
				}


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
		return portafolios;
	}

	/**
	 * Metodo que se retornar todos los comisionistas dentro de la bolsa de valores
	 * @return true si se pudo, false de lo contrario
	 * @throws Exception se lanza una excepcion si ocurre un error en
	 * la conexion o en la consulta. 
	 */


	public ArrayList valores2(String id) throws Exception
	{



		PreparedStatement prepStmt = null;



		ArrayList portafolios = new ArrayList();



		try {
			establecerConexion(cadenaConexion, usuario, clave);


			String query ="SELECT * FROM (PORTAFOLIO JOIN COMPOSICION ON ID_PORTAFOLIO = PORTAFOLIO.ID) JOIN"
					+	"(INSTRUMENTO_FINANCIERO JOIN TIPO_VALOR ON TIPO_VALOR = TIPO_VALOR.ID ) "
					+ "ON INSTRUMENTO_FINANCIERO.ID = ID_VALOR WHERE INSTRUMENTO_FINANCIERO.ID LIKE '"+id+"'";

			System.out.println(query);

			prepStmt = conexion.prepareStatement(query);

			ResultSet rs = prepStmt.executeQuery();


			ResultSetMetaData metaData = rs.getMetaData();

			int count = metaData.getColumnCount();
			for (int i = 1; i <= count; i++)
			{

				System.out.println( metaData.getColumnName(i)+" " +i);

			}


			while(rs.next()){




				String id1 = rs.getString("ID");
				String nivelRiesgo = rs.getString("NIVEL_RIESGO");
				String IdUsuario = rs.getString("ID_USUARIO");
				String nombre = rs.getString("NOMBRE");
				String fechaExp = rs.getString("FECHA_EXPIRACION");
				System.out.println(id1);


				PortafolioValue nuevo = new PortafolioValue(id1, nivelRiesgo, IdUsuario, nombre, fechaExp);



				portafolios.add(nuevo);

				if(portafolios.size()>5000){
					break;
				}


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
		return portafolios;
	}


}
