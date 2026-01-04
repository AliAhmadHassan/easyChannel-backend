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

import com.byteanalysis.easychannel.api.entity.Situation;
import com.byteanalysis.easychannel.api.response.Response;
import com.byteanalysis.easychannel.api.security.jwt.JwtTokenUtil;
import com.byteanalysis.easychannel.api.service.SituationService;

@RestController
@RequestMapping("/api/situation")
@CrossOrigin(origins = "*")
public class SituationController {

	@Autowired
	private SituationService situationService;
	
	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Situation>> create(HttpServletRequest request, @RequestBody Situation situation,
			BindingResult result) {

		Response<Situation> response = new Response<Situation>();

		try {
			validateCreateUpdateSituation(situation, result);

			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Situation situationPersisted = situationService.createOrUpdate(situation);
			response.setData(situationPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Situation>> update(HttpServletRequest request, @RequestBody Situation situation, BindingResult result){
		Response<Situation> response = new Response<Situation>();
		
		try {
			validateCreateUpdateSituation(situation, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Situation situationPersisted = situationService.createOrUpdate(situation);
			response.setData(situationPersisted);
		}catch(Exception e){
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Response<Situation>> findById(@PathVariable("id") Integer id){
		Response<Situation> response = new Response<Situation>();
		
		Situation situation = null;
		
		situation = this.situationService.findById(id);
		
		if (situation == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(situation);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Situation>> delete(@PathVariable("id") Integer id){
		Response<Situation> response = new Response<Situation>();
		
		Situation situation = null;
		
		situation = this.situationService.findById(id);
		
		if (situation == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.situationService.delete(id);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<Situation>>> findAll(HttpServletRequest request,
			@PathVariable("page") Integer page, @PathVariable("count") Integer count) {
		Response<Page<Situation>> response = new Response<Page<Situation>>();

		Page<Situation> situation = null;
		situation = situationService.findAll(page, count);

		response.setData(situation);
		return ResponseEntity.ok(response);
	}

	private void validateCreateUpdateSituation(Situation situation, BindingResult result) {
		if(situation.getName().equals("")) 
			result.addError(new ObjectError("Situation", "Name no information"));
	}
}
