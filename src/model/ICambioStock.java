package model;

public interface ICambioStock {
	
	void subscribirCambiosStock(IStockObserver o);
	
	void desusbcribirCambiosStock(IStockObserver o);
	
	void informarNuevoStock(Modelo m, Integer cantidad);
	
	void informarBajaStock(Modelo m, Integer cantidad);
}
