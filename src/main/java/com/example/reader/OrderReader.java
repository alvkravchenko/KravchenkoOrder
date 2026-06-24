package com.example.reader;

import com.example.model.Order;

import java.util.List;
@FunctionalInterface
public interface OrderReader {

    List<Order> readOrders(String filePath);

}
