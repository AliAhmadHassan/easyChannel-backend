package com.byteanalysis.easychannel.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.MessageReceived;
import com.byteanalysis.easychannel.api.repository.MessageReceivedRepository;
import com.byteanalysis.easychannel.api.service.MessageReceivedService;

@Component
public class MessageReceivedServiceImpl implements MessageReceivedService{

	@Autowired
	private MessageReceivedRepository repository;
	
	@Override
	public MessageReceived createOrUpdate(MessageReceived messageReceived) {
		return this.repository.save(messageReceived);
	}

	@Override
	public MessageReceived findById(Integer id) {
		return this.repository.findOne(id);
	}

	@Override
	public Page<MessageReceived> findAll(Integer page, Integer count) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findAll(pages);
	}

	@Override
	public Page<MessageReceived> findByFromId(Integer page, Integer count, Integer fromId) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findByFromId(pages, fromId);
	}

	@Override
	public Page<MessageReceived> findByToId(Integer page, Integer count, Integer toId) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findByToId(pages, toId);
	}

	@Override
	public Page<MessageReceived> findByUserId(Integer page, Integer count, Integer userId) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findByUserId(pages, userId);
	}

	@Override
	public void delete(Integer id) {
		this.repository.delete(id);
	}

	@Override
	public Page<MessageReceived> findByUnreaded(Integer page, Integer count) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findByReaded(pages, false);
		
	}

}
