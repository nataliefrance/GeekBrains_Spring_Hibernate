package ru.shipova.lesson_4_homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.shipova.lesson_4_homework.entities.Product;
import ru.shipova.lesson_4_homework.services.ProductService;

import java.util.List;

@Controller
@ComponentScan("ru.shipova.lesson_4_homework")
public class MainController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/products")
    public String showProductsPage(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("products", productList);
        return "products";
    }

    @GetMapping("/findByCostMin")
    @ResponseBody
    public List<Product> findByCostMin() {
        return productService.findByCostGreaterThan(0L);
    }

    @GetMapping("/findByCostMax")
    @ResponseBody
    public List<Product> findByCostMax() {
        return productService.findByCostLessThan(100L);
    }

    @GetMapping("/findByCostBetween")
    @ResponseBody
    public List<Product> findByCostBetween() {
        return productService.findByCostBetween(100L, 1000L);
    }

    @GetMapping("/submit_form")
    @ResponseBody
    //не показываем страничку, в виде какого-текста выводим ответ
    //Model model не нужно, потому что мы на фронтенд ничего не кидаем
    public List<Product> getFormResult(@RequestParam(name = "minCost", required = false) Long minCost,
                                       @RequestParam(name = "maxCost", required = false) Long maxCost) {
        if (minCost == null) {
            minCost = 0L;
        }
        if (maxCost == null) {
            maxCost = Long.MAX_VALUE;
        }
        return productService.findByCostBetween(minCost, maxCost);
    }
}
