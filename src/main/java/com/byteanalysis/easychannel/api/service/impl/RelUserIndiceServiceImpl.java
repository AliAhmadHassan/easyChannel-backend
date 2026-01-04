package com.byteanalysis.easychannel.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.RelUserIndice;
import com.byteanalysis.easychannel.api.repository.RelUserIndiceRepository;
import com.byteanalysis.easychannel.api.service.RelUserIndiceService;

@Component
public class RelUserIndiceServiceImpl implements RelUserIndiceService{

	@Autowired
	private RelUserIndiceRepository repository;
	
	@Override
	public RelUserIndice createOrUpdate(RelUserIndice relUserIndice) {
		return this.repository.save(relUserIndice);
	}

	@Override
	public RelUserIndice findById(Integer id) {
		return this.repository.findOne(id);
	}

	@Override
	public Page<RelUserIndice> findAll(Integer page, Integer count) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findAll(pages );
	}

	@Override
	public Page<RelUserIndice> findByUserId(Integer page, Integer count, Integer userId) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findByUserId(pages, userId);
	}

	@Override
	public Page<RelUserIndice> findByIndiceId(Integer page, Integer count, Integer indiceId) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findByIndiceId(pages, indiceId);
	}

	@Override
	public void delete(Integer id) {
		this.repository.delete(id);
	}

}
