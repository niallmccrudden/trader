package com.trader.processor.service;

import com.trader.dto.TradeDTO;

public interface TradeProcessorService {

    void processTrade(TradeDTO tradeDTO);
}
