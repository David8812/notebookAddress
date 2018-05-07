package com.app.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findAll();
	
	User findByUserName(String username);
}
