package model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ConcesionarioTest {

	Concesionario concesionario;

	@Before
	public void setUp() throws Exception {

		concesionario = new Concesionario("Guido Guidi", "Zapiola 232, Quilmes, Buenos Aires, Argentina", null);
		
		registrarClientes();
		registrarPlanes();

	}

	private void registrarPlanes() {
		//GENERO 100 PLANES CON 10 SUBSCRIPCIONES CADA UNO
		for(int i = 0; i < 100; i++){
			PlanAhorro plan = new PlanAhorro(i + 1, new Modelo(), null, null);
			
			for(int cliente = 0; cliente < 10; cliente++)
				plan.agregarSubscripcion(concesionario.getClientes().get(cliente));
			
			concesionario.agregarPlanAhorro(plan);

		}
	}

	private void registrarClientes() {
		//GENERO 1000 CLIENTES AL CONCECIONARIO
		for(int i = 0; i < 1000; i++){
			
			concesionario.agregarCliente(new Cliente(String.format("Nombre_%1$s", i), 
					String.format("Apellido_%d", i),
					String.format("Nombre_%d", i),
					String.format("dirrecion_%d", i),
					String.format("email%d@gmail.com", i),
					LocalDate.of(1980, 1, 1).plusDays(i), 
					LocalDate.of(2015, 1, 1).plusDays(i)));
			
		}
	}

	@Test
	public void getPlanesConMayorCantidadSubscriptoresTop10OrderByCantidadDesc_TEST() {

		
		//TOMO PLAN 10 Y 20 PARA AGREGARLE MAS SUBSCRIPTORES
		
		concesionario.getPlanAhorroByNumeroGrupo(10).get().agregarSubscripcion(concesionario.getClientes().get(20));
		concesionario.getPlanAhorroByNumeroGrupo(10).get().agregarSubscripcion(concesionario.getClientes().get(21));
		concesionario.getPlanAhorroByNumeroGrupo(10).get().agregarSubscripcion(concesionario.getClientes().get(22));
		
		concesionario.getPlanAhorroByNumeroGrupo(20).get().agregarSubscripcion(concesionario.getClientes().get(20));
		concesionario.getPlanAhorroByNumeroGrupo(20).get().agregarSubscripcion(concesionario.getClientes().get(21));
		
		
		List<PlanAhorro> planes = concesionario.getPlanesConMayorCantidadSubscriptoresTop10OrderByCantidadDesc();

		assertEquals(planes.size(), 10);
		assertEquals(planes.get(0).getSubscripciones().size(), 13);
		assertEquals(planes.get(1).getSubscripciones().size(), 12);
		assertEquals(planes.get(9).getSubscripciones().size(), 10);

	}

}
