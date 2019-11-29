package com.services;

import com.models.Supplier;

public interface SupplierService {
    Iterable<Supplier> findAll();

    Supplier findById(Long id);

    void save(Supplier supplier);

    void remove(Long id);
}
