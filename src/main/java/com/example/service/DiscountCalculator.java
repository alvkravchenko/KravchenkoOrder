package com.example.service;

import com.example.model.Order;
import com.example.model.OrderWithDiscount;

import java.util.ArrayList;
import java.util.List;

public class DiscountCalculator {

    private final List<Order> sortedOrders;

    public DiscountCalculator(List<Order> sortedOrders) {
        this.sortedOrders = sortedOrders;
    }

    public List<OrderWithDiscount> calculateWithDetails() {
        List<OrderWithDiscount> result = new ArrayList<>();

        for (int i = 0; i < sortedOrders.size(); i++) {
            Order order = sortedOrders.get(i);
            int orderNumber = i + 1;
            int discount = Math.max(0, 50 - (orderNumber - 1) * 5);
            double fullPrice = order.getAmountOfKilograms() * 10;
            double discountedPrice = fullPrice * (100 - discount) / 100;

            result.add(new OrderWithDiscount(
                    order.getOrderDateTime(),
                    order.getCompanyName(),
                    order.getAmountOfKilograms(),
                    fullPrice,
                    discount,
                    discountedPrice
            ));
        }
        return result;
    }
}
