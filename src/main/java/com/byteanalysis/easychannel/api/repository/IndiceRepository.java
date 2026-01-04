package com.byteanalysis.easychannel.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.byteanalysis.easychannel.api.entity.Indice;

public interface IndiceRepository extends CrudRepository<Indice, Integer> {

	Page<Indice> findAll(Pageable page);
}
