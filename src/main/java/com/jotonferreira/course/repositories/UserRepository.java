package com.jotonferreira.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jotonferreira.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{ // vamos entregar ao JpaRepository a classe User com Long pois vamos pegar o ID
	// trabalha com as infos do User
	
	

}
