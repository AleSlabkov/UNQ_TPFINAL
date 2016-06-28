package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlanDeAhorro {

	private Integer numeroGrupo;
	private Modelo modelo;
	private Integer cantidadDeCoutas;
	private List<Subscripcion> subscripciones;
	private IAdjudicacion tipoAdjudicacion;
	private IFinanciamiento financiamiento;

	public PlanDeAhorro(Integer numeroGrupo, Modelo modelo, Integer cantidadDeCoutas, IAdjudicacion tipoAdjudicacion,
			IFinanciamiento financiamiento) {
		this.numeroGrupo = numeroGrupo;
		this.modelo = modelo;
		this.cantidadDeCoutas = cantidadDeCoutas;
		this.subscripciones = new ArrayList<Subscripcion>();
		this.tipoAdjudicacion = tipoAdjudicacion;
		this.financiamiento = financiamiento;
	}

	public Integer getNumeroGrupo() {
		return numeroGrupo;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public Integer getCantidadDeCoutas() {
		return cantidadDeCoutas;
	}

	public IAdjudicacion getTipoAdjudicacion() {
		return tipoAdjudicacion;
	}

	public IFinanciamiento getFinanciamiento() {
		return financiamiento;
	}

	public List<Subscripcion> getSubscripciones() {
		return subscripciones;
	}

	public void agregarSubscripcion(Subscripcion subscripcion) {
		this.subscripciones.add(subscripcion);
	}

	public Subscripcion adjudicar() throws SinAdjudicableException {
		return this.tipoAdjudicacion.adjudicar(this);
	}

	public List<Subscripcion> getSubscripcionesSinAdjudicacion() {
		return subscripciones.stream().filter(s -> !s.estaAdjudicada()).collect(Collectors.toList());
	}

	public float getAlicuota() {
		return this.financiamiento.getAlicouta(this);
	}

	public float getGastosAdministrativos() {
		return 0;
	}

	public float getSeguroDeVida() {
		return 0;
	}
}
