package com.rafalazar.bootcamp.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafalazar.bootcamp.app.document.Client;

import reactor.core.publisher.Mono;


public interface ClientRepository extends ReactiveMongoRepository<Client, String>{
	
	public Mono<Client> findBankById(String bank, String id);
	
}
