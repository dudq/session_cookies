package com.repositories;

import com.models.Product;
import com.models.Supplier;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Iterable<Product> findAllBySupplier(Supplier supplier);
}
