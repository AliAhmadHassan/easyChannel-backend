package com.byteanalysis.easychannel.api.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.MessageToSend;
import com.byteanalysis.easychannel.api.repository.MessageToSendRepository;
import com.byteanalysis.easychannel.api.service.MessageToSendService;

@Component
public class MessageToSendServiceImpl implements MessageToSendService{

	@Autowired
	private MessageToSendRepository repository;
	
	@Override
	public MessageToSend createOrUpdate(MessageToSend messageToSend) {
		return this.repository.save(messageToSend);
	}

	@Override
	public MessageToSend findById(Integer id) {
		return this.repository.findOne(id);
	}

	@Override
	public Page<MessageToSend> findAll(Integer page, Integer count) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findAll(pages);
	}

	@Override
	public Page<MessageToSend> findByFromId(Integer page, Integer count, Integer fromId) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findByFromId(pages, fromId);
	}

	@Override
	public Page<MessageToSend> findByToId(Integer page, Integer count, Integer toId) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findByToId(pages, toId);
	}

	@Override
	public Page<MessageToSend> findByToShippingForecast(Integer page, Integer count, Date shippingForecast) {
		Pageable pages = new PageRequest(page, count);
		return this.repository.findByShippingForecast(pages, shippingForecast);
	}

	@Override
	public void delete(Integer id) {
		this.repository.delete(id);
	}

}
