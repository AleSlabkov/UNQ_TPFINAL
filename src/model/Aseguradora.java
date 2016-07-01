package model;

public class Aseguradora implements IAseguradoraDePlanes {

	
	@Override
	public float calcularValorDelSeguro(Integer edad,
			float montoAdeudado) {
		return (float) (getValorSegunEdad(edad) + montoAdeudado * 0.05);
	}

	/**
	 * @param edad
	 * @return valor extra segun edad del asegurado
	 */
	private float getValorSegunEdad(int edad) {
		return 50 + 10 * aniosArribaDeCincuenta(edad);
	}

	/**
	 * @param edad
	 * @return cantidad de anios mayores a 50 del asegurado
	 */
	private int aniosArribaDeCincuenta(int edad) {
		return (int) (edad < 50 ? 0 : (edad - 50));
	}

}
