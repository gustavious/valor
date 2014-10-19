package co.edu.uniandes.valorAndes.servletsIter3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.valorAndes.servlets.ServletTemplate;
import co.edu.uniandes.valorAndes.vos.ComposicionValue;
import co.edu.uniandes.valorAndes.vos.OperacionValue;

public class ServletConsultarPortafolio extends ServletTemplate
{

	@Override
	public String darTituloPagina(HttpServletRequest request) 
	{
		return "Composicion del portafolio";
	}

	@Override
	public String darImagenTitulo(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		PrintWriter out = response.getWriter( );
		int nIdPortafolio = Integer.parseInt(request.getParameter("nIdPortafolio"));
		ArrayList<ComposicionValue> composicion = new ArrayList<ComposicionValue>();
		try
		{
			composicion = valorAndes.darComposicionPortafolio(nIdPortafolio);
			out.println("		<table>");
			out.println("			<tr>");
			out.println("				  <td><strong> Id del Portafolio </strong></td>");
			out.println("				  <td><strong> Id del Valor </strong></td>");
			out.println("				  <td><strong> Nombre </strong></td>");
			out.println("				  <td><strong> Porcentaje </strong></td>");
			out.println("				  <td><strong> Operaci&oacute;n </strong></td>");
			out.println("				  <td><strong> Porcentaje de la Operaci&oacute;n </strong></td>");
			out.println("			</tr>");
			for (int i =0 ; i<composicion.size(); i++)
			{
				ComposicionValue actual = (ComposicionValue)composicion.get(i);
				out.println("			<tr>");
				out.println("				  <td id = \"idPortafolio"+i+"> "+actual.getIdPortafolio()+" </td>");
				out.println("				  <td id = \"idValor"+i+"> "+actual.getIdValor()+" </td>");
				out.println("				  <td id = \"nombreValor"+i+"> "+actual.getNombreValor()+" </td>");
				out.println("				  <td id = \"porcentaje"+i+"> "+actual.getPorcentaje()+" </td>");
				out.println("				  <td> ");
				out.println("				  	<select id = \"opcion"+i+"\"> ");
				out.println("				  		<option value = \"default\"> default </option>");
				out.println("				  		<option value = \"ordenarCompra\"> ordenar Compra </option>");
				out.println("				  		<option value = \"ordenarVentaParcial\"> ordenar Venta Parcial</option>");
				out.println("				  		<option value = \"ordenarVentaTotal\"> ordenar Venta Total</option>");
				out.println("				  	</select> ");
				out.println("				  </td> ");
				out.println("				  <td> ");
				out.println("				  <input type= \"text\" name=\"porcentajeO"+i+"\" placeholder=\"Porcentaje de la operaci&oacute;n\">");
				out.println("				  </td> ");
				out.println("			</tr>");
			}
			out.println("		</table>");
			
			out.println("		Si desea agregar valores a su portafolio, por favor indique la informaci&oacute;n");
			out.println("		</table>");
			out.println("			<tr>");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"idValor1\" placeholder=\"id del Valor\">");
			out.println("				  </td> ");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"nombreValor1\" placeholder=\"nombre del Valor\">");
			out.println("				  </td> ");
			out.println("			</tr>");
			out.println("			<tr>");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"idValor2\" placeholder=\"id del Valor\">");
			out.println("				  </td> ");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"nombreValor2\" placeholder=\"nombre del Valor\">");
			out.println("				  </td> ");
			out.println("			</tr>");
			out.println("			<tr>");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"idValor3\" placeholder=\"id del Valor\">");
			out.println("				  </td> ");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"nombreValor3\" placeholder=\"nombre del Valor\">");
			out.println("				  </td> ");
			out.println("			</tr>");
			out.println("			<tr>");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"idValor4\" placeholder=\"id del Valor\">");
			out.println("				  </td> ");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"nombreValor4\" placeholder=\"nombre del Valor\">");
			out.println("				  </td> ");
			out.println("			</tr>");
			out.println("			<tr>");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"idValor5\" placeholder=\"id del Valor\">");
			out.println("				  </td> ");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"nombreValor5\" placeholder=\"nombre del Valor\">");
			out.println("				  </td> ");
			out.println("			</tr>");
			out.println("		</table>");
			out.println("		<button type = \"button\" > <a href = \"recomponer.htm\">Recomponer</a></button>");
		}
		catch(Exception e)
		{
			imprimirMensajeError(out, "Hubo un error en el proceso!");
		}
		
	}

}
