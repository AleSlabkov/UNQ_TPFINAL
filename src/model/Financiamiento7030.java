package model;

public class Financiamiento7030 implements IFinanciamiento {

	@Override
	public float getAlicouta(PlanDeAhorro planDeAhoro) {
		return (float) ((planDeAhoro.getModelo().getPrecio() * 0.70) / planDeAhoro
				.getCantidadDeCoutas());
	}

}
