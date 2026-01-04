package com.byteanalysis.easychannel.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.byteanalysis.easychannel.api.entity.RelUserIndice;

public interface RelUserIndiceRepository extends CrudRepository<RelUserIndice, Integer> {
	Page<RelUserIndice> findAll(Pageable page);
	
	Page<RelUserIndice> findByUserId(Pageable page, Integer userId);
	
	Page<RelUserIndice> findByIndiceId(Pageable page, Integer indiceId);
}
