package dev.sverdlov.orderservice.external;

import dev.sverdlov.api.http.payment.CreatePaymentRequestDto;
import dev.sverdlov.api.http.payment.CreatePaymentResponseDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * DEPRECATED: отказался от RestTemplate, т.к. по новому стандарту, он устаревший;
 *
 */
@HttpExchange(
        accept = "application/json",
        contentType = "application/json",
        url = "/api/payments"
)
public interface PaymentHttpClient {
    @PostExchange
    CreatePaymentResponseDto createPayment(@RequestBody CreatePaymentRequestDto requestDto);
}
