package model;

public class Aseguradora implements IAseguradoraDePlanes {

	@Override
	public float calcularValorDelSeguro(PlanDeAhorro plan,
			Subscripcion subscripcion) {
		return (float) (getValorSegunEdad(subscripcion.getCliente().getEdad()) + plan
				.getModelo().getPrecio() * 0.05);
	}

	private float getValorSegunEdad(int edad) {
		return 50 + 10 * aniosArribaDeCincuenta(edad);
	}

	private int aniosArribaDeCincuenta(double edad) {
		return (int) (edad < 50 ? 0 : (edad - 50) / 10);
	}

}
