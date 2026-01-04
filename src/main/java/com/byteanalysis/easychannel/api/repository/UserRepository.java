package com.byteanalysis.easychannel.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.byteanalysis.easychannel.api.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	Page<User> findAll(Pageable page);
	
	Page<User> findByGroupId(Pageable page, Integer groupId);

	User findByUsername(String username);
}
