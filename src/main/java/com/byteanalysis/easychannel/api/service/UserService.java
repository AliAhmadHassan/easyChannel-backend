package com.byteanalysis.easychannel.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.*;

@Component
public interface UserService {
	User createOrUpdate(User user);
	
	User findById(Integer id);
	
	Page<User> findAll(Integer page, Integer count);
	
	Page<User> findByGroupId(Integer page, Integer count, Integer groupId);
	
	User findByUsername(String username);
	
	void delete(Integer id);
}
