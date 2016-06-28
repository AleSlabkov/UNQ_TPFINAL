package model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SubscripcionTest {

	@Mock
	private Cliente cliente;
	@Mock
	private PlanDeAhorro planDeAhorro;
	private Subscripcion subscripcion;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(cliente.getEmail()).thenReturn("pepe@pepe.com");
		when(planDeAhorro.getCantidadDeCoutas()).thenReturn(84);
		this.subscripcion = new Subscripcion(cliente);
	}

	/**
	 * Crea una subscripcion y testea el acceso a sus colaboradores
	 */
	@Test
	public void crearClienteTest() {
		assertEquals(subscripcion.getCliente(), cliente);
		assertEquals(subscripcion.getProporcionDePago(planDeAhorro), 0f, 0);
		assertTrue(subscripcion.getFechaSubscripcion().isEqual(LocalDate.now()));
		assertFalse(subscripcion.estaAdjudicada());
	}

	/**
	 * Testea registrar dos pagos y comprueba el número consecutivo de cuotas
	 * @throws PlanCompletamentoPagoException
	 */
	@Test
	public void registrarPagosTest() throws PlanCompletamentoPagoException{
		subscripcion.registrarPago(planDeAhorro, LocalDate.of(2016, 5, 10));
		subscripcion.registrarPago(planDeAhorro, LocalDate.of(2016, 6, 10));
		
		assertEquals(subscripcion.getPagos().get(0).getNumeroDeCouta(), 1, 0);
		assertEquals(subscripcion.getPagos().get(1).getNumeroDeCouta(), 2, 0);
	}
	
	/**
	 * Registra pagos y testea la proporción de pago
	 * 
	 * @throws PlanCompletamentoPagoException
	 */
	@Test
	public void registrarPagoVerificandoProporcionTest()
			throws PlanCompletamentoPagoException {
		subscripcion.registrarPago(planDeAhorro, LocalDate.of(2016, 5, 10));
		subscripcion.registrarPago(planDeAhorro, LocalDate.of(2016, 6, 10));

		assertEquals(subscripcion.getProporcionDePago(planDeAhorro), 0.023f,
				0.001);
	}

	/**
	 * Espera una excepción debido a la intención de registrar un pago en un
	 * plan que se encuentra completamente pago
	 * @throws PlanCompletamentoPagoException 
	 */
	@Test(expected = PlanCompletamentoPagoException.class)
	public void registrarPagoEnPlanCompleto() throws PlanCompletamentoPagoException {
		PlanDeAhorro planMock = mock(PlanDeAhorro.class);
		when(planMock.getCantidadDeCoutas()).thenReturn(1);
		
		subscripcion.registrarPago(planMock, LocalDate.of(2016, 5, 10));
		subscripcion.registrarPago(planMock, LocalDate.of(2016, 6, 10));
	}

	/**
	 * Testea adjudicación de la subscripción
	 */
	@Test
	public void registrarAdjudicacionTest() {
		subscripcion.registrarAdjudicacion(LocalDate.of(2016, 6, 1));

		assertTrue(subscripcion.estaAdjudicada());
	}
}
