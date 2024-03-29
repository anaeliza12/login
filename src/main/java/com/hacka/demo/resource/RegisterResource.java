package com.hacka.demo.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hacka.demo.entities.Register;
import com.hacka.demo.service.RegisterService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping(value = "/register")
public class RegisterResource {

	@Autowired
	private RegisterService service;

	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Register register) {
		if (service.insert(register) == true) {
			URI uri = ServletUriComponentsBuilder.fromPath("/{id}").buildAndExpand(register.getId()).toUri();
			return ResponseEntity.created(uri).body("Account created successfully.");
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Register> findById(@PathVariable Integer id) {
		Register register = service.findById(id);
		if (register == null) {
			return ResponseEntity.notFound().build();

		} else
			return ResponseEntity.ok(register);
	}
}
