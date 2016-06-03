package model;

import java.util.ArrayList;
import java.util.List;

public class Planta {

	private String nombre;
	private Fabrica fabrica;
	private List<StockPlanta> stockPlanta;
	
	public Planta(String nombre, Fabrica f)
	{
		this.fabrica = f;
		this.nombre = nombre;
		this.stockPlanta = new ArrayList<StockPlanta>();
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public Fabrica getFabrica()
	{
		return fabrica;
	}
	
	public void agregarStock(Modelo m, Integer cantidad)
	{
		updateStock(m, cantidad);
	}

	public void restarStock(Modelo m, Integer cantidad)
	{
		updateStock(m, -cantidad);
		
	}

	private void updateStock(Modelo m, Integer cantidad) {
		//todo buscar si existe el modelo y sumar o generar una nueva entrada en la lista
		
	}
}
