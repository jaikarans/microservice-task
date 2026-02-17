package io.github.jaikarans.order_service.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderRequest(
        String email,
        @JsonProperty("userId")
        Long userId,
        String orderItem
) {}
