package com.locadora.jpa.exemplos_isolados.compositeKey;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "locadora")
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	@EmbeddedId
	private OrderItemPK orderItemPK;

	public OrderItem() {
		super();
	}

	public OrderItem(String name, OrderItemPK orderItemPK) {
		super();
		this.name = name;
		this.orderItemPK = orderItemPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OrderItemPK getOrderItemPK() {
		return orderItemPK;
	}

	public void setOrderItemPK(OrderItemPK orderItemPK) {
		this.orderItemPK = orderItemPK;
	}

}
