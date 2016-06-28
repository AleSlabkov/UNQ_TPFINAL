package model;

import java.time.LocalDate;

public class Cliente {
	private String nombre;
	private String apellido;
	private String dni;
	private String direccion;
	private String email;
	private LocalDate fechaDeNacimiento;
	private LocalDate fechaDeIngreso;

	public Cliente(String nombre, String apellido, String dni, String direccion, String email, LocalDate fechaDeNacimiento,
			LocalDate fechaDeIngreso) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.direccion = direccion;
		this.email = email;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.fechaDeIngreso = fechaDeIngreso;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDni() {
		return dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public LocalDate getFechaDeIngreso() {
		return fechaDeIngreso;
	}

	public Integer getEdad() {
		return LocalDate.now().compareTo(this.fechaDeNacimiento);
	}

}
