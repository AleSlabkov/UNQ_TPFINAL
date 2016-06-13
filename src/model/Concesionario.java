package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Concesionario implements IObserver {

	private String nombre;
	private String direccion;
	private List<Cliente> clientes;
	private List<PlanDeAhorro> planes;
	private Fabrica fabrica;
	private List<StockModelo> stock;

	public Concesionario(String nombre, String direccion, Fabrica fabrica) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.clientes = new ArrayList<Cliente>();
		this.planes = new ArrayList<PlanDeAhorro>();
		this.fabrica = fabrica;
		this.stock = new ArrayList<StockModelo>();
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public Fabrica getFabrica() {
		return fabrica;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void agregarCliente(Cliente c) {
		this.clientes.add(c);
	}
	
	public List<PlanDeAhorro> getPlanesDeAhorro() {
		return this.planes;
	}

	public List<PlanDeAhorro> getPlanesConMayorCantidadSubscriptoresTop10OrderByCantidadDesc() {

		return this.planes
				.stream()
				.sorted((e1, e2) -> Integer.compare(e2.getSubscripciones()
						.size(), e1.getSubscripciones().size())).limit(10)
				.collect(Collectors.toList());

	}

	public void agregarPlanAhorro(PlanDeAhorro c) {
		this.planes.add(c);
	}

	public Optional<PlanDeAhorro> getPlanAhorroByNumeroGrupo(Integer numero) {

		return planes.stream().filter(u -> u.getNumeroGrupo() == numero)
				.findFirst();
	}
	
	public Object getStockByModelo(Modelo modelo) {
		return this.stock.stream().filter(s -> s.getModelo() == modelo)
				.findFirst().get().getCantidad();
	}

	@Override
	public void update(IObservable o, Object data) {
		updateStock((StockModelo)data);
	}

	private void updateStock(StockModelo stock) {
		if (this.stock.stream().anyMatch(s -> s.getModelo() == stock.getModelo())) {
			updateCantidad(stock);
		} else {
			this.stock.add(stock);
		}
	}

	private void updateCantidad(StockModelo stockNew) {
		StockModelo stock = this.stock.stream()
				.filter(s -> s.getModelo() == stockNew.getModelo()).findFirst().get();
		stock.setCantidad(stock.getCantidad() + stockNew.getCantidad());
	}

}
