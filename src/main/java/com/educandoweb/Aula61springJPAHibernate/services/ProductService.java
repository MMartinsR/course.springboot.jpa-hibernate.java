package com.educandoweb.Aula61springJPAHibernate.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.Aula61springJPAHibernate.entities.Product;
import com.educandoweb.Aula61springJPAHibernate.repositories.ProductRepository;

// Classe da camada de serviço, que será responsável por representar as regras de negócio
// e ser o meio de campo entre o controlador e os repositórios.
// Para que o framework spring possa fazer a injeção de dependência
// para as nossas classes como a ProductService - precisamos registrar esses componentes,
// como componentes spring, através da anotação @Component. Pode ainda registrar de forma
// mais especifica com as anotações @Service e @Repository:
@Service
public class ProductService {
	
	// anotação spring que faz a injeção de dependência automaticamente entre o
	// repositório e o serviço.
	@Autowired  
	private ProductRepository repository;
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		// o método findById retorna um objeto optional
		Optional<Product> obj = repository.findById(id);
		// o método get do optional retorna o objeto que está dentro do optional do tipo 
		// que o Optional foi configurado para receber, no caso o Product.
		return obj.get(); 
	}
	
	
	 
	

}
