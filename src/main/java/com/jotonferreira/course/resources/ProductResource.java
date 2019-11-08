package com.jotonferreira.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jotonferreira.course.entities.Product;
import com.jotonferreira.course.services.ProductService;

// 
@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	// cria um endPoint recebendo as infos dos produtos para mostrar um JSON na web
	// usado no POSTMAN que visualiza como JSON <<<<<<<<<<<<<
	
	@Autowired // injeção de dependencia
	private ProductService service;
	
	// retorna respostas de requisições WEB
	// retorna os produtos
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list); // retorna lista de produtos que foi instanciado
	}
	
	@GetMapping(value = "/{id}") // gera um caminho http para escolher o product pelo ID
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj); // retorna produto pelo id
	}
	
}
