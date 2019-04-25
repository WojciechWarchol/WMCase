package com.wojto.wmcase.entity;

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


@Entity
@Table(name="clients")
public class Client {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="surname")
	private String surname;
	@Column(name="email")
	private String email;
	@Column(name="tel")
	private String tel;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="client_id")
	private List<Order> orders;
	
	
	// konstruktory
	public Client() {
		super();
	}

	public Client(int id, String name, String surname, String email, String tel, List<Order> orders) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.tel = tel;
		this.orders = orders;
	}

	
	// getters/setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
	// Reszta metod
	public boolean addOrder(Order order) {
		if(order.getCases().size() != 0) {
			orders.add(order);
			return true;
		}
		return false;
	}
	
	public boolean deleteOrder(int id) {
		if(id >= 0 && id < orders.size()) {
			for(Order order : orders) {
				if(order.getId() == id) {
					orders.remove(order);
					return true;
				}
			}
		}
		return false;
	}
	
	
}
