package com.service.emp;

import com.dto.emp.EmployeeDTO;
import com.dto.emp.WorkedHoursDTO;
import com.model.emp.Employee;
import com.model.emp.Gender;
import com.model.emp.Job;
import com.repository.emp.EmployeeRepository;
import com.repository.emp.JobRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean createUser(EmployeeDTO employeeDTO) {

        Gender gender = genderRepository.findById(employeeDTO.getGenderId()).orElse(null);
        Job job = jobRepository.findById(employeeDTO.getJobId()).orElse(null);

        if (gender == null || job == null) {
            throw new IllegalArgumentException("Invalid gender or job");
        }


        if (employeeRepository.existsByNameAndLastName(employeeDTO.getName(), employeeDTO.getLastName())) {
            throw new IllegalArgumentException("Name and last name already exist");
        }


        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setBirthDate(employeeDTO.getBirthDate());
        employee.setGender(gender);
        employee.setJob(job);

        employeeRepository.save(employee);
        return false;
    }

    @Override
    public boolean addWorkedHours(WorkedHoursDTO workedHoursDTO) {

        Employee employee = employeeRepository.findById(workedHoursDTO.getEmployeeId()).orElse(null);
        if (employee == null) {
            return false;
        }

        double totalWorkedHours = employeeRepository.getTotalWorkedHoursByEmployeeIdAndDate(
                workedHoursDTO.getEmployeeId(), workedHoursDTO.getWorkedDate());
        if (totalWorkedHours + workedHoursDTO.getWorkedHours() > 20) {
            return false;
        }

        if (employeeRepository.existsByEmployeeIdAndDate(workedHoursDTO.getEmployeeId(), workedHoursDTO.getWorkedDate())) {
            return false;
        }

        employee.addWorkedHours(workedHoursDTO.getWorkedHours(), workedHoursDTO.getWorkedDate());
        employeeRepository.save(employee);

        return true;
    }

    @Override
    public List<EmployeeDTO> getEmployeesByJobId(Long jobId) {
        Job job = jobRepository.findById(jobId).orElse(null);

        if (job == null) {
            throw new IllegalArgumentException("Invalid job");
        }

        List<Employee> employees = employeeRepository.findByJob(job);
        return employees.stream().map(EmployeeMapper::toDTO).collect(Collectors.toList());
    }

    public int getWorkedHoursOnRangeDate(Long employeeId, LocalDate startDate, LocalDate endDate) {
      return 0;
    }

    public Employee getEmployeeById(Long employeeId) {
        return null;
    }
}
