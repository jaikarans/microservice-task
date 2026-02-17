package io.github.jaikarans.notification_service.kafka;

import io.github.jaikarans.notification_service.EmailService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.streams.messaging.MessagingProcessor;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamConfig {

    @Bean
    public KStream<String, String> getMessageStream(
            StreamsBuilder builder,
            MessageProcessor messageProcessor
    ) {

        KStream<String, String> stream = builder.stream(
                "order-notifications",
                Consumed.with(Serdes.String(), Serdes.String())
        );

        stream.process(() -> messageProcessor::process);

        return stream;
    }

}

