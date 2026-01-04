package com.byteanalysis.easychannel.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.byteanalysis.easychannel.api.entity.User;
import com.byteanalysis.easychannel.api.response.Response;
import com.byteanalysis.easychannel.api.security.enums.ProfileEnum;
import com.byteanalysis.easychannel.api.security.jwt.JwtTokenUtil;

import com.byteanalysis.easychannel.api.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	protected JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping()
	@PreAuthorize("hasAnyRole('CONSUMER', 'STORE', 'ADMIN')")
	public ResponseEntity<Response<User>> create(HttpServletRequest request, @RequestBody User user,
			BindingResult result) {

		Response<User> response = new Response<User>();

		try {
			validateCreateUser(user, result);

			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User userPersisted = userService.createOrUpdate(user);

			response.setData(userPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	@PutMapping()
	@PreAuthorize("hasAnyRole('CONSUMER', 'STORE', 'ADMIN')")
	public ResponseEntity<Response<User>> update(HttpServletRequest request, @RequestBody User user,
			BindingResult result) {

		Response<User> response = new Response<User>();

		try {
			validateUpdateUser(user, result);
			User userRequest = userFromRequest(request);
			
			if (!userRequest.getId().equals(user.getId()) && !userRequest.getProfile().equals(ProfileEnum.ROLE_ADMIN)) {
				result.addError(new ObjectError("User", "Ids diferents"));
				response.getErrors().add("ids not correct");
				return ResponseEntity.badRequest().body(response);
			}
			
			fixUpdateUser(user, userRequest);

			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}

			if (userRequest.getProfile().equals(ProfileEnum.ROLE_ATTENDANT)) {
				result.addError(new ObjectError("User", "Access Denied"));
				response.getErrors().add("Access Denied");
				return ResponseEntity.badRequest().body(response);
			} 
			
			User userPersisted = userService.createOrUpdate(user);

			response.setData(userPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	// @PreAuthorize("hasAnyRole('CONSUMER', 'STORE')")
	@PreAuthorize("hasAnyRole('STORE', 'CONSUMER', 'ADMIN')")
	public ResponseEntity<Response<User>> findById(@PathVariable("id") Integer id) {
		Response<User> response = new Response<User>();
		User user = userService.findById(id);
		if (user == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(user);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<User>>> findAll(HttpServletRequest request,
			@PathVariable("page") Integer page, @PathVariable("count") Integer count) {
		Response<Page<User>> response = new Response<Page<User>>();

		Page<User> users = null;

		User userRequest = userFromRequest(request);

		if (userRequest.getProfile().equals(ProfileEnum.ROLE_ADMIN)) {
			users = userService.findAll(page, count);
		}
		response.setData(users);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") Integer id){
		Response<String> response = new Response<String>();
		User user = userService.findById(id);
		if (user == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		userService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	

	private void fixUpdateUser(User user, User userRequest) {
		if(user.getName().equals(""))
			user.setName(userRequest.getName());
		if(user.getUsername().equals(""))
			user.setUsername(userRequest.getUsername());
		if(user.getPassword().equals(""))
			user.setPassword(userRequest.getPassword());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	}

	private void validateUpdateUser(User user, BindingResult result) {
		if(user.getId() == null) {
			result.addError(new ObjectError("User", "Id no information"));
		} else if(user.getUsername() == null) {
			result.addError(new ObjectError("User", "Username no information"));
		} 

	}

	private void validateCreateUser(User user, BindingResult result) {
		/*if(user.getCity() == null) {
			result.addError(new ObjectError("User", "City no information"));
		} else if(user.getEmail() == null) {
			result.addError(new ObjectError("User", "E-Mail no information"));
		} else if(user.getIdentify() == null) {
			result.addError(new ObjectError("User", "Indentity no information"));
		} else if(user.getPassword() == null) {
			result.addError(new ObjectError("User", "Password no information"));
		} else if(user.getName() == null) {
			result.addError(new ObjectError("User", "Name no information"));
		}*/
	}
	
	public User userFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String username = jwtTokenUtil.getUsernameFromToken(token);
		return userService.findByUsername(username);
	}
}
