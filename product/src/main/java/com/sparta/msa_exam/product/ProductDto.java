package com.sparta.msa_exam.product;

import com.sparta.msa_exam.product.Product;

public record ProductDto (
    String id,
    String name,
    Integer supplyPrice
    ) {

    public static ProductDto from(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getSupplyPrice()
        );
    }
}