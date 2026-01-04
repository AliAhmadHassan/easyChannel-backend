package com.byteanalysis.easychannel.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.Situation;

@Component
public interface SituationService {

	Situation createOrUpdate(Situation situation);
	
	Situation findById(Integer id);
	
	Page<Situation> findAll(Integer page, Integer count);
	
	void delete(Integer id);
}
