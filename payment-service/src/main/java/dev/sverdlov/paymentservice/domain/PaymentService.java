package dev.sverdlov.paymentservice.domain;

import dev.sverdlov.api.http.payment.CreatePaymentRequestDto;
import dev.sverdlov.api.http.payment.CreatePaymentResponseDto;
import dev.sverdlov.api.http.payment.PaymentMethod;
import dev.sverdlov.api.http.payment.PaymentStatus;
import dev.sverdlov.paymentservice.domain.db.PaymentEntityMapper;
import dev.sverdlov.paymentservice.domain.db.PaymentEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PaymentService {
    private final PaymentEntityRepository paymentRepository;
    private final PaymentEntityMapper mapper;

    public CreatePaymentResponseDto makePayment(CreatePaymentRequestDto request) {
        var found = paymentRepository.findByOrderId(request.orderId());

        if (found.isPresent()) {
            log.info("Payment already exists for orderId {}", request.orderId());
            return mapper.toResponseDto(found.get());
        }

        var entity = mapper.toEntity(request);

        var status = request.paymentMethod().equals(PaymentMethod.QR)
                ? PaymentStatus.PAYMENT_FAILED
                : PaymentStatus.PAYMENT_SUCCEEDED;

        entity.setPaymentStatus(status);

        var savedEntity = paymentRepository.save(entity);
        return mapper.toResponseDto(savedEntity);
    }
}
