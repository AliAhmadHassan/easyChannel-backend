package com.byteanalysis.easychannel.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.From;
import com.byteanalysis.easychannel.api.repository.FromRepository;
import com.byteanalysis.easychannel.api.service.FromService;

@Component
public class FromServiceImpl implements FromService {

	@Autowired
	protected FromRepository repository;
	
	@Override
	public From createOrUpdate(From from) {
		return this.repository.save(from);
	}

	@Override
	public From findById(Integer id) {
		return this.repository.findOne(id);
	}

	@Override
	public Page<From> findAll(Integer page, Integer count) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findAll(pages);
	}

	@Override
	public void delete(Integer id) {
		this.delete(id);
	}

	@Override
	public From findByFromKey(String fromKey) {
		return this.repository.findByFromKey(fromKey);
	}

}
