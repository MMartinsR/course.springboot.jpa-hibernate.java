package com.educandoweb.Aula61springJPAHibernate.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.educandoweb.Aula61springJPAHibernate.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

// Como essa classe tem uma chave primária composta, ela não entra no construtor diretamente.
// O que acontece é que o construtor recebe objetos dos tipos que a compoem, no caso Order e 
// Product e nos atributos da chave composta, setamos esses objetos
@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable{
	private static final long serialVersionUID = 1L;

	// Sempre que for criar um id composto, devemos instanciar esse objeto, senão 
	// dará nullPointerException
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
	}

	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	
	// Será necessário bloquear a relação de mão dupla aqui, no getOrder, pois para o JavaEE
	// o que vale é o método GET, ou seja o endpoint chamado. Que no caso é o orders.
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	// Para que o jpa mapeie esse método, é necessário usar o padrão JavaEE, com o get antes
	// do nome do método.
	public Double getSubTotal() {
		return price * quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	

}
