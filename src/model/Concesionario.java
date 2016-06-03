package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



public class Concesionario implements IObserverStock {

	private String nombre;
	private String direccion;
	private List<Cliente> clientes;
	private List<PlanAhorro> planes;
	private Fabrica fabrica;


	public Concesionario(String nombre, String direccion, Fabrica fabrica) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.clientes = new ArrayList<Cliente>();
		this.planes = new ArrayList<PlanAhorro>();
		this.fabrica = fabrica;
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

	public List<PlanAhorro> getPlanesConMayorCantidadSubscriptoresTop10OrderByCantidadDesc() {
		
		return this.planes
        .stream()
        .sorted((e1, e2) -> Integer.compare(e2.getSubscripciones().size(),
        		e1.getSubscripciones().size())).limit(10).collect(Collectors.toList());
		
	}

	public void agregarPlanAhorro(PlanAhorro c) {
		this.planes.add(c);
	}
	
	public Optional<PlanAhorro> getPlanAhorroByNumeroGrupo(Integer numero){
		
		return planes.stream().filter(u -> u.getNumeroGrupo() == numero).findFirst();
	}

	@Override
	public void update(Modelo m, Integer cantidad) {
		// TODO tomar la lista de stock y actualizar
		
	}

}
