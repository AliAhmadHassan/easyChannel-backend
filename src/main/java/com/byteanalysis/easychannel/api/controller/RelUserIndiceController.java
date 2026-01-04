package com.byteanalysis.easychannel.api.controller;

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

import com.byteanalysis.easychannel.api.entity.RelUserIndice;
import com.byteanalysis.easychannel.api.response.Response;
import com.byteanalysis.easychannel.api.security.jwt.JwtTokenUtil;
import com.byteanalysis.easychannel.api.service.RelUserIndiceService;

@RestController
@RequestMapping("/api/relUserIndice")
@CrossOrigin(origins = "*")
public class RelUserIndiceController {

	@Autowired
	private RelUserIndiceService relUserIndiceService;
	
	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<RelUserIndice>> create(HttpServletRequest request, @RequestBody RelUserIndice relUserIndice,
			BindingResult result) {

		Response<RelUserIndice> response = new Response<RelUserIndice>();

		try {
			validateCreateUpdateRelUserIndice(relUserIndice, result);

			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			RelUserIndice relUserIndicePersisted = relUserIndiceService.createOrUpdate(relUserIndice);
			response.setData(relUserIndicePersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<RelUserIndice>> update(HttpServletRequest request, @RequestBody RelUserIndice relUserIndice, BindingResult result){
		Response<RelUserIndice> response = new Response<RelUserIndice>();
		
		try {
			validateCreateUpdateRelUserIndice(relUserIndice, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			RelUserIndice relUserIndicePersisted = relUserIndiceService.createOrUpdate(relUserIndice);
			response.setData(relUserIndicePersisted);
		}catch(Exception e){
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Response<RelUserIndice>> findById(@PathVariable("id") Integer id){
		Response<RelUserIndice> response = new Response<RelUserIndice>();
		
		RelUserIndice relUserIndice = null;
		
		relUserIndice = this.relUserIndiceService.findById(id);
		
		if (relUserIndice == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(relUserIndice);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<RelUserIndice>> delete(@PathVariable("id") Integer id){
		Response<RelUserIndice> response = new Response<RelUserIndice>();
		
		RelUserIndice relUserIndice = null;
		
		relUserIndice = this.relUserIndiceService.findById(id);
		
		if (relUserIndice == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.relUserIndiceService.delete(id);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<RelUserIndice>>> findAll(HttpServletRequest request,
			@PathVariable("page") Integer page, @PathVariable("count") Integer count) {
		Response<Page<RelUserIndice>> response = new Response<Page<RelUserIndice>>();

		Page<RelUserIndice> relUserIndice = null;
		relUserIndice = relUserIndiceService.findAll(page, count);

		response.setData(relUserIndice);
		return ResponseEntity.ok(response);
	}

	private void validateCreateUpdateRelUserIndice(RelUserIndice relUserIndice, BindingResult result) {
	}
}
