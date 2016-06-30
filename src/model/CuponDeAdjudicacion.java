package model;

import java.time.LocalDate;

public class CuponDeAdjudicacion {

	private LocalDate fecha;
	private float costoDeFlete;
	private float costoNoFinanciado;

	public CuponDeAdjudicacion(LocalDate fecha, float costoDeFlete, float costoNoFinanciado) {
		this.fecha = fecha;
		this.costoDeFlete = costoDeFlete;
		this.costoNoFinanciado = costoNoFinanciado;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public float getCostoDeFlete() {
		return costoDeFlete;
	}

	public float getCostoNoFinanciado() {
		return costoNoFinanciado;
	}

	public float getCostoAdjudicacion() {
		return costoDeFlete + costoNoFinanciado;
	}

}
