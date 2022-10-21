package com.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.model.User;
import com.hotel.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> findedallUser() {

		return userRepository.findAll();
	}

	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public String addUsers() {
		userRepository.save(new User("teste", "teste", "+551112345678", "teste@teste.com"));
		userRepository.save(new User("teste2", "teste2", "+551112345678", "teste@teste.com"));
		userRepository.save(new User("teste3", "teste3", "+551112345678", "teste@teste.com"));
		return "User is added!";
	}
}
