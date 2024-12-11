package andersen.dev.tickets.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import andersen.dev.tickets.model.User;
import andersen.dev.tickets.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping ("/api/")
@RestController
public class AppController {
	private final UserService userService;
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable ("id") Integer id){
		return userService.getUserByIdWithTickets(id);
	}
	
}
