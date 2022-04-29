package com.educandoweb.Aula61springJPAHibernate.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.Aula61springJPAHibernate.entities.User;
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

	// Tudo que estiver dentro desse método será executado quando a aplicação for iniciada.
	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
	
	
	
	

}