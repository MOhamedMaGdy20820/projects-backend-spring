package com.momg.ecommerce.dto;

import com.momg.ecommerce.entity.Address;
import com.momg.ecommerce.entity.Customer;
import com.momg.ecommerce.entity.Order;
import com.momg.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
