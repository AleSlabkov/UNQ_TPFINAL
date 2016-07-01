package model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

public class DocumentosFactoryTest {
	
	private GeneradorDeDocumentos factory;
	@Mock
	private PlanDeAhorro planDeAhorro;
	@Mock
	private Concesionario concesionario;
	@Mock
	private Subscripcion subscripcion;
	@Mock
	private Cliente cliente;
	@Mock
	private IGoogleMaps maps;
	@Mock
	private IFleteCotizador fleteCotizador;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		factory = new GeneradorDeDocumentos();
	}

	/**
	 * Testea la creación de un comprobante de pago
	 */
	@Test
	public void crearComprobanteDePagoTest() {
		
		ComprobanteDePago comprobanteDePago = factory.generarComprobanteDePago(1, 3500f, 500f, 120f);
		
		assertNotNull(comprobanteDePago);
		assertEquals(comprobanteDePago.getAlicuota(), 3500f, 0);
		assertEquals(comprobanteDePago.getGastosAdministrativos(), 500f, 0);
		assertEquals(comprobanteDePago.getNumeroDeCuota(), 1, 0);
		assertEquals(comprobanteDePago.getSeguroDeVida(), 120f, 0);
		assertTrue(comprobanteDePago.getFechaDePago().isEqual(LocalDate.now()));
	}
	
	@Test
	public void crearCuponDeAdjudicacion() {

		when(fleteCotizador.getCostoByDistancia(anyFloat())).thenReturn(1000f);
		
		CuponDeAdjudicacion cuponDeAdjudicacion = factory.generarCuponDeAdjudicacion(105000f, fleteCotizador, anyFloat());
		
		assertNotNull(cuponDeAdjudicacion);
		assertEquals(cuponDeAdjudicacion.getCostoDeFlete(), 1000f, 0);
		assertEquals(cuponDeAdjudicacion.getCostoNoFinanciado(), 105000f, 0);
		assertEquals(cuponDeAdjudicacion.getCostoAdjudicacion(), 106000f, 0);
		assertTrue(cuponDeAdjudicacion.getFecha().isEqual(LocalDate.now()));
	}
	

}
