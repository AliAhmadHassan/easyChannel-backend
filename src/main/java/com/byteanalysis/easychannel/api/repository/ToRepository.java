package com.byteanalysis.easychannel.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.byteanalysis.easychannel.api.entity.To;

public interface ToRepository extends CrudRepository<To, Integer> {

	Page<To> findAll(Pageable page);
	
	To findByToKey(String toKey);
	
	Page<To> findByUserPreferredId(Pageable page, Integer userId);
	
	Page<To> findByToTypeId(Pageable page, Integer toTypeId);
}
