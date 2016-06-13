package model;

public class StockModelo {
	
	private Modelo modelo;
	private Integer cantidad;
	
	public StockModelo(Modelo modelo, Integer cantidad) {
		this.modelo = modelo;
		this.cantidad = cantidad;
	}

	public Modelo getModelo() {
		return this.modelo;
	}
	
	public Integer getCantidad(){
		return this.cantidad;
	}
	
	public void setCantidad(Integer cantidad){
		this.cantidad = cantidad;
	}

}
