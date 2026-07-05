package dev.sverdlov.orderservice.domain.db;

import dev.sverdlov.api.http.order.CreateOrderRequestDto;
import dev.sverdlov.api.http.order.OrderDto;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface OrderEntityMapper {
    OrderEntity toOrderEntity(CreateOrderRequestDto requestDto);

    OrderDto toOrderDto(OrderEntity orderEntity);

    @AfterMapping
    default void linkOrderItemEntities(@MappingTarget OrderEntity orderEntity) {
        orderEntity
                .getItems()
                .forEach(orderItemEntity -> {
                    orderItemEntity.setOrder(orderEntity);
                });
    }
}
