package com.sparta.msa_exam.order;

import java.util.List;
import java.util.stream.Collectors;

public record OrderDto(
        String id,
        List<String> productIds
) {
    public static OrderDto from(Order order) {
        List<String> productIds = order
                .getProductIds()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());

        return new OrderDto(
                order.getId(),
                productIds);
    }
}