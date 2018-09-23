package com.stewart.Events.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stewart.Events.models.Event;
import com.stewart.Events.repositories.EventRepository;

@Service
public class EventService {
	private final EventRepository eventRepo;
	
	public EventService(EventRepository eventRepo) {
		this.eventRepo = eventRepo;
	}

//	C - Create
	public Event createEvent(Event event) {
		return eventRepo.save(event);
	}
	
//	R - Read

	public ArrayList<Event> findAllEvents(){
		return (ArrayList<Event>) eventRepo.findAll();
	}
	
	public Event findEventById(Long id) {
		Optional<Event> optionalEvent = eventRepo.findEventById(id);
		if(optionalEvent.isPresent()) {
			return optionalEvent.get();
		} else {
			return null;
		}
	}
	
	public ArrayList<Event> findByState(String state){
		return (ArrayList<Event>) eventRepo.findByState(state);
	}
	
	public ArrayList<Event> findNotByState(String state){
		return (ArrayList<Event>) eventRepo.findNotByState(state);
	}
	
//	U - Updated
	public Event updateEvent(Event event) {
		return eventRepo.save(event);
	}
	
//	D - Delete
	public void deleteEventById(Long id) {
		eventRepo.deleteById(id);
	}
}
