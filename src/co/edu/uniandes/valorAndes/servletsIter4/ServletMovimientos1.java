

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







@SuppressWarnings("serial")
public class ServletMovimientos1 extends ServletTemplateAjax
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


		String inicio = request.getParameter( "inicio" );
		String fin = request.getParameter( "fin" );
		String criterio = request.getParameter( "criterio" );
		String palabra = request.getParameter( "palabra" );

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

		if(criterio.equals("nombre")){
			decision = "ISTRUMENTO_FINANCIERO.NOMBRE LIKE '" +palabra+ "'";
		}
		else if(criterio.equals("tipo")){
			decision = "TIPO_VALOR.NOMBRE LIKE '" +palabra+ "'";
		}
		else if(criterio.equals("rentabilidad")){
			decision = "RENTABILIDAD.NOMBRE LIKE '" +palabra+ "'";
		}
		else if(criterio.equals("comisionista")){
			decision = "COMISIONISTA.NOM_REPRESENTANTE LIKE '" +palabra+ "'";
		}
		else if(criterio.equals("inversionista")){
			decision = "NOMBRE_REPRESENTANTE LIKE '" +palabra+ "'";
		}
		else if(criterio.equals("oferente")){
			decision = "OFERENTE.NOM_REPRESENTANTE LIKE '" +palabra+ "'";
		}



		ArrayList movimientos = new ArrayList();;

		try {
			movimientos = cupi.dao().mov(inicio, fin, decision);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		if(inicio != "" && fin != ""){



			escribirContenido(respuesta, movimientos);

		}

		else{



			this.imprimirMensajeError(respuesta, "No ingreso las dos fechas ");

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
		respuesta.println("          <th>Numero de registro</th>");
		respuesta.println("          <th>Tipo de movimiento</th>");
		respuesta.println("          <th>Fecha</th>");
		respuesta.println("          <th>Nombre Instrumento</th>");
		respuesta.println("          <th>Valor Instrumento</th>");
		respuesta.println("          <th>Tipo de valor</th>");

		respuesta.println("          <th>Rentabilidad</th>");
		respuesta.println("          <th>Nombre comisionista</th>");
		respuesta.println("          <th>Nombre inversionista</th>");
		respuesta.println("          <th>Nombre oferente</th>");
		respuesta.println("        </tr>");
		respuesta.println("      </thead>");
		
		
		
		respuesta.println("      <tfoot>");
		respuesta.println("        <tr>");
		respuesta.println("          <th>Numero de registro</th>");
		respuesta.println("          <th>Tipo de movimiento</th>");
		respuesta.println("          <th>Fecha</th>");
		respuesta.println("          <th>Nombre Instrumento</th>");
		respuesta.println("          <th>Valor Instrumento</th>");
		respuesta.println("          <th>Tipo de valor</th>");

		respuesta.println("          <th>Rentabilidad</th>");
		respuesta.println("          <th>Nombre comisionista</th>");
		respuesta.println("          <th>Nombre inversionista</th>");
		respuesta.println("          <th>Nombre oferente</th>");
		respuesta.println("        </tr>");
		respuesta.println("      </tfoot>");
		
		
		
		
		
		
		
		
		
		
		
		
		respuesta.println("      <tbody>");






		for(int i= 0 ; i < movimientos.size(); i++){

			respuesta.println("        <tr>");


			OperacionValue actual =	(OperacionValue) movimientos.get(i);
			respuesta.println("          <td>" + actual.getNumRegistro() + "</td>");
			respuesta.println("          <td>" + actual.getTipoOP() + "</td>");
			respuesta.println("          <td>" + actual.getFecha() + "</td>");
			respuesta.println("          <td>" + actual.getNomInstrumento() + "</td>");
			respuesta.println("          <td>" + actual.getValorIns() + "</td>");
			respuesta.println("          <td>" + actual.getTipoValor() + "</td>");
			respuesta.println("          <td>" + actual.getRentabilidad()+ "</td>");
			respuesta.println("          <td>" + actual.getNomComisionista()+ "</td>");
			respuesta.println("          <td>" + actual.getNomInversionista() + "</td>");
			respuesta.println("          <td>" + actual.getNomOferente() + "</td>");

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
