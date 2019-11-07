package com.jotonferreira.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotonferreira.course.entities.Category;
import com.jotonferreira.course.repositories.CategoryRepository;

@Service //indica que é um serviço necessário a classe CategoryResource
public class CategoryService {
	// camada de serviço para o EndPoint em CategoryResource
	
	@Autowired // injeção de dependencia
	private CategoryRepository repository; // dependencia para CategoryRepository
	
	// retorna todas categorias
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	// busca categoria pelo ID
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}
}
