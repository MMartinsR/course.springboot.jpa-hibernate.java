package com.educandoweb.Aula61springJPAHibernate.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.Aula61springJPAHibernate.entities.User;

// Para dizermos que essa classe é um recurso web que é implementado por um controlador rest, 
// usamos a anotação:
// Além disso, daremos um nome para o recurso através da annotation:
// Daremos também um valor de caminho para esse recurso:
@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	// Vamos agora criar um endpoint para acessar os usuários
	// Response entity, tipo especifico do spring que retorna respostas de requisições web.
	// Além disso, para indicar que esse método responde a requisição do tipo get do http
	// usamos o annotation:
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "Maria", "maria@gmail.com", "999999999", "12345");
		return ResponseEntity.ok().body(u);
		// retorna uma resposta positiva no http e o corpo da resposta passando o objeto
	}
}
