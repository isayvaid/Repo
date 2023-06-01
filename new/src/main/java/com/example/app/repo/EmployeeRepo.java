package com.example.app.repo;

import com.example.app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee ,Integer> {


    Employee findByName(String name);
}
