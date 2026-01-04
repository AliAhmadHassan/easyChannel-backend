package com.byteanalysis.easychannel.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.Message;

@Component
public interface MessageService {

	Message createOrUpdate(Message message);
	
	Message findById(Long id);
	
	Page<Message> findByToId(Integer page, Integer count, Integer toId);
	
	Page<Message> findByFromId(Integer page, Integer count, Integer fromId);
	
	void delete(Long id);
}
