package com.lino4000.alertz.dto;

import com.lino4000.alertz.model.FlagType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FlagResponse {
	
	public Double latitude;
	public Double longitude;
	public FlagType type;
}