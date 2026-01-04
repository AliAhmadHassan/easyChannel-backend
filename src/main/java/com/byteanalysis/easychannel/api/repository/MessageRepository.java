package com.byteanalysis.easychannel.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.byteanalysis.easychannel.api.entity.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {

	Page<Message> findByToId(Pageable page, Integer toId);
	
	Page<Message> findByFromId(Pageable page, Integer FromId);
}
