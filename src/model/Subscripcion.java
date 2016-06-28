package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Subscripcion {

	private Cliente cliente;
	private LocalDate fechaSubscripcion;
	private List<ComprobanteDePago> pagos;
	private LocalDate fechaAdjudicacion;

	public Subscripcion(Cliente cliente) {
		this.cliente = cliente;
		this.fechaSubscripcion = LocalDate.now();
		this.pagos = new ArrayList<ComprobanteDePago>();
		this.fechaAdjudicacion = null;
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

	public void registrarPago(PlanDeAhorro planDeAhorro, LocalDate fecha) throws PlanCompletamentoPagoException {
		
		if (completoPago(planDeAhorro))
			throw new PlanCompletamentoPagoException();
		
		this.pagos.add(new ComprobanteDePago(getNuevoNumerodeCuota(), fecha,
				planDeAhorro.getAlicuota(), planDeAhorro
						.getGastosAdministrativos(), planDeAhorro
						.getSeguroDeVida()));
	}

	public float getProporcionDePago(PlanDeAhorro planDeAhorro) {
		return (float) this.pagos.size()
				/ (float) planDeAhorro.getCantidadDeCoutas();
	}

	public void registrarAdjudicacion(LocalDate fecha) {
		this.fechaAdjudicacion = fecha;
	}

	public boolean estaAdjudicada() {
		return this.fechaAdjudicacion != null;
	}
	
	private boolean completoPago(PlanDeAhorro planDeAhorro) {
		return planDeAhorro.getCantidadDeCoutas() == this.pagos.size();
	}

	private Integer getNuevoNumerodeCuota() {
		return this.pagos.size() == 0 ? 1 : this.pagos.get(this.pagos.size()-1).getNumeroDeCouta() + 1;
	}
}
