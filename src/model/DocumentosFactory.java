package model;

import java.time.LocalDate;

public class DocumentosFactory {

	public ComprobanteDePago generarComprobanteDePago(int numeroCuota, float alicuota, float gastosAdministrativos,
			float costoSeguro) throws ConcesionarioSinGastosAdministrativosException {
		return new ComprobanteDePago(numeroCuota, LocalDate.now(), alicuota, gastosAdministrativos, costoSeguro);
	}

	public CuponDeAdjudicacion generarCuponDeAdjudicacion(float costoNoFinanciado, IFleteCotizador fleteCotizador,
			float distancia) {

		return new CuponDeAdjudicacion(LocalDate.now(), fleteCotizador.getCostoByDistancia(distancia),
				costoNoFinanciado);
	}

}
