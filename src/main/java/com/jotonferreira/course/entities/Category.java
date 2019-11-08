package com.jotonferreira.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_category") // nome dado a classe Order no BD
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id //indica que a chave primaria da classe
	@GeneratedValue(strategy = GenerationType.IDENTITY) //indica que vai ser auto-implementavel no banco de dados o valor do ID
	private Long id;
	
	private String name;
	
	// Usando Set evita que repita a mesma categoria mais de uma vez
	//@Transient // impede que o JPA interprete a coleção
	 // relação muitos para muitos, pois uma categoria pode ter varios produtos e vice-versa
	@JsonIgnore // evita que category fique chamando product e vice-versa em loop infinito
	@ManyToMany(mappedBy = "categories") // indica o outro lado que mapeou as relações
	private Set<Product> products = new HashSet<>(); // instancia o set para não ficar nula e sim vazia

	public Category() {}

	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public Set<Product> getProducts() {
		return products;
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
