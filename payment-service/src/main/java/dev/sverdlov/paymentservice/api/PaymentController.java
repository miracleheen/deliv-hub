package dev.sverdlov.paymentservice.api;

import dev.sverdlov.api.http.payment.CreatePaymentRequestDto;
import dev.sverdlov.api.http.payment.CreatePaymentResponseDto;
import dev.sverdlov.paymentservice.domain.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public CreatePaymentResponseDto createPayment(
            @RequestBody CreatePaymentRequestDto request
    ) {
        log.info("Received request : paymentRequest{}", request);

        return paymentService.makePayment(request);
    }
}
