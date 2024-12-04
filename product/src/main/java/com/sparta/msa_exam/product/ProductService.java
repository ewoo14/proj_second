package com.sparta.msa_exam.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductDto createProduct(ProductDto productDto) {
        Product product = Product.builder()
                .name(productDto.name())
                .supplyPrice(productDto.supplyPrice())
                .build();
        product = productRepository.save(product);
        return ProductDto.from(product);
    }

    public ProductDto getProductById(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("다음 상품 id를 찾을 수 없습니다 : " + productId));
        return ProductDto.from(product);
    }

    public List<ProductDto> getAllProducts() {
        Iterable<Product> products = productRepository.findAll();
        return StreamSupport.stream(products.spliterator(), false)
                .map(ProductDto::from)
                .collect(Collectors.toList());
    }

    public ProductDto updateProduct(String productId, ProductDto productDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("다음 상품 id를 찾을 수 없습니다 : " + productId));
        product.update(productDto.name(), productDto.supplyPrice());
        Product updatedProduct = productRepository.save(product);
        return ProductDto.from(updatedProduct);
    }

    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }
}
