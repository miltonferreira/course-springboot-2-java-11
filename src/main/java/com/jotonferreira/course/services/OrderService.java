package com.jotonferreira.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotonferreira.course.entities.Order;
import com.jotonferreira.course.repositories.OrderRepository;

@Service //indica que é um serviço necessário a classe OrderResource
public class OrderService {
	// camada de serviço
	
	@Autowired // injeção de dependencia
	private OrderRepository repository; // dependencia para OrderRepository
	
	// retorna todos pedidos
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	// busca pedido pelo ID
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
}
