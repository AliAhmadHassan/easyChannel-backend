package com.byteanalysis.easychannel.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.Group;
import com.byteanalysis.easychannel.api.repository.GroupRepository;
import com.byteanalysis.easychannel.api.service.GroupService;

@Component
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupRepository repository;
	
	@Override
	public Group createOrUpdate(Group group) {
		return this.repository.save(group);
	}

	@Override
	public Group findById(Integer id) {
		return this.repository.findOne(id);
	}

	@Override
	public Page<Group> findAll(Integer page, Integer count) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findAll(pages);
	}

	@Override
	public void delete(Integer id) {
		this.repository.delete(id);
	}

}
