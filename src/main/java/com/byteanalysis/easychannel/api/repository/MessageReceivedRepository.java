package com.byteanalysis.easychannel.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.byteanalysis.easychannel.api.entity.MessageReceived;

public interface MessageReceivedRepository extends CrudRepository<MessageReceived, Integer> {
	
	Page<MessageReceived> findByFromId(Pageable page, Integer fromId);
	
	Page<MessageReceived> findByReaded(Pageable page, Boolean readed);
	
	Page<MessageReceived> findByToId(Pageable page, Integer toId);
	
	Page<MessageReceived> findByUserId(Pageable page, Integer userId);
	
	Page<MessageReceived> findAll(Pageable page);
}
