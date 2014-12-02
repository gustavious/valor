package co.edu.uniandes.valorAndes.servletsIter5;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.valorAndes.servlets.ServletTemplate;
import co.edu.uniandes.valorAndes.vos.RecomponerValue;

public class ServletRecomponerTotal extends ServletTemplate
{
	@Override
	public String darTituloPagina(HttpServletRequest request) 
	{
		return "Recomponer Portafolio";
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
		int idPortafolio = Integer.parseInt(request.getParameter("idPortafolio"));
		ArrayList<RecomponerValue> valores = new ArrayList<RecomponerValue>();
		for(int i=0; i<5; i++)
		{
			RecomponerValue actual = new RecomponerValue();
			String valor = request.getParameter("idValor"+i);
			String por = request.getParameter("porcentaje"+i);
			String decision = request.getParameter("decision"+i);
			if( valor != null && !valor.equals("") && por!= null && !por.equals("") && decision!= null && !decision.equals(""))
			{
				int idValor=Integer.parseInt(valor);
				int porcentaje = Integer.parseInt(por);
				actual.setDecision(decision);
				actual.setValorVenta(porcentaje);
				actual.setIdValor(idValor);
				valores.add(actual);
			}
		}
		try
		{
			valorAndes.recomponerPortafolioNuevo(idPortafolio, valores);
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
