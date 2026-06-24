package com.example;

import com.example.processor.OrderProcessor;

public class App {
    public static void main(String[] args) {

        OrderProcessor processor = new OrderProcessor();
        processor.processOrders("discount_day.txt");
    }
}
