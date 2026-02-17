package io.github.jaikarans.order_service.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderResponse(
        @JsonProperty("orderId")
        Long orderId,
        String orderItem,
        String message
) {}
