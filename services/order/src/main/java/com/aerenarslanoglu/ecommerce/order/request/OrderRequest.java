package com.aerenarslanoglu.ecommerce.order.request;

import com.aerenarslanoglu.ecommerce.order.PaymentMethod;
import com.aerenarslanoglu.ecommerce.product.PurchaseRequest;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId,
        List<PurchaseRequest> products
) {
}
