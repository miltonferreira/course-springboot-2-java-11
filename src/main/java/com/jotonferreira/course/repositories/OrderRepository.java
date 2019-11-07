package com.jotonferreira.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jotonferreira.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{ // vamos entregar ao JpaRepository a classe Order com Long pois vamos pegar o ID
	// trabalha com as infos do Order
	
}
