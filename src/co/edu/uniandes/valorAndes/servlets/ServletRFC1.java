package co.edu.uniandes.valorAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletRFC1 extends ServletTemplate
{

	@Override
	public String darTituloPagina(HttpServletRequest request) 
	{
		// TODO Auto-generated method stub
		return "Lista de los valores escogidos seg&uacute;n criterios";
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
		
		out.println("		<table>");
		out.println("			<tr>");
		out.println("				  <td><strong> ID </strong></td>");
		out.println("				  <td><strong> Nombre </strong></td>");
		out.println("				  <td><strong> Valor </strong></td>");
		out.println("				  <td><strong> Fecha de Expiraci&oacute;n </strong></td>");
		out.println("				  <td><strong> ID del Usuario </strong></td>");
		out.println("				  <td><strong> Tipo del valor </strong></td>");
		out.println("				  <td><strong> Negociado </strong></td>");
		out.println("				  <td><strong> ID de la rentabilidad </strong></td>");
		out.println("			</tr>");
		out.println("		</table>");


	}

}
