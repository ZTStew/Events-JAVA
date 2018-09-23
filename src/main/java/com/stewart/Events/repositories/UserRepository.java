package com.stewart.Events.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stewart.Events.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
	User findByEmail(String email);
	List<User> findAll();
	Optional<User> findUserById(Long id);
}
