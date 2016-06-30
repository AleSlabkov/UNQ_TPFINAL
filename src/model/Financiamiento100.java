package model;

public class Financiamiento100 implements IFinanciamiento {

	@Override
	public float getAlicouta(PlanDeAhorro planDeAhoro) {
		return planDeAhoro.getModelo().getPrecio() / planDeAhoro.getCantidadDeCuotas();
	}

	@Override
	public float getCostoNoFinanciado(PlanDeAhorro planDeAhoro) {
		return 0;
	}

}
