package co.edu.uniandes.valorAndes.servletsIter3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.valorAndes.servlets.ServletTemplate;
import co.edu.uniandes.valorAndes.vos.ComposicionValue;
import co.edu.uniandes.valorAndes.vos.ValorAgregarValue;

public class ServletRecomponer extends ServletTemplate{

@Override
	public String darTituloPagina(HttpServletRequest request) 
	{
		// TODO Auto-generated method stub
		return "Edita tu portafolio";
	}

	@Override
	public String darImagenTitulo(HttpServletRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void escribirContenido(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		ArrayList<String> decisiones = new ArrayList<String>();
		ArrayList<Integer> porcentajes = new ArrayList<Integer>();
		ArrayList<ValorAgregarValue> valores = new ArrayList<ValorAgregarValue>();
		int tamanio = valorAndes.darComposicion().size();
		for( int i = 0; i<tamanio; i++)
		{
			String decision = request.getParameter("opcion"+i);
			decisiones.add(decision);
			
			Integer porcentaje = Integer.parseInt(request.getParameter("porcentajeO"+i));
			porcentajes.add(porcentaje);
		}
		
		for(int i = 1; i<6; i++)
		{
			ValorAgregarValue nuevo = new ValorAgregarValue();
			int idValor = 0;
			if(request.getParameter("idValor"+i) != null && !request.getParameter("idValor"+i).equals(""))
			{
				idValor = Integer.parseInt(request.getParameter("idValor"+i));
			}
			
			String nombreValor = request.getParameter("nombreValor"+i);
			nuevo.setIdValor(idValor);
			nuevo.setNombreValor(nombreValor);
		}
		
		PrintWriter out = response.getWriter( );
		int nIdPortafolio = ((ComposicionValue)valorAndes.darComposicion().get(0)).getIdPortafolio();
		
		ArrayList<ComposicionValue> composicion = new ArrayList<ComposicionValue>();
		try
		{
			valorAndes.recomponerPortafolio(decisiones, porcentajes, valores);

			composicion = valorAndes.darComposicionPortafolio(nIdPortafolio);
			out.println("		<table>");
			out.println("			<tr>");
			out.println("				  <td><strong> Id del Portafolio </strong></td>");
			out.println("				  <td><strong> Id del Valor </strong></td>");
			out.println("				  <td><strong> Nombre </strong></td>");
			out.println("				  <td><strong> Porcentaje </strong></td>");
			out.println("			</tr>");
			for (int i =0 ; i<composicion.size(); i++)
			{
				ComposicionValue actual = (ComposicionValue)composicion.get(i);
				out.println("			<tr>");
				out.println("				  <td id = \"idPortafolio"+i+"> "+actual.getIdPortafolio()+" </td>");
				out.println("				  <td id = \"idValor"+i+"> "+actual.getIdValor()+" </td>");
				out.println("				  <td id = \"nombreValor"+i+"> "+actual.getNombreValor()+" </td>");
				out.println("				  <td id = \"porcentaje"+i+"> "+actual.getPorcentaje()+" </td>");
				out.println("			</tr>");
			}
			out.println("		</table>");
			
		}
		catch(Exception e)
		{
			imprimirMensajeError(out, e.getMessage());
			e.printStackTrace();
		}
	}
}
