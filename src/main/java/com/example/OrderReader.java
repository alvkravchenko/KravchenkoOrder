package com.example;

import java.util.List;
@FunctionalInterface
public interface OrderReader {

    List<Order> readOrders(String filePath);

}
