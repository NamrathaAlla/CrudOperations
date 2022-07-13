package com.mss.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

	@Autowired
	UserService userService;

	// INSERT
	@PostMapping("/user")
	public Map<String, Object> insertingUser(@RequestBody UserEntity userEntity) {

		return userService.insertUser(userEntity);

	}

	// UPDATE
	@PutMapping("/user")
	public Map<String, Object> updatingUser(@RequestBody UserEntity userEntity) {

		return userService.updateUser(userEntity);

	}

	// DELETE
	@DeleteMapping("/user/{id}")
	public Map<String, Object> deletingUser(@PathVariable int id) {

		return userService.deleteUser(id);

	}

	// SELECT ALL
	@GetMapping("/user")
	public Map<String, Object> retrievingUser() {

		return userService.retrieveUser();

	}

	// SELECT BY ID
	@GetMapping("/user/{id}")
	public List<Map<String, Object>> selectingUser(@PathVariable int id) {

		return userService.selectUser(id);

	}
	
	
}
