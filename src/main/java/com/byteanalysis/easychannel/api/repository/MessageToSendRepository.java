package com.byteanalysis.easychannel.api.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import com.byteanalysis.easychannel.api.entity.MessageToSend;

public interface MessageToSendRepository extends CrudRepository<MessageToSend, Integer> {
	Page<MessageToSend> findByFromId(Pageable page, Integer fromId);
	
	Page<MessageToSend> findByToId(Pageable page, Integer toId);
	
	@Query("Select mts "
			+ "from MessageToSend mts "
			+ "where sended = 0 "
			+ "and shippingForecast < :shippingForecast")
	Page<MessageToSend> findByShippingForecast(Pageable page, @PathVariable("shippingForecast") Date shippingForecast);

	Page<MessageToSend> findAll(Pageable page);
}
