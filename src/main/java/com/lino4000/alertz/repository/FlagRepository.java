package com.lino4000.alertz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lino4000.alertz.model.Flag;
import com.lino4000.alertz.model.FlagType;

@Repository
public interface FlagRepository extends CrudRepository<Flag, Long>{
	
//	public GeoResult<Flag> findByPlaceWithin(Point where);
	public List<Flag> findAll();
	public List<Flag> findAllByTypeIn(List<FlagType> type);

}
