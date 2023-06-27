package mk.spring.ecom;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	public Order(Long id, String orderNumber, BigDecimal  totalAmount) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.totalAmount = totalAmount;
	}
	public Order() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String orderNumber;

	private BigDecimal totalAmount;
//	
//	@OneToMany(targetEntity = CartItem.class,cascade = CascadeType.ALL)
//	@JoinColumn(name="orderid",referencedColumnName = "id")
//	private List<CartItem> orderList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public  BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount( BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNumber=" + orderNumber + ", totalAmount=" + totalAmount + "]";
	}
//	public List<CartItem> getOrderList() {
//		return orderList;
//	}
//	public void setOrderList(List<CartItem> orderList) {
//		this.orderList = orderList;
//	}

}
