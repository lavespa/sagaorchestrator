package com.altran.takeaway.orchestrator.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.altran.takeaway.orchestrator.bean.OrderDTO;
import com.altran.takeaway.orchestrator.port.IOrchestratorPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrchestratorPublisher implements IOrchestratorPublisher {

	private final String TOPIC_ORDER_CALLBACK = "ordineservicecallback";

	@Autowired
	private ObjectMapper objectMapper;

	@SuppressWarnings("rawtypes")
	@Autowired
	private KafkaTemplate kafkaTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public void sendToOrderCallback(OrderDTO orderDTO) throws JsonProcessingException {

		kafkaTemplate.send(TOPIC_ORDER_CALLBACK, objectMapper.writeValueAsString(orderDTO));

	}

}
