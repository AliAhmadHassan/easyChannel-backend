package com.byteanalysis.easychannel.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.Indice;
import com.byteanalysis.easychannel.api.repository.IndiceRepository;
import com.byteanalysis.easychannel.api.service.IndiceService;

@Component
public class IndiceServiceImpl implements IndiceService{

	@Autowired
	private IndiceRepository repository;
	
	@Override
	public Indice createOrUpdate(Indice indice) {
		return this.repository.save(indice);
	}

	@Override
	public Indice findById(Integer id) {
		return this.repository.findOne(id);
	}

	@Override
	public Page<Indice> findAll(Integer page, Integer count) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findAll(pages);
	}

	@Override
	public void delete(Integer id) {
		this.repository.delete(id);
	}

}
