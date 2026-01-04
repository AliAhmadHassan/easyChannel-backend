package com.byteanalysis.easychannel.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.Config;
import com.byteanalysis.easychannel.api.repository.ConfigRepository;
import com.byteanalysis.easychannel.api.service.ConfigService;

@Component
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	private ConfigRepository repository;
	
	@Override
	public Config findById(Integer id) {
		return this.repository.findOne(id);
	}

	
}
