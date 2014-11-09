
package co.edu.uniandes.valorAndes.servletsIter4;

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
public abstract class ServletTemplateAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected ValorAndes valorAndes;
	
    /**
     * constructor de la clase. Llama al constructor de 
     * su padre.
     */
    public ServletTemplateAjax() 
    {
        super();

    }
    public void init( ) throws ServletException
    {
        try 
        {
			valorAndes = ValorAndes.darInstancia();
		} 
        catch (Exception e)
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

    }

	/**
	 * Recibe la solicitud y la herramienta de respuesta a las solicitudes
	 * hechas por los métodos get. Invoca el método procesarPedido.
	 * @param request pedido del cliente
	 * @param response respuesta del servlet
	 * @throws IOException Excepción de error al escribir la respuesta
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		procesarPedido(request, response);
	}

	/**
	 * Recibe la solicitud y la herramienta de respuesta a las solicitudes
	 * hechas por los métodos post. Invoca el método procesarPedido.
	 * @param request pedido del cliente
	 * @param response respuesta del servlet
	 * @throws IOException Excepción de error al escribir la respuesta
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		procesarPedido(request, response);
	}
	
	/**
     * Procesa el pedido de igual manera para todos
     * @param request Pedido del cliente
     * @param response Respuesta del servlet
     * @throws IOException Excepción de error al escribir la respuesta
     */
    private void procesarPedido( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
    	//TODO Si hay otras fachadas, ellas tambien deben inicializar la ruta.
    	ValorAndes.darInstancia().inicializarRuta(ValorAndes.RUTA);
        //
      
        //
        // Escribe el contenido de la página
        escribirContenido( request, response );
        //
    

    }

  
	
    /**
     * Imprime un mensaje de error
     * @param respuesta Respuesta al cliente
     * @param titulo Título del error
     * @param mensaje Mensaje del error
     */
    protected void imprimirMensajeError( PrintWriter respuesta, String mensaje )
    {
        respuesta.println( "                      <p class=\"error\"><b>Ha ocurrido un error!:<br>" );
        respuesta.println( "                      </b></p><p>" + mensaje + ". </p>" );
        respuesta.println( "                      <p>Intente la " );
        respuesta.println( "                      operación nuevamente. Si el problema persiste, contacte " );
        respuesta.println( "                      al administrador del sistema.</p>" );
        respuesta.println( "                      <p><a href=\"index.htm\">Volver a la página principal</a>" );
    }

    /**
     * Imprime un mensaje de error
     * @param respuesta Respuesta al cliente
     * @param titulo Título del error
     * @param exception Excepción de error
     * @param mensaje Mensaje del error
     */
    protected void imprimirMensajeError( PrintWriter respuesta, String titulo, String mensaje, Exception exception )
    {
        respuesta.println( "                      <p class=\"error\"><b>Ha ocurrido un error!:<br>" );
        respuesta.println( "                      </b>" + titulo + "</p><p>" + mensaje + ". Mas Información:<br>" );
        exception.printStackTrace( respuesta );
        respuesta.println( "</p>" );
        respuesta.println( "                      <p>Intente la " );
        respuesta.println( "                      operación nuevamente. Si el problema persiste, contacte " );
        respuesta.println( "                      al administrador del sistema.</p>" );
        respuesta.println( "                      <p><a href=\"index.htm\">Volver a la página principal</a>" );
    }

    /**
     * Imprime un mensaje de éxito
     * @param respuesta Respuesta al cliente
     * @param titulo Título del mensaje
     * @param mensaje Contenido del mensaje
     */
    protected void imprimirMensajeOk( PrintWriter respuesta, String titulo, String mensaje )
    {
        respuesta.println( "                      <p class=\"ok\"><b>Operación exitosa:<br>" );
        respuesta.println( "                      </b>" + titulo + "</p><p>" + mensaje + ". </p>" );
        respuesta.println( "                      <p><a href=\"index.htm\">Volver a la página principal</a>" );
    }

	
	// -----------------------------------------------------------------
    // Métodos Abstractos
    // -----------------------------------------------------------------

    /**
     * Devuelve el título de la página para el Header
     * @param request Pedido del cliente
     * @return Título de la página para el Header
     */
    public abstract String darTituloPagina( HttpServletRequest request );

    /**
     * Devuelve el nombre de la imagen para el título de la página en el Header
     * @param request Pedido del cliente
     * @return Nombre de la imagen para el título de la página en el Header
     */
    public abstract String darImagenTitulo( HttpServletRequest request );

    /**
     * Escribe el contenido de la página
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepción de error al escribir la respuesta
     */
    public abstract void escribirContenido( HttpServletRequest request, HttpServletResponse response ) throws IOException;

}
