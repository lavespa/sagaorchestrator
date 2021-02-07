package com.altran.takeaway.consegne.port;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.altran.takeaway.consegne.bean.DeliveryDTO;
import com.altran.takeaway.consegne.bean.OrderDTO;


public interface IDeliveryApi {


    //@ApiOperation(value = "View delivery status", response = DeliveryDTO.class,responseContainer = "list")
    @RequestMapping(value = "status", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    List<DeliveryDTO> status();
    
    @RequestMapping(value = "consegnaordine", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    OrderDTO consegnaOrdine(@RequestBody OrderDTO orderDto);
}
