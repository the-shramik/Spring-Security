package com.springsecurity.service;

import com.springsecurity.model.Employee;
import com.springsecurity.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl {

    @Autowired
    private IEmployeeRepository employeeRepository;

    public Employee registerEmployee(Employee employee){
       return employeeRepository.save(employee);
    }
}
