package co.edu.uniandes.valorAndes.serveletsFinal;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.valorAndes.fachada.ValorAndes;
import co.edu.uniandes.valorAndes.servletsIter4.ServletTemplateAjax;



/**
 * Servlet de peticiones de nuestra aplicación de prueba.
 * @author Jorge A López Silva
 */
public class ServletPeticion extends ServletTemplateAjax {


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
			//			Fachada.getInstance();
			pw.write("<html>");
			pw.write("<head>");
			pw.write("<title>Experimento JMS - WebApp1</title>");
			pw.write("</head>");
			pw.write("<body>");
			pw.write("<h1> Experimento JMS - WebApp1 </h1> </br>" +
					"<p> En este experimento se envia un mensaje de la aplicacion " +
					"WebApp1 a la aplicacion WebApp2. " +
					".</p> </br>" +

					"<form action=\"resp.html\" method=\"POST\">" +
					" Mensaje a Enviar: <input type=\"text\" name=\"insertar\"/></br>" +
					"<input type=\"submit\" value=\"Ingresar\">" +
					"</form>");

			pw.write("</body>");
			pw.write("</html>");

		} catch (Exception e) {

			System.out.println("HEY");
			System.out
			.println("Ocurrió un error grave al escribir el ServletPeticion");
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
