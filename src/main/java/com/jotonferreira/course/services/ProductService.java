package com.jotonferreira.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotonferreira.course.entities.Product;
import com.jotonferreira.course.repositories.ProductRepository;

@Service //indica que é um serviço necessário a classe ProductResource
public class ProductService {
	// camada de serviço para o EndPoint em ProductResource
	
	@Autowired // injeção de dependencia
	private ProductRepository repository; // dependencia para ProductRepository
	
	// retorna todos produtos
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	// busca produtos pelo ID
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
}
