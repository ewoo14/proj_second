package com.sparta.msa_exam.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import lombok.Builder;
import lombok.Getter;

@RedisHash("Product")
@Getter
public class Product {
    @Id
    private String id;
    private String name;
    private Integer supplyPrice;

    @Builder
    public Product(String id, String name, Integer supplyPrice) {
        this.id = id;
        this.name = name;
        this.supplyPrice = supplyPrice;
    }

    public static Product create(String name, Integer supplyPrice) {
        return Product.builder()
                .name(name)
                .supplyPrice(supplyPrice)
                .build();
    }

    public void update(String name, Integer supplyPrice) {
        if (name != null) {
            this.name = name;
        }
        if (supplyPrice != null) {
            this.supplyPrice = supplyPrice;
        }
    }
}