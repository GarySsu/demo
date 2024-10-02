package com.example.service;

import java.util.Map;

public interface ProductService {

    Map<String, Long> getAllProductMap();

    void reduceQuantity(String productName, Long quantity);

}
