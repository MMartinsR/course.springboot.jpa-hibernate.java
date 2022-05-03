package com.educandoweb.Aula61springJPAHibernate.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.Aula61springJPAHibernate.entities.User;
import com.educandoweb.Aula61springJPAHibernate.services.UserService;

// Para dizermos que essa classe é um recurso web que é implementado por um controlador rest, 
// usamos a anotação:
// Além disso, daremos um nome para o recurso através da annotation:
// Daremos também um valor de caminho para esse recurso:
@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	// Vamos agora criar um endpoint para acessar os usuários
	// Response entity, tipo especifico do spring que retorna respostas de requisições web.
	// Além disso, para indicar que esse método responde a requisição do tipo get do http
	// usamos o annotation:
	@GetMapping
	public ResponseEntity<List<User>> findAll(){

		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
		// retorna uma resposta positiva no http e o corpo da resposta passando o objeto
	}
	
	// Nesse método, o getmapping receberá o id que é para ser buscado.
	// Além disso, para que a variavel id passada ao método seja aceito pela url, devemos 
	//  usar a anotação @PathVariable
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// Para operações de inserção, usamos a requisição http POST, então a anotação referente 
	// é @PostMapping:
	// Para que o objeto que chegar por parametro, seja deserializado em um objeto java,
	// precisamos usar a anotação @RequestBody: 
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
		
	}
	
	// A requisição HTTP referente a deleção de um objeto/dado, é o @DeleteMapping.
	// Já que a ResponseEntity desta vez não retornará nada, devemos passar o valor Void para
	// ela.
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
