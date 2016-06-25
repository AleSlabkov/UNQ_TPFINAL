package model;

import java.util.ArrayList;
import java.util.List;

public class Fabrica implements ICambioStock, IStockObserver {

	private String nombre;
	private List<Planta> plantas;
	private List<IStockObserver> stockObservers;

	public Fabrica(String nombre) {
		this.nombre = nombre;
		this.plantas = new ArrayList<Planta>();
		this.stockObservers = new ArrayList<IStockObserver>();
	}

	public String getNombre() {
		return nombre;
	}

	public void agregarPlanta(Planta planta) {
		this.plantas.add(planta);

	}

	public List<Planta> getPlantas() {
		return this.plantas;
	}

	@Override
	public void subscribirCambiosStock(IStockObserver o) {
		this.stockObservers.add(o);
	}

	@Override
	public void desusbcribirCambiosStock(IStockObserver o) {
		this.stockObservers.remove(o);

	}

	@Override
	public void informarNuevoStock(Modelo m, Integer cantidad) {
		this.stockObservers.stream().forEach(o -> o.aumentarStock(this, m, cantidad));
	}

	@Override
	public void aumentarStock(ICambioStock o, Modelo m, Integer cantidad) {
		informarNuevoStock(m, cantidad);
	}

	@Override
	public void liberarStock(ICambioStock o, Modelo m, Integer cantidad) {
		informarBajaStock(m, cantidad);
		
	}

	@Override
	public void informarBajaStock(Modelo m, Integer cantidad) {
		this.stockObservers.stream().forEach(o -> o.liberarStock(this, m, cantidad));
		
	}
}
