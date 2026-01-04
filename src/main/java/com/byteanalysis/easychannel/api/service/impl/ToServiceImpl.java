package com.byteanalysis.easychannel.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.To;
import com.byteanalysis.easychannel.api.repository.ToRepository;
import com.byteanalysis.easychannel.api.service.ToService;

@Component
public class ToServiceImpl implements ToService {

	@Autowired
	private ToRepository repository;
	
	@Override
	public To createOrUpdate(To to) {
		return this.repository.save(to);
	}

	@Override
	public To findById(Integer id) {
		return this.repository.findOne(id);
	}

	@Override
	public Page<To> findAll(Integer page, Integer count) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findAll(pages);
	}

	@Override
	public Page<To> findByUserPreferredId(Integer page, Integer count, Integer userId) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findByUserPreferredId(pages, userId);
	}

	@Override
	public Page<To> findByToTypeId(Integer page, Integer count, Integer toTypeId) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findByToTypeId(pages, toTypeId);
	}

	@Override
	public void delete(Integer id) {
		this.repository.delete(id);
	}

	@Override
	public To findByToKey(String toKey) {
		return this.repository.findByToKey(toKey);
	}

}
