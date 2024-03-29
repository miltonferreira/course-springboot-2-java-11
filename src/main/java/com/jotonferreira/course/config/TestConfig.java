package com.jotonferreira.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jotonferreira.course.entities.Category;
import com.jotonferreira.course.entities.Order;
import com.jotonferreira.course.entities.OrderItem;
import com.jotonferreira.course.entities.Payment;
import com.jotonferreira.course.entities.Product;
import com.jotonferreira.course.entities.User;
import com.jotonferreira.course.entities.enums.OrderStatus;
import com.jotonferreira.course.repositories.CategoryRepository;
import com.jotonferreira.course.repositories.OrderItemRepository;
import com.jotonferreira.course.repositories.OrderRepository;
import com.jotonferreira.course.repositories.ProductRepository;
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
	
	@Autowired // cria dependencia e associa uma instancia de CategoryRepository
	private CategoryRepository categoryRepository;
	
	@Autowired // cria dependencia e associa uma instancia de ProductRepository
	private ProductRepository productRepository;
	
	@Autowired // cria dependencia e associa uma instancia de OrderItemRepository
	private OrderItemRepository orderItemRepository;
	
	// tudo vai ser executado quando a aplicação for iniciada
	@Override
	public void run(String... args) throws Exception {
		
		// instancia as categorias -------------------------------------------------------
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		// instancia os usuarios ---------------------------------------------------------
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		userRepository.saveAll(Arrays.asList(u1, u2)); // salva os usuarios no BD
		
		// instancia os pedidos ---------------------------------------------------------
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3)); // salva os pedidos no BD
		
		// instancia os produtos ---------------------------------------------------------
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5)); // salva os produtos no BD
		
		// cria os relacionamentos dos produtos e categorias -----------------------------
		p1.getCategories().add(cat2);
		
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		
		p3.getCategories().add(cat3);
		
		p4.getCategories().add(cat3);
		
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5)); // salva os produtos no BD com as associações das categorias
		
		// cria os relacionamentos entre pedidos e produtos no OrderItem -----------------------------
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4)); // salva os OrderItem's no BD com as associações entre pedidos e produtos
		
		// cria os relacionamentos entre pagamento e pedido -----------------------------
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1); // cria um obj pagamento do pedido que foi pago
		o1.setPayment(pay1); // associa pedido com pagamento
		
		orderRepository.save(o1); // salva novamente o pedido com o pagamento
		
	}
	
}
