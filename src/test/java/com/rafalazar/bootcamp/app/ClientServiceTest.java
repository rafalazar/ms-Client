package com.rafalazar.bootcamp.app;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.rafalazar.bootcamp.app.document.Client;
import com.rafalazar.bootcamp.app.service.ClientService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientServiceTest {

	@Autowired
	private WebTestClient client;
	
	@Autowired
	private ClientService service;
	
	@Test
	void findAllClientsTest() {
		client.get()
		.uri("/clients/findAll")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBodyList(Client.class)
		.consumeWith(res -> {
			List<Client> clients = res.getResponseBody();
			clients.forEach(c -> {
				System.out.println(c.getFullName());
			});
			
			Assertions.assertThat(clients.size()>0).isTrue();
		});
	}
	
	@Test
	void findByIdClient() {
		Client cli = service.findById("5e32e702f2b47b5a07945f5e").block();
		client.get().uri("/clients/findById/{id}",Collections.singletonMap("id", cli.getId()))
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON);
	}

}
