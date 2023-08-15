package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.vo.RequestOrder;
import com.example.orderservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final Environment env;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Order Service on PORT %s", env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable String userId, @RequestBody RequestOrder order) {

        OrderDto orderDto = orderMapper.requestToDto(order);
        orderDto.setUserId(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.dtoToResponse(orderService.createOrder(orderDto)));
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable String userId) {

        return ResponseEntity.ok(orderService.getOrdersByUserId(userId)
                .stream()
                .map(orderMapper::entityToDto)
                .map(orderMapper::dtoToResponse)
                .collect(Collectors.toList()));
    }
}
