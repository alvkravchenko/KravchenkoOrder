package com.example.processor;

import com.example.model.Order;
import com.example.model.OrderWithDiscount;
import com.example.reader.OrderReader;
import com.example.reader.OrderReaderAdapter;
import com.example.service.DiscountCalculator;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderProcessorTest {

        @Test
        public void testTxtFileReading() {

            String filePath = "src/test/resources/test_orders.txt";
            OrderReader reader = OrderReaderAdapter.create(filePath);
            List<Order> orders = reader.readOrders(filePath);


            assertEquals(2, orders.size());
            assertEquals("Company1", orders.get(0).getCompanyName());
            assertEquals(100.0, orders.get(0).getAmountOfKilograms(), 0.001);
            assertEquals("Company2", orders.get(1).getCompanyName());
            assertEquals(200.0, orders.get(1).getAmountOfKilograms(), 0.001);


            DiscountCalculator calculator = new DiscountCalculator(orders);
            List<OrderWithDiscount> result = calculator.calculateWithDetails();

            assertEquals(50, result.get(0).getDiscount());
            assertEquals(1000.0, result.get(0).getOrderPrice(), 0.001);
            assertEquals(500.0, result.get(0).getOrderWithDiscountPrice(), 0.001);

            assertEquals(45, result.get(1).getDiscount());
            assertEquals(2000.0, result.get(1).getOrderPrice(), 0.001);
            assertEquals(1100.0, result.get(1).getOrderWithDiscountPrice(), 0.001);
        }

        @Test
        public void testDataFileReading() {

            String filePath = "src/test/resources/test_orders.data";
            OrderReader reader = OrderReaderAdapter.create(filePath);
            List<Order> orders = reader.readOrders(filePath);


            assertEquals(2, orders.size());
            assertEquals("Company1", orders.get(0).getCompanyName());
            assertEquals(100.0, orders.get(0).getAmountOfKilograms(), 0.001);
            assertEquals("Company2", orders.get(1).getCompanyName());
            assertEquals(200.0, orders.get(1).getAmountOfKilograms(), 0.001);


            DiscountCalculator calculator = new DiscountCalculator(orders);
            List<OrderWithDiscount> result = calculator.calculateWithDetails();

            assertEquals(50, result.get(0).getDiscount());
            assertEquals(1000.0, result.get(0).getOrderPrice(), 0.001);
            assertEquals(500.0, result.get(0).getOrderWithDiscountPrice(), 0.001);

            assertEquals(45, result.get(1).getDiscount());
            assertEquals(2000.0, result.get(1).getOrderPrice(), 0.001);
            assertEquals(1100.0, result.get(1).getOrderWithDiscountPrice(), 0.001);
        }

        @Test
        public void testOrderProcessorIntegration() throws IOException {

            String testFilePath = "src/test/resources/test_orders.txt";
            OrderProcessor processor = new OrderProcessor();
            processor.processOrders(testFilePath);


            Path resultPath = Path.of("result.txt");
            assertTrue(Files.exists(resultPath));


            List<String> lines = Files.readAllLines(resultPath);
            assertEquals(2, lines.size());


            assertTrue(lines.get(0).contains("Company1") || lines.get(1).contains("Company1"));
            assertTrue(lines.get(0).contains("Company2") || lines.get(1).contains("Company2"));
        }
    }

