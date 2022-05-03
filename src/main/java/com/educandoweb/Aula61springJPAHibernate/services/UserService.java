package com.educandoweb.Aula61springJPAHibernate.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.Aula61springJPAHibernate.entities.User;
import com.educandoweb.Aula61springJPAHibernate.repositories.UserRepository;

// Classe da camada de serviço, que será responsável por representar as regras de negócio
// e ser o meio de campo entre o controlador e os repositórios.
// Para que o framework spring possa fazer a injeção de dependência
// para as nossas classes como a UserService - precisamos registrar esses componentes,
// como componentes spring, através da anotação @Component. Pode ainda registrar de forma
// mais especifica com as anotações @Service e @Repository:
@Service
public class UserService {
	
	// anotação spring que faz a injeção de dependência automaticamente entre o
	// repositório e o serviço.
	@Autowired  
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		// o método findById retorna um objeto optional
		Optional<User> obj = repository.findById(id);
		// o método get do optional retorna o objeto que está dentro do optional do tipo 
		// que o Optional foi configurado para receber, no caso o User.
		return obj.get(); 
	}
	
	// Este método save do repositorio padrao do jpa já retorna o objeto salvo.
	public User insert(User obj) {
		return repository.save(obj);
	}

}
