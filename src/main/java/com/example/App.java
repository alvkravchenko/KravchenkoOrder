package com.example;

import com.example.service.DiscountCalculator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App
{
    public static void main( String[] args )
    {

        String filePath = "C:\\Users\\Александр\\Downloads\\discount_day.txt";


        OrderReader reader = getOrderReader(filePath);


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

        details.forEach(d -> System.out.println(
                d.getOrderDateTime() + " | " +
                        d.getCompanyName() + " | " +
                        d.getAmountOfKilograms() + " кг | " +
                        "цена: " + d.getOrderPrice() + " руб | " +
                        "скидка: " + d.getDiscount() + "% | " +
                        "со скидкой: " + d.getOrderWithDiscountPrice() + " руб"
        ));
    }


    public static OrderReader getOrderReader(String filePath){
        if (filePath.endsWith(".txt")){
            return new TxtOrderReader();
        } else {
            return new DataOrderReader();
        }
    }
}
