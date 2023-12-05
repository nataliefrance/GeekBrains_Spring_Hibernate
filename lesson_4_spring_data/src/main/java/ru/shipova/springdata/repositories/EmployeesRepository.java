package ru.shipova.springdata.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shipova.springdata.entities.Employee;

import java.util.List;

@Repository
public interface EmployeesRepository extends CrudRepository<Employee, Long> { //Employee - тип данных с которым мы работаем, Long - его айдишник
    Employee findOneByName(String name);

    List<Employee> findAllByIdBetween(Long minId, Long maxId);
}
