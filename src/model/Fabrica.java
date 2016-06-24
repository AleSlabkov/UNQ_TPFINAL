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
	public void informarNuevoStock(Object data) {
		this.stockObservers.stream().forEach(o -> o.aumentarStock(this, data));
	}

	@Override
	public void aumentarStock(ICambioStock o, Object data) {
		informarNuevoStock(data);
	}

	@Override
	public void liberarStock(ICambioStock o, Object data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informarBajaStock(Object data) {
		// TODO Auto-generated method stub
		
	}
}
