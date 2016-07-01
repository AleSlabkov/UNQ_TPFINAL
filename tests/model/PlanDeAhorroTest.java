package model;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
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
	IAdjudicacion adjudicacion;
	@Mock
	IFinanciamiento financiamiento;
	@Mock
	GeneradorDeDocumentos generadorDeDocumentos;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		plan = new PlanDeAhorro(concesionario, 1, modelo, 84, adjudicacion,
				financiamiento, generadorDeDocumentos);
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
		assertEquals(plan.getTipoAdjudicacion(), adjudicacion);
		assertEquals(plan.getFinanciamiento(), financiamiento);
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
	 * Testea la obtención de la lista de subscripciones sin adjudicacion usando
	 * un plan con dos suscripciones, una adjudicada y otra no
	 */
	@Test
	public void getSubscripcionesSinAdjudicacionTest() {

		when(subscripcion1.estaAdjudicada()).thenReturn(true);

		plan.agregarSubscripcion(subscripcion1);
		plan.agregarSubscripcion(subscripcion2);

		assertEquals(plan.getSubscripcionesSinAdjudicacion().size(), 1);
		assertTrue(plan.getSubscripcionesSinAdjudicacion().contains(
				subscripcion2));
		assertFalse(plan.getSubscripcionesSinAdjudicacion().contains(
				subscripcion1));
	}

	/**
	 * Testea la obtención de la lista de subscripciones sin adjudicacion en un
	 * plan completamente adjudicado (con dos susccripciones adjudicadas)
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
	 * Testea la registracíon de pagos en un plan de ahorro. Recibe con una
	 * suscripción y atraves del generador de Documentos registra un Comprobante
	 * de Pago en la subscripción
	 * 
	 * @throws ConcesionarioSinGastosAdministrativosException
	 * @throws PlanCompletamentePagoException
	 */
	@Test
	public void registrarPagosTest() throws PlanCompletamentePagoException,
			ConcesionarioSinGastosAdministrativosException {

		ComprobanteDePago comprobanteDePago = mock(ComprobanteDePago.class);
		when(
				generadorDeDocumentos.generarComprobanteDePago(anyInt(),
						anyFloat(), anyFloat(), anyFloat())).thenReturn(
				comprobanteDePago);

		when(subscripcion1.getCliente()).thenReturn(mock(Cliente.class));
		when(concesionario.getAseguradora())
				.thenReturn(mock(Aseguradora.class));

		plan.registrarPago(subscripcion1);

		verify(subscripcion1).registrarPago(comprobanteDePago);
	}

	/**
	 * Espera una excepción debido a la intención de registrar un pago en un
	 * subscripción que se encuentra completamente paga
	 * 
	 * @throws PlanCompletamentePagoException
	 * @throws ConcesionarioSinGastosAdministrativosException
	 */
	@Test(expected = PlanCompletamentePagoException.class)
	public void registrarPagoEnPlanCompleto()
			throws PlanCompletamentePagoException,
			ConcesionarioSinGastosAdministrativosException {
		when(subscripcion1.completoPago(plan)).thenReturn(true);

		plan.registrarPago(subscripcion1);
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = ConcesionarioSinGastosAdministrativosException.class)
	public void registrarPagoConConsionarioSinGastosAdministrativos()
			throws PlanCompletamentePagoException,
			ConcesionarioSinGastosAdministrativosException {
		when(concesionario.getGastosAdministrativos()).thenThrow(ConcesionarioSinGastosAdministrativosException.class);

		plan.registrarPago(subscripcion1);
	}
	
	/**
	 * Testea la obtención de la alicuota según el tipo de financiamiento
	 */
	@Test
	public void getAlicuota()
	{
		when(financiamiento.getAlicouta(plan)).thenReturn(3500f);	
		assertEquals(plan.getAlicuota(), 3500f, 0);
	}
	
	@Test
	public void adjudicarTest(){
		
	}
	
	/**
	 * @throws SinAdjudicableException
	 * @throws ConcesionarioSinGastosAdministrativosException
	 */
	@Test (expected = SinAdjudicableException.class)
	public void adjudicarTestSinAdjudicables() throws SinAdjudicableException, ConcesionarioSinGastosAdministrativosException{
		when(subscripcion1.estaAdjudicada()).thenReturn(true);
		when(subscripcion2.estaAdjudicada()).thenReturn(true);
		
		plan.agregarSubscripcion(subscripcion1);
		plan.agregarSubscripcion(subscripcion2);
		
		plan.adjudicar();
	}
	
	/**
	 * @throws SinAdjudicableException
	 * @throws ConcesionarioSinGastosAdministrativosException
	 */
	@SuppressWarnings("unchecked")
	@Test (expected = ConcesionarioSinGastosAdministrativosException.class)
	public void adjudicarTestConcesionarioSinGastosAdministrativos() throws SinAdjudicableException, ConcesionarioSinGastosAdministrativosException{
		when(subscripcion1.estaAdjudicada()).thenReturn(false);
		when(financiamiento.getCostoNoFinanciado(plan)).thenReturn(105000f);
		when(concesionario.getCostoDeFleteByModelo(modelo)).thenReturn(1500f);
		when(concesionario.getGastosAdministrativos()).thenThrow(ConcesionarioSinGastosAdministrativosException.class);
			
		plan.agregarSubscripcion(subscripcion1);
		
		plan.adjudicar();
	}
}
