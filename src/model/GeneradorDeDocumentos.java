package model;

import java.time.LocalDate;

public class GeneradorDeDocumentos {

	public ComprobanteDePago generarComprobanteDePago(int numeroCuota, float alicuota, float gastosAdministrativos,
			float costoSeguro) {
		return new ComprobanteDePago(numeroCuota, LocalDate.now(), alicuota, gastosAdministrativos, costoSeguro);
	}

	public CuponDeAdjudicacion generarCuponDeAdjudicacion(float costoDeFlete, float costoNoFinanciado) {

		return new CuponDeAdjudicacion(LocalDate.now(), costoDeFlete, costoNoFinanciado);
	}

}
