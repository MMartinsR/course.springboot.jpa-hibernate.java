package com.educandoweb.Aula61springJPAHibernate.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.educandoweb.Aula61springJPAHibernate.entities.Order;
import com.educandoweb.Aula61springJPAHibernate.entities.Product;

// Classe auxiliar que será o apoio (chave primária) da classe de item de pedido, pois a
// orientação a objetos não possui o conceito de chave composta para os objetos.

@Embeddable
public class OrderItemPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Muitos itens de pedido num pedido
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	// Muitos produtos num item de pedido
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}
	
	

}
