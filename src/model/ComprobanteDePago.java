package model;

import java.time.LocalDate;

public class ComprobanteDePago {
	
	private Integer numeroDeCouta;
	private LocalDate fechaDePago;
	private float alicuota;
	private float gastosAdministrativos;
	private float seguroDeVida;
	
	public ComprobanteDePago(Integer numeroDeCouta, LocalDate fechaDePago,
			float alicuota, float gastosAdministrativos, float seguroDeVida) {
		super();
		this.numeroDeCouta = numeroDeCouta;
		this.fechaDePago = fechaDePago;
		this.alicuota = alicuota;
		this.gastosAdministrativos = gastosAdministrativos;
		this.seguroDeVida = seguroDeVida;
	}

	public Integer getNumeroDeCouta() {
		return numeroDeCouta;
	}
	
	public LocalDate getFechaDePago() {
		return fechaDePago;
	}
	
	public float getAlicuota() {
		return alicuota;
	}
	
	public float getGastosAdministrativos() {
		return gastosAdministrativos;
	}
	
	public float getSeguroDeVida() {
		return seguroDeVida;
	}	
}
