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
		String tipo = request.getParameter( "tipo" );
		String valor = request.getParameter( "valor" );
		String idUsuario = request.getParameter( "idUsuario" );
		String idComisionista = request.getParameter( "idComisionista" );
		String idInstrumento = request.getParameter( "idInstrumento" );
		
		int id1 = Integer.parseInt(id);
		Double valor1 = Double.parseDouble(valor);
		int idUsuario1 = Integer.parseInt(idUsuario);
		int idComisionista1 = Integer.parseInt(idComisionista);
		int idInstrumento1 = Integer.parseInt(idInstrumento);
		

	

		if(id != null && tipo != null && valor != null && idUsuario != null && idComisionista != null && idInstrumento != null){

			
			
			Calendar fecha = new GregorianCalendar();
	        
	        int año = fecha.get(Calendar.YEAR);
	        int mes = fecha.get(Calendar.MONTH) + 1;
	        int dia = fecha.get(Calendar.DAY_OF_MONTH);
	        int hora = fecha.get(Calendar.HOUR_OF_DAY);
	        int minuto = fecha.get(Calendar.MINUTE);
	        
	       
	        
	        String fechaInic = año +  String.format("%02d",mes) +   dia  + String.format( "%02d%02d",hora, minuto);
	
			try {
				cupi.dao().ordenarOperacion(id1, tipo, valor1, idUsuario1, idComisionista1, idInstrumento1, fechaInic);
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
		respuesta.write("          <p>&nbsp;</p>");

		respuesta.write("         <h2>se actualizo correctamente</h2>");



		respuesta.write("          <p>&nbsp;</p>");
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
