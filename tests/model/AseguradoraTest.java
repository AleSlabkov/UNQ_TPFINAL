package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

public class AseguradoraTest {

	@Mock
	PlanDeAhorro planDeAhorro;
	@Mock
	Modelo modelo;
	@Mock
	Subscripcion subscripcion;
	@Mock
	Cliente cliente;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(planDeAhorro.getModelo()).thenReturn(modelo);
		when(modelo.getPrecio()).thenReturn(350000f);
		when(subscripcion.getCliente()).thenReturn(cliente);
	}

	/**
	 * Testea el cálculo del valor del seguro con un cliente menor de 50 años
	 */
	@Test
	public void clienteMenorDe50() {
		Aseguradora seguro = new Aseguradora();
		when(cliente.getEdad()).thenReturn(30);

		assertEquals(seguro.calcularValorDelSeguro(planDeAhorro, subscripcion),
				17550f, 0);
	}

	/**
	 * Testea el cálculo del valor del seguro con un cliente mayor de 50 años
	 */
	@Test
	public void clienteMayotDe50() {
		Aseguradora seguro = new Aseguradora();
		when(cliente.getEdad()).thenReturn(65);

		assertEquals(seguro.calcularValorDelSeguro(planDeAhorro, subscripcion),
				17560f, 0);
	}

}
