package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class FabricaTest {

	private Fabrica fabrica;

	@Before
	public void setUp() throws Exception {
		fabrica = new Fabrica("Ford");
	}

	/**
	 * Crea una fabrica y testea el acceso a su nombre
	 */
	@Test
	public void crearFabrica() {
		assertEquals(fabrica.getNombre(), "Ford");
	}

	/**
	 * Agrega un planta de producción a la fabrica y testea que haya sido agregada
	 */
	@Test
	public void agregarPlantaTest() {
		fabrica.agregarPlanta(mock(Planta.class));

		assertEquals(fabrica.getPlantas().size(), 1);
	}
	
	/**
	 * Testea la obtención de plantas que producen un modelo determinado
	 * La planta1 produce Ford Ka y Ford Fiesta 
	 * La planta2 produce Ford Fiesta y Ford Focus
	 */
	@Test
	public void plantasQueProducenModeloTest()
	{
		Modelo fordKa = mock(Modelo.class);
		when(fordKa.getNombre()).thenReturn("Ford Ka");
		Modelo fordFiesta = mock(Modelo.class);
		when(fordFiesta.getNombre()).thenReturn("Ford Fiesta");
		Modelo fordFocus = mock(Modelo.class);
		when(fordFocus.getNombre()).thenReturn("Ford Focus");
		
		List<Modelo> modelosPlanta1 = new ArrayList<Modelo>(Arrays.asList(fordKa, fordFiesta));
		List<Modelo> modelosPlanta2 = new ArrayList<Modelo>(Arrays.asList(fordFiesta, fordFocus));
		
		Planta planta1 = mock(Planta.class);
		when(planta1.getModelosProducidos()).thenReturn(modelosPlanta1);
		Planta planta2 = mock(Planta.class);
		when(planta2.getModelosProducidos()).thenReturn(modelosPlanta2);
		
		fabrica.agregarPlanta(planta1);
		fabrica.agregarPlanta(planta2);
		
		assertTrue(fabrica.getPlantasByModelo(fordKa).contains(planta1));
		assertFalse(fabrica.getPlantasByModelo(fordKa).contains(planta2));
		
		assertTrue(fabrica.getPlantasByModelo(fordFiesta).contains(planta1));
		assertTrue(fabrica.getPlantasByModelo(fordFiesta).contains(planta2));
		
		assertFalse(fabrica.getPlantasByModelo(fordFocus).contains(planta1));
		assertTrue(fabrica.getPlantasByModelo(fordFocus).contains(planta2));
	}
	
}
