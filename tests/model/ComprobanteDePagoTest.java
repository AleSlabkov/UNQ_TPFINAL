package model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class ComprobanteDePagoTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Testea la creación de un comprobante de pago
	 */
	@Test
	public void crearComprobanteDePago() {
		ComprobanteDePago comprobante = new ComprobanteDePago(1, LocalDate.now(), 3500f, 130f, 80f);
		
		assertEquals(comprobante.getNumeroDeCouta(), 1, 0);
		assertEquals(comprobante.getAlicuota(), 3500f, 0);
		assertEquals(comprobante.getGastosAdministrativos(), 130f, 0);
		assertEquals(comprobante.getSeguroDeVida(), 80f, 0);
		assertTrue(comprobante.getFechaDePago().isEqual(LocalDate.now()));
	}

}
