package com.example.service;

import com.example.model.Order;
import com.example.model.OrderWithDiscount;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiscountCalculatorTest {
    @Test
    public void testFirstOrderDiscount(){

        LocalDateTime now = LocalDateTime.now();
        Order first = new Order(now, "TestFirstCompany", 10.00);
        List<Order> orders = Arrays.asList(first);

        DiscountCalculator calculator = new DiscountCalculator(orders);
        List<OrderWithDiscount> result = calculator.calculateWithDetails();

        OrderWithDiscount firstOrder = result.get(0);
        assertEquals(50, firstOrder.getDiscount());

        assertEquals(100.0, firstOrder.getOrderPrice(), 0.001);
        assertEquals(50.0, firstOrder.getOrderWithDiscountPrice(), 0.001);
    }

    @Test
    public void testSecondOrderDiscount() {

        LocalDateTime now = LocalDateTime.now();
        Order firstOrder = new Order(now, "CompanyA", 100.0);
        Order secondOrder = new Order(now.plusHours(1), "CompanyB", 100.0);
        List<Order> orders = Arrays.asList(firstOrder, secondOrder);


        DiscountCalculator calculator = new DiscountCalculator(orders);
        List<OrderWithDiscount> result = calculator.calculateWithDetails();


        OrderWithDiscount secondOrderResult = result.get(1);
        assertEquals(45, secondOrderResult.getDiscount());
    }

    @Test
    public void testDiscountNeverNegative() {

        LocalDateTime now = LocalDateTime.now();
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            orders.add(new Order(
                    now.plusHours(i),
                    "Company" + i,
                    10.0
            ));
        }


        DiscountCalculator calculator = new DiscountCalculator(orders);
        List<OrderWithDiscount> result = calculator.calculateWithDetails();


        OrderWithDiscount eleventhOrder = result.get(10);
        assertEquals(0, eleventhOrder.getDiscount());


        OrderWithDiscount twelfthOrder = result.get(11);
        assertEquals(0, twelfthOrder.getDiscount());
    }

    @Test
    public void testEmptyOrderList() {

        List<Order> orders = new ArrayList<>();

        DiscountCalculator calculator = new DiscountCalculator(orders);
        List<OrderWithDiscount> result = calculator.calculateWithDetails();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testDifferentWeights() {

        LocalDateTime now = LocalDateTime.now();
        Order order1 = new Order(now, "CompanyA", 50.0);   // 50 кг
        Order order2 = new Order(now.plusHours(1), "CompanyB", 200.0); // 200 кг
        List<Order> orders = Arrays.asList(order1, order2);


        DiscountCalculator calculator = new DiscountCalculator(orders);
        List<OrderWithDiscount> result = calculator.calculateWithDetails();


        OrderWithDiscount first = result.get(0);
        assertEquals(50, first.getDiscount());
        assertEquals(500.0, first.getOrderPrice(), 0.001);      // 50 * 10 = 500
        assertEquals(250.0, first.getOrderWithDiscountPrice(), 0.001); // 500 * 0.5 = 250


        OrderWithDiscount second = result.get(1);
        assertEquals(45, second.getDiscount());
        assertEquals(2000.0, second.getOrderPrice(), 0.001);    // 200 * 10 = 2000
        assertEquals(1100.0, second.getOrderWithDiscountPrice(), 0.001); // 2000 * 0.55 = 1100
    }
}
