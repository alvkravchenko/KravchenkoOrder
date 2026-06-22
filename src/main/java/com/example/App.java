package com.example;


import com.example.service.OrderProcessor;


public class App {
    public static void main(String[] args) {

        OrderProcessor processor = new OrderProcessor();
        processor.processOrders();
    }
}
