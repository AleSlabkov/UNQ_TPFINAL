package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

public class Financiamiento100Test {

	IFinanciamiento financiamiento;

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

		financiamiento = new Financiamiento100();
	}

	/**
	 * Testea la obtencion de la alicuota en un plan de 84 coutas y un modelo
	 * con un costo de $350000
	 */
	@Test
	public void getAlicuotaTest() {
		assertEquals(financiamiento.getAlicouta(planDeAhorro), 4166.66, 0.01);
	}

	/**
	 * Testea que el costo no financiado en un plan 100% sea 0
	 */
	@Test
	public void getCostoNoFinanciadoTest() {
		assertEquals(financiamiento.getCostoNoFinanciado(planDeAhorro), 0, 0);
	}

}
