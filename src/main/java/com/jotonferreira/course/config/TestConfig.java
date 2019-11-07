package com.jotonferreira.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jotonferreira.course.entities.Order;
import com.jotonferreira.course.entities.User;
import com.jotonferreira.course.repositories.OrderRepository;
import com.jotonferreira.course.repositories.UserRepository;

@Configuration
@Profile("test") // rodar a configuração feita somente no perfil indicado
public class TestConfig implements CommandLineRunner{
	// classe de configuração com o BD do arquivo application-test.properties e application.properties
	// popula o BD
	
	@Autowired // cria dependencia e associa uma instancia de UserRepository
	private UserRepository userRepository;
	
	@Autowired // cria dependencia e associa uma instancia de OrderRepository
	private OrderRepository orderRepository;
	
	// tudo vai ser executado quando a aplicação for iniciada
	@Override
	public void run(String... args) throws Exception {
		
		// instancia os usuarios ---------------------------------------------------------
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		userRepository.saveAll(Arrays.asList(u1, u2)); // salva os usuarios no BD
		
		// instancia os pedidos ---------------------------------------------------------
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1);
		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3)); // salva os pedidos no BD
		
	}
	
	
	
}
