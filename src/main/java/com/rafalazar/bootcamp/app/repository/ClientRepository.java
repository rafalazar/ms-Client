package com.rafalazar.bootcamp.app.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafalazar.bootcamp.app.document.Client;

import reactor.core.publisher.Mono;


public interface ClientRepository extends ReactiveMongoRepository<Client, String>{
	@Query("{'bank' : ?0}")
	public Mono<Client> updateBank(String bank, String id);
}
