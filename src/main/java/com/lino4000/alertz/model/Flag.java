package com.lino4000.alertz.model;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Flag {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	public double latitude;

	public double longitude;

	public FlagType type;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	public Date createdAt;

	public Date forecast;

	public Duration duration;

	@PrePersist
	private void onCreated(){
		createdAt = new Date();
	}
}
