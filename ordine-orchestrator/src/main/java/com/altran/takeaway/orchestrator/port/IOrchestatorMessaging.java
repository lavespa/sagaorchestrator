package com.altran.takeaway.orchestrator.port;

import org.springframework.kafka.annotation.KafkaListener;

public interface IOrchestatorMessaging {
	
	String ORDER_TOPIC="ordineservice";

    @KafkaListener(topics = ORDER_TOPIC)
    void consumeMessage(String content);

}
