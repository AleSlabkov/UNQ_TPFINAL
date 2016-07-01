package model;

public interface IFinanciamiento {

	/**
	 * @param planDeAhoro
	 * @return valor de la alicuota del plan
	 */
	float getAlicouta(PlanDeAhorro planDeAhoro);

	/**
	 * @param planDeAhorro
	 * @return el costo del modelo no financiado por el plan
	 */
	float getCostoNoFinanciado(PlanDeAhorro planDeAhorro);

}
