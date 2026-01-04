package com.byteanalysis.easychannel.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.byteanalysis.easychannel.api.entity.Group;

public interface GroupRepository extends CrudRepository<Group, Integer> {

	Page<Group> findAll(Pageable page);
}
