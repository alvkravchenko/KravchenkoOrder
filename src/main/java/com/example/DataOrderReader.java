package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataOrderReader implements OrderReader {

    @Override
    public List<Order> readOrders(String filePath) {
        try (Stream<String> lines = Files.lines(Path.of(filePath))) {
            return lines.map(line -> line.split("#")).map(parts -> new Order(
                    LocalDateTime.parse(parts[0]), parts[1], Double.parseDouble(parts[2])
            )).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
