package com.example.demo.services.impls;

import com.example.demo.entities.Employee;
import com.example.demo.repositories.EmployeeRepo;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepository;

    public boolean login(String username, String password) {
        Optional<Employee> employeeOpt = employeeRepository.findByUsername(username);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            return employee.getPassword().equals(password);
        }
        return false;
    }
}