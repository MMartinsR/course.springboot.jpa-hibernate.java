package com.educandoweb.Aula61springJPAHibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.Aula61springJPAHibernate.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
	
	// Não é necessário criar uma implementação da interface porque o JpaRepository já
	// possui uma implementação padrão. Essa interface faz o meio de campo entre a aplicação 
	// web, ou seja, as requisições e a camada de Serviço

}
