package com.educandoweb.Aula61springJPAHibernate.services.exceptions;

// Classe de exceção personalizada da camada de serviço.
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	// Passaremos a essa exceção o id do objeto que tentamos encontrar e não encontramos, 
	// o que resultará em lançar essa exceção com o texto:
	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}
	
	
	

}
