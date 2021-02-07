package com.altran.takeaway.consegne.business;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altran.takeaway.consegne.bean.DeliveryDTO;
import com.altran.takeaway.consegne.bean.OrderDTO;
import com.altran.takeaway.consegne.bean.type.OrderStatusType;
import com.altran.takeaway.consegne.dao.DeliveryRepository;
import com.altran.takeaway.consegne.entity.Delivery;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class DeliveryService {
	
	private static final Logger logger = LoggerFactory.getLogger(DeliveryService.class);
	
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    
    @Autowired
    private ObjectMapper objectMapper;

    public List<DeliveryDTO> getAll()
    {
        List<Delivery> deliveryList  =deliveryRepository.findAll();
        List<DeliveryDTO> res=null;
        if(deliveryList!=null)
        {
            res=new ArrayList<>();
            for(Delivery delivery:deliveryList)
            {
                DeliveryDTO deliveryDTO=dozerBeanMapper.map(delivery,DeliveryDTO.class);
                res.add(deliveryDTO);
            }
        }
        return res;
    }
    
   public OrderDTO consegnaOrdine(OrderDTO orderDTO) {
		
		try {
			Delivery delivery = new Delivery();
			delivery.setAddressDTO(orderDTO.getAddressDTO());
			delivery.setOrderId(orderDTO.getId());
			
			//Salvo la consegna
			deliveryRepository.save(delivery);
			
			logger.info("Processing delivery id " + delivery.getId() + " for order id " + orderDTO.getId());
			Thread.sleep(5000);
			orderDTO.setOrderStatus(OrderStatusType.DELIVERED);
			orderDTO.setStatusDescription("Delivered");
			logger.info("Delivered order id " + orderDTO.getId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	   
	   return orderDTO;
	} 
}
