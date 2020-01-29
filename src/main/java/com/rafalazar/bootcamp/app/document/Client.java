package com.rafalazar.bootcamp.app.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="clients")
public class Client {
	
	@Id
	private String id;
	private String name;
	private String lasName;
	private String dni;
	private String ruc;
	private String socialName;
	private String address;
	private String type;
	private Date joinAt;

}
