/*
 * You can use the following import statements
 * 
 * import org.springframework.jdbc.core.RowMapper;
 * import java.sql.ResultSet;
 * import java.sql.SQLException;
 * 
 */

// Write your code here
package com.employeeList2.model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee(
                rs.getInt("employeeId"),
                rs.getString("employeeName"),
                rs.getString("email"),
                rs.getString("department"));
    }

}

