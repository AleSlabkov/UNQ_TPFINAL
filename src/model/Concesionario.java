package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Concesionario implements IStockObserver {

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
	public void aumentarStock(ICambioStock o, Modelo m, Integer cantidad) {
		updateStock(m, cantidad);
	}

	private void updateStock(Modelo m, Integer cantidad) {
		if (this.stock.stream().anyMatch(s -> s.getModelo() == m)) {
			updateCantidad(m, cantidad);
		} else {
			this.stock.add(new StockModelo(m, cantidad));
		}
	}

	private void updateCantidad(Modelo m, Integer cantidad) {
		StockModelo stock = this.stock.stream()
				.filter(s -> s.getModelo() == m).findFirst().get();
		stock.setCantidad(stock.getCantidad() + cantidad);
	}

	@Override
	public void liberarStock(ICambioStock o, Modelo m, Integer cantidad) {
		updateStock(m, cantidad * -1);
		
	}

}
