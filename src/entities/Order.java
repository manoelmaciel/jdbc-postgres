package entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Order {
	
	private Long id; 
	private double latitude;
	private double longitude ;
	private Instant moment;
	private OrderStatus status;
	
	private List<Product> products = new ArrayList<>();
	
	public Order() {
		super();
	}

	public Order(Long id, double latitude, double longitude, Instant moment, OrderStatus status) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.moment = moment;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<Product> getProducts() {
		return products;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", moment=" + moment
				+ ", status=" + status + "]";
	}	

}
	