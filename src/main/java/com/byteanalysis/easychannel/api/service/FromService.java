package com.byteanalysis.easychannel.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.From;

@Component
public interface FromService {

	From createOrUpdate(From from);
	
	From findById(Integer id);
	
	From findByFromKey(String fromKey);
	
	Page<From> findAll(Integer page, Integer count);
	
	void delete(Integer id);
}
