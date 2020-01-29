package com.rafalazar.bootcamp.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafalazar.bootcamp.app.document.Client;


public interface ClientRepository extends ReactiveMongoRepository<Client, String>{

}
