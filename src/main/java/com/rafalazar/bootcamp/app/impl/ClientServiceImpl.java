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
public class ClientServiceImpl implements ClientService{

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
		if(cl.getJoinAt() == null) {
			cl.setJoinAt(new Date());
		}
		return repo.save(cl);
	}

	@Override
	public Mono<Client> update(Client cl, String id) {
		return repo.findById(id)
				.flatMap(c -> {
					if(cl.getJoinAt() == null) {
						c.setJoinAt(new Date());
					}
					
					if(c.getType() == "Personal") {
						c.setName(cl.getName());
						c.setLasName(cl.getLasName());
						c.setDni(cl.getDni());
						c.setAddress(cl.getAddress());
						c.setType(cl.getType());
					}else {
						c.setRuc(cl.getRuc());
						c.setSocialName(cl.getSocialName());
						c.setAddress(cl.getAddress());
						c.setType(cl.getType());
					}
					
					return repo.save(c);
					
				});
	}

	@Override
	public Mono<Void> delete(Client c) {
		return repo.delete(c);
	}

}
