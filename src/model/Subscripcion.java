package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Subscripcion {

	private Cliente cliente;
	private LocalDate fechaSubscripcion;
	private List<ComprobanteDePago> pagos;
	private CuponDeAdjudicacion adjudicacion;

	public Subscripcion(Cliente cliente) {
		this.cliente = cliente;
		this.fechaSubscripcion = LocalDate.now();
		this.pagos = new ArrayList<ComprobanteDePago>();
		this.adjudicacion = null;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public LocalDate getFechaSubscripcion() {
		return this.fechaSubscripcion;
	}

	public List<ComprobanteDePago> getPagos() {
		return this.pagos;
	}

	public void registrarPago(ComprobanteDePago comprobanteDePago) {
		this.pagos.add(comprobanteDePago);
	}

	public float getProporcionDePago(PlanDeAhorro planDeAhorro) {
		return (float) this.pagos.size()
				/ (float) planDeAhorro.getCantidadDeCuotas();
	}

	public void registrarAdjudicacion(CuponDeAdjudicacion cuponDeAdjudicacion) {
		this.adjudicacion = cuponDeAdjudicacion;
	}

	public boolean estaAdjudicada() {
		return this.adjudicacion != null;
	}

	public boolean completoPago(PlanDeAhorro planDeAhorro) {
		return planDeAhorro.getCantidadDeCuotas() == getPagos().size();
	}

	public Integer getProximaCuota() {
		return getPagos().size() == 0 ? 1 : this.pagos.size() + 1;
	}

	public float getMontoAdeudado(PlanDeAhorro planDeAhorro) {
		return (planDeAhorro.getModelo().getPrecio() / planDeAhorro
				.getCantidadDeCuotas()) * cuotasRestantes(planDeAhorro);
	}

	private float cuotasRestantes(PlanDeAhorro planDeAhorro) {
		return planDeAhorro.getCantidadDeCuotas() - getPagos().size();
	}
}
