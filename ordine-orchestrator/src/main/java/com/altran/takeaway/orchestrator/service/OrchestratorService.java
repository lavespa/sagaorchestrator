package com.altran.takeaway.orchestrator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.altran.takeaway.orchestrator.bean.OrderDTO;

import reactor.core.publisher.Mono;

@Service
public class OrchestratorService {
	
	    @Autowired
	    @Qualifier("cucine")
	    private WebClient cucineClient;

	    @Autowired
	    @Qualifier("consegne")
	    private WebClient consegneClient;
	    
	    public OrderDTO bookingHamburghers(OrderDTO orderDto)
	    {
	    	Mono<OrderDTO> order = this.cucineClient
	    			              .post().uri("/cucine/bookingHamburghers")
	    			              .body(BodyInserters.fromValue(orderDto))
	    			              .retrieve()
	    			              .bodyToMono(OrderDTO.class);
	    	
	    	return order.block();
	    }
	    
	    public OrderDTO consegnaOrdine(OrderDTO orderDto)
	    {
	    	Mono<OrderDTO> deliveryOrder = this.consegneClient
			    			              .post().uri("/delivery/consegnaordine")
			    			              .body(BodyInserters.fromValue(orderDto))
			    			              .retrieve()
			    			              .bodyToMono(OrderDTO.class);
	    	
	    	return deliveryOrder.block();
	    }
	
	

}
