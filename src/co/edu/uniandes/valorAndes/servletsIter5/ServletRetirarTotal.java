package co.edu.uniandes.valorAndes.servletsIter5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.valorAndes.servlets.ServletTemplate;

public class ServletRetirarTotal extends ServletTemplate
{

	@Override
	public String darTituloPagina(HttpServletRequest request) 
	{
		return "Retirando Intermediario";
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
			valorAndes.retirarNuevo(idUsuario, idComisionista);
			out.println("<h2>LA TRANSACCION SE REALIZO EXISTOSAMENTE!</h2>");
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			imprimirMensajeError(out, e.getMessage());
			e.printStackTrace();
		}
	}

}
