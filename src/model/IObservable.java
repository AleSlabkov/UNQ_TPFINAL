package model;

public interface IObservable {
	
	void addObserver(IObserver o);
	
	void deleteObserver(IObserver o);
	
	void notifyObservers();

	void notifyObservers(Object data);
}
