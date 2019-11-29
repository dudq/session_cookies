package com.services.impl;

import com.models.Product;
import com.models.Supplier;
import com.repositories.ProductRepository;
import com.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.delete(id);

    }

    @Override
    public Iterable<Product> findAllBySupplier(Supplier supplier) {
        return productRepository.findAllBySupplier(supplier);
    }
}
