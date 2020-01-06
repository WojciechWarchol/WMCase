package com.wojto.wmcase.entity;

import com.wojto.wmcase.enums.OrderStatus;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.*;

@Entity
@Proxy(lazy=false)  // test
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
//	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@ElementCollection
	@JoinTable(name="quantities",
		joinColumns = {@JoinColumn(name="order_id", referencedColumnName="id")}
//		,
//		inverseJoinColumns = {@JoinColumn(name="case_id", referencedColumnName="id")}
		)
	@MapKeyJoinColumn(name="case_id")
	@Column(name="quantity")
	private Map<Case, Integer> cases;
	
	@Column(name="comments")
	private String comments;
	@Column(name="charge")
	private double charge;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	@Column(name="dt")
	private Date date;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="client_id")
	private Client client;
	
	// konstruktory
	public Order() {
		super();
	}
	
	public Order(int id, Map<Case, Integer> cases, String uwagi) {
		this.id = id;
		this.cases = cases;
		this.comments = uwagi;
		for(Map.Entry<Case, Integer> entry : cases.entrySet()) {
			this.charge += ( entry.getKey().getPrice() * entry.getValue() );
		}
		this.orderStatus = OrderStatus.ZAPYTANIE;
		this.date = new Date();
	}


	// getters/setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Case, Integer> getCases() {
		if(cases == null) {
			this.cases = new HashMap<Case, Integer>();
		}
		return cases;
	}

	public List<Case> getCaseList(){
		if(cases == null) {
			this.cases = new HashMap<Case, Integer>();
		}
		return new ArrayList<>(cases.keySet());
	}

	public void setCases(Map<Case, Integer> cases) {
		this.cases = cases;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public double getCharge() {
		double charge = 0;
		for(Map.Entry<Case, Integer> entry : cases.entrySet()) {
			charge += ( entry.getKey().getPrice() * entry.getValue() );
		}
		this.charge = charge;
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
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
		// Not sure if this is goood practice
		client.addOrder(this);
	}

	// Inne metody
	public boolean addCase(Case skrzynka) {
		if(cases == null) {
			this.cases = new HashMap<Case, Integer>();
		}
		
		if(skrzynka != null) {
			cases.put(skrzynka, 1);
			return true;
		}
		
		// Not sure if this is good practice
		skrzynka.setOrder(this);
		
		return false;
	}
	
	public boolean deleteCase(int id) {
		if(id >= 0) {
			for(Case case1 : cases.keySet()) {
				if(case1.getId() == id) {
					cases.remove(case1);
					return true;
				}
			}
		}
		return false;
	}

	public boolean setQuantity(int id, int quantity) {
		if(id >= 0 && quantity == 0)   deleteCase(id);
		if(id >= 0 && quantity >= 0) {
			for(Map.Entry<Case, Integer> entry : cases.entrySet()) {
				if(entry.getKey().getId() == id) {
					entry.setValue(quantity);
				}
			}
			return true;
		}
		return false;
	}
	
	public void orderSummary() {
		System.out.println("Zamówienie nr " + id + "\nZawiera:\n");
		for(Case case1 : cases.keySet()) {
			System.out.println(case1.toString());
		}
	}
	
}
