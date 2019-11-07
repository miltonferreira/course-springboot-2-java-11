package com.jotonferreira.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jotonferreira.course.entities.Order;
import com.jotonferreira.course.services.OrderService;

// 
@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
	// cria um endPoint recebendo as infos dos usuarios para mostrar um JSON na web 
	
	@Autowired // injeção de dependencia
	private OrderService service;
	
	// retorna respostas de requisições WEB
	// retorna os usuarios
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = service.findAll();
		return ResponseEntity.ok().body(list); // retorna lista de usuarios que foi instanciado
	}
	
	@GetMapping(value = "/{id}") // gera um caminho http para escolher o user pelo ID
	public ResponseEntity<Order> findById(@PathVariable Long id){
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj); // retorna usuario pelo id
	}
	
}
