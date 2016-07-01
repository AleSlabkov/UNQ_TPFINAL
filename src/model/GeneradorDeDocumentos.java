package model;

import java.time.LocalDate;

public class GeneradorDeDocumentos {

	/**
	 * @param numeroCuota
	 * @param alicuota
	 * @param gastosAdministrativos
	 * @param costoSeguro
	 * @return comprobante de pago
	 */
	public ComprobanteDePago generarComprobanteDePago(int numeroCuota, float alicuota, float gastosAdministrativos,
			float costoSeguro) {
		return new ComprobanteDePago(numeroCuota, LocalDate.now(), alicuota, gastosAdministrativos, costoSeguro);
	}

	/**
	 * @param costoDeFlete
	 * @param costoNoFinanciado
	 * @return cupon de adjudicacion
	 */
	public CuponDeAdjudicacion generarCuponDeAdjudicacion(float costoDeFlete, float costoNoFinanciado) {

		return new CuponDeAdjudicacion(LocalDate.now(), costoDeFlete, costoNoFinanciado);
	}

}
