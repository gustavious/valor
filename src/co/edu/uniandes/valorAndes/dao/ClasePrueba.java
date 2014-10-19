package co.edu.uniandes.valorAndes.dao;

import java.util.ArrayList;

public class ClasePrueba
{
	public ArrayList<Integer> numeros;
	
	public ClasePrueba( )
	{
		numeros = new ArrayList<Integer>();
		
		for (int i = 0; i< 50; i++)
		{
			numeros.add(new Integer(i));
		}
		
		for (int i = 0; i< numeros.size(); i++)
		{
			Integer numero = (Integer) numeros.get(i);
			System.out.println(numero);
		}
	}
	
	public static void main(String[] args)
	{
		ClasePrueba nueva = new ClasePrueba();
	}
}
