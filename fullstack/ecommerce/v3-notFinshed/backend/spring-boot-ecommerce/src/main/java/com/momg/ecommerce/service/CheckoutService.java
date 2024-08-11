package com.momg.ecommerce.service;

import com.momg.ecommerce.dto.Purchase;
import com.momg.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
