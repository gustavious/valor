/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n18_colegioWeb
 * Autor: Pablo Barvo - Apr 26, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package co.edu.uniandes.valorAndes.servletsIter3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.valorAndes.fachada.ValorAndes;
import co.edu.uniandes.valorAndes.servlets.ServletTemplate;







@SuppressWarnings("serial")
public class ServletOferentes extends ServletTemplate
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * mundo para realizar las operaciones
	 */
	private ValorAndes cupi;

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Inicializaci�n del Servlet
	 */
	public void init( ) throws ServletException
	{

		cupi = ValorAndes.darInstancia();		

	}





	/**
	 * Escribe el contenido de la p�gina
	 * @param request Pedido del cliente
	 * @param response Respuesta
	 * @throws IOException Excepci�n de error al escribir la respuesta
	 */
	public void escribirContenido( HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
	
		// Saca el Printer
		PrintWriter respuesta = response.getWriter( );

		escribirContenido(respuesta);
	



	}







	/**
	 * Imprime el contenido con la informaci�n especificada
	 * @param respuesta Respuesta al cliente
	 */
	private void escribirContenido( PrintWriter respuesta )
	{
		
		
		try {
			ArrayList datos = cupi.dao().darOferentes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		
		respuesta.write("          <p>&nbsp;</p>");
		respuesta.write("          <p>&nbsp;</p> <div class=\"col-lg-12\">");
		
		

		respuesta.write("         <h2>Listado de oferentes:</h2>");
		
//		aqui hare la tabla :(



		respuesta.write("          </div><p>&nbsp;</p>");
		respuesta.write("          <p>&nbsp;</p>");
		
	}







	@Override
	public String darTituloPagina(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public String darImagenTitulo(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
