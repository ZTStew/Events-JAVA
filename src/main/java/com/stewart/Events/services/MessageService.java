package com.stewart.Events.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stewart.Events.models.Message;
import com.stewart.Events.repositories.MessageRepository;

@Service
public class MessageService {
	private final MessageRepository messageRepo;
	
	public MessageService(MessageRepository messageRepo) {
		this.messageRepo = messageRepo;
	}
	
//	C - Create
	public Message createMessage(Message message) {
		return messageRepo.save(message);
	}
	
//	R - Read

	public ArrayList<Message> findAllMessages(){
		return (ArrayList<Message>) messageRepo.findAll();
	}
	
	public Message findMessageById(Long id) {
		Optional<Message> optionalMessage = messageRepo.findMessageById(id);
		if(optionalMessage.isPresent()) {
			return optionalMessage.get();
		} else {
			return null;
		}
	}
	
//	U - Updated
	public Message updateMessage(Message message) {
		return messageRepo.save(message);
	}
	
//	D - Delete
	public void deleteMessageById(Long id) {
		messageRepo.deleteById(id);
	}
}
