package com.rafalazar.bootcamp.app.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafalazar.bootcamp.app.document.Client;
import com.rafalazar.bootcamp.app.service.ClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	
	//LISTAR TODOS LOS CLIENTES
	@GetMapping("/findAll")
	public Flux<Client> findAll(){
		return service.findAll();
	}
	
	//LISTAR UN CLIENTE POR SU ID
	@GetMapping("/findById/{id}")
	public Mono<Client> findById(@PathVariable("id") String id){
		return service.findById(id);
	}
	
	//CREAR UN CLIENTE
	@PostMapping("/create")
	public Mono<ResponseEntity<Client>> create(@RequestBody Client cl){
		return service.save(cl).map(c -> ResponseEntity.created(URI.create("/clients".concat(c.getId())))
				.contentType(MediaType.APPLICATION_JSON).body(c));
	}
	
	//ACTUALIZAR UN CLIENTE
	@PutMapping("/update/{id}")
	public Mono<ResponseEntity<Client>> update(@PathVariable("id") String id, @RequestBody Client cl){
		return service.update(cl, id)
				.map(c -> ResponseEntity.created(URI.create("/clients".concat(c.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(c))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	//ELIMINAR UN CLIENTE
	@DeleteMapping("/delete/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
		return service.findById(id)
				.flatMap(c -> {
					return service.delete(c)
							.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
				}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
	//ACTUALIZAR EL BANCO
	@PutMapping("/updateBank/{id}")
	public Mono<ResponseEntity<Client>> updateBankById(@PathVariable("id") String id, @RequestBody Client cl){
		return service.updateBankById(cl.getBank(), id)
				.map(c -> ResponseEntity.created(URI.create("/clients".concat(c.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(c))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	

}
