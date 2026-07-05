package dev.sverdlov.paymentservice.domain.db;

import dev.sverdlov.api.http.payment.CreatePaymentRequestDto;
import dev.sverdlov.api.http.payment.CreatePaymentResponseDto;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentEntityMapper {
    PaymentEntity toEntity(CreatePaymentRequestDto request);

    //маппинг из id = entity; в paymentId = CrResponseDto
    @Mapping(source = "id", target = "paymentId")
    CreatePaymentResponseDto toResponseDto(PaymentEntity entity);
}
