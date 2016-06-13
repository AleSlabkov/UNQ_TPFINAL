package model;

import java.util.ArrayList;
import java.util.List;

public class Fabrica implements IObservable, IObserver {

	private String nombre;
	private List<Planta> plantas;
	private List<IObserver> observers;

	public Fabrica(String nombre) {
		this.nombre = nombre;
		this.plantas = new ArrayList<Planta>();
		this.observers = new ArrayList<IObserver>();
	}

	public String getNombre() {
		return nombre;
	}

	public void agregarPlanta(Planta planta) {
		this.plantas.add(planta);

	}

	public List<Planta> getPlantas() {
		return this.plantas;
	}

	@Override
	public void addObserver(IObserver o) {
		this.observers.add(o);
	}

	@Override
	public void deleteObserver(IObserver o) {
		this.observers.remove(o);

	}

	@Override
	public void notifyObservers() {
	}

	@Override
	public void notifyObservers(Object data) {
		this.observers.stream().forEach(o -> o.update(this, data));
	}

	@Override
	public void update(IObservable o, Object data) {
		notifyObservers(data);
	}
}
