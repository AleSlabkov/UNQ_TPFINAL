package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Random;

public class SorteoTest {

	private Sorteo sorteo;
	private Random rndMock;
	
	@Before
	public void setUp() throws Exception {
		
		this.rndMock = mock(Random.class);
		
		//when(mockMensaje.getCreador()).thenReturn(mockUser);
		//when(mockMensaje.getDescripcion()).thenReturn("Este es el mensaje");
		
		sorteo = new Sorteo(rndMock);
	}

	@Test
	public void adjudicar() {
		
		PlanDeAhorro p = mock(PlanDeAhorro.class);
		
		ArrayList<Subscripcion> arr = new ArrayList<Subscripcion>();
		
		arr.add(new Subscripcion(mock(Cliente.class)));
		arr.add(new Subscripcion(mock(Cliente.class)));
		arr.add(new Subscripcion(mock(Cliente.class)));
		
		when(p.getSubscripcionesSinAdjudicacion()).thenReturn(arr);
		
		assertNotNull(sorteo.adjudicar(p));
		
		verify(p).getSubscripcionesSinAdjudicacion();
		verify(rndMock).nextInt(3);
		
	}

}
