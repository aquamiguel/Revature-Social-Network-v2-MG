package com.revature.controllers;


import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exception.ResourceNotFoundException;
import com.revature.beans.Users;
import com.revature.repos.UsersRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class UsersController {

	@Autowired
	private UsersRepository userRepo;
	
	@GetMapping("/users")
	public List<Users> getAllUsers() {
		
		return userRepo.findAll();
	}
	
	@PostMapping("/verify")
	public Users verifyLogin(@Valid @RequestBody Users loginUser) throws ResourceNotFoundException {
		System.out.println(loginUser.getEmail());
		List<Users> userList = getAllUsers();
		Users user = new Users();
		Iterator<Users> iterator = userList.iterator();
		while(iterator.hasNext()) {
			System.out.println(userList.iterator());
			user = iterator.next();
			System.out.println(user.getUserId());
			if(user.getEmail().equals(loginUser.getEmail()) && user.getPassword().equals(loginUser.getPassword())) {
				return user;
			}
		}
		throw new ResourceNotFoundException("Login incorrect");
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/users/{user_id}")
	public ResponseEntity<Users> getUserById(@PathVariable(value = "user_id") int userId)
			throws ResourceNotFoundException {
		Users user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found For This Id :: " + userId));
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping("/reset")
	public Users resetPassword(@Valid @RequestBody Users resetUser) throws ResourceNotFoundException {
		System.out.println(resetUser.getEmail());
		List<Users> userList = getAllUsers();
		Users user = new Users();
		Iterator<Users> iterator = userList.iterator();
		while(iterator.hasNext()) {
			System.out.println(userList.iterator());
			user = iterator.next();
			System.out.println(user.getUserId());
			if(user.getEmail().equals(resetUser.getEmail()) && user.getBirthDate().equals(resetUser.getBirthDate())) {
				user.setPassword(resetUser.getPassword());
				final Users updatedUser = userRepo.save(user);
				return updatedUser;
			}
		}
		throw new ResourceNotFoundException("Reset Password not possible");
	}
	
	@PostMapping("/adduser")
	public Users createUser(@Valid @RequestBody Users user) {
		return userRepo.save(user);
	}
	
	@PutMapping("/users/{user_id}")
	public ResponseEntity<Users> updateUser(@PathVariable(value = "user_id") int userId,
			@Valid @RequestBody Users userDetails) throws ResourceNotFoundException {
		Users user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found For This Id :: " + userId));
		
		user.setEmail(userDetails.getEmail());
		user.setPassword(userDetails.getPassword());
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setBirthDate(userDetails.getBirthDate());
		
		final Users updatedUser = userRepo.save(user);
		return ResponseEntity.ok(updatedUser);
	}
}
