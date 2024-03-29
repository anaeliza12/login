package com.hacka.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacka.demo.entities.Login;
import com.hacka.demo.repository.LoginRepopsitory;

@Service
public class LoginService {

	@Autowired
	private LoginRepopsitory repository;

	public List<Login> findAll() {
		return repository.findAll();
	}

	public Login findById(Integer id) {
		return repository.findById(id).get();
	}

	public boolean findLogin(Login login) {
		boolean verify = false;
		for (Login l : repository.findAll()) {
			if (l.getEmail().equalsIgnoreCase(login.getEmail())
					&& l.getPassword().equalsIgnoreCase(login.getPassword())) {
				verify = true;
				break;
			}
		}

		return verify;
	}

}
