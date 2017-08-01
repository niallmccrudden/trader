package com.trader.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import com.trader.processor.listener.TradeMessageListener;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    @Value("${order.processor.exchange.name:order-processing-exchange}")
    private String orderProcessingExchangeName;

    @Value("${order.processor.queue.name:order-processing-queue}")
    private String orderProcessingQueueName;

    @Value("${order.processor.request.timeout.ms:10000}")
    private Long orderProcessorRequestTimeout = 10000l;

    // Reuse the order processor property here
    @Value("${order.processor.queue.shutdown.timeout.ms:10000}")
    private long shutdownTimeoutMs;

    @Value("${processed.trades.queue.name:processed-trades-queue}")
    private String processedTradesQueueName;


    /** Queues **/

    @Bean(name = "orderProcessingQueue")
    public Queue orderProcessingQueue() {
        Map<String, Object> args = new HashMap<>();
        return new Queue(orderProcessingQueueName, true, false, false, args);
    }

    @Bean(name = "processedTradesQueue")
    public Queue processedTradesQueue() {
        Map<String, Object> args = new HashMap<>();
        return new Queue(processedTradesQueueName, true, false, false, args);
    }

    /** Exchanges **/


    @Bean(name = "orderProcessingExchange")
    public DirectExchange orderProcessingExchange() {
        return new DirectExchange(orderProcessingExchangeName);
    }

    /** Exchange bindings **/

    @Bean
    public Binding orderQueueToExchangeBinding() {
        return BindingBuilder.bind(orderProcessingQueue()).to(orderProcessingExchange()).with(orderProcessingQueueName);
    }

    /** Processor templates **/

    @Bean(name="orderProcessorTemplate")
    public RabbitTemplate orderProcessorTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);

        RetryTemplate retryTemplate = new RetryTemplate();
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();

        backOffPolicy.setInitialInterval(500);
        backOffPolicy.setMultiplier(10.0);
        backOffPolicy.setMaxInterval(10000);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        template.setRetryTemplate(retryTemplate);
        template.setReplyTimeout(orderProcessorRequestTimeout + 1000);

        return template;
    }

    @Bean
    public SimpleMessageListenerContainer orderProcessingMessageListenerContainer(ConnectionFactory connectionFactory,
                                                                                  TradeMessageListener tradeMessageListener) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("order-processing-queue");

        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(tradeMessageListener);
        messageListenerAdapter.setMessageConverter(null); // We want the raw Message
        container.setMessageListener(messageListenerAdapter);

        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setTxSize(1);
        container.setShutdownTimeout(shutdownTimeoutMs);

        return container;
    }

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
        messageConverter.setJsonObjectMapper(objectMapper);
        return messageConverter;
    }
}
