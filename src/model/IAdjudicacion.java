package model;

public interface IAdjudicacion {

	/**
	 * @param plan
	 * @return subscripcion de acuerdo a logica de quien lo implemente
	 * @throws SinAdjudicableException
	 */
	Subscripcion adjudicar(PlanDeAhorro plan) throws SinAdjudicableException;
}
