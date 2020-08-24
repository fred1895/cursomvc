package com.coursespringboot.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coursespringboot.workshop.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	
}
