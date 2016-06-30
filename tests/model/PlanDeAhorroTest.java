package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PlanDeAhorroTest {

	PlanDeAhorro plan;
	@Mock
	Concesionario concesionario;
	@Mock
	Subscripcion subscripcion1;
	@Mock
	Subscripcion subscripcion2;
	@Mock
	Modelo modelo;
	@Mock
	DocumentosFactory comprobanteFactory;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		plan = new PlanDeAhorro(concesionario, 1, modelo, 84, new Sorteo(null),
				new Financiamiento100(), comprobanteFactory);
	}

	/**
	 * Crea un plan de ahorro y testea el acceso a sus colaboradores internos
	 */
	@Test
	public void crearPlanTest() {
		assertEquals(plan.getNumeroGrupo(), 1, 0);
		assertEquals(plan.getModelo().getNombre(), modelo.getNombre());
		assertEquals(plan.getCantidadDeCuotas(), (Integer) 84);
		assertEquals(plan.getConcesionario(), concesionario);
	}

	/**
	 * Testea el agregado de suscripciones al plan de ahorro
	 */
	@Test
	public void agregarSuscripcionesTest() {
		plan.agregarSubscripcion(subscripcion1);
		plan.agregarSubscripcion(subscripcion2);
		
		assertEquals(plan.getSubscripciones().size(), 2);
		assertTrue(plan.getSubscripciones().contains(subscripcion1));
		assertTrue(plan.getSubscripciones().contains(subscripcion2));
	}

	/**
	 * Testea la obtención de la lista de subscripciones sin adjudicacion
	 */
	@Test
	public void getSubscripcionesSinAdjudicacionTest() {
		
		when(subscripcion1.estaAdjudicada()).thenReturn(true);
		
		plan.agregarSubscripcion(subscripcion1);
		plan.agregarSubscripcion(subscripcion2);

		assertEquals(plan.getSubscripcionesSinAdjudicacion().size(), 1);
		assertTrue(plan.getSubscripcionesSinAdjudicacion().contains(subscripcion2));
		assertFalse(plan.getSubscripcionesSinAdjudicacion().contains(subscripcion1));
	}
	
	/**
	 * Testea la obtención de la lista de subscripciones sin adjudicacion en un plan completamente adjudicado
	 */
	@Test
	public void getSubscripcionesSinAdjudicacionEmpty() {
		
		when(subscripcion1.estaAdjudicada()).thenReturn(true);
		when(subscripcion2.estaAdjudicada()).thenReturn(true);
		
		plan.agregarSubscripcion(subscripcion1);
		plan.agregarSubscripcion(subscripcion2);

		assertTrue(plan.getSubscripcionesSinAdjudicacion().isEmpty());
	}
	
	/**
	 * @throws ConcesionarioSinGastosAdministrativosException
	 * @throws PlanCompletamentePagoException 
	 */
	@Test
	public void registrarPagosTest() throws ConcesionarioSinGastosAdministrativosException, PlanCompletamentePagoException {
		
		ComprobanteDePago comprobanteDePago = mock(ComprobanteDePago.class);	
		when(comprobanteFactory.generarComprobanteDePago(any(), any(), any(), any())).thenReturn(comprobanteDePago);
		
		assertEquals(plan.registrarPago(subscripcion1), comprobanteDePago);
	}
	
	/**
	 * Espera una excepción debido a la intención de registrar un pago en un
	 * plan que se encuentra completamente pago
	 * @throws PlanCompletamentePagoException 
	 * @throws ConcesionarioSinGastosAdministrativosException 
	 */
	@Test(expected = PlanCompletamentePagoException.class)
	public void registrarPagoEnPlanCompleto() throws PlanCompletamentePagoException, ConcesionarioSinGastosAdministrativosException {
		when(subscripcion1.completoPago(plan)).thenReturn(true);
		
		plan.registrarPago(subscripcion1);
	}
}
