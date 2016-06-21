package model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class PlanDeAhorroTest {

	PlanDeAhorro plan;
	Cliente clienteAle;
	Cliente clienteMartin;
	Subscripcion subscripcion1;
	Subscripcion subscripcion2;
	Modelo modelo;

	@Before
	public void setUp() throws Exception {

		clienteAle = new Cliente("Alejandro", "Slabkov", "3100000", "Hudson",
				"ale@gmail.com", LocalDate.of(1985, 1, 1), LocalDate.of(2015,
						12, 1));
		clienteMartin = new Cliente("Martin", "Serna", "3100000", "Wilde",
				"martin@gmail.com", LocalDate.of(1984, 4, 14), LocalDate.of(
						2016, 1, 25));

		subscripcion1 = new Subscripcion(clienteAle);
		subscripcion2 = new Subscripcion(clienteMartin);

		modelo = new Modelo("Gol Trend", 5, LocalDate.of(2015, 1, 1), false,
				120000);
	}

	/**
	 * Crea un plan de ahorro y testea el acceso a sus colaboradores internos
	 */
	@Test
	public void crearPlanTest() {
		plan = new PlanDeAhorro(1, modelo, 84, new Sorteo(null),
				new Financiamiento100());
		
		assertEquals(plan.getModelo().getNombre(), "Gol Trend");
		assertEquals(plan.getCantidadDeCoutas(), (Integer)84);
	}

	/**
	 * Testea el funcionamiento de la adjudicación de un plan con financiamiento
	 * del 100% por sorteo
	 */
	@Test
	public void adjudicarPlan100PorcientoPorSorteo() {

		plan = new PlanDeAhorro(1, modelo, 84, new Sorteo(null),
				new Financiamiento100());
		plan.agregarSubscripcion(clienteAle);
		plan.agregarSubscripcion(clienteMartin);

		assertNotNull(plan.adjudicar());
	}
	/**
	 * Testea las subscripciones sin adjudicacion
	 */
	@Test
	public void getSubscripcionesSinAdjudicacionTest(){
		
		plan = new PlanDeAhorro(1, modelo, 84, new Sorteo(null),
				new Financiamiento100());
		plan.agregarSubscripcion(clienteAle);
		plan.agregarSubscripcion(clienteMartin);

		assertEquals(plan.getSubscripcionesSinAdjudicacion().size(), 2);
	}

}
