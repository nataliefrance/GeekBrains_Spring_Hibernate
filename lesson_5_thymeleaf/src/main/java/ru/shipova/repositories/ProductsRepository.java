package ru.shipova.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.shipova.entities.Product;

@Repository
public interface ProductsRepository extends PagingAndSortingRepository<Product, Long> {
}
