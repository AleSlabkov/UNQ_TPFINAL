package model;

import java.time.LocalDate;

public class DocumentosFactory {

	public ComprobanteDePago generarComprobanteDePago(PlanDeAhorro plan,
			Subscripcion subscripcion)
			throws ConcesionarioSinGastosAdministrativosException {
		return new ComprobanteDePago(subscripcion.getProximaCuota(),
				LocalDate.now(), plan.getAlicuota(), plan.getConcesionario()
						.getGastosAdministrativos(), plan.getConcesionario()
						.getSeguroDeVida(subscripcion.getCliente().getEdad(),
								subscripcion.getMontoAdeudado(plan)));
	}
}
