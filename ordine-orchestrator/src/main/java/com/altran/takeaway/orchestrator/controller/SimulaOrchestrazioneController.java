package com.altran.takeaway.orchestrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.altran.takeaway.orchestrator.bean.OrderDTO;
import com.altran.takeaway.orchestrator.service.OrchestratorService;

@RestController
@RequestMapping("/orchcontroller/")
public class SimulaOrchestrazioneController {
	
@Autowired
private OrchestratorService orchestratorService;

@RequestMapping(value = "bookingHamburghers", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
public OrderDTO testBookHamburghers(@RequestBody OrderDTO orderDTO) {
	
	return orchestratorService.bookingHamburghers(orderDTO);
}


}
