package com.repository.emp;

import com.model.emp.Employee;
import com.model.emp.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    boolean existsById(Long jobId);

    List<Job> findAll();

    // Agrega el método para buscar empleados por puesto
    List<Employee> findEmployeesByJobId(Long jobId);
}