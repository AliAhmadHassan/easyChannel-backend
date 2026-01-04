package com.byteanalysis.easychannel.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.ToType;

@Component
public interface ToTypeService {
	ToType createOrUpdate(ToType toType);
	
	ToType findById(Integer id);
	
	Page<ToType> findAll(Integer page, Integer count);
	
	void delete(Integer id);
}
