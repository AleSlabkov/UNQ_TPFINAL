package model;

import java.time.LocalDate;

public class Modelo {
	
	private String nombre;
	private Integer cantidadPuertas;
	private LocalDate fechaLanzamiento;
	private float precioBase;
	private String pepe;
	
	public Modelo(String nombre, Integer cantidadPuertas, LocalDate fechaLanzamiento, float precioBase){
		this.nombre = nombre;
		this.cantidadPuertas = cantidadPuertas;
		this.fechaLanzamiento = fechaLanzamiento;
		this.precioBase = precioBase;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public Integer getCantidadPuertas(){
		return cantidadPuertas;
	}
	
	public LocalDate getFechaLanzamiento(){
		return fechaLanzamiento;
	}
	
	public float getPrecioBase(){
		return precioBase;
	}
	
	public void setPrecio(float precio){
		this.precioBase = precio;
	}
	

}
