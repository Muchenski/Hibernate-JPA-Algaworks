package com.locadora.jpa.exemplos_isolados.compositeKey;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(schema = "locadora")
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	@EmbeddedId
	private OrderItemPK orderItemPK;

	@Version
	private Long version;

	public OrderItem() {
		super();
	}

	public OrderItem(String name, OrderItemPK orderItemPK, Long version) {
		super();
		this.name = name;
		this.orderItemPK = orderItemPK;
		this.version = version;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
