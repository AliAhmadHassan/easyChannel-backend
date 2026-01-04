package com.byteanalysis.easychannel.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.ToType;
import com.byteanalysis.easychannel.api.repository.ToTypeRepository;
import com.byteanalysis.easychannel.api.service.ToTypeService;

@Component
public class ToTypeServiceImpl implements ToTypeService {

	@Autowired
	private ToTypeRepository repository;
	
	@Override
	public ToType createOrUpdate(ToType toType) {
		return this.repository.save(toType);
	}

	@Override
	public ToType findById(Integer id) {
		return this.repository.findOne(id);
	}

	@Override
	public Page<ToType> findAll(Integer page, Integer count) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findAll(pages );
	}

	@Override
	public void delete(Integer id) {
		this.repository.delete(id);
	}

}
