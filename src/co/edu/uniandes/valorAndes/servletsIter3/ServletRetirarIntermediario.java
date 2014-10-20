package co.edu.uniandes.valorAndes.servletsIter3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.valorAndes.servlets.ServletTemplate;

public class ServletRetirarIntermediario extends ServletTemplate
{
	@Override
	public String darTituloPagina(HttpServletRequest request) 
	{
		return "Retirando Intermediarios";
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
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		int idComisionista = Integer.parseInt(request.getParameter("idComisionista"));
		try
		{
			valorAndes.retirarIntermediario(idUsuario, idComisionista);
			out.println("LA TRANSACCIÓN SE REALIZO EXISTOSAMENTE!");
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			imprimirMensajeError(out, e.getMessage());
			e.printStackTrace();
		}
	}
}
