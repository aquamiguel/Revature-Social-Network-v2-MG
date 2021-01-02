package com.revature.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Posts;
import com.revature.beans.Users;
import com.revature.exception.ResourceNotFoundException;
import com.revature.repos.PostsRepository;
import com.revature.repos.UsersRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v3")
public class PostsController {

	@Autowired
	private PostsRepository postsRepo;
	
	@Autowired
	private UsersRepository userRepo;
	
	@GetMapping("/posts")
	public List<Posts> getAllPosts() {
		
		return postsRepo.findAll();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/posts/{post_id}")
	public ResponseEntity<Posts> getPostById(@PathVariable(value = "post_id") int postId)
			throws ResourceNotFoundException {
		Posts post = postsRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post Not Found For This Id :: " + postId));
		return ResponseEntity.ok().body(post);
	}
	
	
	@PostMapping("/addpost")
	public Posts createPost(@Valid @RequestBody Posts post) {
		post.getUser().getUserId();
		return postsRepo.save(post);
		//Users user = userRepo.findById(post.getUserId().);
	}
	
	@PutMapping("/posts/{post_id}")
	public ResponseEntity<Posts> updatePost(@PathVariable(value = "post_id") int postId,
			@Valid @RequestBody Posts postDetails) throws ResourceNotFoundException {
		Posts post = postsRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post Not Found For This Id :: " + postId));

		post.setUser(postDetails.getUser());
		post.setCountLikes(postDetails.getCountLikes());
		post.setPostText(postDetails.getPostText());
		post.setPostDate(postDetails.getPostDate());
		post.setPostMediaUrl(postDetails.getPostMediaUrl());
		
		final Posts updatedPost = postsRepo.save(post);
		return ResponseEntity.ok(updatedPost);
	}
	
	@DeleteMapping("/posts/{post_id}")
	public Map<String, Boolean> deletePost(@PathVariable(value = "post_id") int postId)
			throws ResourceNotFoundException {
		Posts post = postsRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post Not Found For This Id :: " + postId));

		postsRepo.delete(post);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
