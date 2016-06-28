package model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AdjudicacionMayorCoberturaTest {

	private MayorCobertura adjudicacion;
	@Mock
	private PlanDeAhorro planDeAhorro;

	@Before
	public void setUp() throws Exception {
		this.adjudicacion = new MayorCobertura();
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * @throws SinAdjudicableException
	 */
	@Test
	public void adjudicarUnicoPosible() throws SinAdjudicableException {
		Subscripcion subscripcion1 = mock(Subscripcion.class);
		when(subscripcion1.getProporcionDePago(planDeAhorro)).thenReturn(0.1f);
		Subscripcion subscripcion2 = mock(Subscripcion.class);
		when(subscripcion2.getProporcionDePago(planDeAhorro)).thenReturn(0.2f);

		List<Subscripcion> subscripcionesSinAdjudicacion = new ArrayList<Subscripcion>();
		subscripcionesSinAdjudicacion.add(subscripcion1);
		subscripcionesSinAdjudicacion.add(subscripcion2);
		
		when(planDeAhorro.getSubscripcionesSinAdjudicacion()).thenReturn(subscripcionesSinAdjudicacion);
		
		assertEquals(adjudicacion.adjudicar(planDeAhorro), subscripcion2);
	}
	
	/**
	 * @throws SinAdjudicableException
	 */
	@Test
	public void adjudicarPorAntiguedadCliente() throws SinAdjudicableException {
		Subscripcion subscripcion1 = mock(Subscripcion.class);
		Cliente cliente1 = mock(Cliente.class);
		when(subscripcion1.getProporcionDePago(planDeAhorro)).thenReturn(0.2f);
		when(subscripcion1.getCliente()).thenReturn(cliente1);
		when(cliente1.getFechaDeIngreso()).thenReturn(LocalDate.of(2011,3,15));
		
		Subscripcion subscripcion2 = mock(Subscripcion.class);
		Cliente cliente2 = mock(Cliente.class);
		when(subscripcion2.getProporcionDePago(planDeAhorro)).thenReturn(0.2f);
		when(subscripcion2.getCliente()).thenReturn(cliente2);
		when(cliente2.getFechaDeIngreso()).thenReturn(LocalDate.of(2013,8,15));

		List<Subscripcion> subscripcionesSinAdjudicacion = new ArrayList<Subscripcion>();
		subscripcionesSinAdjudicacion.add(subscripcion1);
		subscripcionesSinAdjudicacion.add(subscripcion2);
		
		when(planDeAhorro.getSubscripcionesSinAdjudicacion()).thenReturn(subscripcionesSinAdjudicacion);
		
		assertEquals(adjudicacion.adjudicar(planDeAhorro), subscripcion1);
	}
	
	/**
	 * @throws SinAdjudicableException
	 */
	@Test
	public void adjudicarPorAntiguedadDeSubscripcion() throws SinAdjudicableException {
		
		Subscripcion subscripcion1 = mock(Subscripcion.class);
		Cliente cliente1 = mock(Cliente.class);
		when(subscripcion1.getProporcionDePago(planDeAhorro)).thenReturn(0.2f);
		when(subscripcion1.getCliente()).thenReturn(cliente1);
		when(subscripcion1.getFechaSubscripcion()).thenReturn(LocalDate.of(2015,6,10));
		when(cliente1.getFechaDeIngreso()).thenReturn(LocalDate.of(2013,8,15));
		
		Subscripcion subscripcion2 = mock(Subscripcion.class);
		Cliente cliente2 = mock(Cliente.class);
		when(subscripcion2.getProporcionDePago(planDeAhorro)).thenReturn(0.2f);
		when(subscripcion2.getCliente()).thenReturn(cliente2);
		when(subscripcion2.getFechaSubscripcion()).thenReturn(LocalDate.of(2015,4,14));
		when(cliente2.getFechaDeIngreso()).thenReturn(LocalDate.of(2013,8,15));

		List<Subscripcion> subscripcionesSinAdjudicacion = new ArrayList<Subscripcion>();
		subscripcionesSinAdjudicacion.add(subscripcion1);
		subscripcionesSinAdjudicacion.add(subscripcion2);
		
		when(planDeAhorro.getSubscripcionesSinAdjudicacion()).thenReturn(subscripcionesSinAdjudicacion);
		
		assertEquals(adjudicacion.adjudicar(planDeAhorro), subscripcion2);
	}
	
	/**
	 * @throws SinAdjudicableException
	 */
	@Test(expected = SinAdjudicableException.class)
	public void adjudicarSinAdjudicacionesDisponibles() throws SinAdjudicableException {
	
		PlanDeAhorro plan = mock(PlanDeAhorro.class);
		
		when(plan.getSubscripcionesSinAdjudicacion()).thenReturn(new ArrayList<Subscripcion>());
		
		adjudicacion.adjudicar(plan);
	}

}
