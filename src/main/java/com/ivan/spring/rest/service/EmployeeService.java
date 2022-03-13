package com.ivan.spring.rest.service;

import com.ivan.spring.rest.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public Employee getEmployee(int id);

    public void deleteEmployee(int id);
}


