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

import com.byteanalysis.easychannel.api.entity.To;
import com.byteanalysis.easychannel.api.response.Response;
import com.byteanalysis.easychannel.api.security.jwt.JwtTokenUtil;
import com.byteanalysis.easychannel.api.service.ToService;

@RestController
@RequestMapping("/api/to")
@CrossOrigin(origins = "*")
public class ToController {

	@Autowired
	private ToService toService;
	
	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	@PostMapping()
	public ResponseEntity<Response<To>> create(HttpServletRequest request, @RequestBody To to,
			BindingResult result) {

		Response<To> response = new Response<To>();

		try {
			validateCreateUpdateTo(to, result);

			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			To toPersisted = toService.createOrUpdate(to);
			response.setData(toPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}
	
	@PutMapping()
	public ResponseEntity<Response<To>> update(HttpServletRequest request, @RequestBody To to, BindingResult result){
		Response<To> response = new Response<To>();
		
		try {
			validateCreateUpdateTo(to, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			To toPersisted = toService.createOrUpdate(to);
			response.setData(toPersisted);
		}catch(Exception e){
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Response<To>> findById(@PathVariable("id") Integer id){
		Response<To> response = new Response<To>();
		
		To to = null;
		
		to = this.toService.findById(id);
		
		if (to == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(to);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{toKey}/toKey")
	public ResponseEntity<Response<To>> findByToKey(@PathVariable("toKey") String toKey){
		Response<To> response = new Response<To>();
		
		To to = null;
		
		to = this.toService.findByToKey(toKey);
		
		if (to == null) {
			response.getErrors().add("Register not found id: " + toKey);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(to);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<To>> delete(@PathVariable("id") Integer id){
		Response<To> response = new Response<To>();
		
		To to = null;
		
		to = this.toService.findById(id);
		
		if (to == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.toService.delete(id);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<To>>> findAll(HttpServletRequest request,
			@PathVariable("page") Integer page, @PathVariable("count") Integer count) {
		Response<Page<To>> response = new Response<Page<To>>();

		Page<To> to = null;
		to = toService.findAll(page, count);

		response.setData(to);
		return ResponseEntity.ok(response);
	}
	
	//findByUserPreferredId
	@GetMapping(value = "{page}/{count}/{userId}/userId")
	public ResponseEntity<Response<Page<To>>> findByUserPreferredId(HttpServletRequest request,
			@PathVariable("page") Integer page, @PathVariable("count") Integer count,
			@PathVariable("userId") Integer userId) {
		Response<Page<To>> response = new Response<Page<To>>();

		Page<To> to = null;
		to = toService.findByUserPreferredId(page, count, userId);

		response.setData(to);
		return ResponseEntity.ok(response);
	}

	private void validateCreateUpdateTo(To to, BindingResult result) {
		if(to.getToKey().equals("")) 
			result.addError(new ObjectError("To", "ToKey no information"));
	}
}
