package com.services;

import java.util.Map;

public interface MyCartService {
    Map<Long, Integer> selectedProduct(Long id, Integer quantity);
}
