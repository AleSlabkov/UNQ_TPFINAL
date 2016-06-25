package model;

import java.util.ArrayList;
import java.util.List;

public class Planta implements ICambioStock {

	private String nombre;
	private Fabrica fabrica;
	private List<Modelo> modelosProducidos;
	private List<StockModelo> stock;
	private List<IStockObserver> stockObservers;

	public Planta(String nombre, Fabrica f) {
		this.fabrica = f;
		this.nombre = nombre;
		this.modelosProducidos = new ArrayList<Modelo>();
		this.stock = new ArrayList<StockModelo>();
		this.stockObservers = new ArrayList<IStockObserver>();
	}

	public String getNombre() {
		return nombre;
	}

	public Fabrica getFabrica() {
		return fabrica;
	}

	public List<Modelo> getModelosProducidos() {
		return this.modelosProducidos;
	}

	public void agregarModeloProducido(Modelo modelo) {
		this.modelosProducidos.add(modelo);
	}

	public void producirModelo(Modelo modelo, int cantidad) {
		if (this.stock.stream().anyMatch(s -> s.getModelo() == modelo)) {
			updateStock(modelo, cantidad);
		} else {
			this.stock.add(new StockModelo(modelo, cantidad));
		}
		
		informarNuevoStock(modelo, cantidad);
	}

	/**
	 * Elimina la una cantidad determinada por paramentro del stock de un modelo
	 * Notifica a sus observers del cambio
	 * Precondición: el modelo tiene stock en la planta
	 * @param modelo a eliminar
	 * @param cantidad a eliminar
	 */
	public void liberarStockModelo(Modelo modelo, int cantidad) {
		
		updateStock(modelo, cantidad * -1);
		
		informarBajaStock(modelo, cantidad);
	}

	private void updateStock(Modelo modelo, Integer cantidad) {
		StockModelo stock = this.stock.stream()
				.filter(s -> s.getModelo() == modelo).findFirst().get();
		stock.setCantidad(stock.getCantidad() + cantidad);
	}

	public Integer getCantidadByModelo(Modelo modelo) {
		return this.stock.stream().filter(s -> s.getModelo() == modelo)
				.findFirst().get().getCantidad();
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
	public void informarBajaStock(Modelo m, Integer cantidad) {
		this.stockObservers.stream().forEach(o -> o.liberarStock(this, m, cantidad));
		
	}
}
