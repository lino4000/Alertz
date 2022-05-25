package com.lino4000.alertz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lino4000.alertz.model.Flag;
import com.lino4000.alertz.model.FlagType;

@Repository
public interface FlagRepository extends CrudRepository<Flag, Long>{

	public static final String HAVERSINE ="(6378137 * acos(cos(radians(:latitude)) * cos(radians(f.latitude)) *" +
	"cos(radians(f.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(f.latitude))))";
	
	public List<Flag> findAll();
	public List<Flag> findAllByTypeIn(List<FlagType> type);

	@Query("SELECT f FROM Flag f WHERE " + HAVERSINE + " < :distance ORDER BY "+ HAVERSINE + " DESC")
	public List<Flag> findAllByDistance(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("distance") double distance);

	@Query("SELECT f FROM Flag f WHERE f.type IN (:type) AND " + HAVERSINE + " < :distance ORDER BY "+ HAVERSINE + " DESC")
	public List<Flag> findAllByTypeAndDistance(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("distance") double distance, @Param("type") List<FlagType> type);

}
