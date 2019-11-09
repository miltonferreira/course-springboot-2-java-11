package com.jotonferreira.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jotonferreira.course.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{ // vamos entregar ao JpaRepository a classe OrderItem com Long pois vamos pegar o ID
	// trabalha com as infos do OrderItem
	
}
