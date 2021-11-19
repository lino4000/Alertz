package com.lino4000.alertz.model;

import javax.persistence.Entity;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Flag {
	
	@Id
	private long id;
	
	public double latitude;

	public double longitude;

	public FlagType type;
}
