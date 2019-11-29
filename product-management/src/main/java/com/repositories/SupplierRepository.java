package com.repositories;

import com.models.Supplier;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SupplierRepository extends PagingAndSortingRepository<Supplier, Long> {
}
