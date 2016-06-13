package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Subscripcion {

	private Cliente cliente;
	private List<String> pagos;
	private LocalDate fechaAdjudicacion;

	public Subscripcion(Cliente cliente) {
		this.cliente = cliente;
		this.pagos = new ArrayList<String>();
		this.fechaAdjudicacion = null;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void registrarPago(String pago) {
		this.pagos.add(pago);
	}

	public float getProporcionDePago(PlanDeAhorro planDeAhorro) {
		return (float)this.pagos.size() / (float)planDeAhorro.getCantidadDeCoutas();
	}

	public void registrarAdjudicacion(LocalDate fecha) {
		this.fechaAdjudicacion = fecha;
	}

	public boolean estaAdjudicada() {
		return this.fechaAdjudicacion != null;
	}
}
