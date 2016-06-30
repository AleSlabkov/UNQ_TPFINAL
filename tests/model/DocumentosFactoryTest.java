package model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
		when(planDeAhorro.getConcesionario()).thenReturn(concesionario);
		when(subscripcion.getCliente()).thenReturn(cliente);
		assertNotNull(factory.generarComprobanteDePago(planDeAhorro, subscripcion));
	}
	
	/**
	 * @throws ConcesionarioSinGastosAdministrativosException
	 */
	@SuppressWarnings("unchecked")
	@Test (expected = ConcesionarioSinGastosAdministrativosException.class)
	public void crearComprobanteDePagoExceptionTest() throws ConcesionarioSinGastosAdministrativosException {
		when(planDeAhorro.getConcesionario()).thenReturn(concesionario);
		when(planDeAhorro.getConcesionario().getGastosAdministrativos()).thenThrow(ConcesionarioSinGastosAdministrativosException.class);
		
		factory.generarComprobanteDePago(planDeAhorro, subscripcion);
	}

}
