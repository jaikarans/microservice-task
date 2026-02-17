package io.github.jaikarans.notification_service.kafka;

import io.github.jaikarans.notification_service.EmailDetails;
import io.github.jaikarans.notification_service.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageProcessor implements Processor<String, String, Void, Void> {
    private final EmailServiceImpl emailService;

    private ProcessorContext<Void, Void> context;

    @Override
    public void init(ProcessorContext<Void, Void> context) {
        this.context = context;
    }

    @Override
    public void process(Record<String, String> record) {
        var header = record.headers().lastHeader("email");
        var email = new String(header.value());
        var key = record.key();
        var value = record.value();
        var mesgBody = "Congratualtion you order is placed your order id is "
                + key + "\n and your status is: \n" + value;
        var emailData = new EmailDetails(
                email,
                mesgBody,
                "Order Status"
        );

        System.out.println("Stream is running");

        emailService.sendEmail(emailData);

    }

    @Override
    public void close() {}

}

