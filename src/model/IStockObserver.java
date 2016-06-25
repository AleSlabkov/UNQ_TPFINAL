package model;

public interface IStockObserver {

	void aumentarStock(ICambioStock o, Modelo m, Integer cantidad);
	void liberarStock(ICambioStock o,  Modelo m, Integer cantidad);
	

}
