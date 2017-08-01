package com.trader.processor.service.impl;

import com.trader.dto.TradeDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.trader.processor.service.TradeProcessorService;

import javax.inject.Inject;
import javax.inject.Named;

@Service
public class TradeProcessorServiceImpl implements TradeProcessorService {

    private static final Logger log = LoggerFactory.getLogger(TradeProcessorServiceImpl.class);


    private final AmqpTemplate amqpTemplate;
    private final Exchange exchange;
    private final Queue queue;


    @Inject
    public TradeProcessorServiceImpl(@Named("orderProcessorTemplate") AmqpTemplate amqpTemplate,
                              @Named("orderProcessingExchange") Exchange exchange,
                              @Named("processedTradesQueue") Queue queue
    ) {
        this.amqpTemplate = amqpTemplate;
        this.exchange = exchange;
        this.queue = queue;
    }

    public void processTrade(TradeDTO tradeDTO) {
        log.info("Will submit to queue " + queue.getName());
        // Process message - For now will just update the time placed
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MMM-yy HH:mm:ss.S");
        Date now = new Date();
        tradeDTO.setTimePlaced(sdfDate.format(now));
        amqpTemplate.convertAndSend(exchange.getName(), queue.getName(), tradeDTO);
    }
}