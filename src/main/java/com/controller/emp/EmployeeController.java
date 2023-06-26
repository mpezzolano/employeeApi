package com.controller.emp;


import com.dto.emp.EmployeeDTO;

import com.dto.emp.WorkedHoursDTO;
import com.model.emp.Employee;
import com.service.emp.EmployeeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody EmployeeDTO employeeDTO) {
        boolean created = employeeService.createUser(employeeDTO);
        if (created) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Employee created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee creation failed");
        }
    }

    @PostMapping("/addWorkedHours")
    public ResponseEntity<String> addWorkedHours(@RequestBody WorkedHoursDTO workedHoursDTO) {
        boolean success = employeeService.addWorkedHours(workedHoursDTO);
        if (success) {
            return ResponseEntity.ok("Worked hours added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid employee or duplicate entry");
        }
    }

    @GetMapping("/searchEmployeeForJob")
    public ResponseEntity<List<EmployeeDTO>> searchEmployeeForJob(@RequestParam("jobId") Long jobId) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByJobId(jobId);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{employeeId}/workedHours")
    public ResponseEntity<String> checkWorkedHoursOnRangeDate(
            @PathVariable("employeeId") Long employeeId,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        try {
            int totalWorkedHours = employeeService.getWorkedHoursOnRangeDate(employeeId, startDate, endDate);
            return ResponseEntity.ok("Total worked hours: " + totalWorkedHours);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/checkPaymentsEmployee")
    public ResponseEntity<?> checkPaymentsEmployee(
            @RequestParam Long employeeId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        try {
            // Validar que el empleado exista
            Employee employee = employeeService.getEmployeeById(employeeId);
            if (employee == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
            }

            // Validar que la fecha de inicio sea menor a la fecha de fin
            if (startDate.isAfter(endDate)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date range");
            }

            // Consultar los pagos al empleado en el rango de fechas
            List<Payment> payments = paymentService.getPaymentsByEmployeeAndDateRange(employeeId, startDate, endDate);

            // Devolver los pagos como respuesta
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request");
        }
    }

}
