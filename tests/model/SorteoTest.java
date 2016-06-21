package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SorteoTest {

	private Sorteo sorteo;
	private Random rndMock;
	
	@Before
	public void setUp() throws Exception {
		
		this.rndMock = mock(Random.class);

		sorteo = new Sorteo(rndMock);
	}

	@Test
	public void adjudicar() {
		
		PlanDeAhorro p = mock(PlanDeAhorro.class);
		
		Subscripcion subscripcion = mock(Subscripcion.class);
		
		List<Subscripcion> subscripcionesSinAdjudicacion = mock(List.class);
		when(subscripcionesSinAdjudicacion.get(1)).thenReturn(subscripcion);
		when(subscripcionesSinAdjudicacion.size()).thenReturn(3);
		
		
		
		when(p.getSubscripcionesSinAdjudicacion()).thenReturn(subscripcionesSinAdjudicacion);
		when(rndMock.nextInt(3)).thenReturn(1);
		
		assertEquals(sorteo.adjudicar(p), subscripcion);
		
		verify(subscripcion).registrarAdjudicacion(any());
		verify(p).getSubscripcionesSinAdjudicacion();
		verify(rndMock).nextInt(3);
		
	}

}
