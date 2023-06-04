package com.app.Service;

import com.example.app.NewApplication;
import com.example.app.entity.Employee;
import com.example.app.repo.EmployeeRepo;
import com.example.app.service.EmployeeService;

import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = NewApplication.class)
    public class EmployeeServiceTest {

        @MockBean
        EmployeeRepo employeeRepo;

        @Autowired
        EmployeeService employeeService;

        @Test
        public void saveEmployeeTest() {
            Employee employee = new Employee(1,"Sayli","IT",1000);
            Mockito.when(employeeRepo.save(employee)).thenReturn(employee);
            Employee response =  employeeService.saveEmployee(employee);
            Assert.assertEquals(response,employee);
        }

        @Test
        public void getEmployeeTest() {
            Employee employee1 = new Employee(1,"Sayli","IT",1000);
            Employee employee2 = new Employee(2,"Manjiri","CSE",1000);
            List<Employee> employeeList = new ArrayList<>();
            employeeList.add(employee1);
            employeeList.add(employee2);

            Mockito.when(employeeRepo.findAll()).thenReturn(employeeList);
            List<Employee> response = employeeService.getEmployees();
            Assert.assertEquals(response.size(), employeeList.size());

        }

        @Test
        public void getEmployeeByIdTest() {
            Employee employee = new Employee(1,"Sayli","IT",1000);
            Mockito.when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
            Employee response = employeeService.getEmployeesById(1);
            Assert.assertEquals(response.getName(),"Sayli");
        }

        @Test
        public void getEmployeeByNameTest() {
            Employee employee = new Employee(1,"Sayli","IT",1000);
            Mockito.when(employeeRepo.findByName("Sayli")).thenReturn(employee);
            Employee response = employeeService.getEmployeesByName("Sayli");
            Assert.assertEquals(response.getName(),employee.getName());
        }

        @Test
        public void deleteEmployeeTest() {
            Employee employee = new Employee(1,"Sayli","IT",1000);
            Mockito.when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
            employeeService.deleteEmployee(1);
            Mockito.verify(employeeRepo).deleteById(employee.getId());
        }

        @Test
        public void updateEmployeeTest() {
            Employee employee = new Employee(1,"Sayli","IT",1000);
            Mockito.when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
            Employee updatedEmployee = new Employee(1,"Manjiri","CSE",1000);
            Mockito.when(employeeRepo.save(updatedEmployee)).thenReturn(updatedEmployee);
            Employee response = employeeService.updateEmployee(updatedEmployee);
            Assert.assertEquals(response.getName(),"Manjiri");
        }
    }

