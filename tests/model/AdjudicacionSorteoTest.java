package model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class AdjudicacionSorteoTest {

	private Sorteo sorteo;
	private Random rndMock;
	
	@Before
	public void setUp() throws Exception {
		
		this.rndMock = mock(Random.class);

		sorteo = new Sorteo(rndMock);
	}

	/**
	 * @throws SinAdjudicableException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void adjudicar() throws SinAdjudicableException {
		
		PlanDeAhorro plan = mock(PlanDeAhorro.class);
		
		Subscripcion subscripcion = mock(Subscripcion.class);
		
		List<Subscripcion> subscripcionesSinAdjudicacion = mock(List.class);
		when(subscripcionesSinAdjudicacion.get(1)).thenReturn(subscripcion);
		when(subscripcionesSinAdjudicacion.size()).thenReturn(3);

		when(plan.getSubscripcionesSinAdjudicacion()).thenReturn(subscripcionesSinAdjudicacion);
		when(rndMock.nextInt(3)).thenReturn(1);
		
		assertEquals(sorteo.adjudicar(plan), subscripcion);

		verify(rndMock).nextInt(3);
		
	}
	
	/**
	 * @throws SinAdjudicableException
	 */
	@Test(expected = SinAdjudicableException.class)
	public void adjudicarSinAdjudicacionesDisponibles() throws SinAdjudicableException {
	
		PlanDeAhorro plan = mock(PlanDeAhorro.class);
		
		when(plan.getSubscripcionesSinAdjudicacion()).thenReturn(new ArrayList<Subscripcion>());
		
		sorteo.adjudicar(plan);
		
	}
	


}
