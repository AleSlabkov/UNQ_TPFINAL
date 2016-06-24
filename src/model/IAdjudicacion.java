package model;

public interface IAdjudicacion {

	Subscripcion adjudicar(PlanDeAhorro plan) throws SinAdjudicableException;
}
