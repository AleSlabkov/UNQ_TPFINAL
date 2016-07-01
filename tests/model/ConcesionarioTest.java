package model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
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
	private IAseguradoraDePlanes aseguradora;
	@Mock
	private Cliente cliente;
	@Mock
	private PlanDeAhorro planDeAhorro;
	@Mock
	private Modelo golTrend;
	@Mock
	private Modelo vento;
	@Mock
	private IGoogleMaps maps;
	@Mock
	private IFleteCotizador fleteCotizador;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		concesionario = new Concesionario("Guido Guidi",
				"Zapiola 232, Quilmes, Buenos Aires, Argentina", fabrica,
				aseguradora, maps, fleteCotizador);

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
		assertEquals(concesionario.getAseguradora(), aseguradora);
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
	 * Testea la obtención de un plan de ahorro por su número
	 */
	@Test
	public void getPlanAhorroByNumeroGrupoTest() {

		PlanDeAhorro plan3 = mock(PlanDeAhorro.class);
		when(plan3.getNumeroGrupo()).thenReturn(30);

		concesionario.agregarPlanAhorro(plan3);

		assertEquals(concesionario.getPlanAhorroByNumeroGrupo(30).get()
				.getNumeroGrupo().intValue(), 30);

	}

	/**
	 * Testea la obtención de los 10 planes con mayor cantidad de subscriptores
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void getPlanesConMayorCantidadSubscriptoresTop10OrderByCantidadDescTest() {

		PlanDeAhorro plan1 = mock(PlanDeAhorro.class);
		List<Subscripcion> plan1Sub = mock(List.class);
		when(plan1Sub.size()).thenReturn(20);

		PlanDeAhorro plan2 = mock(PlanDeAhorro.class);
		List<Subscripcion> plan2Sub = mock(List.class);
		when(plan2Sub.size()).thenReturn(10);

		PlanDeAhorro plan3 = mock(PlanDeAhorro.class);
		List<Subscripcion> plan3Sub = mock(List.class);
		when(plan3Sub.size()).thenReturn(30);

		when(plan1.getSubscripciones()).thenReturn(plan1Sub);
		when(plan2.getSubscripciones()).thenReturn(plan2Sub);
		when(plan3.getSubscripciones()).thenReturn(plan3Sub);

		concesionario.agregarPlanAhorro(plan1);
		concesionario.agregarPlanAhorro(plan2);
		concesionario.agregarPlanAhorro(plan3);

		List<PlanDeAhorro> planes = concesionario
				.getPlanesConMayorCantidadSubscriptoresTop10OrderByCantidadDesc();

		assertEquals(planes.size(), 3);
		assertEquals(planes.get(0).getSubscripciones().size(), 30);
		assertEquals(planes.get(1).getSubscripciones().size(), 20);
		assertEquals(planes.get(2).getSubscripciones().size(), 10);

	}

	/**
	 * Testea el stock del concesionario en base a una fabrica con una planta de
	 * producción
	 */
	@Test
	public void stockConcesionarioTest() {

		Fabrica fabrica = new Fabrica("VolksWagen");
		fabrica.subscribirCambiosStock(concesionario);

		Planta planta = new Planta("Planta1", null, fabrica);
		planta.subscribirCambiosStock(fabrica);
		planta.producirModelo(golTrend, 30000);
		planta.producirModelo(vento, 10000);

		assertEquals(concesionario.getStockByModelo(golTrend), 30000);
		assertEquals(concesionario.getStockByModelo(vento), 10000);
	}

	/**
	 * Testea el stock del concesionario en base a una fabrica con dos planta de
	 * producción
	 */
	@Test
	public void stockConcesionarioConDosPlantasTest() {

		Fabrica fabrica = new Fabrica("VolksWagen");
		fabrica.subscribirCambiosStock(concesionario);

		Planta planta1 = new Planta("Planta1", null, fabrica);
		planta1.subscribirCambiosStock(fabrica);
		planta1.producirModelo(golTrend, 30000);
		planta1.producirModelo(vento, 10000);

		Planta planta2 = new Planta("Planta2", null, fabrica);
		planta2.subscribirCambiosStock(fabrica);
		planta2.producirModelo(golTrend, 20000);
		planta2.producirModelo(vento, 10000);

		assertEquals(concesionario.getStockByModelo(golTrend), 50000);
		assertEquals(concesionario.getStockByModelo(vento), 20000);
	}

	/**
	 * Testea el stock del concesionario en base a una fabrica con dos planta de
	 * producción y liberación de stock de dos modelos distintos
	 */
	@Test
	public void stockConcesionarioConDosPlantasYLiberacionesTest() {

		Fabrica fabrica = new Fabrica("VolksWagen");
		fabrica.subscribirCambiosStock(concesionario);

		Planta planta1 = new Planta("Planta1", null, fabrica);
		planta1.subscribirCambiosStock(fabrica);
		planta1.producirModelo(golTrend, 30000);
		planta1.producirModelo(vento, 10000);

		Planta planta2 = new Planta("Planta2", null, fabrica);
		planta2.subscribirCambiosStock(fabrica);
		planta2.producirModelo(golTrend, 20000);
		planta2.producirModelo(vento, 10000);

		planta1.liberarStockModelo(vento, 1000);
		planta2.liberarStockModelo(golTrend, 500);

		assertEquals(concesionario.getStockByModelo(golTrend), 49500);
		assertEquals(concesionario.getStockByModelo(vento), 19000);
	}

	/**
	 * Testea la obtención de la planta más cercana al concesionario
	 */
	@Test
	public void getPlantaDeProduccionMasCercana() {
		Planta planta1 = mock(Planta.class);
		Planta planta2 = mock(Planta.class);

		List<Planta> plantas = new ArrayList<Planta>(Arrays.asList(planta1,
				planta2));
		when(fabrica.getPlantasByModelo(golTrend)).thenReturn(plantas);

		when(maps.getDistancia(concesionario.getDireccion(), planta1.getDireccion()))
				.thenReturn(600f);
		when(maps.getDistancia(concesionario.getDireccion(), planta1.getDireccion()))
				.thenReturn(1000f);

		assertEquals(concesionario.getPlantaMasCercanaByModelo(golTrend),
				planta1);
	}

	/**
	 * Testea la modificación de los gastos administrativos del concesionario
	 * 
	 * @throws ConcesionarioSinGastosAdministrativosException
	 */
	@Test
	public void cambiarGastosAdministrativosTest()
			throws ConcesionarioSinGastosAdministrativosException {
		concesionario.modificarValorGastosAdministrativos(1000f);
		assertEquals(concesionario.getGastosAdministrativos(), 1000f, 0);
	}

	/**
	 * Espera una excepción en un concesionario que no registra gastos
	 * administrativos
	 * 
	 * @throws ConcesionarioSinGastosAdministrativosException
	 */
	@Test(expected = ConcesionarioSinGastosAdministrativosException.class)
	public void cambiarGastosAdministrativosConExceptionTest()
			throws ConcesionarioSinGastosAdministrativosException {
		concesionario.getGastosAdministrativos();
	}

	/**
	 * Testea la obtención del costo del flete
	 */
	@Test
	public void getCostoDeFleteByModelo() {
		List<Planta> plantas = new ArrayList<Planta>(Arrays.asList(mock(Planta.class)));
		when(fabrica.getPlantasByModelo(golTrend)).thenReturn(plantas);	
		when(fleteCotizador.getCostoByDistancia(anyFloat())).thenReturn(2500f);
		
		assertEquals(concesionario.getCostoDeFleteByModelo(golTrend), 2500f, 0);
	}
	
	/**
	 * Testea la obtención del valor del seguro de vida
	 */
	@Test
	public void getSeguroDeVida(){
		when(aseguradora.calcularValorDelSeguro(anyInt(), anyFloat())).thenReturn(350f);
		assertEquals(concesionario.getSeguroDeVida(50, 55000f), 350f, 0);
	}
}
