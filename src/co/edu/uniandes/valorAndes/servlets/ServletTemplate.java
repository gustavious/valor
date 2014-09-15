
package co.edu.uniandes.valorAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.valorAndes.fachada.ValorAndes;


/**
 * Clase abstacta que implementa un Servlet.
 */
public abstract class ServletTemplate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * constructor de la clase. Llama al constructor de 
     * su padre.
     */
    public ServletTemplate() {
        super();

    }

	/**
	 * Recibe la solicitud y la herramienta de respuesta a las solicitudes
	 * hechas por los m�todos get. Invoca el m�todo procesarPedido.
	 * @param request pedido del cliente
	 * @param response respuesta del servlet
	 * @throws IOException Excepci�n de error al escribir la respuesta
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		procesarPedido(request, response);
	}

	/**
	 * Recibe la solicitud y la herramienta de respuesta a las solicitudes
	 * hechas por los m�todos post. Invoca el m�todo procesarPedido.
	 * @param request pedido del cliente
	 * @param response respuesta del servlet
	 * @throws IOException Excepci�n de error al escribir la respuesta
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		procesarPedido(request, response);
	}
	
	/**
     * Procesa el pedido de igual manera para todos
     * @param request Pedido del cliente
     * @param response Respuesta del servlet
     * @throws IOException Excepci�n de error al escribir la respuesta
     */
    private void procesarPedido( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
    	//TODO Si hay otras fachadas, ellas tambien deben inicializar la ruta.
    	ValorAndes.darInstancia().inicializarRuta(this.getServletContext().getRealPath(((HttpServletRequest) this.getServletContext()).getContextPath()));
        //
        // Comienza con el Header del template
        imprimirHeader( request, response );
        //
        // Escribe el contenido de la p�gina
        escribirContenido( request, response );
        //
        // Termina con el footer del template
        imprimirFooter( response );

    }

    /**
     * Escribe la cabecera de la p�gina web
     * @param request pedido del cliente
     * @param response respuesta del servlet
     * @throws IOException Excepci�n de error al recibir la respuesta
     */
	private void imprimirHeader(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		 //
        // Saca el printer de la repuesta
        PrintWriter respuesta = response.getWriter( );
        //
        // Imprime el header
        respuesta.println( "<html>" );
        respuesta.println( "<head>" );
        respuesta.println( "<meta http-equiv=\"Content-Language\" content=\"es-co\">" );
        respuesta.println( "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\">" );
        respuesta.println( "<title>Video Andes Online - " + darTituloPagina( request ) + "</title>" );
        respuesta.println( "<link type=\"text/css\" rel=\"stylesheet\" href=\"style/video.css\">" );
        respuesta.println( "</head>" );

        respuesta.println( "<body>" );
        respuesta.println( "<div align=center>" );
        respuesta.println( "<center>" );

        respuesta.println( "<table border=\"0\" width=\"720\" id=\"table1\">" );
        respuesta.println( "   <tr>" );
        respuesta.println( "       <td>" );
        respuesta.println( "       <p align=\"center\">" );
        respuesta.println( "       <img border=\"0\" src=\"imagenes/titulo.jpg\" width=\"640\" height=\"100\"></td>" );
        respuesta.println( "   </tr>" );
        respuesta.println( "   <tr>" );
        respuesta.println( "       <td>&nbsp;</td>" );
        respuesta.println( "   </tr>" );
        respuesta.println( "   <tr>" );
        respuesta.println( "       <td>" );
        respuesta.println( "       <table border=\"1\" width=\"100%\" style=\"border-collapse: collapse\" bordercolor=\"#999999\" id=\"table2\">" );
        respuesta.println( "           <tr>" );
        respuesta.println( "               <td class=\"h2\" height=\"30\">" );
        respuesta.println( "               <h1 align=\"center\">Sistema Web de Manejo de Videos</td>" );
        respuesta.println( "           </tr>" );
        respuesta.println( "           <tr>" );
        respuesta.println( "               <td bgcolor=\"#000000\" height=\"3px\"></td>" );
        respuesta.println( "           </tr>" );
        respuesta.println( "           <tr>" );
        respuesta.println( "               <td>" );
        respuesta.println( "               <table border=\"0\" width=\"710\" id=\"table3\">" );
        respuesta.println( "                   <tr>" );
        respuesta.println( "                       <td width=\"696\" colspan=\"4\" bgcolor=\"#E2E2E2\">" );
        respuesta.println( "                       <table border=\"0\" width=\"614\" id=\"table4\">" );
        respuesta.println( "                           <tr>" );
        respuesta.println( "                               <td width=\"19\">&nbsp;</td>" );
        respuesta.println( "                               <td width=\"21\">" );
        respuesta.println( "                               <img border=\"0\" src=\"imagenes/" + darImagenTitulo( request ) + "\" width=\"48\" height=\"48\"></td>" );
        respuesta.println( "                               <td width=\"560\"><h2>" + darTituloPagina( request ) + "</td>" );
        respuesta.println( "                           </tr>" );
        respuesta.println( "                       </table>" );
        respuesta.println( "                       </td>" );
        respuesta.println( "                   </tr>" );
        respuesta.println( "                   <tr>" );
        respuesta.println( "                       <td width=\"42\">&nbsp;</td>" );
        respuesta.println( "                       <td width=\"572\" colspan=\"2\">&nbsp;</td>" );
        respuesta.println( "                       <td width=\"82\">&nbsp;</td>" );
        respuesta.println( "                   </tr>" );
        respuesta.println( "                   <tr>" );
        respuesta.println( "                       <td width=\"42\">&nbsp;</td>" );
        respuesta.println( "                       <td width=\"25\">&nbsp;</td>" );
        respuesta.println( "                       <td width=\"543\">" );
		
	}

	private void imprimirFooter(HttpServletResponse response) throws IOException
	{
		
        // Saca el writer de la respuesta
        PrintWriter respuesta = response.getWriter( );
        //
        // Imprime el footer
        respuesta.println( "                   </td>" );
        respuesta.println( "                       <td width=\"82\">&nbsp;</td>" );
        respuesta.println( "                   </tr>" );
        respuesta.println( "                   <tr>" );
        respuesta.println( "                       <td width=\"42\">&nbsp;</td>" );
        respuesta.println( "                       <td width=\"25\">&nbsp;</td>" );
        respuesta.println( "                       <td width=\"543\">&nbsp;</td>" );
        respuesta.println( "                       <td width=\"82\">&nbsp;</td>" );
        respuesta.println( "                   </tr>" );
        respuesta.println( "               </table>" );
        respuesta.println( "                      <a href=\"index.htm\">Volver a la p�gina principal</a>" );
        respuesta.println( "               </td>" );
        respuesta.println( "           </tr>" );
        respuesta.println( "           <tr>" );
        respuesta.println( "               <td bgcolor=\"#000000\" height=\"2px\"></td>" );
        respuesta.println( "           </tr>" );
        respuesta.println( "           <tr>" );
        respuesta.println( "               <td>&nbsp; Video Andes Online.<br>" );
        respuesta.println( "               &nbsp;" );
        respuesta.println( "               Todos los derechos no reservados (excepto las im�genes).<br>" );
        respuesta.println( "               <b>&nbsp; 2006</b></td>" );
        respuesta.println( "           </tr>" );
        respuesta.println( "       </table>" );
        respuesta.println( "       </td>" );
        respuesta.println( "   </tr>" );
        respuesta.println( "</table>" );

        respuesta.println( "</center>" );
        respuesta.println( "</div>" );
        respuesta.println( "</body>" );

        respuesta.println( "</html>" );
		
	}
	
    /**
     * Imprime un mensaje de error
     * @param respuesta Respuesta al cliente
     * @param titulo T�tulo del error
     * @param mensaje Mensaje del error
     */
    protected void imprimirMensajeError( PrintWriter respuesta, String titulo, String mensaje )
    {
        respuesta.println( "                      <p class=\"error\"><b>Ha ocurrido un error!:<br>" );
        respuesta.println( "                      </b>" + titulo + "</p><p>" + mensaje + ". </p>" );
        respuesta.println( "                      <p>Intente la " );
        respuesta.println( "                      operaci�n nuevamente. Si el problema persiste, contacte " );
        respuesta.println( "                      al administrador del sistema.</p>" );
        respuesta.println( "                      <p><a href=\"index.htm\">Volver a la p�gina principal</a>" );
    }

    /**
     * Imprime un mensaje de error
     * @param respuesta Respuesta al cliente
     * @param titulo T�tulo del error
     * @param exception Excepci�n de error
     * @param mensaje Mensaje del error
     */
    protected void imprimirMensajeError( PrintWriter respuesta, String titulo, String mensaje, Exception exception )
    {
        respuesta.println( "                      <p class=\"error\"><b>Ha ocurrido un error!:<br>" );
        respuesta.println( "                      </b>" + titulo + "</p><p>" + mensaje + ". Mas Informaci�n:<br>" );
        exception.printStackTrace( respuesta );
        respuesta.println( "</p>" );
        respuesta.println( "                      <p>Intente la " );
        respuesta.println( "                      operaci�n nuevamente. Si el problema persiste, contacte " );
        respuesta.println( "                      al administrador del sistema.</p>" );
        respuesta.println( "                      <p><a href=\"index.htm\">Volver a la p�gina principal</a>" );
    }

    /**
     * Imprime un mensaje de �xito
     * @param respuesta Respuesta al cliente
     * @param titulo T�tulo del mensaje
     * @param mensaje Contenido del mensaje
     */
    protected void imprimirMensajeOk( PrintWriter respuesta, String titulo, String mensaje )
    {
        respuesta.println( "                      <p class=\"ok\"><b>Operaci�n exitosa:<br>" );
        respuesta.println( "                      </b>" + titulo + "</p><p>" + mensaje + ". </p>" );
        respuesta.println( "                      <p><a href=\"index.htm\">Volver a la p�gina principal</a>" );
    }

	
	// -----------------------------------------------------------------
    // M�todos Abstractos
    // -----------------------------------------------------------------

    /**
     * Devuelve el t�tulo de la p�gina para el Header
     * @param request Pedido del cliente
     * @return T�tulo de la p�gina para el Header
     */
    public abstract String darTituloPagina( HttpServletRequest request );

    /**
     * Devuelve el nombre de la imagen para el t�tulo de la p�gina en el Header
     * @param request Pedido del cliente
     * @return Nombre de la imagen para el t�tulo de la p�gina en el Header
     */
    public abstract String darImagenTitulo( HttpServletRequest request );

    /**
     * Escribe el contenido de la p�gina
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepci�n de error al escribir la respuesta
     */
    public abstract void escribirContenido( HttpServletRequest request, HttpServletResponse response ) throws IOException;

}
