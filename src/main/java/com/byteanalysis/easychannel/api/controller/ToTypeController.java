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

import com.byteanalysis.easychannel.api.entity.ToType;
import com.byteanalysis.easychannel.api.response.Response;
import com.byteanalysis.easychannel.api.security.jwt.JwtTokenUtil;
import com.byteanalysis.easychannel.api.service.ToTypeService;

@RestController
@RequestMapping("/api/toType")
@CrossOrigin(origins = "*")
public class ToTypeController {

	@Autowired
	private ToTypeService toService;
	
	@Autowired
	protected JwtTokenUtil jwtToTypekenUtil;
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<ToType>> create(HttpServletRequest request, @RequestBody ToType to,
			BindingResult result) {

		Response<ToType> response = new Response<ToType>();

		try {
			validateCreateUpdateToType(to, result);

			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			ToType toPersisted = toService.createOrUpdate(to);
			response.setData(toPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<ToType>> update(HttpServletRequest request, @RequestBody ToType to, BindingResult result){
		Response<ToType> response = new Response<ToType>();
		
		try {
			validateCreateUpdateToType(to, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			ToType toPersisted = toService.createOrUpdate(to);
			response.setData(toPersisted);
		}catch(Exception e){
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Response<ToType>> findById(@PathVariable("id") Integer id){
		Response<ToType> response = new Response<ToType>();
		
		ToType to = null;
		
		to = this.toService.findById(id);
		
		if (to == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(to);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<ToType>> delete(@PathVariable("id") Integer id){
		Response<ToType> response = new Response<ToType>();
		
		ToType to = null;
		
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
	public ResponseEntity<Response<Page<ToType>>> findAll(HttpServletRequest request,
			@PathVariable("page") Integer page, @PathVariable("count") Integer count) {
		Response<Page<ToType>> response = new Response<Page<ToType>>();

		Page<ToType> to = null;
		to = toService.findAll(page, count);

		response.setData(to);
		return ResponseEntity.ok(response);
	}

	private void validateCreateUpdateToType(ToType to, BindingResult result) {
		if(to.getDescription().equals("")) 
			result.addError(new ObjectError("ToType", "Description no information"));
	}
}
