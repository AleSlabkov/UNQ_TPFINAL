package model;

import java.util.List;
import java.util.Random;

public class Sorteo implements IAdjudicacion {

	private Random random;

	public Sorteo(Random random) {
		this.random = random;
	}

	@Override
	public Subscripcion adjudicar(PlanDeAhorro plan)
			throws SinAdjudicableException {

		// obtengo subscripciones sin adjudicar
		List<Subscripcion> subscripciones = plan
				.getSubscripcionesSinAdjudicacion();

		if (subscripciones.isEmpty())
			throw new SinAdjudicableException();

		// uso el random
		Integer number = random.nextInt(subscripciones.size());

		// obtengo la subscripcion a adjudicar
		Subscripcion subscripcion = subscripciones.get(number);

		return subscripcion;
	}
}
