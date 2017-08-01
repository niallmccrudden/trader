package com.trader.processor.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.trader.processor.service.TradeProcessorService;
import com.trader.dto.TradeDTO;

import javax.inject.Inject;

@Component
public class TradeMessageListener {

    private final Logger logger = LoggerFactory.getLogger(TradeMessageListener.class);

    private final TradeProcessorService tradeProcessorService;
    private final MessageConverter messageConverter;

    @Inject
    public TradeMessageListener(TradeProcessorService tradeProcessorService, MessageConverter messageConverter) {
        this.tradeProcessorService = tradeProcessorService;
        this.messageConverter = messageConverter;
    }

    public void handleMessage(Message message) {
          TradeDTO tradeDTO = (TradeDTO) messageConverter.fromMessage(message);
          logger.info("Handling Message - " + tradeDTO.getAmountBuy());
          tradeProcessorService.processTrade(tradeDTO);
    }
}
