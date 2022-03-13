package com.ivan.spring.rest.dao;

import com.ivan.spring.rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

    @Autowired
    private SessionFactory factory;

    @Override
    public List<Employee> getAllEmployees() {

       Session session = factory.getCurrentSession();

       List<Employee> allEmployees = session.createQuery("from Employee",
               Employee.class)
               .getResultList();

//        Query<Employee> query =session.createQuery("from Employee", Employee.class);
//
//       List<Employee> allEmployees =  query.getResultList();

       return allEmployees;


    }

    @Override
    public void saveEmployee(Employee employee) {


        Session session = factory.getCurrentSession();

        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {

        Session session = factory.getCurrentSession();
       Employee employee = session.get(Employee.class,id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {

        Session session = factory.getCurrentSession();
        Query<Employee> query = session.createQuery("delete from Employee "
                        + "where id =: employeeId");

        query.setParameter("employeeId",id);
        query.executeUpdate();
    }


}
