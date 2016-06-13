package model;

import static org.junit.Assert.*;
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
		when(cliente.getEmail()).thenReturn("pepe@truno.com");
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
		assertFalse(subscripcion.estaAdjudicada());
	}

	/**
	 * Registra pagos y testea la proporcio de pago
	 */
	@Test
	public void registrarPagoTest() {
		subscripcion.registrarPago("Junio 2016");
		subscripcion.registrarPago("Julio 2016");

		assertEquals(subscripcion.getProporcionDePago(planDeAhorro), 0.023f,
				0.001);
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
