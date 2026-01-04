package com.byteanalysis.easychannel.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.RelUserIndice;

@Component
public interface RelUserIndiceService {

	RelUserIndice createOrUpdate(RelUserIndice relUserIndice);
	
	RelUserIndice findById(Integer id);
	
	Page<RelUserIndice> findAll(Integer page, Integer count);
	
	Page<RelUserIndice> findByUserId(Integer page, Integer count, Integer userId);
	
	Page<RelUserIndice> findByIndiceId(Integer page, Integer count, Integer indiceId);
	
	void delete(Integer id);
}
