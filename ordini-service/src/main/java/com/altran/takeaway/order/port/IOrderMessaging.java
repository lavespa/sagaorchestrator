package com.altran.takeaway.order.port;

import org.springframework.kafka.annotation.KafkaListener;

public interface IOrderMessaging {

    String TOPIC_ORDER_CALLBACK ="ordineservicecallback";

    @KafkaListener(topics = TOPIC_ORDER_CALLBACK)
    void callback(String message);
}
