package model;

import java.util.ArrayList;
import java.util.List;

public class PlanAhorro {

	private Integer numeroGrupo;
	private List<Cliente> subscripciones;
	private Modelo modelo;
	private IAdjudicacion adjudicacion;
	private IFinanciamiento financiamiento;

	public PlanAhorro(Integer numeroGrupo, Modelo modelo, IAdjudicacion adjudicacion, IFinanciamiento financiamiento) {
		this.numeroGrupo = numeroGrupo;
		this.subscripciones = new ArrayList<Cliente>();
		this.modelo = modelo;
		this.adjudicacion = adjudicacion;
		this.financiamiento = financiamiento;
	}

	public Integer getNumeroGrupo() {
		return numeroGrupo;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public List<Cliente> getSubscripciones() {
		return subscripciones;
	}

	public void agregarSubscripcion(Cliente c) {
		this.subscripciones.add(c);
	}

}
