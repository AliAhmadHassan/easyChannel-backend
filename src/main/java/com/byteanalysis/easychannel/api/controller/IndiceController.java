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

import com.byteanalysis.easychannel.api.entity.Indice;
import com.byteanalysis.easychannel.api.response.Response;
import com.byteanalysis.easychannel.api.security.jwt.JwtTokenUtil;
import com.byteanalysis.easychannel.api.service.IndiceService;

@RestController
@RequestMapping("/api/indice")
@CrossOrigin(origins = "*")
public class IndiceController {

	@Autowired
	private IndiceService indiceService;
	
	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Indice>> create(HttpServletRequest request, @RequestBody Indice indice,
			BindingResult result) {

		Response<Indice> response = new Response<Indice>();

		try {
			validateCreateUpdateIndice(indice, result);

			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Indice indicePersisted = indiceService.createOrUpdate(indice);
			response.setData(indicePersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Indice>> update(HttpServletRequest request, @RequestBody Indice indice, BindingResult result){
		Response<Indice> response = new Response<Indice>();
		
		try {
			validateCreateUpdateIndice(indice, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Indice indicePersisted = indiceService.createOrUpdate(indice);
			response.setData(indicePersisted);
		}catch(Exception e){
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Response<Indice>> findById(@PathVariable("id") Integer id){
		Response<Indice> response = new Response<Indice>();
		
		Indice indice = null;
		
		indice = this.indiceService.findById(id);
		
		if (indice == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(indice);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Indice>> delete(@PathVariable("id") Integer id){
		Response<Indice> response = new Response<Indice>();
		
		Indice indice = null;
		
		indice = this.indiceService.findById(id);
		
		if (indice == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.indiceService.delete(id);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<Indice>>> findAll(HttpServletRequest request,
			@PathVariable("page") Integer page, @PathVariable("count") Integer count) {
		Response<Page<Indice>> response = new Response<Page<Indice>>();

		Page<Indice> indice = null;
		indice = indiceService.findAll(page, count);

		response.setData(indice);
		return ResponseEntity.ok(response);
	}

	private void validateCreateUpdateIndice(Indice indice, BindingResult result) {
		if(indice.getName().equals("")) 
			result.addError(new ObjectError("Indice", "Name no information"));
	}
}
