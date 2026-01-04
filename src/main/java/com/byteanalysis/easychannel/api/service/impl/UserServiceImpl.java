package com.byteanalysis.easychannel.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.User;
import com.byteanalysis.easychannel.api.repository.UserRepository;
import com.byteanalysis.easychannel.api.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public User createOrUpdate(User user) {
		return this.repository.save(user);
	}

	@Override
	public User findById(Integer id) {
		return this.repository.findOne(id);
	}

	@Override
	public Page<User> findAll(Integer page, Integer count) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findAll(pages );
	}

	@Override
	public Page<User> findByGroupId(Integer page, Integer count, Integer groupId) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findByGroupId(pages, groupId);
	}

	@Override
	public void delete(Integer id) {
		this.repository.delete(id);
	}

	@Override
	public User findByUsername(String username) {
		return this.repository.findByUsername(username);
	}

}
