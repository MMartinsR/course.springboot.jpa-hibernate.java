package com.educandoweb.Aula61springJPAHibernate.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.Aula61springJPAHibernate.entities.Product;
import com.educandoweb.Aula61springJPAHibernate.services.ProductService;

// Para dizermos que essa classe é um recurso web que é implementado por um controlador rest, 
// usamos a anotação:
// Além disso, daremos um nome para o recurso através da annotation:
// Daremos também um valor de caminho para esse recurso:
@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	// Vamos agora criar um endpoint para acessar os usuários
	// Response entity, tipo especifico do spring que retorna respostas de requisições web.
	// Além disso, para indicar que esse método responde a requisição do tipo get do http
	// usamos o annotation:
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){

		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list);
		// retorna uma resposta positiva no http e o corpo da resposta passando o objeto
	}
	
	// Nesse método, o getmapping receberá o id que é para ser buscado.
	// Além disso, para que a variavel id passada ao método seja aceito pela url, devemos 
	//  usar a anotação @PathVariable
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
