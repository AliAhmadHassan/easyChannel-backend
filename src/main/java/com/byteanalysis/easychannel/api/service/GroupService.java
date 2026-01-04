package com.byteanalysis.easychannel.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.Group;

@Component
public interface GroupService {

	Group createOrUpdate(Group group);
	
	Group findById(Integer id);
	
	Page<Group> findAll(Integer page, Integer count);
	
	void delete(Integer id);
}
