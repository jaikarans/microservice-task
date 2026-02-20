package io.github.jaikarans.user_service.service;

import io.github.jaikarans.user_service.dto.request.OrderRequest;
import io.github.jaikarans.user_service.dto.request.PlaceOrderRequest;
import io.github.jaikarans.user_service.dto.response.PlaceOrderResponse;
import io.github.jaikarans.user_service.repository.UserRepository;
import io.github.jaikarans.user_service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Value("${services.order-service.url}")
    private String orderServiceUrl;

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RestTemplate restTemplate;

    public PlaceOrderResponse putOrder(OrderRequest request, String jwtToken) throws RuntimeException {
        if(!userRepository.existsById(jwtService.extractUidFromJwt(jwtToken))){
            return null;
        }

        var requestBody = new PlaceOrderRequest(
                jwtService.extractEmailFromJwt(jwtToken),
                jwtService.extractUidFromJwt(jwtToken),
                request.orderItem()
        );

        System.out.println("orderServiceUrl: "+orderServiceUrl);

        return restTemplate.postForEntity("http://"+orderServiceUrl+"/api/place-order", requestBody, PlaceOrderResponse.class).getBody();

    }
}
