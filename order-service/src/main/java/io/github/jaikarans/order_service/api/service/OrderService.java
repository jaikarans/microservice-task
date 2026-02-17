package io.github.jaikarans.order_service.api.service;

import io.github.jaikarans.order_service.api.dto.request.OrderRequest;
import io.github.jaikarans.order_service.api.entity.Order;
import io.github.jaikarans.order_service.api.entity.OrderStatus;
import io.github.jaikarans.order_service.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order placeOrder(OrderRequest request) {
        var order = new Order();
        order.setUserId(request.userId());
        order.setOrderItem(request.orderItem());
        order.setStatus(OrderStatus.CREATED);
        order.setTotalAmmount(123L);// for now we just hardcore hardcore

        return orderRepository.save(order);
    }

}
