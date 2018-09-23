package com.stewart.Events.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stewart.Events.models.Event;
import com.stewart.Events.models.Message;
import com.stewart.Events.models.User;
import com.stewart.Events.services.EventService;
import com.stewart.Events.services.MessageService;
import com.stewart.Events.services.UserService;

@Controller
@RequestMapping("/events")
public class EventsController {
	private EventService eventServ;
	private MessageService messageServ;
	private UserService userServ;
	
	public EventsController(EventService eventServ, MessageService messageServ, UserService userServ) {
		this.eventServ = eventServ;
		this.messageServ = messageServ;
		this.userServ = userServ;
	}
	
	@GetMapping("")
	public String main(@ModelAttribute("event") Event event, HttpSession session, Model model) {
		if(session.getAttribute("user") == null) {
			session.invalidate();
			return "redirect:/users/login";
		}
		Long id = (Long) session.getAttribute("user");
		User user = userServ.findUserById(id);
		
		ArrayList<Event> stateEvents = eventServ.findByState(user.getState());
		ArrayList<Event> notStateEvents = eventServ.findNotByState(user.getState());
		
		model.addAttribute("stateEvents", stateEvents);
		model.addAttribute("notStateEvents", notStateEvents);
		
		session.setAttribute("userObj", userServ.findUserById((long) session.getAttribute("user")));
		
		return "dashboard.jsp";
	}
	
	@PostMapping("/new")
	public String newEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "dashboard.jsp";
		}
		long user_id = (long) session.getAttribute("user");
		User host = userServ.findUserById(user_id);
		event.setUser(host);
		eventServ.createEvent(event);
		return "redirect:/events";
	}
	
	@GetMapping("/{id}")
	public String showEvent(@PathVariable("id") long id, @ModelAttribute("message") Message message, Model model, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/users/login";
		}
		model.addAttribute("event", eventServ.findEventById(id));
		return "eventInfo.jsp";
	}
	
	@PostMapping("/new/message/{id}")
	public String createComment(@Valid @ModelAttribute("message") Message message, HttpSession session, BindingResult result, @PathVariable("id") Long id) {
		if(result.hasErrors()) {
			return "redirect:/events/" + id;
		}
		
		message.setId(null);
		long user_id = (long) session.getAttribute("user");
		User commenter = userServ.findUserById(user_id);
		message.setUser(commenter);
		messageServ.createMessage(message);
		Event eve = eventServ.findEventById(id);
		message.setEvent(eve);
		eventServ.createEvent(eve);
		
		return "redirect:/events/" + id;
	}
	
	@RequestMapping("/join/{id}")
	public String join(@PathVariable("id") Long id, HttpSession session) {
		Event eve = eventServ.findEventById(id);
		Long user_id = (Long) session.getAttribute("user");
		User use = userServ.findUserById(user_id);
		
		List<User> users = eve.getUsers();

		if(!users.contains(use)){
			users.add(use);
			eve.setUsers(users);
			eventServ.updateEvent(eve);
		}

		return "redirect:/events";
	}
	
	@RequestMapping("/unjoin/info/{id}")
	public String unjoinInfo(@PathVariable("id") Long id, HttpSession session) {
		Event eve = eventServ.findEventById(id);
		Long user_id = (Long) session.getAttribute("user");
		User use = userServ.findUserById(user_id);
		
		List<User> users = eve.getUsers();

		if(users.contains(use)){
			users.remove(use);
			eve.setUsers(users);
			eventServ.updateEvent(eve);
		}

		return "redirect:/events/" + id;
	}
	
	@RequestMapping("/unjoin/{id}")
	public String unjoin(@PathVariable("id") Long id, HttpSession session) {
		Event eve = eventServ.findEventById(id);
		Long user_id = (Long) session.getAttribute("user");
		User use = userServ.findUserById(user_id);
		
		List<User> users = eve.getUsers();

		if(users.contains(use)){
			users.remove(use);
			eve.setUsers(users);
			eventServ.updateEvent(eve);
		}

		return "redirect:/events";
	}
	
	@GetMapping("/edit/{id}")
	public String rendEdit(@PathVariable("id") Long id, Model model, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/users/login";
		}
		model.addAttribute("event", eventServ.findEventById(id));
		return "edit.jsp";
	}
	
	@PostMapping("/edit/{id}")
	public String edit(@Valid @ModelAttribute("event") Event event, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "edit.jsp";
		} else {
			long user_id = (long) session.getAttribute("user");
			User host = userServ.findUserById(user_id);
			event.setUser(host);
			
			eventServ.updateEvent(event);
			return "redirect:/events";
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		System.out.println("Here at the end");
		eventServ.deleteEventById(id);
		return "redirect:/events";
	}

}
