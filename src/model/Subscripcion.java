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

	/**Registra un Pago en la subscripción
	 * @param comprobanteDePago
	 */
	public void registrarPago(ComprobanteDePago comprobanteDePago) {
		this.pagos.add(comprobanteDePago);
	}

	/**
	 * @param planDeAhorro
	 * @return La proporción de pago de la subcripcion de acuerdo a la cantidad
	 * total de coutas del plan y la cantidad pagas
	 */
	public float getProporcionDePago(PlanDeAhorro planDeAhorro) {
		return (float) this.pagos.size()
				/ (float) planDeAhorro.getCantidadDeCuotas();
	}

	/**Registra la adjudicación del Plan
	 * @param cuponDeAdjudicacion
	 */
	public void registrarAdjudicacion(CuponDeAdjudicacion cuponDeAdjudicacion) {
		this.adjudicacion = cuponDeAdjudicacion;
	}

	/**
	 * @return Verdadero si la subscripción ya ha sido adjudicada
	 */
	public boolean estaAdjudicada() {
		return this.adjudicacion != null;
	}

	/**
	 * @param planDeAhorro
	 * @return Verdadero si la subscripción está completamente paga
	 */
	public boolean completoPago(PlanDeAhorro planDeAhorro) {
		return planDeAhorro.getCantidadDeCuotas() == getPagos().size();
	}

	/**
	 * @return El próximo número de couta
	 */
	public Integer getProximaCuota() {
		return getPagos().size() == 0 ? 1 : this.pagos.size() + 1;
	}

	/**
	 * @param planDeAhorro
	 * @return El monto pendiente de pago de acuerdo a la cantidad de coutas pagas
	 */
	public float getMontoAdeudado(PlanDeAhorro planDeAhorro) {
		return (planDeAhorro.getModelo().getPrecio() / planDeAhorro
				.getCantidadDeCuotas()) * cuotasRestantes(planDeAhorro);
	}

	/**
	 * @param planDeAhorro
	 * @return La cantidad de coutas que restan pagar para completar el total del plan
	 */
	private float cuotasRestantes(PlanDeAhorro planDeAhorro) {
		return planDeAhorro.getCantidadDeCuotas() - getPagos().size();
	}
}
