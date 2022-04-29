package com.educandoweb.Aula61springJPAHibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.Aula61springJPAHibernate.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	// Não é necessário criar uma implementação da interface porque o JpaRepository já
	// possui uma implementação padrão.

}
