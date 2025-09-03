package com.aerenarslanoglu.ecommerce.product;

import com.aerenarslanoglu.ecommerce.exceptions.ProductPurchaseException;
import com.aerenarslanoglu.ecommerce.product.requests.ProductPurchaseRequest;
import com.aerenarslanoglu.ecommerce.product.requests.ProductRequest;
import com.aerenarslanoglu.ecommerce.product.responses.ProductPurchaseResponse;
import com.aerenarslanoglu.ecommerce.product.responses.ProductResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        var productIds = request.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        var storedProducts = repository.findAllByIdInOrderById(productIds);

        if(productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }

        var storedRequest = request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for(var storedProduct : storedProducts) {
            var requestedProduct = storedRequest.stream().
                    filter(req->
                            req.productId().equals(storedProduct.getId()))
                    .findFirst().orElse(null);
            if(storedProduct.getAvailableQuantity() < requestedProduct.quantity()){
                throw new ProductPurchaseException("Not enough products available");
            }
            var newAvailableQuantity = storedProduct.getAvailableQuantity() - requestedProduct.quantity();
            storedProduct.setAvailableQuantity(newAvailableQuantity);
            repository.save(storedProduct);
            purchasedProducts.add(mapper.toProductPurchaseResponse(storedProduct,requestedProduct.quantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(()->new EntityNotFoundException("Product not found with id: "+productId));
    }

    public List<ProductResponse> findAll() {
        return repository.findAll().stream().
                map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
