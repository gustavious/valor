package co.edu.uniandes.valorAndes.test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.edu.uniandes.valorAndes.dao.ConsultaDAO;
import co.edu.uniandes.valorAndes.fachada.ValorAndes;
import junit.framework.TestCase;

public class RestriccionesChequeoTest extends TestCase
{

	public ConsultaDAO dao;

	public RestriccionesChequeoTest( )
	{
		dao = new ConsultaDAO();
		dao.inicializar(ValorAndes.RUTA2);
	}

	public void setupEscenarioUsuario()
	{
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO USUARIO VALUES ( 105, 'Antonio Sola', 'Espa馻', 'Calle 10', 'Barcelona', 4783100, 'antonio123', 'antonio1', 'antonio@gmail.com', 'Catalu馻', 08002, 'Inversionista')";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Error al establecer la conexi髇");
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
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi贸n.");
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

			String query = "INSERT INTO USUARIO VALUES ( 106, 'Ismael Cala', 'EE UU', 'Street Dolphin', 'Miami', 6785432, 'ismael123', 'ismael1', 'ismael@gmail.com', 'Florida', 3333, 'Intermediario')";
			String query2 = "DELETE FROM USUARIO WHERE ID = 105";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query2);
			prepStmt.executeQuery();
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			assertTrue("No se deber韆 poder agregar un usuario un tipo que sea diferente de 'Inversionista','Oferente','Comisionista'", e.getMessage().contains("ORA-02290"));
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
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi贸n.");
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

	public void setupEscenarioInversionista()
	{
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO USUARIO VALUES ( 107, 'Richard Quest', 'EE UU', 'Fifth Avenue', 'New York', 5672134, 'richard123', 'richard1', 'richard@gmail.com', 'New York', 8700, 'Inversionista')";
			String query2 = "INSERT INTO INVERSIONISTA VALUES ('Richard Quest', 8, 'Natural', 'Fifth Avenue', 5672134, 'New York', 'Richard Quest', 000010, 107)";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();
			prepStmt = dao.conexion.prepareStatement(query2);
			prepStmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Error al establecer la conexi髇");
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
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi贸n.");
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

			String query2 = "INSERT INTO INVERSIONISTA VALUES ('Richard Quest', 9, 'Nat', 'Fifth Avenue', 5672134, 'New York', 'Richard Quest', 000010, 107)";
			String query3 = "DELETE FROM INVERSIONISTA WHERE ID = 8";
			String query4 = "DELETE FROM USUARIO WHERE ID = 107";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query3);
			prepStmt.executeQuery();
			prepStmt = dao.conexion.prepareStatement(query4);
			prepStmt.executeQuery();
			prepStmt = dao.conexion.prepareStatement(query2);
			prepStmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			assertTrue("No se deber韆 poder agregar un inversionista cuyo tipo sea diferente de 'Natural' 'Juridica'", e.getMessage().contains("ORA-02290"));
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
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi贸n.");
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
	
	public void setupEscenarioOferente()
	{
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO USUARIO VALUES ( 110, 'Guillermo Diaz', 'Colombia', 'calle 170', 'Bogota', 6959428, 'guillermo123', 'guillermo1', 'guillermo@pdr.com', 'Bogota', 110111, 'Oferente')";
			String query2 = "INSERT INTO OFERENTE VALUES ('Paz del rio', 8, 'Juridica', 'Bogota', 5672134, 'Calle 100', 'Mariana Perez', 000111, 110)";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();
			prepStmt = dao.conexion.prepareStatement(query2);
			prepStmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Error al establecer la conexi髇");
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
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi贸n.");
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

			String query2 = "INSERT INTO OFERENTE VALUES ('Paz del rio', 9, 'Industrial', 'Bogota', 5672134, 'Calle 100', 'Mariana Perez', 000111, 110)";
			String query3 = "DELETE FROM OFERENTE WHERE ID = 8";
			String query4 = "DELETE FROM USUARIO WHERE ID = 110";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query3);
			prepStmt.executeQuery();
			prepStmt = dao.conexion.prepareStatement(query4);
			prepStmt.executeQuery();
			prepStmt = dao.conexion.prepareStatement(query2);
			prepStmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			assertTrue("No se deber韆 poder agregar un oferente cuyo tipo sea diferente de 'Natural' 'Juridica'", e.getMessage().contains("ORA-02290"));
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
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi贸n.");
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
	
	public void setupEscenarioInstrumentoFinanciero()
	{
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO INSTRUMENTO_FINANCIERO VALUES ( 10, 'Bono Aval', 1000000, TO_DATE('10/09/2014', 'dd/mm/yyyy'), 8, 2, 'No', 1 )";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Error al establecer la conexi髇");
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
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi贸n.");
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

			String query2 = "INSERT INTO INSTRUMENTO_FINANCIERO VALUES ( 11, 'Bono Aval', 1000000, TO_DATE('10/09/2014', 'dd/mm/yyyy'), 8, 2, 'OK', 1 )";
			String query = "DELETE FROM INSTRUMENTO_FINANCIERO WHERE ID = 10";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();
			prepStmt = dao.conexion.prepareStatement(query2);
			prepStmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());;
			assertTrue("No se deber韆 poder agregar un instrumento financiero que su estado de negociado sea diferente de 'Si' o 'No'", e.getMessage().contains("ORA-02290"));
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
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi贸n.");
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
	
	public void setupEscenarioOperacionBursatil()
	{
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO OPERACION_BURSATIL VALUES ( 7, 'Venta', 40000000, 7, 6, 13, 3, 4, TO_DATE('01/09/2014','dd/mm/yyyy'), TO_DATE('24/09/2014','dd/mm/yyyy'))";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Error al establecer la conexi髇");
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
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi贸n.");
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

			String query2 = "INSERT INTO OPERACION_BURSATIL VALUES ( 10, 'Vendiendo', 40000000, 7, 6, 13, 3, 4, TO_DATE('01/09/2014','dd/mm/yyyy'), TO_DATE('24/09/2014','dd/mm/yyyy'))";
			String query = "DELETE FROM OPERACION_BURSATIL WHERE ID = 7";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();
			prepStmt = dao.conexion.prepareStatement(query2);
			prepStmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			assertTrue("No se deber韆 poder agregar una operaci髇 bursatil cuyo tipo sea diferente de 'Venta' o 'Compra'", e.getMessage().contains("ORA-02290"));
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
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi贸n.");
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
	
	public void setupEscenarioRentabilidad()
	{
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO RENTABILIDAD VALUES ( 10, 'Rentabilidadp', 'rentabilidad10', 0.10, 'renta fija', 'termino fijo')";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Error al establecer la conexi髇");
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
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi贸n.");
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

	public void testRentabilidad1( )
	{
		setupEscenarioRentabilidad();
		PreparedStatement prepStmt = null;
		try 
		{

			String query2 = "INSERT INTO RENTABILIDAD VALUES ( 11, 'Rentabilidadp', 'rentabilidad10', 0.10, 'renta ', 'termino fijo')";
			String query = "DELETE FROM RENTABILIDAD WHERE ID = 10";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();
			prepStmt = dao.conexion.prepareStatement(query2);
			prepStmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			assertTrue("No se deber韆 poder agregar un tipo de rentabilidad cuyo comportamiento sea diferente de 'renta fija' o 'renta variable'", e.getMessage().contains("ORA-02290"));
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
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi贸n.");
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

	public void testRentabilidad2( )
	{
		setupEscenarioRentabilidad();
		PreparedStatement prepStmt = null;
		try 
		{

			String query = "INSERT INTO RENTABILIDAD VALUES ( 11, 'Rentabilidadp', 'rentabilidad10', 0.10, 'renta fija', 'termino')";
			dao.establecerConexion(dao.darCadenaConexion(), dao.darUsuario(), dao.darClave());
			prepStmt = dao.conexion.prepareStatement(query);
			prepStmt.executeQuery();

		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			assertTrue("No se deber韆 poder agregar un tipo de rentabilidad cuya duraci髇 sea diferente de 'termino fijo' o 'termino indefinido'", e.getMessage().contains("ORA-02290"));
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
						throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi贸n.");
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
