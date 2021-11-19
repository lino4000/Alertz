package com.lino4000.alertz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lino4000.alertz.dto.FlagResponse;
import com.lino4000.alertz.model.Flag;
import com.lino4000.alertz.model.FlagType;
import com.lino4000.alertz.repository.FlagRepository;

@Service
public class MapService {
	
	@Autowired
	private FlagRepository flagRepository;
	
	
	public List<Flag> addFlag(FlagResponse flagResponse) {
		Flag flag = Flag.builder()
				.id(flagRepository.count()+1)
				.latitude(flagResponse.latitude)
				.longitude(flagResponse.longitude)
				.type(flagResponse.type)
				.build();
		flagRepository.save(flag);
		
		return flagRepository.findAll();
	}
	
	public List<Flag> getFlags(List<FlagType> type){
		if(null == type)
			return flagRepository.findAll();
		else
			return flagRepository.findAllByTypeIn(type);
	}

}
