package com.byteanalysis.easychannel.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.byteanalysis.easychannel.api.entity.Group;
import com.byteanalysis.easychannel.api.response.Response;
import com.byteanalysis.easychannel.api.security.jwt.JwtTokenUtil;
import com.byteanalysis.easychannel.api.service.GroupService;

@RestController
@RequestMapping("/api/group")
@CrossOrigin(origins = "*")
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Group>> create(HttpServletRequest request, @RequestBody Group group,
			BindingResult result) {

		Response<Group> response = new Response<Group>();

		try {
			validateCreateUpdateGroup(group, result);

			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Group groupPersisted = groupService.createOrUpdate(group);
			response.setData(groupPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Group>> update(HttpServletRequest request, @RequestBody Group group, BindingResult result){
		Response<Group> response = new Response<Group>();
		
		try {
			validateCreateUpdateGroup(group, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Group groupPersisted = groupService.createOrUpdate(group);
			response.setData(groupPersisted);
		}catch(Exception e){
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Response<Group>> findById(@PathVariable("id") Integer id){
		Response<Group> response = new Response<Group>();
		
		Group group = null;
		
		group = this.groupService.findById(id);
		
		if (group == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(group);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Group>> delete(@PathVariable("id") Integer id){
		Response<Group> response = new Response<Group>();
		
		Group group = null;
		
		group = this.groupService.findById(id);
		
		if (group == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.groupService.delete(id);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<Group>>> findAll(HttpServletRequest request,
			@PathVariable("page") Integer page, @PathVariable("count") Integer count) {
		Response<Page<Group>> response = new Response<Page<Group>>();

		Page<Group> group = null;
		group = groupService.findAll(page, count);

		response.setData(group);
		return ResponseEntity.ok(response);
	}

	private void validateCreateUpdateGroup(Group group, BindingResult result) {
		if(group.getName().equals("")) 
			result.addError(new ObjectError("Group", "Name no information"));
	}
}
