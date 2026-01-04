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

import com.byteanalysis.easychannel.api.entity.From;
import com.byteanalysis.easychannel.api.response.Response;
import com.byteanalysis.easychannel.api.security.jwt.JwtTokenUtil;
import com.byteanalysis.easychannel.api.service.FromService;

@RestController
@RequestMapping("/api/from")
@CrossOrigin(origins = "*")
public class FromController {

	@Autowired
	private FromService fromService;
	
	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<From>> create(HttpServletRequest request, @RequestBody From from,
			BindingResult result) {

		Response<From> response = new Response<From>();

		try {
			validateCreateUpdateFrom(from, result);

			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			From fromPersisted = fromService.createOrUpdate(from);
			response.setData(fromPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<From>> update(HttpServletRequest request, @RequestBody From from, BindingResult result){
		Response<From> response = new Response<From>();
		
		try {
			validateCreateUpdateFrom(from, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			From fromPersisted = fromService.createOrUpdate(from);
			response.setData(fromPersisted);
		}catch(Exception e){
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Response<From>> findById(@PathVariable("id") Integer id){
		Response<From> response = new Response<From>();
		
		From from = null;
		
		from = this.fromService.findById(id);
		
		if (from == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(from);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{fromKey}/fromKey")
	public ResponseEntity<Response<From>> findByFromKey(@PathVariable("fromKey") String fromKey){
		Response<From> response = new Response<From>();
		
		From from = null;
		
		from = this.fromService.findByFromKey(fromKey);
		
		if (from == null) {
			response.getErrors().add("Register not found id: " + fromKey);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(from);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<From>> delete(@PathVariable("id") Integer id){
		Response<From> response = new Response<From>();
		
		From from = null;
		
		from = this.fromService.findById(id);
		
		if (from == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.fromService.delete(id);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<From>>> findAll(HttpServletRequest request,
			@PathVariable("page") Integer page, @PathVariable("count") Integer count) {
		Response<Page<From>> response = new Response<Page<From>>();

		Page<From> from = null;
		from = fromService.findAll(page, count);

		response.setData(from);
		return ResponseEntity.ok(response);
	}

	private void validateCreateUpdateFrom(From from, BindingResult result) {
		if(from.getFromKey().equals("")) 
			result.addError(new ObjectError("From", "FromKey no information"));
	}
}
