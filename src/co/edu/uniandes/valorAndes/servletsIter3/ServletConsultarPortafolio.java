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
			out.println("		<h2><p align=\"center\">Composici&oacute;n del portafolio<br/></p></h2>");
			out.println("		<br/>");
			out.println("		<br/>");
			out.println("		<br/>");
			out.println("		<table>");
			out.println("			<tr>");
			out.println("				  <td><h3><strong><p align=\"center\"> Id del Portafolio </p></strong></h3></td>");
			out.println("				  <td><h3><strong><p align=\"center\"> Id del Valor </p></strong></h3></td>");
			out.println("				  <td><h3><strong><p align=\"center\"> Porcentaje </p></strong></h3></td>");
			out.println("				  <td><h3><strong><p align=\"center\"> Operaci&oacute;n </p></strong></h3></td>");
			out.println("				  <td><h3><strong><p align=\"center\"> Porcentaje de la Operaci&oacute;n </h3></strong></h2></td>");
			out.println("			</tr>");
			for (int i =0 ; i<composicion.size(); i++)
			{
				ComposicionValue actual = (ComposicionValue)composicion.get(i);
				out.println("			<tr>");
				out.println("				  <td id = \"idPortafolio"+i+"><h4><p align=\"center\"> "+ actual.getIdPortafolio() +"</p></h4></td>");
				out.println("				  <td id = \"idValor"+i+"><h4><p align=\"center\">"+actual.getIdValor()+"</p></h4></td>");
				out.println("				  <td id = \"porcentaje"+i+"><h4><p align=\"center\">"+actual.getPorcentaje()+"</p></h4></td>");
				out.println("				  <td><h4><p align=\"center\">");
				out.println("				  	<select id = \"opcion"+i+"\"> ");
				out.println("				  		<option value = \"default\"> default </option>");
				out.println("				  		<option value = \"ordenarCompra\"> ordenar Compra </option>");
				out.println("				  		<option value = \"ordenarVentaParcial\"> ordenar Venta Parcial</option>");
				out.println("				  		<option value = \"ordenarVentaTotal\"> ordenar Venta Total</option>");
				out.println("				  	</select> <p align=\"center\">");
				out.println("				  </p></h4></td> ");
				out.println("				  <td><h4><p align=\"center\">");
				out.println("				  <input type= \"text\" name=\"porcentajeO"+i+"\" placeholder=\"Porcentaje de la operaci&oacute;n\">");
				out.println("				  </p></h4></td>");
				out.println("			</tr>");
			}
			out.println("		</table>");
			out.println("		<br/>");
			out.println("		<br/>");
			out.println("		<br/>");
			out.println("		<h1><p align=\"center\">Si desea agregar valores a su portafolio, por favor indique la informaci&oacute;n <br/></p></h1>");
			out.println("		<br/>");
			out.println("		<table align=\"center\">");
			out.println("			<tr>");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"idValor1\" align=\"center\" placeholder=\"id del Valor\">");
			out.println("				  </td> ");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"nombreValor1\" align=\"center\" placeholder=\"nombre del Valor\">");
			out.println("				  </td> ");
			out.println("			</tr>");
			out.println("			<tr>");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"idValor2\" align=\"center\" placeholder=\"id del Valor\">");
			out.println("				  </td> ");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"nombreValor2\" align=\"center\" placeholder=\"nombre del Valor\">");
			out.println("				  </td> ");
			out.println("			</tr>");
			out.println("			<tr>");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"idValor3\" align=\"center\" placeholder=\"id del Valor\">");
			out.println("				  </td> ");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"nombreValor3\" align=\"center\" placeholder=\"nombre del Valor\">");
			out.println("				  </td> ");
			out.println("			</tr>");
			out.println("			<tr>");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"idValor4\" align=\"center\" placeholder=\"id del Valor\">");
			out.println("				  </td> ");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"nombreValor4\" align=\"center\" placeholder=\"nombre del Valor\">");
			out.println("				  </td> ");
			out.println("			</tr>");
			out.println("			<tr>");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"idValor5\" align=\"center\" placeholder=\"id del Valor\">");
			out.println("				  </td> ");
			out.println("				  <td> ");
			out.println("				  <input type= \"text\" name=\"nombreValor5\" align=\"center\" placeholder=\"nombre del Valor\">");
			out.println("				  </td> ");
			out.println("			</tr>");
			out.println("		</table>");
			out.println("		<button type = \"button\" align=\"center\"> <a href = \"recomponer.htm\">Recomponer</a></button>");
		}
		catch(Exception e)
		{
			imprimirMensajeError(out, "Hubo un error en el proceso!");
		}
		
	}


}
