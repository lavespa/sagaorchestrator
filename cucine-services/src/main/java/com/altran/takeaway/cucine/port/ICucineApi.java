package com.altran.takeaway.cucine.port;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.altran.takeaway.cucine.bean.HamburgerDTO;
import com.altran.takeaway.cucine.bean.OrderDTO;

import io.swagger.annotations.ApiOperation;


public interface ICucineApi {


    @ApiOperation(value = "View kitchen status", response = HamburgerDTO.class,responseContainer = "list")
    @RequestMapping(value = "status", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    List<HamburgerDTO> status();

    @ApiOperation(value = "Add hamburger", response = Void.class)
    @RequestMapping(value = "add", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    void addHamburger(HamburgerDTO hamburgerDTO);
    
    @ApiOperation(value = "Booking hamburgher", response = Void.class)
    @RequestMapping(value = "bookingHamburghers", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    OrderDTO bookHamburghers(@RequestBody OrderDTO orderDTO);
}
