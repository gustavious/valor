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
			out.println("			</tr>");
			for (int i =0 ; i<composicion.size(); i++)
			{
				ComposicionValue actual = (ComposicionValue)composicion.get(i);
				out.println("			<tr>");
				out.println("				  <td> "+actual.getIdPortafolio()+" </td>");
				out.println("				  <td> "+actual.getIdValor()+" </td>");
				out.println("				  <td> "+actual.getNombreValor()+" </td>");
				out.println("				  <td> "+actual.getPorcentaje()+" </td>");
				out.println("				  <td> ");
				out.println("				  	<select> ");
				out.println("				  		<option value = \"ordenarCompra\"> ordenarCompra </option>");
				out.println("				  		<option value = \"ordenarVenta\"> ordenarVenta </option>");
				out.println("				  	</select> ");
				out.println("				  </td> ");
				
				out.println("			</tr>");
			}
			out.println("		</table>");
		}
		catch(Exception e)
		{
			imprimirMensajeError(out, "Hubo un error en el proceso!");
		}
		
	}

}
