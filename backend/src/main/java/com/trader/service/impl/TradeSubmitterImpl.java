package com.trader.service.impl;

import com.trader.service.TradeSubmitter;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;

import com.trader.dto.TradeDTO;

import javax.inject.Inject;
import javax.inject.Named;


@Service
public class TradeSubmitterImpl implements TradeSubmitter {

    private final AmqpTemplate amqpTemplate;
    private final Exchange exchange;
    private final Queue queue;

    private static final Logger log = LoggerFactory.getLogger(TradeSubmitterImpl.class);


    @Inject
    public TradeSubmitterImpl(@Named("orderProcessorTemplate") AmqpTemplate amqpTemplate,
                              @Named("orderProcessingExchange") Exchange exchange,
                              @Named("orderProcessingQueue") Queue queue
    ) {
        this.amqpTemplate = amqpTemplate;
        this.exchange = exchange;
        this.queue = queue;
    }

    public void submit(TradeDTO tradeDTO) {
        log.info("Submitting message to queue " + queue.getName());
        amqpTemplate.convertAndSend(exchange.getName(), queue.getName(), tradeDTO);
    }
}