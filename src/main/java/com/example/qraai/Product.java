package com.example.qraai;

public class Product {
    private String serial,description,date;

    public Product(String serial, String description, String date) {
        this.serial = serial;
        this.description = description;
        this.date = date;
    }

    public String getSerial() {
        return serial;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}
