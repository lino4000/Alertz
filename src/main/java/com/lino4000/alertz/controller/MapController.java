package com.lino4000.alertz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lino4000.alertz.dto.FlagResponse;
import com.lino4000.alertz.model.Flag;
import com.lino4000.alertz.model.FlagType;
import com.lino4000.alertz.service.MapService;

import lombok.extern.slf4j.Slf4j;



@Controller
@Slf4j
public class MapController {

	@Autowired
	private MapService mapService;
	
	@GetMapping("/map")
	public String map() {
		log.trace("==> Acessar mapa");
		log.trace("<== Mapa mostrado");
		return "map";
	}
	
	@PostMapping("/addFlag")
	@ResponseBody
	public List<Flag> addFlag(@RequestBody FlagResponse flag) {
		log.trace("==> Adicionar bandeira");
		log.trace("<== Bandeira adicionada");
		return mapService.addFlag(flag);
	}
	
	@GetMapping("/showFlags")
	@ResponseBody
	public List<Flag> showFlags(@RequestParam(required = false) List<FlagType> type) {
		log.trace("==> Filtrar bandeiras");
		log.trace("<== Bandeiras filtradas");
		return mapService.getFlags(type);
/*		List<Flag> flags = mapService.getFlags();
		if (flags == null) {
	        return ResponseEntity.notFound().build();
	    } else {
	        return ResponseEntity.ok(flags);
	    } 
*/	}
}