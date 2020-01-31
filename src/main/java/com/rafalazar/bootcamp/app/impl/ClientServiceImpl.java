package com.rafalazar.bootcamp.app.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafalazar.bootcamp.app.document.Client;
import com.rafalazar.bootcamp.app.repository.ClientRepository;
import com.rafalazar.bootcamp.app.service.ClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository repo;

	@Override
	public Flux<Client> findAll() {
		return repo.findAll();
	}

	@Override
	public Mono<Client> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public Mono<Client> save(Client cl) {
		if (cl.getJoinAt() == null) {
			cl.setJoinAt(new Date());
		}else {
			cl.setJoinAt(cl.getJoinAt());
		}
		
		return repo.save(cl);
	}

	//Esta es la manera correcta de actualizar
	@Override
	public Mono<Client> update(Client cl, String id) {
		return repo.findById(id)
				.flatMap(c -> {
					
					//Fecha joinAt
					if(cl.getJoinAt() == null) {
						c.setJoinAt(new Date());
					}else {
						c.setJoinAt(cl.getJoinAt());
					}
					
					//FullName
					if(cl.getFullName() == null) {
						c.setFullName(c.getFullName());
					}else {
						c.setFullName(cl.getFullName());
					}
					
					//NumDoc
					if(cl.getNumDoc() == null) {
						c.setNumDoc(c.getNumDoc());
					}else {
						c.setNumDoc(cl.getNumDoc());
					}
					
					//Address
					if(cl.getAddress() == null) {
						c.setAddress(c.getAddress());
					}else {
						c.setAddress(cl.getAddress());
					}
					
					//Bank
					if(cl.getBank() == null) {
						c.setBank(c.getBank());
					}else {
						c.setBank(cl.getBank());
					}
					
					//Type
					if(cl.getType() == null) {
						c.setType(c.getType());
					}else {
						c.setType(cl.getType());
					}
					
					return repo.save(c);
					
				});

	}

	@Override
	public Mono<Void> delete(Client c) {
		return repo.delete(c);
	}

	@Override
	public Mono<Client> updateBankById(String bank, String id) {
	
		return repo.findById(id)
				.flatMap(c -> {
					if(c.getBank() == null) {
						c.setBank(c.getBank());
					}else {
						c.setBank(bank);
					}
					
					return repo.save(c);
				});
	}

}
