package com.service.emp;

import com.dto.emp.EmployeeDTO;
import com.dto.emp.WorkedHoursDTO;

import java.util.List;


public interface EmployeeService {

    boolean createUser(EmployeeDTO employeeDTO);

    boolean addWorkedHours(WorkedHoursDTO workedHoursDTO);

    List<EmployeeDTO> getEmployeesByJobId(Long jobId);
}
