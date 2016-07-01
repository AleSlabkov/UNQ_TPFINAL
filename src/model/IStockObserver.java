package model;

public interface IStockObserver {

	/** actualiza el aumento de stock
	 * @param o notificador
	 * @param m
	 * @param cantidad
	 */
	void aumentarStock(ICambioStock o, Modelo m, Integer cantidad);
	
	/** actualiza la baja de stock
	 * @param o notificador
	 * @param m
	 * @param cantidad
	 */
	void liberarStock(ICambioStock o,  Modelo m, Integer cantidad);
	

}
