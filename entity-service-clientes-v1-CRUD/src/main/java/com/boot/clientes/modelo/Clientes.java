package com.boot.clientes.modelo;

public class Clientes {
	private int idCliente;
	private String nombreCliente;
	private String correoCliente;
	
	public Clientes(int idCliente, String nombreCliente, String correoCliente) {
		super();
		this.idCliente = idCliente;
		this.nombreCliente = nombreCliente;
		this.correoCliente = correoCliente;
		
	}
	
	public Clientes() {
		
	}
	
	
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getCorreoCliente() {
		return correoCliente;
	}

	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}	

}
