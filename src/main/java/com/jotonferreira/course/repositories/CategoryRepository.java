package com.jotonferreira.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jotonferreira.course.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{ // vamos entregar ao JpaRepository a classe Category com Long pois vamos pegar o ID
	// trabalha com as infos do Category
	
	

}
