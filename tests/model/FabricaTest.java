package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class FabricaTest {

	private Fabrica fabrica;

	@Before
	public void setUp() throws Exception {

		fabrica = new Fabrica("Ford");
	}

	/**
	 * Crea una fabrica y testea el acceso a su nombre
	 */
	@Test
	public void crearFabrica() {
		assertEquals(fabrica.getNombre(), "Ford");
	}

	/**
	 * Agrega un planta de producción a la planta y testea que haya sido agregada
	 */
	@Test
	public void agregarPlantaTest() {
		Planta planta = mock(Planta.class);

		fabrica.agregarPlanta(planta);

		assertEquals(fabrica.getPlantas().size(), 1);
	}
}
