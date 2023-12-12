package ru.shipova.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shipova.entities.Product;
import ru.shipova.repositories.ProductsRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productsRepository.findAll();
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productsRepository.save(product);
    }
}
