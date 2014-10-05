package co.edu.uniandes.valorAndes.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import co.edu.uniandes.valorAndes.dao.ConsultaDAO;
import junit.framework.TestCase;

public class UnicidadTuplasTest extends TestCase
{

	public ConsultaDAO dao;
	
	public void setupEscenarioUsuario()
	{
		dao = new ConsultaDAO();
		PreparedStatement prepStmt = null;
		String path = JOptionPane.showInputDialog("Ingrese la ruta donde se encuentra el archivo de conexiones");
		dao.inicializar(path);
		try 
		{
			
			String query = "INSERT INTO USUARIO VALUES ( 15, 'Juan Garcia', 'Colombia', 'Calle 1', 'Medellin', 8396781, 'juan123', 'juan1', 'juan@gmail.com', 'Antioquia', 110113, 'Inversionista')";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			ResultSet rs = prepStmt.executeQuery();
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al establecer la conexión");
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				} 
				catch (SQLException exception) 
				{
					
					try
					{
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiÃ³n.");
					}
					catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			try 
			{
				dao.closeConnection(dao.conexion);
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				System.out.println("Error al desconectarse de la base de datos");
				e.printStackTrace();
				
			}
			
		}
	}
	
	public void testUsuario( )
	{
		setupEscenarioUsuario();
		PreparedStatement prepStmt = null;
		try 
		{
			
			String query = "INSERT INTO USUARIO VALUES ( 15, 'Juan Garcia', 'Colombia', 'Calle 1', 'Medellin', 8396781, 'juan123', 'juan1', 'juan@gmail.com', 'Antioquia', 110113, 'Inversionista')";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			ResultSet rs = prepStmt.executeQuery();
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue("No se debería poder agregar un usuario con una pk existente", e.getMessage().contains("ORA-00001: restricción única (ISIS2304011420.USUARIO_PK) violada"));
			System.out.println("Error al establecer la conexión");
		}
		finally 
		{
			if (prepStmt != null) 
			{
				try 
				{
					prepStmt.close();
				} 
				catch (SQLException exception) 
				{
					
					try
					{
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexiÃ³n.");
					}
					catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			try 
			{
				dao.closeConnection(dao.conexion);
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				System.out.println("Error al desconectarse de la base de datos");
				e.printStackTrace();
				
			}
			
		}
	}
	
}
