package com.stewart.Events.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stewart.Events.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event,Long>{
	List<Event> findAll();
	Optional<Event> findEventById(Long id);
	ArrayList<Event> findByState(String state);
	@Query(value="SELECT * FROM events WHERE state != ?1", nativeQuery=true)
	ArrayList<Event> findNotByState(String state);
}
