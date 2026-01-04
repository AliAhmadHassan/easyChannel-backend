package com.byteanalysis.easychannel.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.byteanalysis.easychannel.api.entity.Config;
import com.byteanalysis.easychannel.api.response.Response;
import com.byteanalysis.easychannel.api.service.ConfigService;

@RestController
@RequestMapping("/api/config")
@CrossOrigin(origins = "*")
public class ConfigController {
	
	@Autowired
	private ConfigService configService;
	
	@GetMapping("{id}")
	public ResponseEntity<Response<Config>> findById(@PathVariable("id") Integer id){
		Response<Config> response = new Response<Config>();
		
		Config config = null;
		
		config = this.configService.findById(id);
		
		if (config == null) {
			response.getErrors().add("Register not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(config);
		
		return ResponseEntity.ok(response);
	}
}
