package com.locadora.jpa.exemplos_isolados.compositeKey;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItemPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String productId;

	private String orderId;

	public OrderItemPK() {
		super();
	}

	public OrderItemPK(String productId, String orderId) {
		super();
		this.productId = productId;
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, productId);
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
		return Objects.equals(orderId, other.orderId) && Objects.equals(productId, other.productId);
	}

}
