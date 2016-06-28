package model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class ClienteTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Crear un cliente y verifica el acceso a su información
	 */
	@Test
	public void test() {
		Cliente cliente = new Cliente("Alejandro", "Slabkov", "3100000",
				"Hudson", "ale@gmail.com", LocalDate.of(1985, 1, 1),
				LocalDate.of(2015, 12, 1));

		assertEquals(cliente.getNombre(), "Alejandro");
		assertEquals(cliente.getApellido(), "Slabkov");
		assertEquals(cliente.getDni(), "3100000");
		assertEquals(cliente.getDireccion(), "Hudson");
		assertEquals(cliente.getEmail(), "ale@gmail.com");
		assertEquals(cliente.getFechaDeNacimiento(), LocalDate.of(1985, 1, 1));
		assertEquals(cliente.getFechaDeIngreso(), LocalDate.of(2015, 12, 1));
		assertEquals(cliente.getEdad(), 31, 0);
	}

}
