package com.altran.takeaway.orchestrator.port;

import com.altran.takeaway.orchestrator.bean.OrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IOrchestratorPublisher {
	
	void sendToOrderCallback(OrderDTO orderDTO) throws JsonProcessingException;

}
