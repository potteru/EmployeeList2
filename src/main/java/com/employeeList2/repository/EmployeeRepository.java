// Write your code here
package com.employeeList2.repository;

import java.util.ArrayList;

import com.employeeList2.model.Employee;

public interface EmployeeRepository {

	ArrayList<Employee> getEmployeeList();

	Employee addEmployee(Employee employee);

	Employee getEmployeeById(int employeeId);

	Employee updateEmployee(int employeeId, Employee employee);

	void deleteEmployee(int employeeId);

}

