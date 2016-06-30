package model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

public class DocumentosFactoryTest {
	
	private DocumentosFactory factory;
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
		factory = new DocumentosFactory();
	}

	/**
	 * @throws ConcesionarioSinGastosAdministrativosException
	 */
	@Test
	public void crearComprobanteDePagoTest() throws ConcesionarioSinGastosAdministrativosException {
		
		ComprobanteDePago cp = factory.generarComprobanteDePago(1, 12f, 12f, 12f);
		
		assertNotNull(cp);
		assertEquals(12f, cp.getAlicuota(), 0);
		assertEquals(12f, cp.getGastosAdministrativos(), 0);
		assertEquals(1, cp.getNumeroDeCuota(), 0);
		assertEquals(12f, cp.getSeguroDeVida(), 0);
		assertTrue(cp.getFechaDePago().isAfter(LocalDate.now()));
	}
	
	/**
	 * @throws ConcesionarioSinGastosAdministrativosException
	 */
	@SuppressWarnings("unchecked")
	@Test (expected = ConcesionarioSinGastosAdministrativosException.class)
	public void crearComprobanteDePagoExceptionTest() throws ConcesionarioSinGastosAdministrativosException {
		when(planDeAhorro.getConcesionario()).thenReturn(concesionario);
		when(planDeAhorro.getConcesionario().getGastosAdministrativos()).thenThrow(ConcesionarioSinGastosAdministrativosException.class);
		
		factory.generarComprobanteDePago(1, 12f, 12f,12f);
	}
	
	/**
	 * @throws ConcesionarioSinGastosAdministrativosException
	 */
	@Test
	public void crearCuponDeAdjudicacion() {

		when(fleteCotizador.getCostoByDistancia(10f)).thenReturn(1000f);
		
		CuponDeAdjudicacion ca = factory.generarCuponDeAdjudicacion(3f, fleteCotizador, 10f);
		
		assertNotNull(ca);
		assertEquals(1000f, ca.getCostoDeFlete(), 0);
		assertEquals(1003f, ca.getCostoAdjudicacion(), 0);
	}
	

}
