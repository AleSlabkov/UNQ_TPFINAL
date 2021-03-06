package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Ale
 *
 */
/**
 * @author Ale
 *
 */
public class Concesionario implements IStockObserver {

	private String nombre;
	private String direccion;
	private List<Cliente> clientes;
	private List<PlanDeAhorro> planes;
	private Fabrica fabrica;
	private List<StockModelo> stock;
	private float gastosAdministrativos;
	private IAseguradoraDePlanes aseguradora;
	private IGoogleMaps maps;
	private IFleteCotizador fleteCotizador;

	public Concesionario(String nombre, String direccion, Fabrica fabrica,
			IAseguradoraDePlanes aseguradora, IGoogleMaps maps,
			IFleteCotizador fleteCotizador) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.clientes = new ArrayList<Cliente>();
		this.planes = new ArrayList<PlanDeAhorro>();
		this.fabrica = fabrica;
		this.stock = new ArrayList<StockModelo>();
		this.aseguradora = aseguradora;
		this.maps = maps;
		this.fleteCotizador = fleteCotizador;
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

	public IAseguradoraDePlanes getAseguradora() {
		return this.aseguradora;
	}

	
	/**
	 * @return obtiene los 10 primeros planes con mayor cantidad de subscriptores ordenados por cantidad descendente
	 */
	public List<PlanDeAhorro> getPlanesConMayorCantidadSubscriptoresTop10OrderByCantidadDesc() {

		return this.planes
				.stream()
				.sorted((e1, e2) -> Integer.compare(e2.getSubscripciones()
						.size(), e1.getSubscripciones().size())).limit(10)
				.collect(Collectors.toList());

	}

	
	/**
	 * @param agrega un plan
	 */
	public void agregarPlanAhorro(PlanDeAhorro plan) {
		this.planes.add(plan);
	}

	/**
	 * @param numero
	 * @return devuelve un plan de ahorro con el numero informado, puede llegar a ser optional
	 */
	public Optional<PlanDeAhorro> getPlanAhorroByNumeroGrupo(Integer numero) {

		return planes.stream().filter(u -> u.getNumeroGrupo() == numero)
				.findFirst();
	}

	
	/**
	 * @param modelo
	 * @return devuelve el stock de un modelo
	 */
	public int getStockByModelo(Modelo modelo) {
		Optional<StockModelo> sm = this.stock.stream().filter(s -> s.getModelo() == modelo).findFirst();
		 return sm.isPresent()? sm.get().getCantidad() : 0;
				
	}

	@Override
	public void aumentarStock(ICambioStock o, Modelo m, Integer cantidad) {
		updateStock(m, cantidad);
	}

	@Override
	public void liberarStock(ICambioStock o, Modelo m, Integer cantidad) {
		updateStock(m, cantidad * -1);

	}

	/**
	 * @param modelo
	 * @return devuelve el costo del flete para traer el modelo de la planta mas cercana
	 */
	public float getCostoDeFleteByModelo(Modelo modelo) {
		return fleteCotizador
				.getCostoByDistancia(getDistanciaAPlantaMasCercanaByModelo(modelo));
	}

	/**
	 * @param valor
	 */
	public void modificarValorGastosAdministrativos(float valor) {
		this.gastosAdministrativos = valor;
	}

	/**
	 * @return devuelve los gastos administrativos del concesionario
	 * @throws ConcesionarioSinGastosAdministrativosException
	 */
	public float getGastosAdministrativos()
			throws ConcesionarioSinGastosAdministrativosException {

		if (this.gastosAdministrativos == 0)
			throw new ConcesionarioSinGastosAdministrativosException();

		return this.gastosAdministrativos;
	}

	/**
	 * Retorna el valor del seguro de vida de acuerdo a la aseguradora
	 * 
	 * @param edad del asegurado
	 * @param montoAdeudado por el asegurado
	 * @return valor del seguro en float
	 */
	public float getSeguroDeVida(Integer edad, float montoAdeudado) {
		return this.aseguradora.calcularValorDelSeguro(edad, montoAdeudado);
	}

	/**
	 * Devuelve la Planta de producción mas cercana al concesionario que produce
	 * el modelo buscado
	 * 
	 * @param modelo
	 *            buscado
	 * @return Una Planta de producción
	 */
	public Planta getPlantaMasCercanaByModelo(Modelo modelo) {
		return this.fabrica
				.getPlantasByModelo(modelo)
				.stream()
				.min((p1, p2) -> Float.compare(
						this.maps.getDistancia(this.direccion,
								p1.getDireccion()),
						this.maps.getDistancia(this.direccion,
								p2.getDireccion()))).get();
	}

	/**
	 * Devuelve la distancia a la Planta de producción mas cercana al
	 * concesionario que produce el modelo buscado
	 * 
	 * @param modelobuscado
	 * @return Una distancia en float
	 */
	private float getDistanciaAPlantaMasCercanaByModelo(Modelo modelo) {
		return this.maps.getDistancia(this.direccion,
				getPlantaMasCercanaByModelo(modelo).getDireccion());
	}
	
	/** actualiza el stock de un modelo
	 * @param m
	 * @param cantidad
	 */
	private void updateStock(Modelo m, Integer cantidad) {
		if (this.stock.stream().anyMatch(s -> s.getModelo() == m)) 
			updateCantidad(m, cantidad);
		else 
			this.stock.add(new StockModelo(m, cantidad));
		
	}

	/** actualiza la cantidad de unidades de un modelo
	 * @param m
	 * @param cantidad
	 */
	private void updateCantidad(Modelo m, Integer cantidad) {
		
		StockModelo stock = this.stock.stream().filter(s -> s.getModelo() == m).findFirst().get();
		stock.setCantidad(stock.getCantidad() + cantidad);
	}

}
