package com.rafalazar.bootcamp.app.service;

import com.rafalazar.bootcamp.app.document.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {
	
	//Listar todo
	public Flux<Client> findAll();
	//Listar por id
	public Mono<Client> findById(String id);
	//Crear cliente
	public Mono<Client> save(Client c);
	//Actualizar
	public Mono<Client> update(Client c, String id);
	//Eliminar
	public Mono<Void> delete(Client c);
	
	//Actualizar banco - experimental
	public Mono<Client> updateBank(String bank, String id);
	
}
