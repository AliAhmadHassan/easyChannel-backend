package com.byteanalysis.easychannel.api.controller;

import java.util.Date;

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

import com.byteanalysis.easychannel.api.entity.Message;
import com.byteanalysis.easychannel.api.entity.MessageReceived;
import com.byteanalysis.easychannel.api.response.Response;
import com.byteanalysis.easychannel.api.security.jwt.JwtTokenUtil;
import com.byteanalysis.easychannel.api.service.MessageReceivedService;
import com.byteanalysis.easychannel.api.service.MessageService;

@RestController
@RequestMapping("/api/message")
@CrossOrigin(origins = "*")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Message>> create(HttpServletRequest request, @RequestBody Message message,
			BindingResult result) {

		Response<Message> response = new Response<Message>();

		try {
			validateCreateUpdateMessage(message, result);

			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			
			Message messagePersisted = messageService.createOrUpdate(message);
			response.setData(messagePersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}
	
	@PutMapping()
	public ResponseEntity<Response<Message>> update(HttpServletRequest request, @RequestBody Message message, BindingResult result){
		Response<Message> response = new Response<Message>();
		
		try {
			validateCreateUpdateMessage(message, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Message messagePersisted = messageService.createOrUpdate(message);
			response.setData(messagePersisted);
		}catch(Exception e){
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Response<Message>> findById(@PathVariable("id") Long id){
		Response<Message> response = new Response<Message>();
		
		Message message = null;
		
		message = this.messageService.findById(id);
		
		if (message == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(message);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Message>> delete(@PathVariable("id") Long id){
		Response<Message> response = new Response<Message>();
		
		Message message = null;
		
		message = this.messageService.findById(id);
		
		if (message == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.messageService.delete(id);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{page}/{count}/{toId}/toId")
	public ResponseEntity<Response<Page<Message>>> findByToId(HttpServletRequest request,
			@PathVariable("page") Integer page, @PathVariable("count") Integer count, @PathVariable("toId") Integer toId) {
	
		Response<Page<Message>> response = new Response<Page<Message>>();

		Page<Message> message = null;
		message = messageService.findByToId(page, count, toId);

		response.setData(message);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{page}/{count}/{fromId}/fromId")
	public ResponseEntity<Response<Page<Message>>> findByFromId(HttpServletRequest request,
			@PathVariable("page") Integer page, @PathVariable("count") Integer count, @PathVariable("fromId") Integer fromId) {
		Response<Page<Message>> response = new Response<Page<Message>>();

		Page<Message> message = null;
		message = messageService.findByFromId(page, count, fromId);

		response.setData(message);
		return ResponseEntity.ok(response);
	}
	
	private void validateCreateUpdateMessage(Message message, BindingResult result) {
		if(message.getText().equals("")) 
			result.addError(new ObjectError("Message", "Text no information"));
	}
}
