package com.example.service;

import com.example.model.Order;
import com.example.model.OrderWithDiscount;
import com.example.reader.OrderReader;
import com.example.reader.OrderReaderAdapter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderProcessor {

    public void processOrders() {
        String filePath = "discount_day.txt";


        OrderReader reader = OrderReaderAdapter.create(filePath);


        List<Order> orders = reader.readOrders(filePath);


        List<Order> sorted = orders.stream()
                .sorted(Comparator.comparing(Order::getOrderDateTime))
                .collect(Collectors.toList());


        DiscountCalculator calculator = new DiscountCalculator(sorted);
        List<OrderWithDiscount> details = calculator.calculateWithDetails();


        Map<String, Double> companyTotal = details.stream()
                .collect(Collectors.groupingBy(
                        OrderWithDiscount::getCompanyName,
                        Collectors.summingDouble(OrderWithDiscount::getOrderWithDiscountPrice)
                ));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"))) {
            for (Map.Entry<String, Double> entry : companyTotal.entrySet()) {
                writer.write(entry.getKey() + " - " + entry.getValue());
                writer.newLine();
            }
            System.out.println("Результат сохранен в файл result.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
