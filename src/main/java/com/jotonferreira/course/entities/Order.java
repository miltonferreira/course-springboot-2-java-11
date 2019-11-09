package com.jotonferreira.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jotonferreira.course.entities.enums.OrderStatus;

@Entity
@Table(name = "tb_order") // nome dado a classe Order no BD
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id //indica que a chave primaria da classe
	@GeneratedValue(strategy = GenerationType.IDENTITY) //indica que vai ser auto-implementavel no banco de dados o valor do ID
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") // formata forma de mostra o tempo
	private Instant moment;
	
	private Integer orderStatus;
	
	@ManyToOne // indica muitos pedidos para um cliente
	@JoinColumn(name = "client_id") //nome da chave-estrangeira 
	private User client; // dependencia para a classe User
	
	// id pega no OrderItem, o order pega na dependencia do OrderItemPK
	@OneToMany(mappedBy = "id.order") // Um pedido para vários itens - OrderItem tem id, pelo id que tem o pedido
	private Set<OrderItem> items = new HashSet<>(); // nao admite repetição do mesmo item
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // indica a dependencia em Payment - usando "cascade" faz ter o mesmo ID para os dois
	private Payment payment;
	
	public Order() {}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		
		if(orderStatus != null) { // evita que passe um valor nulo ao orderStatus
			this.orderStatus = orderStatus.getCode();
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	// pega uma coleção de OrderItem como visto no UML do projeto
	public Set<OrderItem> getItems() {
		return items;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
