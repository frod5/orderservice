package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.jpa.OrderEntity;
import com.example.orderservice.vo.RequestOrder;
import com.example.orderservice.vo.ResponseOrder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrderMapper {

    OrderDto requestToDto(RequestOrder order);
    ResponseOrder dtoToResponse(OrderDto orderDto);
    OrderEntity toEntity(OrderDto orderDto);
    OrderDto entityToDto(OrderEntity entity);
}
