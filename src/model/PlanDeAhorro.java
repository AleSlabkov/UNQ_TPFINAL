package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlanDeAhorro {

	private Concesionario concesionario;
	private Integer numeroGrupo;
	private Modelo modelo;
	private Integer cantidadDeCoutas;
	private List<Subscripcion> subscripciones;
	private IAdjudicacion tipoAdjudicacion;
	private IFinanciamiento financiamiento;
	private GeneradorDeDocumentos generadorDeDocumentos;

	public PlanDeAhorro(Concesionario concesionario, Integer numeroGrupo,
			Modelo modelo, Integer cantidadDeCoutas,
			IAdjudicacion tipoAdjudicacion, IFinanciamiento financiamiento,
			GeneradorDeDocumentos generadorDeDocumentos) {
		this.concesionario = concesionario;
		this.numeroGrupo = numeroGrupo;
		this.modelo = modelo;
		this.cantidadDeCoutas = cantidadDeCoutas;
		this.subscripciones = new ArrayList<Subscripcion>();
		this.tipoAdjudicacion = tipoAdjudicacion;
		this.financiamiento = financiamiento;
		this.generadorDeDocumentos = generadorDeDocumentos;
	}

	public Concesionario getConcesionario() {
		return this.concesionario;
	}

	public Integer getNumeroGrupo() {
		return numeroGrupo;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public Integer getCantidadDeCuotas() {
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

	/**
	 * Agrega una subscripción al Plan de Ahorro
	 * 
	 * @param subscripcion
	 */
	public void agregarSubscripcion(Subscripcion subscripcion) {
		this.subscripciones.add(subscripcion);
	}

	/**
	 * Adjudica el plan de ahorro a uno de sus subscriptores
	 * 
	 * @return Una subscripcion adjudicada
	 * @throws SinAdjudicableException
	 *             cuando el plan ya fue completamente adjudicado
	 * @throws ConcesionarioSinGastosAdministrativosException
	 *             cuando el concesionario no registra gastos administrativos
	 */
	public Subscripcion adjudicar() throws SinAdjudicableException,
			ConcesionarioSinGastosAdministrativosException {
		Subscripcion subscripcion = this.tipoAdjudicacion.adjudicar(this);

		subscripcion.registrarAdjudicacion(generarCupon());

		return subscripcion;
	}

	/**
	 * @return Devuelve la lista de suscripciones del plan que aún no han sido
	 *         adjudicadas
	 */
	public List<Subscripcion> getSubscripcionesSinAdjudicacion() {
		return subscripciones.stream().filter(s -> !s.estaAdjudicada())
				.collect(Collectors.toList());
	}

	/**
	 * @return Devuelve la alicuota del plan de ahorro según su tipo de
	 *         financiamiento
	 */
	public float getAlicuota() {
		return this.financiamiento.getAlicouta(this);
	}

	public void registrarPago(Subscripcion subscripcion)
			throws ConcesionarioSinGastosAdministrativosException,
			PlanCompletamentePagoException {

		if (subscripcion.completoPago(this))
			throw new PlanCompletamentePagoException();

		ComprobanteDePago cp = generadorDeDocumentos.generarComprobanteDePago(
				subscripcion.getProximaCuota(),
				getAlicuota(),
				concesionario.getGastosAdministrativos(),
				concesionario.getAseguradora().calcularValorDelSeguro(
						subscripcion.getCliente().getEdad(),
						subscripcion.getMontoAdeudado(this)));

		subscripcion.registrarPago(cp);

	}

	/**
	 * @return
	 * @throws ConcesionarioSinGastosAdministrativosException
	 */
	private CuponDeAdjudicacion generarCupon()
			throws ConcesionarioSinGastosAdministrativosException {
		return generadorDeDocumentos.generarCuponDeAdjudicacion( 
				this.concesionario.getCostoDeFleteByModelo(this.modelo),
				this.financiamiento.getCostoNoFinanciado(this));
	}
}
