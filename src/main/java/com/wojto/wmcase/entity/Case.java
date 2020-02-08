package com.wojto.wmcase.entity;

import com.wojto.wmcase.enums.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Proxy(lazy=false)
@Table(name="cases")
public class Case {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="length")
	private int length;
	@Column(name="width")
	private int width;
	@Column(name="height")
	private int height;
	@Column(name="surface")
	private double surface;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	@Enumerated(EnumType.STRING)
	private Material material;
	@Enumerated(EnumType.STRING)
	private Color color;
	@Enumerated(EnumType.STRING)
	private Filling filling;
	
	@Enumerated(EnumType.STRING)
	private Handle handle;
	@Column(name="handle_num")
	private int handleNum;
	
	@Column(name="wheels")
	private boolean wheels;
	@Column(name="wheel_num")
	private int wheelNum;
	
	@Enumerated(EnumType.STRING)
	private Locks locks;
	
	@Column(name="comments")
	private String comments;
	@Column(name="price")
	private double price;

	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="order_id")
	private Order order;

	
	// konstruktor(y)
	public Case() {
		super();
	}
	
	public Case(int id, int length, int width, int height, Type type, Material material, Color color, Filling filling,
			Handle handle, int handleNum, boolean wheels, int wheelNum, Locks locks, String comments) {
		super();
		this.id = id;
		this.length = length;
		this.width = width;
		this.height = height;
		this.surface = (double) ((length * width)*2 + (length * height)*2 
				+ ( width * height) * 2)/1000_000;
		this.type = type;
		this.material = material;
		this.color = color;
		this.filling = filling;
		this.handle = handle;
		this.handleNum = handleNum;
		this.wheels = wheels;
		this.wheelNum = wheelNum;
		this.locks = locks;
		this.comments = comments;
		this.evaluation();
	}

	// geters/setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
		this.surface = (this.length * this.width * this.height)/1000000000;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		this.surface = (this.length * this.width * this.height)/1000000000;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		this.surface = (this.length * this.width * this.height)/1000000000;
	}

	public double getSurface() {
		return surface;
	}
	
	//Surface doesn't have a normal setter. Only an evaluator based on the dimensions.
	public void surfaceEvaluation() {
		this.surface = (double) ((this.length * this.width)*2 + (this.length * this.height)*2 
				+ (this.width * this.height) * 2)/1000_000;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Filling getFilling() {
		return filling;
	}

	public void setFilling(Filling filling) {
		this.filling = filling;
	}

	public Handle getHandle() {
		return handle;
	}

	public void setHandle(Handle handle) {
		this.handle = handle;
	}

	public int getHandleNum() {
		return handleNum;
	}

	public void setHandleNum(int handleNum) {
		this.handleNum = handleNum;
	}

	public boolean getWheels() {
		return wheels;
	}

	public void setWheels(boolean kola) {
		this.wheels = kola;
		if(!this.wheels) {
			setWheelNum(0);
		}
	}

	public int getWheelNum() {
		return wheelNum;
	}

	public void setWheelNum(int wheelNum) {
		if (this.wheels) {
		this.wheelNum = wheelNum;
		} 
	}

	public Locks getLocks() {
		return locks;
	}

	public void setLocks(Locks locks) {
		this.locks = locks;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (price > 0) {
		this.price = price;
		}
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	// Inne metody
	public void evaluation() {
		double basePrice = 0;
		double finalPrice = 0;
		this.surface = (double) ((length * width)*2 + (length * height)*2 
				+ ( width * height) * 2)/1000_000;
		basePrice += (surface * 500);
		System.out.println("LOG OF basePrice: " + basePrice);
		System.err.println("LOG OF surface: " + surface);
		finalPrice = basePrice;
		
		switch(material) {
			case SKLEJKA:
				finalPrice += basePrice * 0;
				break;
			case CON_PEARL:
				finalPrice += basePrice * 0.1;
				break;
		}
		
		if(color == Color.OTHER) {
			finalPrice += basePrice * 0.1;
		}
		
		System.out.println("Evaluated the final price to: " + finalPrice);
		this.price = finalPrice;
		
	}

	@Override
	public String toString() {
		return "ID: " + id +
				", Case o wymiarach: " + length + "x" + width + "x" + height + "mm i powierzchni "
				+ surface + "m2, typ=" + type
				+ ", material=" + material + ", kolor=" + color + ", wypelnienie=" + filling + ", uchwyty="
				+ handle + ", iloscUchwytow=" + handleNum + ", kola=" + wheels + ", iloscKol=" + wheelNum
				+ ", zamki=" + locks + ", uwagi=" + comments + ", cena=" + price + "zl]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Case aCase = (Case) o;
		return id == aCase.id &&
				length == aCase.length &&
				width == aCase.width &&
				height == aCase.height &&
				Double.compare(aCase.surface, surface) == 0 &&
				handleNum == aCase.handleNum &&
				wheels == aCase.wheels &&
				wheelNum == aCase.wheelNum &&
				Double.compare(aCase.price, price) == 0 &&
				type == aCase.type &&
				material == aCase.material &&
				color == aCase.color &&
				filling == aCase.filling &&
				handle == aCase.handle &&
				locks == aCase.locks &&
				Objects.equals(comments, aCase.comments) &&
				order.equals(aCase.order);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, length, width, height, surface, type, material, color, filling, handle, handleNum, wheels, wheelNum, locks, comments, price, order);
	}
}
