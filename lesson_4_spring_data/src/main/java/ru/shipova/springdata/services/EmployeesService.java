package ru.shipova.springdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shipova.springdata.entities.Employee;
import ru.shipova.springdata.repositories.EmployeesRepository;

import java.util.List;

@Service
public class EmployeesService {
    private EmployeesRepository employeesRepository;

    @Autowired
    public void setEmployeesRepository(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public List<Employee> findAll() {
        return (List<Employee>) employeesRepository.findAll();
    }

    public Employee findOneByName(String name) {
        return employeesRepository.findOneByName(name);
    }
}
