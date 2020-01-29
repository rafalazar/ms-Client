package com.rafalazar.bootcamp.app.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection="clients")
public class Client {
	
	@Id
	private String id;
	private String fullName;
	private String numDoc;
	private String address;
	private String bank;
	private String type;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date joinAt;

}
