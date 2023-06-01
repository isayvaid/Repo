package com.example.app.service;

import com.example.app.entity.Employee;
import com.example.app.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//this is service
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo repo;

    public Employee saveEmployee(Employee employee){
        return repo.save(employee);
    }

    public List<Employee> saveEmployees(List<Employee> employees){
        return repo.saveAll(employees);
    }

    public List<Employee> getEmployees(){
        return repo.findAll();
    }

    public Employee getEmployeesById(int id){
      return repo.findById(id).orElse(null);
    }

    public Employee getEmployeesByName(String name){
        return repo.findByName(name);
    }
    public String deleteEmployee(int id){
        repo.deleteById(id);
        return "employee removed !!" +id;
    }

    public Employee updateEmployee(Employee employee){
        Employee existingEmployee= repo.findById(employee.getId()).orElse(null);
        existingEmployee.setName(employee.getName());
        existingEmployee.setDept(employee.getDept());
        existingEmployee.setSalary(employee.getSalary());
        return repo.save(existingEmployee);

    }
}
