package io.github.jaikarans.notification_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.mail.username=test@test.com",
		"spring.mail.password=test"
})
class NotificationServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
