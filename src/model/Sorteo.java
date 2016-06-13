package model;

import java.util.concurrent.ThreadLocalRandom;

public class Sorteo implements IAdjudicacion {

	@Override
	public Subscripcion adjudicar(PlanDeAhorro plan) {
		return plan.getSubscripciones().get(ThreadLocalRandom.current().nextInt(plan.getSubscripciones().size()));
	}

}
