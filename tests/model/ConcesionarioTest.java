package model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ConcesionarioTest {

	private Concesionario concesionario;
	@Mock
	private Fabrica fabrica;
	@Mock
	private Cliente cliente;
	@Mock
	private PlanDeAhorro planDeAhorro;
	@Mock
	private Modelo golTrend;
	@Mock
	private Modelo vento;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		concesionario = new Concesionario("Guido Guidi",
				"Zapiola 232, Quilmes, Buenos Aires, Argentina", fabrica);

	}

	/**
	 * Testea la creación de un concesionario
	 */
	@Test
	public void crearConcesionarioTest() {
		assertEquals(concesionario.getNombre(), "Guido Guidi");
		assertEquals(concesionario.getDireccion(),
				"Zapiola 232, Quilmes, Buenos Aires, Argentina");
		assertEquals(concesionario.getFabrica(), fabrica);
	}

	/**
	 * Testea agregar un cliente al concesionario
	 */
	@Test
	public void agregarCliente() {
		concesionario.agregarCliente(cliente);

		assertEquals(concesionario.getClientes().size(), 1);
		assertEquals(concesionario.getClientes().get(0), cliente);
	}

	/**
	 * Testea agregar un plan de ahorro al concesionario
	 */
	@Test
	public void agregarPlanDeAhorro() {
		concesionario.agregarPlanAhorro(planDeAhorro);
		
		assertEquals(concesionario.getPlanesDeAhorro().size(), 1);
		assertEquals(concesionario.getPlanesDeAhorro().get(0), planDeAhorro);
	}

	/**
	 * 
	 */
	@Test
	public void getPlanesConMayorCantidadSubscriptoresTop10OrderByCantidadDescTest() {

		registrarClientes();
		registrarPlanes();

		// TOMO PLAN 10 Y 20 PARA AGREGARLE MAS SUBSCRIPTORES

		concesionario.getPlanAhorroByNumeroGrupo(10).get()
				.agregarSubscripcion(concesionario.getClientes().get(20));
		concesionario.getPlanAhorroByNumeroGrupo(10).get()
				.agregarSubscripcion(concesionario.getClientes().get(21));
		concesionario.getPlanAhorroByNumeroGrupo(10).get()
				.agregarSubscripcion(concesionario.getClientes().get(22));

		concesionario.getPlanAhorroByNumeroGrupo(20).get()
				.agregarSubscripcion(concesionario.getClientes().get(20));
		concesionario.getPlanAhorroByNumeroGrupo(20).get()
				.agregarSubscripcion(concesionario.getClientes().get(21));

		List<PlanDeAhorro> planes = concesionario
				.getPlanesConMayorCantidadSubscriptoresTop10OrderByCantidadDesc();

		assertEquals(planes.size(), 10);
		assertEquals(planes.get(0).getSubscripciones().size(), 13);
		assertEquals(planes.get(1).getSubscripciones().size(), 12);
		assertEquals(planes.get(9).getSubscripciones().size(), 10);

	}

	/**
	 * 
	 */
	private void registrarPlanes() {
		// GENERO 100 PLANES CON 10 SUBSCRIPCIONES CADA UNO
		for (int i = 0; i < 100; i++) {
			PlanDeAhorro plan = new PlanDeAhorro(i + 1, new Modelo("Gol Trend",
					5, LocalDate.of(2015, 1, 1), false, 120000), 84, null, null);

			for (int cliente = 0; cliente < 10; cliente++)
				plan.agregarSubscripcion(concesionario.getClientes().get(
						cliente));

			concesionario.agregarPlanAhorro(plan);

		}
	}

	/**
	 * 
	 */
	private void registrarClientes() {
		// GENERO 1000 CLIENTES AL CONCECIONARIO
		for (int i = 0; i < 1000; i++) {

			concesionario.agregarCliente(new Cliente(String.format(
					"Nombre_%1$s", i), String.format("Apellido_%d", i), String
					.format("Nombre_%d", i), String.format("dirrecion_%d", i),
					String.format("email%d@gmail.com", i), LocalDate.of(1980,
							1, 1).plusDays(i), LocalDate.of(2015, 1, 1)
							.plusDays(i)));

		}
	}

	/**
	 * Testea el stock del concesionario en base a una fabrica con una planta de producción
	 */
	@Test
	public void stockConcesionarioTest() {
		
		Fabrica fabrica = new Fabrica("VolksWagen");
		fabrica.subscribirCambiosStock(concesionario);
			
		Planta planta = new Planta("Planta1", fabrica);
		planta.subscribirCambiosStock(fabrica);
		planta.producirModelo(golTrend, 30000);
		planta.producirModelo(vento, 10000);	
		
		assertEquals(concesionario.getStockByModelo(golTrend), 30000);
		assertEquals(concesionario.getStockByModelo(vento), 10000); 
	}
	
	/**
	 * Testea el stock del concesionario en base a una fabrica con dos planta de producción
	 */
	@Test
	public void stockConcesionarioConDosPlantasTest() {
		
		Fabrica fabrica = new Fabrica("VolksWagen");
		fabrica.subscribirCambiosStock(concesionario);
		
		Planta planta1 = new Planta("Planta1", fabrica);
		planta1.subscribirCambiosStock(fabrica);
		planta1.producirModelo(golTrend, 30000);
		planta1.producirModelo(vento, 10000);
		
		Planta planta2 = new Planta("Planta2", fabrica);
		planta2.subscribirCambiosStock(fabrica);
		planta2.producirModelo(golTrend, 20000);
		planta2.producirModelo(vento, 10000);	
		
		assertEquals(concesionario.getStockByModelo(golTrend), 50000);
		assertEquals(concesionario.getStockByModelo(vento), 20000); 
	}
	
	/**
	 * Testea el stock del concesionario en base a una fabrica con dos planta de producción
	 * y liberación de stock de dos modelos distintos
	 */
	@Test
	public void stockConcesionarioConDosPlantasYLiberacionesTest() {
		
		Fabrica fabrica = new Fabrica("VolksWagen");
		fabrica.subscribirCambiosStock(concesionario);
		
		Planta planta1 = new Planta("Planta1", fabrica);
		planta1.subscribirCambiosStock(fabrica);
		planta1.producirModelo(golTrend, 30000);
		planta1.producirModelo(vento, 10000);
		
		Planta planta2 = new Planta("Planta2", fabrica);
		planta2.subscribirCambiosStock(fabrica);
		planta2.producirModelo(golTrend, 20000);
		planta2.producirModelo(vento, 10000);	
		
		planta1.liberarStockModelo(vento, 1000);
		planta2.liberarStockModelo(golTrend, 500);
		
		assertEquals(concesionario.getStockByModelo(golTrend), 49500);
		assertEquals(concesionario.getStockByModelo(vento), 19000); 
	}

}
