package com.services.impl;

import com.models.MyCart;
import com.services.MyCartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class MyCartServiceImpl implements MyCartService {

    @Autowired
    MyCart myCart;

    @Override
    public Map<Long, Integer> selectedProduct(Long id, Integer quantity) {

        return null;
    }
}
