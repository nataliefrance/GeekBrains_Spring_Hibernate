package ru.shipova.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shipova.entities.Product;
import ru.shipova.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    //http://localhost:8189/app/products/
    @GetMapping("/")
    public String showAllProducts(Model model) {
        Product addProduct = new Product();
        model.addAttribute("addProduct", addProduct); //"addProduct" - th:object="${addProduct}"
        model.addAttribute("products", productService.getAllProducts());
        return "products"; //название странички, которую мы возвращаем
    }

    @PostMapping("/add")
    public String addNewProduct(@ModelAttribute(name = "addProduct") Product addProduct) {
        productService.save(addProduct);
        return "redirect:/products/";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
        return "redirect:/products/";
    }

    //http://localhost:8189/app/products/submit_form
    @GetMapping("/submit_form")
    @ResponseBody
    //не показываем страничку, в виде какого-текста выводим ответ
    //Model model не нужно, потому что мы на фронтенд ничего не кидаем
    public List<Product> getFormResult(@RequestParam(name = "minPrice", required = false) Integer minPrice,
                                       @RequestParam(name = "maxPrice", required = false) Integer maxPrice) {
        if (minPrice == null) {
            minPrice = 0;
        }
        if (maxPrice == null) {
            maxPrice = Integer.MAX_VALUE;
        }
        return productService.findByPriceBetween(minPrice, maxPrice);
    }
}
