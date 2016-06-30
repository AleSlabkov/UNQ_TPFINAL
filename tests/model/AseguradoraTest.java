package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AseguradoraTest {
	
	private Aseguradora seguro;

	@Before
	public void setUp() throws Exception {
		seguro = new Aseguradora();
	}

	/**
	 * Testea el cálculo del valor del seguro con un cliente menor de 50 años
	 */
	@Test
	public void clienteMenorDe50() {
		assertEquals(seguro.calcularValorDelSeguro(30, 1000f),
				100f, 0);
	}

	/**
	 * Testea el cálculo del valor del seguro con un cliente mayor de 50 años
	 */
	@Test
	public void clienteMayotDe50() {
		assertEquals(seguro.calcularValorDelSeguro(65, 1000f),
				250f, 0);
	}

}
