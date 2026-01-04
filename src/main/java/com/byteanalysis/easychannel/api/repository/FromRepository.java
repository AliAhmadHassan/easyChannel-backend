package com.byteanalysis.easychannel.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.byteanalysis.easychannel.api.entity.From;

public interface FromRepository extends CrudRepository<From, Integer> {

	Page<From> findAll(Pageable page);
	
	From findByFromKey(String fromKey);
}
