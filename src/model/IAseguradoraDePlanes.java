package model;

public interface IAseguradoraDePlanes {

	/**
	 * @param edad
	 * @param montoAdeudado
	 * @return el valor del seguro dependiendo edad y monto adeudado
	 */
	float calcularValorDelSeguro(Integer edad, float montoAdeudado);

}
