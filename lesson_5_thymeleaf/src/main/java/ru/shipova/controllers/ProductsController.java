package ru.shipova.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shipova.entities.Product;
import ru.shipova.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductService productsService;

    @Autowired
    public void setProductsService(ProductService productsService) {
        this.productsService = productsService;
    }

    //http://localhost:8189/app/products/
    @GetMapping("/")
    public String showAllProducts(Model model) {
        Product addProduct = new Product();
        model.addAttribute("addProduct", addProduct); //"addProduct" - th:object="${addProduct}"
        model.addAttribute("products", productsService.getAllProducts());
        return "products"; //название странички, которую мы возвращаем
    }

    @PostMapping("/add")
    public String addNewProduct(@ModelAttribute(name = "addProduct") Product addProduct) {
        productsService.save(addProduct);
        return "redirect:/products/";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable(name = "id") Long id) {
        productsService.deleteById(id);
        return "redirect:/products/";
    }
}
