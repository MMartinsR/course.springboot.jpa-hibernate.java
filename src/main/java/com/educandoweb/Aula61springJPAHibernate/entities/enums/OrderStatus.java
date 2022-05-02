package com.educandoweb.Aula61springJPAHibernate.entities.enums;

public enum OrderStatus {
	// Para que possamos definir os códigos de cada enum, devemos criar um construtor,
	// para o tipo enumerado.
	
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	// método para converter um valor númerico em tipo enumerado
	public static OrderStatus valueOf(int code) {
		for (OrderStatus value : OrderStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code.");
	}

}
