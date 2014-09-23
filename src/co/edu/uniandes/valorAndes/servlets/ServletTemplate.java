
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
       
	protected ValorAndes valorAndes;
	
    /**
     * constructor de la clase. Llama al constructor de 
     * su padre.
     */
    public ServletTemplate() 
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
        // Comienza con el Header del template
        imprimirHeader( request, response );
        //
        // Escribe el contenido de la página
        escribirContenido( request, response );
        //
        // Termina con el footer del template
        imprimirFooter( response );

    }

    /**
     * Escribe la cabecera de la página web
     * @param request pedido del cliente
     * @param response respuesta del servlet
     * @throws IOException Excepción de error al recibir la respuesta
     */
	private void imprimirHeader(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		 //
        // Saca el printer de la repuesta
        PrintWriter out = response.getWriter( );
        //
        // Imprime el header
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("");
        out.println("<head>");
        out.println("");
        out.println("    <meta charset=\"ISO-8859-1\">");
        out.println("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
        out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.println("    <meta name=\"description\" content=\"\">");
        out.println("    <meta name=\"author\" content=\"\">");
        out.println("");
        out.println("    <title>Landing Page - Start Bootstrap Theme</title>");
        out.println("");
        out.println("    <!-- Bootstrap Core CSS -->");
        out.println("    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">");
        out.println("");
        out.println("    <!-- Custom CSS -->");
        out.println("    <link href=\"css/landing-page.css\" rel=\"stylesheet\">");
        out.println("");
        out.println("    <!-- Custom Fonts -->");
        out.println("    <link href=\"font-awesome-4.1.0/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">");
        out.println("    <link href=\"http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">");
        out.println("");
        out.println("    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->");
        out.println("    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->");
        out.println("    <!--[if lt IE 9]>");
        out.println("        <script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>");
        out.println("        <script src=\"https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js\"></script>");
        out.println("    <![endif]-->");
        out.println("");
        out.println("</head>");
        out.println("");
        out.println("<body>");
        out.println("");
        out.println("    <!-- Navigation -->");
        out.println("    <nav class=\"navbar navbar-default navbar-fixed-top\" role=\"navigation\">");
        out.println("        <div class=\"container\">");
        out.println("            <!-- Brand and toggle get grouped for better mobile display -->");
        out.println("            <div class=\"navbar-header\">");
        out.println("                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">");
        out.println("                    <span class=\"sr-only\">Toggle navigation</span>");
        out.println("                    <span class=\"icon-bar\"></span>");
        out.println("                    <span class=\"icon-bar\"></span>");
        out.println("                    <span class=\"icon-bar\"></span>");
        out.println("                </button>");
        out.println("                <a class=\"navbar-brand\" href=\"index.html\">Inicio</a>");
        out.println("            </div>");
        out.println("            <!-- Collect the nav links, forms, and other content for toggling -->");
        out.println("            <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">");
        out.println("                <ul class=\"nav navbar-nav navbar-right\">");
        out.println("                    <li>");
        out.println("                        <a href=\"#\">Acerca de nosotros</a>");
        out.println("                    </li>");
        out.println("                    <li>");
        out.println("                        <a href=\"#\">Servicios</a>");
        out.println("                    </li>");
        out.println("                    <li>");
        out.println("                        <a href=\"mp.mancipe10@uniandes.edu.co?Subject=Programa%20quejas\">Contactenos</a>");
        out.println("                    </li>");
        out.println("                </ul>");
        out.println("            </div>");
        out.println("            <!-- /.navbar-collapse -->");
        out.println("        </div>");
        out.println("        <!-- /.container -->");
        out.println("    </nav>");
        out.println("");
        out.println("    <!-- Header -->");
        out.println("    <div class=\"intro-header\">");
        out.println("");
        out.println("        <div class=\"container\">");
        out.println("");
        out.println("            <div class=\"row\">");
        out.println("                <div class=\"col-lg-12\">");
        out.println("                    <div class=\"intro-message\">");
        out.println("                        <h1>Valores De Los Andes</h1>");
        out.println("                        <h3>Gracias por confiar en nosotros</h3>");
        out.println("                        <hr class=\"intro-divider\">");
        out.println("                        <ul class=\"list-inline intro-social-buttons\">");
        out.println("                            <li>");
        out.println("                                <a href=\"https://twitter.com/SBootstrap\" class=\"btn btn-default btn-lg\"><i class=\"fa fa-twitter fa-fw\"></i> <span class=\"network-name\">Twitter</span></a>");
        out.println("                            </li>");
        out.println("                            <li>");
        out.println("                                <a href=\"https://github.com/IronSummitMedia/startbootstrap\" class=\"btn btn-default btn-lg\"><i class=\"fa fa-github fa-fw\"></i> <span class=\"network-name\">Github</span></a>");
        out.println("                            </li>");
        out.println("                            <li>");
        out.println("                                <a href=\"#\" class=\"btn btn-default btn-lg\"><i class=\"fa fa-linkedin fa-fw\"></i> <span class=\"network-name\">Linkedin</span></a>");
        out.println("                            </li>");
        out.println("                        </ul>");
        out.println("                    </div>");
        out.println("                </div>");
        out.println("            </div>");
        out.println("");
        out.println("        </div>");
        out.println("        <!-- /.container -->");
        out.println("");
        out.println("    </div>");
        out.println("    <!-- /.intro-header -->");
		
	}

	private void imprimirFooter(HttpServletResponse response) throws IOException
	{
		
        // Saca el writer de la respuesta
        PrintWriter out = response.getWriter( );
        //
        // Imprime el footer
        out.println("    <!-- Footer -->");
        out.println("    <footer>");
        out.println("        <div class=\"container\">");
        out.println("            <div class=\"row\">");
        out.println("                <div class=\"col-lg-12\">");
        out.println("                    <ul class=\"list-inline\">");
        out.println("                        <li>");
        out.println("                            <a href=\"#home\">Inicio</a>");
        out.println("                        </li>");
        out.println("                        <li class=\"footer-menu-divider\">&sdot;</li>");
        out.println("                        <li>");
        out.println("                            <a href=\"#about\">Acerca de nosotros</a>");
        out.println("                        </li>");
        out.println("                        <li class=\"footer-menu-divider\">&sdot;</li>");
        out.println("                        <li>");
        out.println("                            <a href=\"#services\">Servicios</a>");
        out.println("                        </li>");
        out.println("                        <li class=\"footer-menu-divider\">&sdot;</li>");
        out.println("                        <li>");
        out.println("                            <a href=\"#contact\">Contactos</a>");
        out.println("                        </li>");
        out.println("                    </ul>");
        out.println("                    <p class=\"copyright text-muted small\">Copyright &copy; ValorAndes 2014. All Rights Reserved</p>");
        out.println("                </div>");
        out.println("            </div>");
        out.println("        </div>");
        out.println("    </footer>");
        out.println("");
        out.println("    <!-- jQuery Version 1.11.0 -->");
        out.println("    <script src=\"js/jquery-1.11.0.js\"></script>");
        out.println("");
        out.println("    <!-- Bootstrap Core JavaScript -->");
        out.println("    <script src=\"js/bootstrap.min.js\"></script>");
        out.println("");
        out.println("</body>");
        out.println("");
        out.println("</html>");

		
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
