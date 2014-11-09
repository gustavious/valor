

package co.edu.uniandes.valorAndes.servletsIter4;

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
import co.edu.uniandes.valorAndes.vos.OperacionValue;
import co.edu.uniandes.valorAndes.vos.PortafolioValue;







@SuppressWarnings("serial")
public class ServletValores2 extends ServletTemplateAjax
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
	

		//		String numRegistro = rs.getString("ID");
		//		String tipoOP = rs.getString("TIPO");
		//		String fecha = rs.getString("FECHA_FINAL");
		//		String nomInstrumento = rs.getString("NOMBRE");
		//		String valorIns = rs.getString("VALOR_1");
		//		String tipoValor = rs.getString("NOMBRE_1");
		//		String rentabilidad = rs.getString("NOMBRE_2");
		//		
		//		String nomComisionista = rs.getString("NOM_REPRESENTANTE");
		//		String nomInversionista = rs.getString("NOMBRE_REPRESENTANTE");
		//		String nomOferente = rs.getString("NOM_REPRESENTANTE_1");
		//		
		String decision = "";

	



		ArrayList movimientos = new ArrayList();;

		try {
			movimientos = cupi.dao().valores2( id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		if (id != ""){



			escribirContenido(respuesta, movimientos);

		}

		else{



			this.imprimirMensajeError(respuesta, "No ingreso bien los datos ");

		}   






	}










	/**
	 * Imprime el contenido con la información especificada
	 * @param respuesta Respuesta al cliente
	 */
	private void escribirContenido( PrintWriter respuesta , ArrayList movimientos)
	{




		
		respuesta.println("");
		respuesta.println("    <!-- Table -->");

		respuesta.println("    <table id=\"tabla\" class=\"table table-hover\">");
		respuesta.println("      <thead>");
		respuesta.println("        <tr>");
		respuesta.println("          <th>Numero del portafolio</th>");
		respuesta.println("          <th>Tipo de movimiento</th>");
		respuesta.println("          <th>Fecha</th>");
		
		respuesta.println("        </tr>");
		respuesta.println("      </thead>");
		
		
		
		respuesta.println("      <tfoot>");
		respuesta.println("        <tr>");
		respuesta.println("          <th>Numero del portafolio</th>");
		respuesta.println("          <th>Tipo de movimiento</th>");
		respuesta.println("          <th>Fecha</th>");

		
		respuesta.println("        </tr>");
		respuesta.println("      </tfoot>");
		
		
		
		
		
		
		
		
		
		
		
		
		respuesta.println("      <tbody>");






		for(int i= 0 ; i < movimientos.size(); i++){

			respuesta.println("        <tr>");


			PortafolioValue actual =	(PortafolioValue) movimientos.get(i);
			respuesta.println("          <td>" + actual.getId() + "</td>");
			respuesta.println("          <td>" + actual.getNiveRiesgo() + "</td>");
			respuesta.println("          <td>" + actual.getIdUsuario() + "</td>");
		

			respuesta.println("        </tr>");

		}




		respuesta.println("      </tbody>");
		respuesta.println("    </table>");
	


		
		

		respuesta.println(" <script>");
		respuesta.println(" $(document).ready(function() {");



		respuesta.println(" $('#tabla tfoot th').each( function () {");
		respuesta.println("       var title = $('#tabla thead th').eq( $(this).index() ).text();");
		respuesta.println("       $(this).html( '<input type=\"text\" placeholder=\"Filtar '+title+'\" />' );");
		respuesta.println("   } );");

		// DataTable
		respuesta.println(" var table = $('#tabla').DataTable();");

		// Apply the search
		respuesta.println("  table.columns().eq( 0 ).each( function ( colIdx ) {");
		respuesta.println("     $( 'input', table.column( colIdx ).footer() ).on( 'keyup change', function () {");
		respuesta.println("       table");
		respuesta.println("            .column( colIdx )");
		respuesta.println("            .search( this.value )");
		respuesta.println("           .draw();");
		respuesta.println("    } );");
		respuesta.println(" } );");
		respuesta.println("} );");


		respuesta.println(" 		</script>");







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
