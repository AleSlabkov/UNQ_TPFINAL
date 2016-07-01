package model;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MayorCobertura implements IAdjudicacion {

	@Override
	public Subscripcion adjudicar(PlanDeAhorro plan)
			throws SinAdjudicableException {

		// obtengo subscripciones sin adjudicar
		List<Subscripcion> subscripcionesSinAdjudicacion = plan.getSubscripcionesSinAdjudicacion();

		if (subscripcionesSinAdjudicacion.isEmpty())
			throw new SinAdjudicableException();

		List<Subscripcion> adjudicables = getAdjudicablesPorMayorCobertura(plan, subscripcionesSinAdjudicacion);

		Subscripcion subscripcion;

		if (adjudicables.size() == 1) 
			subscripcion = adjudicables.get(0);
		else 
		{
			adjudicables = getAdjudicablesPorFechaDeIngreso(plan, adjudicables);
			
			if (adjudicables.size() == 1) 
				subscripcion = adjudicables.get(0);
			
			else 
			{
				adjudicables = getAdjudicablesPorFechaDeSubscripcion(plan, adjudicables);
				subscripcion = adjudicables.get(0);
			}
		}

		return subscripcion;
	}

	/** 
	 * @param plan
	 * @param subscripciones
	 * @return subscripciones adjudicables por mayor cobertura
	 */
	private List<Subscripcion> getAdjudicablesPorMayorCobertura(
			PlanDeAhorro plan, List<Subscripcion> subscripcionesSinAdjudicacion) {
		
		float mayorCobertura = subscripcionesSinAdjudicacion.stream()
				.map(s -> s.getProporcionDePago(plan))
				.sorted(Comparator.reverseOrder()).collect(Collectors.toList())
				.get(0);

		return subscripcionesSinAdjudicacion.stream()
				.filter(s -> s.getProporcionDePago(plan) == mayorCobertura)
				.collect(Collectors.toList());
	}
	
	/**
	 * @param plan
	 * @param subscripcionesSinAdjudicacion
	 * @return subscripciones adjudicables por fecha de ingreso asc
	 */
	private List<Subscripcion> getAdjudicablesPorFechaDeIngreso(
			PlanDeAhorro plan, List<Subscripcion> subscripcionesSinAdjudicacion) {
		LocalDate primerIngreso = subscripcionesSinAdjudicacion.stream()
				.map(s -> s.getCliente().getFechaDeIngreso())
				.sorted().collect(Collectors.toList())
				.get(0);
		
		return subscripcionesSinAdjudicacion.stream()
				.filter(s -> s.getCliente().getFechaDeIngreso().isEqual(primerIngreso))
				.collect(Collectors.toList());
	}
	
	/**
	 * @param plan
	 * @param subscripcionesSinAdjudicacion
	 * @return subscripciones adjudicables por fecha de subscripcion asc
	 */
	private List<Subscripcion> getAdjudicablesPorFechaDeSubscripcion(
			PlanDeAhorro plan, List<Subscripcion> subscripcionesSinAdjudicacion) {
		LocalDate primerInscripcion = subscripcionesSinAdjudicacion.stream()
				.map(s -> s.getFechaSubscripcion())
				.sorted().collect(Collectors.toList())
				.get(0);
		
		return subscripcionesSinAdjudicacion.stream()
				.filter(s -> s.getFechaSubscripcion().isEqual(primerInscripcion))
				.collect(Collectors.toList());
	}
}
