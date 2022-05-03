package com.educandoweb.Aula61springJPAHibernate.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.Aula61springJPAHibernate.entities.Category;
import com.educandoweb.Aula61springJPAHibernate.entities.Order;
import com.educandoweb.Aula61springJPAHibernate.entities.Product;
import com.educandoweb.Aula61springJPAHibernate.entities.User;
import com.educandoweb.Aula61springJPAHibernate.entities.enums.OrderStatus;
import com.educandoweb.Aula61springJPAHibernate.repositories.CategoryRepository;
import com.educandoweb.Aula61springJPAHibernate.repositories.OrderRepository;
import com.educandoweb.Aula61springJPAHibernate.repositories.ProductRepository;
import com.educandoweb.Aula61springJPAHibernate.repositories.UserRepository;

// Usamos a anotação para definir que esta é uma classe de configuração.
// Usamos também a anotação Profile

// Vamos implementar a interface CommandLineRunner para que possamos rodar essas configurações
// qndo a aplicação estiver rodando.
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	// Classe utilitaria para fazer algumas configurações na aplicação. Ela servirá para fazer
	// o database seeding, ou seja, vai popular o nosso banco com alguns objetos.
	
	// Vamos agora fazer uma injeção de dependência da classe UserRepository aqui, para que 
	// possamos salvar os dados de teste no banco de dados. Fazemos isso criando um atributo
	// do tipo UserRepository e usando a anotação Autowired do springboot que será responsavel
	// por fazer a injeção de dependência automaticamente.
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

	// Tudo que estiver dentro desse método será executado quando a aplicação for iniciada.
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		
		userRepository.saveAll(Arrays.asList(u1, u2));		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
	}
	
	
	
	

}
