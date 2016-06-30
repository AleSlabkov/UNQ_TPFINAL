package model;

import java.time.LocalDate;
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
	private DocumentosFactory documentosFactory;

	public PlanDeAhorro(Concesionario concesionario, Integer numeroGrupo, Modelo modelo, Integer cantidadDeCoutas,
			IAdjudicacion tipoAdjudicacion, IFinanciamiento financiamiento, DocumentosFactory documentosFactory) {
		this.concesionario = concesionario;
		this.numeroGrupo = numeroGrupo;
		this.modelo = modelo;
		this.cantidadDeCoutas = cantidadDeCoutas;
		this.subscripciones = new ArrayList<Subscripcion>();
		this.tipoAdjudicacion = tipoAdjudicacion;
		this.financiamiento = financiamiento;
		this.documentosFactory = documentosFactory;
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

	public void agregarSubscripcion(Subscripcion subscripcion) {
		this.subscripciones.add(subscripcion);
	}

	public Subscripcion adjudicar() throws SinAdjudicableException, ConcesionarioSinGastosAdministrativosException {

		Subscripcion subscripcion = this.tipoAdjudicacion.adjudicar(this);

		subscripcion.registrarAdjudicacion(generarCupon());

		return subscripcion;
	}

	public List<Subscripcion> getSubscripcionesSinAdjudicacion() {
		return subscripciones.stream().filter(s -> !s.estaAdjudicada()).collect(Collectors.toList());
	}

	public float getAlicuota() {
		return this.financiamiento.getAlicouta(this);
	}

	private CuponDeAdjudicacion generarCupon() throws ConcesionarioSinGastosAdministrativosException {
		return new CuponDeAdjudicacion(LocalDate.now(), concesionario.getGastosAdministrativos(),
				financiamiento.getCostoNoFinanciado(this));
	}

	public Concesionario getConcesionario() {
		return this.concesionario;
	}

	public ComprobanteDePago registrarPago(Subscripcion subscripcion)
			throws ConcesionarioSinGastosAdministrativosException, PlanCompletamentePagoException {

		if (subscripcion.completoPago(this))
			throw new PlanCompletamentePagoException();

		return this.documentosFactory.generarComprobanteDePago(subscripcion.getProximaCuota(), getAlicuota(),
				concesionario.getGastosAdministrativos(), concesionario.getAseguradora().calcularValorDelSeguro(
						subscripcion.getCliente().getEdad(), subscripcion.getMontoAdeudado(this)));

	}
}
