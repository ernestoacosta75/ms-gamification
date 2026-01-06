package microservices.book.gamification.infrastructure.adapter.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

import java.time.Duration;

@Configuration
public class AMQPConfig {
    /**
     * This bean declares the central exchange in RabbitMQ. The exchange is the entry point
     * where other microservices send their events.
     * @param exchangeName
     * @return
     */
    @Bean
    public TopicExchange challengesTopicExchange(@Value("${amqp.exchange.attempts}") String exchangeName) {
        return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
    }

    /**
     * This bean declares the queue that will receive messages related to the Gamification microservice.
     * The queue holds the messages until the Gamification application is ready to process them.
     * durable(true) ensures that the queue will survive broker restarts, protecting messages that haven't
     * been processed yet.
     * @param queueName
     * @return
     */
    @Bean
    public Queue gamificationQueue(@Value("${amqp.queue.gamification}") String queueName) {
        // Configuring the queue to have a custom TTL of six hours and a maximum length of 25,000 messages.
        // These parameters will be configured as apolicy in the RabbitMQ broker instead of here in the code.
        return QueueBuilder.durable(queueName)
                .build();
    }

    /**
     * This bean creates a binding, which is the rule that links the Exchange to the Queue.
     * This specific binding tells RabbitMQ: "Take any message sent to the 'attemptsExchange'
     * that has a routing key of 'attempt.correct' and deliver it to the 'gamificationQueue'."
     * @param gamificationQueue
     * @param attemptsExchange
     * @return
     */
    @Bean
    public Binding correctAttemptsBinding(final Queue gamificationQueue,
                                          final TopicExchange attemptsExchange) {
        return BindingBuilder
                .bind(gamificationQueue)
                .to(attemptsExchange)
                .with("attempt.correct");
    }

    /**
     * This bean defines how incoming JSON messages from RabbitMQ should be converted (deserialized)
     * into Java objects.
     * It uses MappingJackson2MessageConverter for JSON conversion.
     * It registers the ParameterNamesModule, to tell the Jackson library to use the names of the
     * constructor parameter when creating Java objects from th JSON payload, which is essential for
     * working with immutable data classes (or DTOs).
     * @return
     */
    @Bean
    public MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory =
                new DefaultMessageHandlerMethodFactory();

        final MappingJackson2MessageConverter jsonConverter =
                new MappingJackson2MessageConverter();

        jsonConverter
                .getObjectMapper()
                .registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
        factory.setMessageConverter(jsonConverter);
        return factory;
    }

    /**
     * This bean apply the custom JSON conversion settings to all methods in the application
     * annotated with @RabbitListener.
     * It hooks the configured messageHandlerMethodFactory into Spring's message listening
     * infrastructure, ensuring that whenever a message arrives, it is correctly deserialized
     * before being passed to the event-handling methods.
     * @param messageHandlerMethodFactory
     * @return
     */
    @Bean
    public RabbitListenerConfigurer rabbitListenerConfigurer(
            final MessageHandlerMethodFactory messageHandlerMethodFactory) {
        return (c) -> c.setMessageHandlerMethodFactory(messageHandlerMethodFactory);
    }
}
