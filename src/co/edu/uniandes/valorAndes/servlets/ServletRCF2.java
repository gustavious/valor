package co.edu.uniandes.valorAndes.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.valorAndes.vos.OperacionValue;

@SuppressWarnings("serial")
public class ServletRCF2 extends ServletTemplate
{

	@Override
	public String darTituloPagina(HttpServletRequest request) 
	{
		// TODO Auto-generated method stub
		return "Consulta de Operaciones Bursatiles";
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
		String nTipoUsuario = request.getParameter("nTipoUsuario");
		String nTipoOperacion = request.getParameter("nTipoOperacion");
		String Fecha1 = request.getParameter("nFechaInicial");
		Date nFechaInicial = Date.valueOf(Fecha1);
		String Fecha2 = request.getParameter("nFechaFinal");
		Date nFechaFinal = Date.valueOf(Fecha2);
		String costo = request.getParameter("nCosto");
		Double nCosto = Double.parseDouble(costo);
		String nRentabilidad = request.getParameter("nRentabilidad");

		
		ArrayList<OperacionValue> operaciones = new ArrayList<OperacionValue>();
		try 
		{
			operaciones = valorAndes.darOperaciones(nTipoUsuario, nTipoOperacion, nFechaInicial, nFechaFinal, nCosto, nRentabilidad);
			out.println("		<table>");
			out.println("			<tr>");
			out.println("				  <td><strong> ID </strong></td>");
			out.println("				  <td><strong> Tipo </strong></td>");
			out.println("				  <td><strong> Valor </strong></td>");
			out.println("				  <td><strong> ID del Valor </strong></td>");
			out.println("				  <td><strong> Fecha Inicial </strong></td>");
			out.println("				  <td><strong> Fecha Final </strong></td>");
			out.println("			</tr>");
			for (int i =0 ; i<operaciones.size(); i++)
			{
				OperacionValue operacion = (OperacionValue)operaciones.get(i);
				out.println("			<tr>");
				out.println("				  <td> "+operacion.getId()+" </td>");
				out.println("				  <td> "+operacion.getTipo()+" </td>");
				out.println("				  <td> "+operacion.getValor()+" </td>");
				out.println("				  <td> "+operacion.getIdInstrumento()+" </td>");
				out.println("				  <td> "+operacion.getFechaInic()+" </td>");
				out.println("				  <td> "+operacion.getFechaFin()+" </td>");
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