package io.github.jaikarans.order_service.api.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value("${kafka.topics.order}")
    private String orderTopic;


    public void sendMessageToKafka(String orderId, String email, String message) {
        Message<String> kafkaMessage = MessageBuilder.withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, orderTopic)
                .setHeader(KafkaHeaders.KEY, orderId)
                .setHeader("email", email)
                        .build();

        kafkaTemplate.send(kafkaMessage);
    }
}
