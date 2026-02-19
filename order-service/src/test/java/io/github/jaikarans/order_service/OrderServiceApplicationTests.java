package io.github.jaikarans.order_service;

import io.github.jaikarans.order_service.api.kafka.producer.OrderProducer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class OrderServiceApplicationTests {

	@MockitoBean
	OrderProducer orderProducer;

	@Test
	void contextLoads() {
	}

}
