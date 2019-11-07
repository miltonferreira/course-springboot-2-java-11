package com.jotonferreira.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotonferreira.course.entities.User;
import com.jotonferreira.course.repositories.UserRepository;

@Service //indica que é um serviço necessário a classe UserResource
public class UserService {
	// camada de serviço para o EndPoint em UserResource
	
	@Autowired // injeção de dependencia
	private UserRepository repository; // dependencia para UserRepository
	
	// retorna todos usuarios
	public List<User> findAll(){
		return repository.findAll();
	}
	
	// busca usuario pelo ID
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
}
