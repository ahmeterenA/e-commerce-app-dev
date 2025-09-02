package com.aerenarslanoglu.ecommerce.product;

import com.aerenarslanoglu.ecommerce.product.requests.ProductPurchaseRequest;
import com.aerenarslanoglu.ecommerce.product.requests.ProductRequest;
import com.aerenarslanoglu.ecommerce.product.responses.ProductPurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public Integer createProduct(ProductRequest request) {
        return null;
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        return null;
    }
}
