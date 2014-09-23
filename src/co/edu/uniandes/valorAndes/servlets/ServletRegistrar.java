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
public class ServletRegistrar extends ServletTemplate
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
		String idComisionista1 = request.getParameter( "idComisionista1" );
		String idComisionista2 = request.getParameter( "idComisionista2" );
		String valor = request.getParameter( "valor" );
		

		
		int id1 = Integer.parseInt(id);
		Double valor1 = Double.parseDouble(valor);
	
		int idComisionistaUno = Integer.parseInt(idComisionista1);
		int idComisionistaDos = Integer.parseInt(idComisionista2);
		
		

	

		if(id != null && valor != null && idComisionista2 != null && idComisionista1 != null){

			
			
			Calendar fecha = new GregorianCalendar();
	        
	        int año = fecha.get(Calendar.YEAR);
	        int mes = fecha.get(Calendar.MONTH) + 1;
	        int dia = fecha.get(Calendar.DAY_OF_MONTH);
	        int hora = fecha.get(Calendar.HOUR_OF_DAY);
	        int minuto = fecha.get(Calendar.MINUTE);
	        
	       
	        
	        String fechaFin = año +  String.format("%02d",mes) +   dia  + String.format( "%02d%02d",hora, minuto);
	
			try {
				cupi.dao().registrarOperacion(id1, idComisionistaDos, idComisionistaUno, valor1, fechaFin);
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
		
		

		respuesta.write("         <h2>se registró la operación bursatil de manera exitosa</h2>");



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
