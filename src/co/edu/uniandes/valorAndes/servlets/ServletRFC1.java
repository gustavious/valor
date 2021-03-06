package co.edu.uniandes.valorAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.valorAndes.vos.ValorValue;

@SuppressWarnings("serial")
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
		String nTipoValor = request.getParameter("nTipoValor");
		String nTipoRentabilidad = request.getParameter("nTipoRentabilidad");
		String nNegociado = request.getParameter("nNegociado");
		System.out.println(nNegociado);
		String nFechaExpiracion = request.getParameter("nFechaExpiracion");
		String nIdI = request.getParameter("nIdInversionista");
		int nIdInversionista = Integer.parseInt(nIdI);
		String nIdC = request.getParameter("nIdComisionista");
		int nIdComisionista = Integer.parseInt(nIdC);
		String nIdO = request.getParameter("nIdOferente");
		int nIdOferente = Integer.parseInt(nIdO);
		
		ArrayList<ValorValue> valores = new ArrayList<ValorValue>();
		try 
		{
			valores = valorAndes.darValoresEscogidos(nTipoValor, nTipoRentabilidad, nNegociado, nFechaExpiracion, nIdInversionista, nIdComisionista, nIdOferente);
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
			for (int i =0 ; i<valores.size(); i++)
			{
				ValorValue valor = (ValorValue)valores.get(i);
				out.println("			<tr>");
				out.println("				  <td> " +valor.getId()+" </td>");
				out.println("				  <td> "+valor.getNombre()+" </td>");
				out.println("				  <td> "+valor.getValor()+" </td>");
				out.println("				  <td> "+valor.getFechaExpiracion()+" </td>");
				out.println("				  <td> "+valor.getIdUsuario()+" </td>");
				out.println("				  <td> "+valor.getTipoValor()+" </td>");
				out.println("				  <td> "+valor.getNegociado()+" </td>");
				out.println("				  <td>"+valor.getIdRentabilidad()+" </td>");
				out.println("			</tr>");
			}
			out.println("		</table>");

		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			imprimirMensajeError(out, "Error", e.getMessage(), e);
		}
		
		

	}

}
