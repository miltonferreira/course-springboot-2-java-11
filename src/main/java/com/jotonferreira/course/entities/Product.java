package com.jotonferreira.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_product") // nome dado a classe Order no BD
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id //indica que a chave primaria da classe
	@GeneratedValue(strategy = GenerationType.IDENTITY) //indica que vai ser auto-implementavel no banco de dados o valor do ID
	private Long id;
	
	private String name;
	private String description;
	private Double price;
	private String imgURL;
	
	// Usando Set evita que repita a mesma categoria mais de uma vez
	//@Transient // impede que o JPA interprete a coleção
	@ManyToMany // relação muitos para muitos, pois um produto pode ter varios categorias e vice-versa
	@JoinTable(name = "tb_product_category",  // indica o nome da table e chaves estrangeiras que vão associar as tables de produtos e categorias
			joinColumns = @JoinColumn(name = "product_id"), // nome da chave estrangeira referente a tabela de Product
			inverseJoinColumns = @JoinColumn(name = "category_id")) // nome da chave estrangeira referente a tabela de Category
	private Set<Category> categories = new HashSet<>(); // instancia o set para não ficar nula e sim vazia
	
	// id pega no OrderItem, o product pega na dependencia do OrderItemPK
	@OneToMany(mappedBy = "id.product") // Um produto para vários itens - OrderItem tem id, pelo id que tem o produto
	private Set<OrderItem> items = new HashSet<>(); // nao admite repetição do mesmo item
	
	public Product() {}

	public Product(Long id, String name, String description, Double price, String imgURL) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgURL = imgURL;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	
	// pega uma pedido de Order como visto no UML do projeto
	@JsonIgnore // evita que OrderItem fique chamando Product e Product fique chamando OrderItem, ficando em loop infinito
	public Set<Order> getOrders(){
		
		Set<Order> set = new HashSet<>(); // cria uma nova coleção para receber os pedidos
		
		// varre o OrderItem para pegar os pedidos
		for(OrderItem x : items) {
			set.add(x.getOrder()); // adiciona os pedidos na coleção
		}
		
		return set; // retorna a nova coleção com os pedidos
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
