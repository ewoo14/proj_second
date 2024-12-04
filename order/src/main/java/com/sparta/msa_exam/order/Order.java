package com.sparta.msa_exam.order;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@RedisHash("Order")
@Getter
public class Order {
    @Id
    private String id;
    private List<String> productIds;

    @Builder
    public Order(String id, List<String> productIds) {
        this.id = id;
        this.productIds = productIds;
    }

    public static Order create(List<String> productIds) {
        return Order.builder()
                .productIds(productIds)
                .build();
    }

    public void update(List<String> newProductIds) {
        this.productIds.clear();
        this.productIds.addAll(newProductIds);
    }
}
