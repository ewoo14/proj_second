package com.sparta.msa_exam.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .productIds(orderDto.productIds())
                .build();
        order = orderRepository.save(order);
        return OrderDto.from(order);
    }

    public OrderDto addProductToOrder(String orderId, List<String> newProductIds) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("다음 주문 id를 찾을 수 없습니다 : " + orderId));
        order.update(newProductIds);
        order = orderRepository.save(order);
        return OrderDto.from(order);
    }

    public OrderDto getOrderById(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("다음 주문 id를 찾을 수 없습니다 : " + orderId));
        return OrderDto.from(order);
    }

    public List<OrderDto> getAllOrders() {
        Iterable<Order> orders = orderRepository.findAll();
        return StreamSupport.stream(orders.spliterator(), false)
                .map(OrderDto::from)
                .collect(Collectors.toList());
    }
}