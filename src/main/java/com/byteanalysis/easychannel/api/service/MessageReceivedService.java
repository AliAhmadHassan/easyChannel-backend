package com.byteanalysis.easychannel.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.MessageReceived;

@Component
public interface MessageReceivedService {

	MessageReceived createOrUpdate(MessageReceived messageReceived);
	
	MessageReceived findById(Integer id);
	
	Page<MessageReceived> findByUnreaded(Integer page, Integer count);
	
	Page<MessageReceived> findAll(Integer page, Integer count);
	
	Page<MessageReceived> findByFromId(Integer page, Integer count, Integer fromId);
	
	Page<MessageReceived> findByToId(Integer page, Integer count, Integer toId);
	
	Page<MessageReceived> findByUserId(Integer page, Integer count, Integer userId);
	
	void delete(Integer id);
}
