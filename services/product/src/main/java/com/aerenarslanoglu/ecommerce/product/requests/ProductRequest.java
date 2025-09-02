package com.aerenarslanoglu.ecommerce.product.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @Positive(message = "Available quantity should be positive number")
        double availableQuantity,
        @Positive(message = "price should be positive number")
        BigDecimal price,
        @NotNull(message = "Product category is required")
        Integer categoryId
) {
}
