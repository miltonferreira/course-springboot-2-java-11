package com.jotonferreira.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jotonferreira.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{ // vamos entregar ao JpaRepository a classe Product com Long pois vamos pegar o ID
	// trabalha com as infos do Product
	
	

}
