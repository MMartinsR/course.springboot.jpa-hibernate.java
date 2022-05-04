package com.educandoweb.Aula61springJPAHibernate.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.Aula61springJPAHibernate.entities.User;
import com.educandoweb.Aula61springJPAHibernate.repositories.UserRepository;
import com.educandoweb.Aula61springJPAHibernate.services.exceptions.DatabaseException;
import com.educandoweb.Aula61springJPAHibernate.services.exceptions.ResourceNotFoundException;

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
		// o método .get do optional retorna o objeto que está dentro do optional do tipo 
		// que o Optional foi configurado para receber, no caso o User.
		// Agora usaremos o .orElseThrow, que se não tiver um objeto dentro dele, irá lançar
		// uma exceção. Então usamos uma expressão lambda para instanciar uma nova exceção.
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); 
	}
	
	// Este método save do repositorio padrao do jpa já retorna o objeto salvo.
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	// Vamos capturar a exceção que o Java lança quando não consegue deletar pois o indice
	// nao existe, e lançar a nossa exceção personalizada
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}
	
	// Apenas permite monitorar um dado, sem de fato busca lo do banco, é mais eficiente 
	// do que usar o findById nesses casos.
	public User update(Long id, User obj) {
		try {
			User entity = repository.getById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}

}
