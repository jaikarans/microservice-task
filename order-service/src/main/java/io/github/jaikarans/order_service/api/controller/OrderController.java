package io.github.jaikarans.order_service.api.controller;

import io.github.jaikarans.order_service.api.dto.request.OrderRequest;
import io.github.jaikarans.order_service.api.dto.response.OrderResponse;
import io.github.jaikarans.order_service.api.entity.Order;
import io.github.jaikarans.order_service.api.entity.OrderStatus;
import io.github.jaikarans.order_service.api.kafka.producer.OrderProducer;
import io.github.jaikarans.order_service.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderProducer orderProducer;

    @GetMapping("/test")
    public String test(){
        return "test" ;
    }

    @PostMapping("/place-order")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest request) {
        var order = orderService.placeOrder(request);
        if (order == null) {
            orderProducer.sendMessageToKafka(request.email(), request.email(), "order is not processed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new OrderResponse(
                            null,
                            null,
                            "Sorry order is not Placed due to Internal server error"
                    ));
        } else {

            orderProducer.sendMessageToKafka(String.valueOf(order.getId()), request.email(), "Congratulations your order status is: ".concat(order.getStatus().toString()));

        }

        System.out.println("request body"+request.toString());

        var response = new OrderResponse(
                order.getId(),
                order.getOrderItem(),
                "order Placed successfully on "
        );

        return ResponseEntity.ok(response);
    }

}
