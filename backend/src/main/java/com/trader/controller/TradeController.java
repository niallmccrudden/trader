package com.trader.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.trader.dto.TradeDTO;
import com.trader.service.TradeService;

@RestController
public class TradeController {

    private TradeService tradeService;

    @Autowired
    public TradeController(TradeService tradeService){
        this.tradeService = tradeService;
    }

    @RequestMapping("/")
    public String home(){
        return "Home Endpoint";
    }

    @RequestMapping( value = "/", method = RequestMethod.POST )
    public ResponseEntity trade(@Validated @RequestBody TradeDTO tradeDTO, Errors errors){
        if (errors.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        this.tradeService.processTrade(tradeDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
