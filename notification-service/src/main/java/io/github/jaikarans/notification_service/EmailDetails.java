package io.github.jaikarans.notification_service;

public record EmailDetails(
        String recipient,
        String msgBody,
        String subject
) {}
