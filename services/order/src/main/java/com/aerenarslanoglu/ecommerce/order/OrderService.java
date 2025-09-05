package com.aerenarslanoglu.ecommerce.order;

import com.aerenarslanoglu.ecommerce.customer.CustomerClient;
import com.aerenarslanoglu.ecommerce.exception.BusinessException;
import com.aerenarslanoglu.ecommerce.order.request.OrderRequest;
import com.aerenarslanoglu.ecommerce.product.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public Integer createdOrder(OrderRequest request){
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Can't create the order. Customer not found"));


        productClient.purchaseProducts(request.products());
    }
}
