package com.byteanalysis.easychannel.api.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.byteanalysis.easychannel.api.entity.MessageReceived;
import com.byteanalysis.easychannel.api.entity.To;
import com.byteanalysis.easychannel.api.entity.User;
import com.byteanalysis.easychannel.api.response.Response;
import com.byteanalysis.easychannel.api.security.jwt.JwtTokenUtil;
import com.byteanalysis.easychannel.api.service.MessageReceivedService;
import com.byteanalysis.easychannel.api.service.ToService;
import com.byteanalysis.easychannel.api.service.UserService;

@RestController
@RequestMapping("/api/messageReceived")
@CrossOrigin(origins = "*")
public class MessageReceivedController {

	public static boolean novaMensagem = true;
	
	@Autowired
	private MessageReceivedService messageReceivedService;
	
	@Autowired
	private ToService toService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<MessageReceived>> create(HttpServletRequest request, @RequestBody MessageReceived messageReceived,
			BindingResult result) {

		Response<MessageReceived> response = new Response<MessageReceived>();

		try {
			validateCreateUpdateMessageReceived(messageReceived, result);

			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			MessageReceived messageReceivedPersisted = messageReceivedService.createOrUpdate(messageReceived);
			response.setData(messageReceivedPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		novaMensagem = true;
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<MessageReceived>> update(HttpServletRequest request, @RequestBody MessageReceived messageReceived, BindingResult result){
		Response<MessageReceived> response = new Response<MessageReceived>();
		
		try {
			validateCreateUpdateMessageReceived(messageReceived, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			MessageReceived messageReceivedPersisted = messageReceivedService.createOrUpdate(messageReceived);
			response.setData(messageReceivedPersisted);
		}catch(Exception e){
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		novaMensagem = true;
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("{id}/setReaded")
	public ResponseEntity<String> updateToReaded(HttpServletRequest request, @PathVariable("id") Integer id){
		
		for (MessageReceived messageReceived : this.messageReceivedService.findByToId(0, 100, id)) {
			if(!messageReceived.getReaded()) {
				messageReceived.setReaded(true);
				messageReceived.setReadedDate(new Date());
				
				this.messageReceivedService.createOrUpdate(messageReceived);
			}
		}
		
		To _to = toService.findById(id);
		_to.setUserPreferred(userFromRequest(request));
		
		toService.createOrUpdate(_to);
		
		return ResponseEntity.ok("ok");
	}
	
	
	
	@GetMapping("{id}")
	public ResponseEntity<Response<MessageReceived>> findById(@PathVariable("id") Integer id){
		Response<MessageReceived> response = new Response<MessageReceived>();
		
		MessageReceived messageReceived = null;
		
		messageReceived = this.messageReceivedService.findById(id);
		
		if (messageReceived == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(messageReceived);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{page}/{count}/unreaded")
	public ResponseEntity<Response<Page<MessageReceived>>> findByUnreaded(HttpServletRequest request,
			@PathVariable("page") Integer page, @PathVariable("count") Integer count) {
		
		Response<Page<MessageReceived>> response = new Response<Page<MessageReceived>>();
		
		if(!novaMensagem) {
			return ResponseEntity.ok(response);
		}
			
		
		

		Page<MessageReceived> messageReceived = null;
		messageReceived = messageReceivedService.findByUnreaded(page, count);

		if(messageReceived.getContent().size() == 0)
			novaMensagem = false;
		
		response.setData(messageReceived);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<MessageReceived>> delete(@PathVariable("id") Integer id){
		Response<MessageReceived> response = new Response<MessageReceived>();
		
		MessageReceived messageReceived = null;
		
		messageReceived = this.messageReceivedService.findById(id);
		
		if (messageReceived == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.messageReceivedService.delete(id);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<MessageReceived>>> findAll(HttpServletRequest request,
			@PathVariable("page") Integer page, @PathVariable("count") Integer count) {
		Response<Page<MessageReceived>> response = new Response<Page<MessageReceived>>();

		Page<MessageReceived> messageReceived = null;
		messageReceived = messageReceivedService.findAll(page, count);

		response.setData(messageReceived);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{page}/{count}/{fromId}/fromId")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<MessageReceived>>> findByFromId(HttpServletRequest request,
			@PathVariable("page") Integer page, @PathVariable("count") Integer count, 
			 @PathVariable("fromId") Integer fromId) {
		Response<Page<MessageReceived>> response = new Response<Page<MessageReceived>>();

		Page<MessageReceived> messageReceived = null;
		messageReceived = messageReceivedService.findByFromId(page, count, fromId);

		response.setData(messageReceived);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{page}/{count}/{toId}/toId")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<MessageReceived>>> findByToId(HttpServletRequest request,
			@PathVariable("page") Integer page, @PathVariable("count") Integer count, 
			 @PathVariable("toId") Integer toId) {
		Response<Page<MessageReceived>> response = new Response<Page<MessageReceived>>();

		Page<MessageReceived> messageReceived = null;
		messageReceived = messageReceivedService.findByToId(page, count, toId);

		response.setData(messageReceived);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{page}/{count}/{userId}/userId")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<MessageReceived>>> findByUserId(HttpServletRequest request,
			@PathVariable("page") Integer page, @PathVariable("count") Integer count, 
			 @PathVariable("userId") Integer userId) {
		Response<Page<MessageReceived>> response = new Response<Page<MessageReceived>>();

		Page<MessageReceived> messageReceived = null;
		messageReceived = messageReceivedService.findByUserId(page, count, userId);

		response.setData(messageReceived);
		return ResponseEntity.ok(response);
	}

	public User userFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String username = jwtTokenUtil.getUsernameFromToken(token);
		return userService.findByUsername(username);
	}
	
	private void validateCreateUpdateMessageReceived(MessageReceived messageReceived, BindingResult result) {
	}
}
