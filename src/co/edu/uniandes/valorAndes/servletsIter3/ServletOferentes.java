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
import co.edu.uniandes.valorAndes.vos.ComisionistaValue;
import co.edu.uniandes.valorAndes.vos.InversionistaValue;
import co.edu.uniandes.valorAndes.vos.OferenteValue;







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

		escribirContenido(respuesta);
	



	}







	/**
	 * Imprime el contenido con la información especificada
	 * @param respuesta Respuesta al cliente
	 */
	private void escribirContenido( PrintWriter respuesta )
	{
		
		
		ArrayList datos = new ArrayList();
		try {
			 datos = cupi.dao().darOferentes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		
		respuesta.write("          <p>&nbsp;</p>");
		respuesta.write("          <p>&nbsp;</p> <div class=\"col-lg-12\">");
		
		

		respuesta.println("    <div class=\"panel panel-primary\">");
		respuesta.println("    <!-- Default panel contents -->");
		respuesta.println("    <div class=\"panel-heading\">Listado de inversionistas:</div>");
		respuesta.println("");
		respuesta.println("    <!-- Table -->");
		respuesta.println("    <table class=\"table table-hover\">");
		respuesta.println("      <thead>");
		respuesta.println("        <tr>");
		respuesta.println("          <th>Numero de registro</th>");
		respuesta.println("          <th>Nombre de la entidad</th>");
		respuesta.println("          <th>Tipo</th>");
		respuesta.println("          <th>Direccion</th>");
		respuesta.println("          <th>Telefono</th>");
		respuesta.println("          <th>Ciudad</th>");
		respuesta.println("          <th>Nombre Representante</th>");
		respuesta.println("          <th>Valores en la bolsa</th>");
		respuesta.println("        </tr>");
		respuesta.println("      </thead>");
		respuesta.println("      <tbody>");
		
		
		
		
		
		
		for(int i= 0 ; i < datos.size(); i++){
			
		respuesta.println("        <tr>");
			
			
		OferenteValue actual =	 (OferenteValue) datos.get(i);
		respuesta.println("          <td>" + actual.getId() + "</td>");
		respuesta.println("          <td>" + actual.getNombre() + "</td>");
		respuesta.println("          <td>" + actual.getTipo() + "</td>");
		respuesta.println("          <td>" + actual.getDireccion() + "</td>");
		respuesta.println("          <td>" + actual.getTelefono() + "</td>");
		respuesta.println("          <td>" + actual.getCiudad() + "</td>");
		respuesta.println("          <td>" + actual.getNomRepresentante() + "</td>");
		respuesta.println("          <td>" + actual.stringValores()+ "</td>");
		
		respuesta.println("        </tr>");
		
		}
		
		
		
		
		respuesta.println("      </tbody>");
		respuesta.println("    </table>");
		respuesta.println("  </div>");




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
