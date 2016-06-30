package model;

public interface IGoogleMaps {
	
	/**
	 * Devuelve la distancia entre dos direcciones
	 * @param direccion1
	 * @param direccion2
	 * @return La distancia en float
	 */
	float getDistancia(String direccion1, String direccion2);
}
