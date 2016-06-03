package model;

import java.time.LocalDate;

public class Subscripcion {
	
	private Cliente cliente;
	private Integer cuotasPagas;
	private LocalDate fechaAdjudicacion;
	
	public Subscripcion(Cliente cliente, Integer cuotasPagas) {
		this.cliente = cliente;
		this.cuotasPagas = cuotasPagas;
		this.fechaAdjudicacion = null;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public void registrarPago() {
		this.cuotasPagas ++;
	}
	
	public float getProporcionDePago(Integer coutasTotales)
	{
		return coutasTotales / this.cuotasPagas;
	}
	
	public void registrarAdjudicacion(LocalDate fecha)
	{
		this.fechaAdjudicacion = fecha;
	}
	
	public boolean estaAdjudicada()
	{
		return this.fechaAdjudicacion != null;
	}
}
