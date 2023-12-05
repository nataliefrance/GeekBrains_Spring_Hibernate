package ru.shipova.lesson_4_homework.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shipova.lesson_4_homework.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByCostGreaterThan(Long minCost);

    List<Product> findByCostLessThan(Long maxCost);

    List<Product> findByCostBetween(Long minCost, Long maxCost);
}
