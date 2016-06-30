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
	@Mock
	private ComprobanteDePago comprobanteDePago;
	private Subscripcion subscripcion;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(cliente.getEmail()).thenReturn("pepe@pepe.com");
		when(planDeAhorro.getCantidadDeCuotas()).thenReturn(84);
		this.subscripcion = new Subscripcion(cliente);
	}

	/**
	 * Crea una subscripcion y testea el acceso a sus colaboradores
	 */
	@Test
	public void crearSubscripcionTest() {
		assertEquals(subscripcion.getCliente(), cliente);
		assertEquals(subscripcion.getProporcionDePago(planDeAhorro), 0f, 0);
		assertTrue(subscripcion.getFechaSubscripcion().isEqual(LocalDate.now()));
		assertFalse(subscripcion.estaAdjudicada());
	}

	/**
	 * Testea registrar dos pagos y comprueba el número consecutivo de cuotas
	 * @throws PlanCompletamentePagoException
	 */
	@Test
	public void registrarPagosTest() {
		
		subscripcion.registrarPago(comprobanteDePago);
		
		assertTrue(subscripcion.getPagos().contains(comprobanteDePago));
	}
	
	/**
	 * Registra pagos y testea la proporción de pago
	 * 
	 * @throws PlanCompletamentePagoException
	 */
	@Test
	public void registrarPagoVerificandoProporcionTest() {
		subscripcion.registrarPago(comprobanteDePago);
		subscripcion.registrarPago(comprobanteDePago);

		assertEquals(subscripcion.getProporcionDePago(planDeAhorro), 0.023f,
				0.001);
	}

	/**
	 * Testea adjudicación de la subscripción
	 */
	@Test
	public void registrarAdjudicacionTest() {
		CuponDeAdjudicacion cuponDeAdjudicacion = mock(CuponDeAdjudicacion.class);
		subscripcion.registrarAdjudicacion(cuponDeAdjudicacion);

		assertTrue(subscripcion.estaAdjudicada());
	}
}
