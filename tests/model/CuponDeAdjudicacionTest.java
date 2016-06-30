package model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class CuponDeAdjudicacionTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Testea la creación y acceso a información de un cupón de adjudicación
	 */
	@Test
	public void crearCuponDeAjudicacionTest() {
		CuponDeAdjudicacion cuponDeAdjudicacion = new CuponDeAdjudicacion(LocalDate.now(), 3000f, 105000f);
		
		assertEquals(cuponDeAdjudicacion.getFecha(), LocalDate.now());
		assertEquals(cuponDeAdjudicacion.getCostoDeFlete(), 3000f, 0);
		assertEquals(cuponDeAdjudicacion.getCostoNoFinanciado(), 105000f, 0);
		assertEquals(cuponDeAdjudicacion.getCostoAdjudicacion(), 108000f, 0);
	}

}
