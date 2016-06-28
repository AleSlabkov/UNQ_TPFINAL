package model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class Financiamiento7030Test {

	@Mock
	PlanDeAhorro planDeAhorro;
	@Mock
	Modelo modelo;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(planDeAhorro.getCantidadDeCuotas()).thenReturn(84);
		when(planDeAhorro.getModelo()).thenReturn(modelo);
		when(modelo.getPrecio()).thenReturn(350000f);
	}

	/**
	 * Testea la obtencion de la alicuota en un plan de 84 coutas y un modelo
	 * con un costo de $350000
	 */
	@Test
	public void getAlicuotaTest() {
		Financiamiento7030 financiamiento = new Financiamiento7030();

		assertEquals(financiamiento.getAlicouta(planDeAhorro), 2916.66, 0.01);
	}
}
