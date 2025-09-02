package com.aerenarslanoglu.ecommerce.product;

import com.aerenarslanoglu.ecommerce.product.requests.ProductPurchaseRequest;
import com.aerenarslanoglu.ecommerce.product.requests.ProductRequest;
import com.aerenarslanoglu.ecommerce.product.responses.ProductPurchaseResponse;
import com.aerenarslanoglu.ecommerce.product.responses.ProductResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;
    public Integer createProduct(ProductRequest request) {
        Product product = mapper.toProduct(request);

        return repository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        return null;
    }

    public ProductResponse findById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(()->new EntityNotFoundException("Product not found with id: "+productId));
    }
}
