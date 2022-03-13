package com.ivan.spring.rest.controller;

import com.ivan.spring.rest.entity.Employee;
import com.ivan.spring.rest.exception_heading.EmployeeIncorrectData;
import com.ivan.spring.rest.exception_heading.NoSuchEmployeeException;
import com.ivan.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//используем такую аннотацию
@RestController
@RequestMapping("/api")
public class MyRestController {
    //контроллер работает с интерфейсом
    @Autowired
    private EmployeeService employeeService;
    //метод для получения списка работников
    @GetMapping("employees")
    public List<Employee> showListEmployee(){
        //простое получения списка работников
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;

    }
    @GetMapping("employees/{id}")
    public Employee getEmployee(@PathVariable int id){
        // получение одного
        Employee employee = employeeService.getEmployee(id);
        //проверка
        if(employee == null){
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in DataBase");
        }
        return employee;

    }







}
