/*
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 * 
 */

// Write your code here
package com.employeeList2.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.employeeList2.model.Employee;
import com.employeeList2.model.EmployeeRowMapper;
import com.employeeList2.repository.EmployeeRepository;

@Service
public class EmployeeH2Service implements EmployeeRepository {

	// Do not modify the above code

	// Write your code here
	@Autowired
	private JdbcTemplate db;

	@Override
	public ArrayList<Employee> getEmployeeList() {
		Collection<Employee> employeelist = db.query("select * from employeelist", new EmployeeRowMapper());
		ArrayList<Employee> arrList = new ArrayList<>(employeelist);
		return arrList;
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		try {
			Employee employee = db.queryForObject("select * from employeelist where employeeId = ?",
					new EmployeeRowMapper(), employeeId);
			return employee;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Employee addEmployee(Employee employee) {

		db.update("insert into employeelist( employeeName, email, department) values (?, ?, ?)",
				employee.getEmployeeName(), employee.getEmail(), employee.getDepartment());

		Employee savedEmployee = db.queryForObject(
				"select * from employeelist where employeeName = ? and  email = ? and department = ?",
				new EmployeeRowMapper(), employee.getEmployeeName(), employee.getEmail(), employee.getDepartment());

		return savedEmployee;
	}

	@Override
	public Employee updateEmployee(int employeeId, Employee employee) {
		try {

			if (employee.getEmployeeName() != null) {
				db.update("update employeelist set employeeName = ? and email = ? where employeeId = ?",
						employee.getEmployeeName(), employee.getEmployeeId());
			}

			return getEmployeeById(employeeId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void deleteEmployee(int employeeId) {

		db.update("delete from employeelist where employeeId = ?", employeeId);

	}

}
