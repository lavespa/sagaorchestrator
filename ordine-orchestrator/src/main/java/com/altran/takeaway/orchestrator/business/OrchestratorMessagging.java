package com.altran.takeaway.orchestrator.business;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altran.takeaway.orchestrator.bean.OrderDTO;
import com.altran.takeaway.orchestrator.bean.type.OrderStatusType;
import com.altran.takeaway.orchestrator.messaging.OrchestratorPublisher;
import com.altran.takeaway.orchestrator.port.IOrchestatorMessaging;
import com.altran.takeaway.orchestrator.service.OrchestratorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrchestratorMessagging implements IOrchestatorMessaging {
	
	 private static final Logger logger = LoggerFactory.getLogger(OrchestratorMessagging.class);
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Autowired
	private OrchestratorService orchestratorService;
	
	@Autowired
	private OrchestratorPublisher orchestratorPublisher;

	@Override
	public void consumeMessage(String content) {
		
		try {
			
            OrderDTO orderDTO=objectMapper.readValue(content, OrderDTO.class);
            //Processo l'ordine nella cucina e controllo se c'è disponibilità
            orderDTO = orchestratorService.bookingHamburghers(orderDTO);
            
          //Invio comunicazione messaggio all'ordine
            orchestratorPublisher.sendToOrderCallback(orderDTO);
            
            //Controllo lo stato dell'ordine: se c'è disponibilità procedo con
            // il servizio di consegne altrimenti procedo con l'ABORT
            String statusOrder = orderDTO.getOrderStatus().name();
            
            if(!statusOrder.equals(OrderStatusType.ABORTED.name()))
            {
            	//Ordine in preparazione(COOKING)
            	logger.info("Start cooking for order id "+orderDTO.getId()+" start");
            	
                Thread.sleep(5000);
                logger.info("Packaging start");
                orderDTO.setOrderStatus(OrderStatusType.PACKAGING);
                orderDTO.setStatusDescription("Order in packaging");
                
                orchestratorPublisher.sendToOrderCallback(orderDTO);
                
                //Preparo la spedizione
                OrderDTO deliveryOrderDto = orchestratorService.consegnaOrdine(orderDTO);
                logger.info("Order id "+orderDTO.getId()+" sent to delivery");
                
                orchestratorPublisher.sendToOrderCallback(deliveryOrderDto);
                
            }
            
            	
            
            
		}
		catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
		
		
		
	}

}
