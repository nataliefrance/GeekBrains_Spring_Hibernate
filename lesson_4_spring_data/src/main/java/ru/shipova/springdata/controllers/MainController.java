package ru.shipova.springdata.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.shipova.springdata.entities.Employee;
import ru.shipova.springdata.services.EmployeesService;

import java.util.List;

@Controller
@ComponentScan("ru.shipova.springdata")
public class MainController {
    private EmployeesService employeesService;

    @Autowired
    public void setEmployeesService(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/employees")
    public String showEmployeesPage(Model model) {
        List<Employee> employeeList = employeesService.findAll();
        model.addAttribute("employees", employeeList);
        return "employees";
    }

    @GetMapping("/submit_form")
    @ResponseBody //не показываем страничку, в виде какого-текста выводим ответ
    //Model model не нужно, потому что мы на фронтенд ничего не кидаем
    public String getFormResult(@RequestParam(name = "word") String word, @RequestParam(value = "value") String value) {
        return "Word: " + word + "; Value = " + value + ";";
    }

    @GetMapping("/test_custom_method")
    @ResponseBody
    public Employee testCustomMethod() {
        return employeesService.findOneByName("Arnold S.");
    }
}
