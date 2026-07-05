package dev.sverdlov.orderservice.api;

import dev.sverdlov.api.http.payment.PaymentMethod;

public record OrderPaymentRequest(
        PaymentMethod paymentMethod
) {
}
