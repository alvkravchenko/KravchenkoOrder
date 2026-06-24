package com.example.model;

import java.time.LocalDateTime;

public class Order {

    private LocalDateTime orderDateTime;
    private String companyName;
    private double amountOfKilograms;

    public Order(LocalDateTime orderDateTime, String companyName, double amountOfKilograms) {
        this.orderDateTime = orderDateTime;
        this.companyName = companyName;
        this.amountOfKilograms = amountOfKilograms;
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


}
