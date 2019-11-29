package com.services;

import com.models.Product;
import com.models.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

    void save(Product product);

    void remove(Long id);

    Iterable<Product> findAllBySupplier(Supplier supplier);
}
