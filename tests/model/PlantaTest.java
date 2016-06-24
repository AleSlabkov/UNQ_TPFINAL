package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;

import static org.mockito.Mockito.*;


public class PlantaTest {

	private Planta planta;
	@Mock
	private Fabrica fabrica;
	@Mock
	private Modelo modelo;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		planta = new Planta("Pacheco", fabrica);
	}

	/**
	 * Testea la creaci�n de una planta
	 */
	@Test
	public void crearPlantaTest() {
		assertEquals(planta.getNombre(), "Pacheco");
		assertEquals(planta.getFabrica(), fabrica);
	}

	/**
	 * Testea el agregado de modelos producci�n
	 */
	@Test
	public void agregarModeloProducidoTest() {
		planta.agregarModeloProducido(modelo);

		assertEquals(planta.getModelosProducidos().size(), 1);
		assertEquals(planta.getModelosProducidos().get(0), modelo);
	}

	/**
	 * Testea la producci�n de un modelo
	 */
	@Test
	public void producirModeloTest() {
		

		planta.producirModelo(modelo, 1000);
		planta.producirModelo(modelo, 2000);

		assertEquals(planta.getCantidadByModelo(modelo), (Integer) 3000);
	}
	
	/**
	 * testea que se notifique a un observer registrado
	 */
	@Test
	public void producirModeloVerifyNotification() {
		

		IStockObserver observer1 = mock(IStockObserver.class);
		IStockObserver observer2 = mock(IStockObserver.class);
		
		planta.subscribirCambiosStock(observer1);
		planta.subscribirCambiosStock(observer2);
		
		planta.producirModelo(modelo, 1);
		
		verify(observer1).aumentarStock(eq(planta), any());
		verify(observer2).aumentarStock(eq(planta), any());


	}

	/**
	 * Testea la liberaci�n de stock de un modelo
	 */
	@Test
	public void liberarStockModeloTest() {
		planta.producirModelo(modelo, 20000);
		planta.liberarStockModelo(modelo, 5000);
		
		assertEquals(planta.getCantidadByModelo(modelo), (Integer) 15000);
	}
}
