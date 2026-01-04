package com.byteanalysis.easychannel.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.byteanalysis.easychannel.api.entity.ToType;

public interface ToTypeRepository extends CrudRepository<ToType, Integer> {
	
	Page<ToType> findAll(Pageable page);
}
