package model;

public interface ICambioStock {
	
	/** subscribe a algun objeto que quiera ser avisado de algun cambio de stock
	 * @param o
	 */
	void subscribirCambiosStock(IStockObserver o);
	
	/** desubscribe a algun objeto que quiera ser avisado de algun cambio de stock
	 * @param o
	 */
	void desusbcribirCambiosStock(IStockObserver o);
	
	/** informa a los objetos a notificar la produccion de modelo/cantidad
	 * @param m
	 * @param cantidad
	 */
	void informarNuevoStock(Modelo m, Integer cantidad);
	
	/** informa a los objetos a notificar la baja de modelo/cantidad
	 * @param m
	 * @param cantidad
	 */
	void informarBajaStock(Modelo m, Integer cantidad);
}
