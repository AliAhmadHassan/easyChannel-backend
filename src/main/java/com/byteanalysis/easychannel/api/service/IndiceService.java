package com.byteanalysis.easychannel.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.Indice;

@Component
public interface IndiceService {

	Indice createOrUpdate(Indice indice);
	
	Indice findById(Integer id);
	
	Page<Indice> findAll(Integer page, Integer count);
	
	void delete(Integer id);
}
