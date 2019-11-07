package com.jotonferreira.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jotonferreira.course.entities.User;
import com.jotonferreira.course.repositories.UserRepository;

@Configuration
@Profile("test") // rodar a configuração feita somente no perfil indicado
public class TestConfig implements CommandLineRunner{
	// classe de configuração com o BD do arquivo application-test.properties e application.properties
	// popula o BD
	
	@Autowired // cria dependencia e associa uma instancia de UserRepository
	private UserRepository userRepository;
	
	// tudo vai ser executado quando a aplicação for iniciada
	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		userRepository.saveAll(Arrays.asList(u1, u2)); // salva os usuarios no BD
		
	}
	
	
	
}
