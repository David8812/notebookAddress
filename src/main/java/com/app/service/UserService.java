package com.app.service;

import java.util.List;

import com.app.model.User;

public interface UserService {

	public List<User> findAll();

	public User findByUserName(String nombre);

	public User save(User user);

	public void delete(User user);

	public User findById(Long id);

	public void deleteById(Long id);
}
