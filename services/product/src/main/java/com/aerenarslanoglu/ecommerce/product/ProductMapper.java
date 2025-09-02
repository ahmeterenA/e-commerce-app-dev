package com.aerenarslanoglu.ecommerce.product;

import com.aerenarslanoglu.ecommerce.product.requests.ProductRequest;
import com.aerenarslanoglu.ecommerce.product.responses.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId()
        );
    }

    public Product toProduct(ProductRequest productRequest) {
        return Product.builder()
                .id(productRequest.id())
                .name(productRequest.name())
                .description(productRequest.description())
                .availableQuantity(productRequest.availableQuantity())
                .price(productRequest.price())
                .category(
                        Category.builder()
                                .id(productRequest.categoryId())
                                .build()
                )
                .build();
    }
}
