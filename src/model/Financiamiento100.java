package model;

public class Financiamiento100 implements IFinanciamiento {

	@Override
	public float getAlicouta(PlanDeAhorro planDeAhoro) {
		return planDeAhoro.getModelo().getPrecio() / planDeAhoro.getCantidadDeCoutas();
	}

}
