package model;

import java.util.ArrayList;
import java.util.List;

public class Fabrica {

	private List<IObserverStock> registered;
	private List<Planta> plantas;
	
	public Fabrica()
	{
		this.registered = new ArrayList<IObserverStock>();
		this.plantas = new ArrayList<Planta>();
	}
	
	public void agregarPlanta(String nombre)
	{
		this.plantas.add(new Planta(nombre, this));
	}
	
	public void agregarStockAPlanta(Modelo m, Integer cantidad)
	{
		//obtengo la planta y ejecuto el agregar stock
		
		for (IObserverStock ob : registered) 
			ob.update(m, cantidad);
		
	}
}
