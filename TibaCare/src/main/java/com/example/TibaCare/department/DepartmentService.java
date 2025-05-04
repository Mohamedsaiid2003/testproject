package com.example.TibaCare.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentsById(Long id) {
        return departmentRepository.findById(id);
    }
    public Department saveDepartments(Department department) {
        return departmentRepository.save(department);
    }

    public Optional<Department> updatedService(Long id, Department serviceDetails) {
        Optional<Department> existingservice = departmentRepository.findById(id);
        if (existingservice.isPresent()) {
            Department service = existingservice.get();
            service.setId(service.getId());
            return Optional.of(departmentRepository.save(service));
        }
        return Optional.empty();
    }
    public boolean deleteDepartments(Long id) {
        Optional<Department> existingService = departmentRepository.findById(id);
        if (existingService.isPresent()) {
            departmentRepository.delete(existingService.get());
            return true;
        }
        return false;
    }
}
