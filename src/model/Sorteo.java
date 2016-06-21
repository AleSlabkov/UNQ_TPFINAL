package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Sorteo implements IAdjudicacion {

	private Random rnd;
	
	public Sorteo(Random rnd) {
		this.rnd = rnd;
	}

	@Override
	public Subscripcion adjudicar(PlanDeAhorro plan) {
		
		//obtengo subscripciones sin adjudicar
		List<Subscripcion> subs = plan.getSubscripcionesSinAdjudicacion();
		
		//uso el random
		Integer number = rnd.nextInt(subs.size());
		
		//obtengo la subscripcion a adjudicar
		Subscripcion su = subs.get(number);
		
		//registro adjudicacion
		su.registrarAdjudicacion(LocalDate.now());
		
		return su;
		
	}

}
