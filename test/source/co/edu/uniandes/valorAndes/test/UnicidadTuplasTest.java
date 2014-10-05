package co.edu.uniandes.valorAndes.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import co.edu.uniandes.valorAndes.dao.ConsultaDAO;
import co.edu.uniandes.valorAndes.fachada.ValorAndes;
import junit.framework.TestCase;

public class UnicidadTuplasTest extends TestCase
{

	public ConsultaDAO dao;

	public UnicidadTuplasTest( )
	{
		dao = new ConsultaDAO();
		dao.inicializar(ValorAndes.RUTA2);
	}

	public void setupEscenarioUsuario()
	{
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO USUARIO VALUES ( 98, 'Juan Garcia', 'Colombia', 'Calle 1', 'Medellin', 8396781, 'juan123', 'juan1', 'juan@gmail.com', 'Antioquia', 110113, 'Inversionista')";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Error");
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
				System.out.println("Error al desconectarse de la base de datos");
				e.printStackTrace();

			}

		}
	}

	public void setupEscenarioComisionista( )
	{
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO USUARIO VALUES ( 99, 'Alenjandra Ramirez', 'Colombia', 'Calle 2', 'Bogota', 8396781, 'ale123', 'ale1', 'ale@davivalores.com', 'Bogota', 11011, 'Comisionista')";
			String query2 = "INSERT INTO COMISIONISTA VALUES (6, 'Davivalores', 'Bogota', 'Calle 26', 2567890, 'Pedro', 89090, 99)";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();

			prepStmt = dao.conexion.prepareStatement(query2);
			prepStmt.executeQuery();


		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Error");
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
				System.out.println("Error al desconectarse de la base de datos");
				e.printStackTrace();

			}

		}
	}

	public void setupEscenarioInversionista( )
	{
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO USUARIO VALUES ( 100, 'Pablo Andrade', 'Argentina', 'Calle 28', 'Buenos Aires', 5670023, 'pablo123', 'pablo1', 'pablo@gmail.com', 'Buenos Aires', 1428, 'Inversionista')";
			String query2 = "INSERT INTO INVERSIONISTA VALUES ( 'Pablo Andrade', 6, 'Natural', 'Calle 28', 5670023, 'Buenos Aires', 'Pablo Andrade', 23457 , 100)";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();

			prepStmt = dao.conexion.prepareStatement(query2);
			prepStmt.executeQuery();


		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Error");
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
				System.out.println("Error al desconectarse de la base de datos");
				e.printStackTrace();

			}

		}
	}

	public void setupEscenarioOferente( )
	{
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO USUARIO VALUES ( 101, 'Carlos Lopez', 'Colombia', 'Carrera 7', 'Bogota', 7654312, 'carlos123', 'carlos1', 'carlos@ecopetrol.com', 'Bogota', 110111, 'Oferente')";
			String query2 = "INSERT INTO OFERENTE VALUES ( 'Ecopetrol', 6, 'Juridica', 'Bogota', 7654312, 'Carrera 7', 'Emilio Parra', 34567 , 101)";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();

			prepStmt = dao.conexion.prepareStatement(query2);
			prepStmt.executeQuery();


		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Error");
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
				System.out.println("Error al desconectarse de la base de datos");
				e.printStackTrace();

			}

		}
	}
	
	public void setupEscenarioTipoValor( )
	{
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO TIPO_VALOR VALUES ( 5, 'Accion', 'Acción' )";

			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();


		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Error");
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
				System.out.println("Error al desconectarse de la base de datos");
				e.printStackTrace();

			}

		}
	}
	
	public void setupEscenarioInstrumentoFinanciero( )
	{
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO INSTRUMENTO_FINANCIERO VALUES ( 6, 'Titulo Alpina', 21000, (TO_DATE( '26/02/2013', 'dd/mm/yyyy')), 5, 4, 'No', 4)";

			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();


		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Error");
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
				System.out.println("Error al desconectarse de la base de datos");
				e.printStackTrace();

			}

		}
	}
	
	public void setupEscenarioRentabilidad( )
	{
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO RENTABILIDAD VALUES ( 5, 'Rentabilidadv', 'Rentabilidad5', 0.4 , 'renta variable', 'termino fijo' )";

			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();


		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Error");
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
				System.out.println("Error al desconectarse de la base de datos");
				e.printStackTrace();

			}

		}
	}
	
	public void setupEscenarioOperacionBursatil( )
	{
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO OPERACION_BURSATIL VALUES ( 6, 'Venta', 31000, 9, 4, 11, 3, 3, (TO_DATE( '01/09/2014', 'dd/mm/yyyy')), (TO_DATE( '17/09/2014', 'dd/mm/yyyy')))";

			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();


		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Error");
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

			String query = "INSERT INTO USUARIO VALUES ( 98, 'Juan Garcia', 'Colombia', 'Calle 1', 'Medellin', 8396781, 'juan123', 'juan1', 'juan@gmail.com', 'Antioquia', 110113, 'Inversionista')";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();

		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			assertTrue("No se debería poder agregar un usuario con una pk existente", e.getMessage().contains("ORA-00001"));
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
				System.out.println("Error al desconectarse de la base de datos");
				e.printStackTrace();

			}

		}
	}

	public void testComisionista( )
	{
		setupEscenarioComisionista();
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO COMISIONISTA VALUES (6, 'Davivalores', 'Bogota', 'Calle 26', 2567890, 'Pedro', 89090, 99)";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			ResultSet rs = prepStmt.executeQuery();

		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			assertTrue("No se debería poder agregar un comisionista con una pk existente", e.getMessage().contains("ORA-00001"));
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
				System.out.println("Error al desconectarse de la base de datos");
				e.printStackTrace();

			}

		}
	}

	public void testInversionista( )
	{
		setupEscenarioInversionista();
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO INVERSIONISTA VALUES ( 'Pablo Andrade', 6, 'Natural', 'Calle 28', 5670023, 'Buenos Aires', 'Pablo Andrade', 23457 , 100)";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();

		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			assertTrue("No se debería poder agregar un inversionista con una pk existente", e.getMessage().contains("ORA-00001"));
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
				System.out.println("Error al desconectarse de la base de datos");
				e.printStackTrace();

			}

		}
	}
	
	public void testOferente( )
	{
		setupEscenarioOferente();
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO OFERENTE VALUES ( 'Ecopetrol', 6, 'Juridica', 'Carrera 7', 7654312, 'Bogotá', 'Emilio Parra', 34567 , 101)";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();

		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			assertTrue("No se debería poder agregar un oferente con una pk existente", e.getMessage().contains("ORA-00001"));
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
				System.out.println("Error al desconectarse de la base de datos");
				e.printStackTrace();

			}

		}
	}
	
	public void testTipoValor( )
	{
		setupEscenarioTipoValor();
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO TIPO_VALOR VALUES ( 5, 'Accion', 'Acción' )";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();

		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			assertTrue("No se debería poder agregar un tipo de valor con una pk existente", e.getMessage().contains("ORA-00001"));
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
				System.out.println("Error al desconectarse de la base de datos");
				e.printStackTrace();

			}

		}
	}

	public void testInstrumentoFinanciero( )
	{
		setupEscenarioInstrumentoFinanciero();
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO INSTRUMENTO_FINANCIERO VALUES ( 6, 'Titulo Alpina', 21000, (TO_DATE( '26/02/2013', 'dd/mm/yyyy')), 5, 4, 'No', 4)";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();

		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			assertTrue("No se debería poder agregar un instrumento financiero con una pk existente", e.getMessage().contains("ORA-00001"));
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
				System.out.println("Error al desconectarse de la base de datos");
				e.printStackTrace();

			}

		}
	}
	
	public void testRentabilidad( )
	{
		setupEscenarioRentabilidad();
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO RENTABILIDAD VALUES ( 5, 'Rentabilidadv', 'Rentabilidad5', 0.4, 'renta variable', 'termino fijo' )";

			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();

		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			assertTrue("No se debería poder agregar un tipo de rentabilidad con una pk existente", e.getMessage().contains("ORA-00001"));
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
				System.out.println("Error al desconectarse de la base de datos");
				e.printStackTrace();

			}

		}
	}

	public void testOperacionBursatil( )
	{
		setupEscenarioOperacionBursatil();
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO OPERACION_BURSATIL VALUES ( 6, 'Venta', 31000, 9, 4, 11, 3, 3, (TO_DATE( '01/09/2014', 'dd/mm/yyyy')), (TO_DATE( '17/09/2014', 'dd/mm/yyyy')))";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();

		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			assertTrue("No se debería poder agregar una operacion bursatil con una pk existente", e.getMessage().contains("ORA-00001"));
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
				System.out.println("Error al desconectarse de la base de datos");
				e.printStackTrace();

			}

		}
	}
}

