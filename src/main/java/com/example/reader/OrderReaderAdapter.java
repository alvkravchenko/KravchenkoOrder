package com.example.reader;

public class OrderReaderAdapter {

    public static OrderReader create (String filePath) {

        if (filePath.endsWith(".txt")) {
            return new TxtOrderReader();
        } else {
            return new DataOrderReader();
        }
    }
}
