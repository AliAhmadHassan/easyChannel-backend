package com.byteanalysis.easychannel.api.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.MessageToSend;

@Component
public interface MessageToSendService {

	MessageToSend createOrUpdate(MessageToSend messageToSend);
	
	MessageToSend findById(Integer id);
	
	Page<MessageToSend> findAll(Integer page, Integer count);
	
	Page<MessageToSend> findByFromId(Integer page, Integer count, Integer fromId);
	
	Page<MessageToSend> findByToId(Integer page, Integer count, Integer toId);
	
	Page<MessageToSend> findByToShippingForecast(Integer page, Integer count, Date shippingForecast);
	
	void delete(Integer id);
}
