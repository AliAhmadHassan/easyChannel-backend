package com.byteanalysis.easychannel.api.service;

import org.springframework.stereotype.Component;

import com.byteanalysis.easychannel.api.entity.Config;

@Component
public interface ConfigService{
	Config findById(Integer id);	
}
