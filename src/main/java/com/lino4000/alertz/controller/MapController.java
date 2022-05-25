package com.lino4000.alertz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lino4000.alertz.dto.FlagResponse;
import com.lino4000.alertz.model.Flag;
import com.lino4000.alertz.model.FlagType;
import com.lino4000.alertz.service.MapService;
import com.lino4000.alertz.util.Compare;


@Controller
public class MapController {

	@Autowired
	private MapService mapService;
	
	@GetMapping("/map")
	public String map() {
		return "map";
	}

	@GetMapping("/flag**")
	@ResponseBody
	public List<Flag> showFlagsByDistance(@RequestParam(required = false) List<FlagType> type) {
				return mapService.getFlags(type);
	}

	@GetMapping("/flag/@{latitude},{longitude}/")
	@ResponseBody
	public List<Flag> showFlagsByDistance(
		@RequestParam(required = false) List<FlagType> type,
		@RequestParam Double distance, @PathVariable("latitude") Double latitude, @PathVariable("longitude") Double longitude) {
			if (Compare.anyNull(latitude,longitude,distance))
				return mapService.getFlags(type);
			else
				return mapService.getFlagsByDistance(latitude, longitude, distance, type);
	}

	@PostMapping("/flag")
	@ResponseBody
	public List<Flag> addFlag(@RequestBody FlagResponse flag) {
		return mapService.addFlag(flag);
	}
	
	@PutMapping("/flag")
	@ResponseBody
	public List<Flag> updateFlag(@RequestBody FlagResponse flag) {
		return mapService.addFlag(flag);
	}

	@DeleteMapping("/flag")
	@ResponseBody
	public List<Flag> deleteFlag(@RequestBody FlagResponse flag) {
		return mapService.addFlag(flag);
	}
}