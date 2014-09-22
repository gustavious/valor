/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n18_colegioWeb
 * Autor: Pablo Barvo - Apr 26, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package co.edu.uniandes.valorAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.valorAndes.fachada.ValorAndes;







@SuppressWarnings("serial")
public class ServletCancelar extends ServletTemplate
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * mundo para realizar las operaciones
	 */
	private ValorAndes cupi;

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Inicialización del Servlet
	 */
	public void init( ) throws ServletException
	{

		cupi = ValorAndes.darInstancia();		

	}







	/**
	 * Escribe el contenido de la página
	 * @param request Pedido del cliente
	 * @param response Respuesta
	 * @throws IOException Excepción de error al escribir la respuesta
	 */
	public void escribirContenido( HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
	
		// Saca el Printer
		PrintWriter respuesta = response.getWriter( );


		String id = request.getParameter( "id" );
		
		String idComisionista = request.getParameter( "idComisionista" );
	
		
		int id1 = Integer.parseInt(id);
		
		int idComisionista1 = Integer.parseInt(idComisionista);
	

	

		if(id != null && idComisionista != null ){

			
			
			Calendar fecha = new GregorianCalendar();
	        
	       
	
			try {
				cupi.dao().cancelarOperacion(id1, idComisionista1);
				escribirContenido(respuesta);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.imprimirMensajeError(respuesta, "Alguno de los id no existe dentro de la base de datos ");
			}
			

		}

		else{


			
			this.imprimirMensajeError(respuesta, "Alguno de los id no existe dentro de la base de datos ");

		}   







	}







	/**
	 * Imprime el contenido con la información especificada
	 * @param respuesta Respuesta al cliente
	 */
	private void escribirContenido( PrintWriter respuesta )
	{



		
		respuesta.write("          <p>&nbsp;</p>");
		respuesta.write("          <p>&nbsp;</p> <div class=\"col-lg-12\">");
		
		

		respuesta.write("         <h2>se cancelo la operación de manera exitosa</h2>");



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
