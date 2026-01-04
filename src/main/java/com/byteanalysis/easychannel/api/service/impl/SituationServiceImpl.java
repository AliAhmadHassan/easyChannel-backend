package com.byteanalysis.easychannel.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.Situation;
import com.byteanalysis.easychannel.api.repository.SituationRepository;
import com.byteanalysis.easychannel.api.service.SituationService;

@Component
public class SituationServiceImpl implements SituationService{

	@Autowired
	private SituationRepository repository;
	
	@Override
	public Situation createOrUpdate(Situation situation) {
		return this.repository.save(situation);
	}

	@Override
	public Situation findById(Integer id) {
		return this.repository.findOne(id);
	}

	@Override
	public Page<Situation> findAll(Integer page, Integer count) {
		Pageable pages = new PageRequest(page, count); 
		return this.repository.findAll(pages);
	}

	@Override
	public void delete(Integer id) {
		this.repository.delete(id);
	}

}
