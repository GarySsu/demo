package com.example.service.impl;

import com.example.service.ProductService;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/**
 * @author garyssu
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final Map<String, Long> productMap = new ConcurrentHashMap<>();

    @Override
    public Map<String, Long> getAllProductMap() {
        return this.productMap;
    }

    @Override
    public void reduceQuantity(String productName, Long quantity) {
        Long productQuantity = productMap.get(productName);
        productMap.put(productName, productQuantity - quantity);
    }

}
