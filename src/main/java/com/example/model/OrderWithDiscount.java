package com.example.model;

import java.time.LocalDateTime;

public class OrderWithDiscount {

    private LocalDateTime orderDateTime;
    private String companyName;
    private double amountOfKilograms;
    private double orderPrice;
    private int discount;
    private double orderWithDiscountPrice;

    public OrderWithDiscount(LocalDateTime orderDateTime, String companyName, double amountOfKilograms, double orderPrice, int discount, double orderWithDiscountPrice) {
        this.orderDateTime = orderDateTime;
        this.companyName = companyName;
        this.amountOfKilograms = amountOfKilograms;
        this.orderPrice = orderPrice;
        this.discount = discount;
        this.orderWithDiscountPrice = orderWithDiscountPrice;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getAmountOfKilograms() {
        return amountOfKilograms;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public double getOrderWithDiscountPrice() {
        return orderWithDiscountPrice;
    }
}

