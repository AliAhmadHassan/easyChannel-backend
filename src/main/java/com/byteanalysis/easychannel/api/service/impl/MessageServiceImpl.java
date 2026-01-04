package com.byteanalysis.easychannel.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.Message;
import com.byteanalysis.easychannel.api.repository.MessageRepository;
import com.byteanalysis.easychannel.api.service.MessageService;

@Component
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageRepository repository;
	
	@Override
	public Message createOrUpdate(Message message) {
		return this.repository.save(message);
	}

	@Override
	public Message findById(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	public Page<Message> findByToId(Integer page, Integer count, Integer toId) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findByToId(pages, toId);
	}

	@Override
	public Page<Message> findByFromId(Integer page, Integer count, Integer fromId) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findByFromId(pages, fromId);
	}

	@Override
	public void delete(Long id) {
		this.repository.delete(id);
	}

}
