package com.wojto.wmcase.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wojto.wmcase.enums.OrderStatus;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="order_id")
	private List<Case> cases;
	
	@Column(name="comments")
	private String comments;
	@Column(name="charge")
	private double charge;
	@Column(name="order_status")
	private OrderStatus orderStatus;
	
	// konstruktory
	public Order() {
		super();
	}
	
	public Order(int id, List<Case> cases, String uwagi) {
		this.id = id;
		this.cases = cases;
		this.comments = uwagi;
		for(Case skrzynka : cases) {
			this.charge += skrzynka.getPrice();
		}
		this.orderStatus = OrderStatus.ZAPYTANIE;
	}


	// getters/setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Case> getCases() {
		return cases;
	}

	public void setCases(List<Case> cases) {
		this.cases = cases;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		if (charge >= 0) {
		this.charge = charge;
		}
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	// Inne metody
	public boolean addCase(Case skrzynka) {
		if(skrzynka != null) {
			cases.add(skrzynka);
			return true;
		}
		return false;
	}
	
	public boolean deleteCase(int id) {
		if(id >= 0 && id <= cases.size()) {
			for(Case case1 : cases) {
				if(case1.getId() == id) {
					cases.remove(case1);
					return true;
				}
			}
		}
		return false;
	}
	
	public void orderSummary() {
		System.out.println("Zamówienie nr " + id + "\nZawiera:\n");
		for(Case case1 : cases) {
			System.out.println(case1.toString());
		}
	}
	
}
