package com.jotonferreira.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jotonferreira.course.entities.Category;
import com.jotonferreira.course.services.CategoryService;

// 
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	// cria um endPoint recebendo as infos das categorias para mostrar um JSON na web 
	
	@Autowired // injeção de dependencia
	private CategoryService service;
	
	// retorna respostas de requisições WEB
	// retorna as categorias
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = service.findAll();
		return ResponseEntity.ok().body(list); // retorna lista de categorias que foi instanciado
	}
	
	@GetMapping(value = "/{id}") // gera um caminho http para escolher a categoria pelo ID
	public ResponseEntity<Category> findById(@PathVariable Long id){
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj); // retorna categoria pelo id
	}
	
}
