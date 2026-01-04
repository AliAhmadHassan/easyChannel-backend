package com.byteanalysis.easychannel.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.To;

@Component
public interface ToService {
	To createOrUpdate(To to);
	
	To findById(Integer id);
	
	To findByToKey(String toKey);
	
	Page<To> findAll(Integer page, Integer count);
	
	Page<To> findByUserPreferredId(Integer page, Integer count, Integer userId);
	
	Page<To> findByToTypeId(Integer page, Integer count, Integer toTypeId);
	
	void delete(Integer id);
}
