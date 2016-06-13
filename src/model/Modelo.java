package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Modelo {

	private String nombre;
	private Integer cantidadPuertas;
	private LocalDate fechaLanzamiento;
	private boolean full;
	private float precio;
	private List<Planta> plantasDeProduccion;

	public Modelo(String nombre, Integer cantidadPuertas,
			LocalDate fechaLanzamiento, boolean full, float precio) {
		this.nombre = nombre;
		this.cantidadPuertas = cantidadPuertas;
		this.fechaLanzamiento = fechaLanzamiento;
		this.full = full;
		this.precio = precio;
		this.plantasDeProduccion = new ArrayList<Planta>();
	}

	public String getNombre() {
		return this.nombre;
	}

	public Integer getCantidadPuertas() {
		return this.cantidadPuertas;
	}

	public LocalDate getFechaLanzamiento() {
		return this.fechaLanzamiento;
	}

	public boolean getFull() {
		return this.full;
	}

	public float getPrecio() {
		return this.precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public List<Planta> getPlantasDeProduccion() {
		return this.plantasDeProduccion;
	}
	
	public void agregarPlantaDeProduccion(Planta planta) {
		this.plantasDeProduccion.add(planta);
	}
}
