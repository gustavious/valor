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
	private ValorAndes valor;

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Inicialización del Servlet
	 */
	public void init( ) throws ServletException
	{

		valor = ValorAndes.darInstancia();		

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
		

		Boolean seActualiza = false;

		
		


		if(seActualiza == false ){

			error(respuesta);

		}

		else{



			
			imprimirContendio( respuesta );	

		}   







	}

	private void error(PrintWriter respuesta) {

		respuesta.write("  <div class=\"content\">");
		respuesta.write("    <div class=\"content_resize\">");
		respuesta.write("      <div class=\"mainbar\">");
		respuesta.write("        <div class=\"article\">");


		respuesta.write("          <h2No se pudo</h2>");


		respuesta.write("          <p>&nbsp;</p>");
		respuesta.write("        </div>");
		respuesta.write("        <div class=\"header\">");
		
		respuesta.write("        </div>");
		respuesta.write("        <div class=\"clr\"></div>");
		respuesta.write("        <div class=\"clr\"></div>");
		respuesta.write("      </div>");
		respuesta.write("      <div class=\"clr\"></div>");
		respuesta.write("    </div>");
		respuesta.write("  </div>");


	}





	/**
	 * Imprime el contenido con la información especificada
	 * @param respuesta Respuesta al cliente
	 */
	private void imprimirContendio( PrintWriter respuesta )
	{



		


		respuesta.write("          <p>&nbsp;</p>");

		respuesta.write("         <h2>se actualizo correctamente</h2>");




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
