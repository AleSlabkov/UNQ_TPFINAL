package model;

public interface IStockObserver {

	void aumentarStock(ICambioStock o, Object data);
	void liberarStock(ICambioStock o, Object data);
	

}
