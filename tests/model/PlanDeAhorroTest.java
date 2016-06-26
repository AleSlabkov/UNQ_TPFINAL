package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class PlanDeAhorroTest {

	PlanDeAhorro plan;
	@Mock
	Subscripcion subscripcion1;
	@Mock
	Subscripcion subscripcion2;
	@Mock
	Modelo modelo;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		plan = new PlanDeAhorro(1, modelo, 84, new Sorteo(null),
				new Financiamiento100());
	}

	/**
	 * Crea un plan de ahorro y testea el acceso a sus colaboradores internos
	 */
	@Test
	public void crearPlanTest() {
		assertEquals(plan.getModelo().getNombre(), modelo.getNombre());
		assertEquals(plan.getCantidadDeCoutas(), (Integer) 84);
	}

	/**
	 * Test el agregado de suscripciones al plan de ahorro
	 */
	@Test
	public void agregarSuscripcionesTest() {
		plan.agregarSubscripcion(subscripcion1);
		plan.agregarSubscripcion(subscripcion2);
		

		assertEquals(plan.getSubscripciones().size(), 2);
	}

	/**
	 * Testea la obtención de subscripciones sin adjudicacion
	 */
	@Test
	public void getSubscripcionesSinAdjudicacionTest() {
		
		when(subscripcion1.estaAdjudicada()).thenReturn(true);
		
		plan.agregarSubscripcion(subscripcion1);
		plan.agregarSubscripcion(subscripcion2);

		assertEquals(plan.getSubscripcionesSinAdjudicacion().size(), 1);
	}
	
	/**
	 * Testea la obtención de subscripciones sin adjudicacion en un plan completamente adjudicado
	 */
	@Test
	public void getSubscripcionesSinAdjudicacionFail() {
		
		when(subscripcion1.estaAdjudicada()).thenReturn(true);
		when(subscripcion2.estaAdjudicada()).thenReturn(true);
		
		plan.agregarSubscripcion(subscripcion1);
		plan.agregarSubscripcion(subscripcion2);

		assertTrue(plan.getSubscripcionesSinAdjudicacion().isEmpty());
	}
}
