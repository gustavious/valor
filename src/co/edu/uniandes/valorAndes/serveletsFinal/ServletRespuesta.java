package co.edu.uniandes.valorAndes.serveletsFinal;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.valorAndes.fachada.ValorAndes;
import co.edu.uniandes.valorAndes.servletsIter4.ServletTemplateAjax;


public class ServletRespuesta extends ServletTemplateAjax {


	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * mundo para realizar las operaciones
	 */
	private ValorAndes cupi;


	/**
	 * Método que imprime el contenido actual de la pagina de peticiones.
	 */
	@Override
	public void escribirContenido(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			PrintWriter pw = resp.getWriter();

			//			String insercion = cupi.darInstancia().requerimiento1();
			pw.write("<html>");
			pw.write("<head>");
			pw.write("<title>Experimento JMS - WebApp2</title>");
			pw.write("</head>");
			pw.write("<body>");
			pw.write("<h1> Experimento JMS - WebApp2</h1>");
//			if(insercion!=null)
//				pw.write("<p> Se ha recibido el mensaje: " + insercion + "</p>");
//			else
//				pw.write("<p> No se  recibio el mensaje </p>");
			pw.write("Has click aquí para <a href=\"index.html\"> volver </a>.");
			pw.write("</body>");
			pw.write("</html>");

		} catch (Exception e) {
			e.printStackTrace();
			System.out
			.println("Ocurrió un error grave al escribir el ServletRespuesta");
		}

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
