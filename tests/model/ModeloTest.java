package model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ModeloTest {

	private Modelo modelo;
	@Mock
	private Planta planta;

	@Before
	public void setUp() throws Exception {
		modelo = new Modelo("Ford Focus", 5, LocalDate.of(2015, 8, 15), true, 490000);
		MockitoAnnotations.initMocks(this);
		when(planta.getNombre()).thenReturn("Pacheco");
	}

	/**
	 * Testea la creación de un modelo
	 */
	@Test
	public void crearModeloTest() {
		assertEquals(modelo.getNombre(), "Ford Focus");
		assertEquals(modelo.getCantidadPuertas(), (Integer) 5);
		assertEquals(modelo.getFechaLanzamiento(), LocalDate.of(2015, 8, 15));
		assertTrue(modelo.getFull());
		assertEquals(modelo.getPrecio() ,490000f, 0);
	}
	
	/**
	 * Testea la modificación de precio de un modelo
	 */
	@Test
	public void modificarValorModelo(){
		modelo.setPrecio(385000f);
		assertEquals(modelo.getPrecio(), 385000f, 0);
	}

	/**
	 * Testea el agregado de una planta de producción a un modelo
	 */
	@Test
	public void agregarPlantaDeProduccionTest() {
		modelo.agregarPlantaDeProduccion(planta);
		
		assertEquals(modelo.getPlantasDeProduccion().size(), 1);
		assertEquals(modelo.getPlantasDeProduccion().get(0), planta);
	}

}
