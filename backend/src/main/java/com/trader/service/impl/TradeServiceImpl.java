package com.trader.service.impl;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.trader.dto.TradeDTO;
import com.trader.service.TradeService;
import com.trader.service.TradeSubmitter;

@Service
public class TradeServiceImpl implements TradeService {

    private static final Logger log = LoggerFactory.getLogger(TradeServiceImpl.class);

    private TradeSubmitter tradeSubmitter;

    @Autowired
    public TradeServiceImpl(TradeSubmitter tradeSubmitter){
        this.tradeSubmitter = tradeSubmitter;
    }

    public void processTrade(TradeDTO tradeDTO) {
        tradeSubmitter.submit(tradeDTO);
    }
}