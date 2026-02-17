package io.github.jaikarans.order_service.api.repository;

import io.github.jaikarans.order_service.api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
