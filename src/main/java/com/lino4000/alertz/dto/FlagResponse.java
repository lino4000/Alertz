package com.lino4000.alertz.dto;

import com.lino4000.alertz.model.FlagType;

import lombok.Value;

@Value
public class FlagResponse {
	
	public Long latitude;
	public Long longitude;
	public FlagType type;
	
    public FlagResponse() {
        this.latitude = null;
		this.longitude = null;
		this.type = null;
    }
}