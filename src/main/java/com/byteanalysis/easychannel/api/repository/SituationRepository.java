package com.byteanalysis.easychannel.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.byteanalysis.easychannel.api.entity.Situation;

public interface SituationRepository extends CrudRepository<Situation, Integer> {
	
	Page<Situation> findAll(Pageable page);
}
