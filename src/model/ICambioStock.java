package model;

public interface ICambioStock {
	
	void subscribirCambiosStock(IStockObserver o);
	
	void desusbcribirCambiosStock(IStockObserver o);
	
	void informarNuevoStock(Object data);
	
	void informarBajaStock(Object data);
}
